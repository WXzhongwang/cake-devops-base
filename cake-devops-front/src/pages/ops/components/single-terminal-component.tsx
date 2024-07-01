import React, { useEffect, useRef, useState } from "react";
import { connect, Dispatch } from "umi";
import { ITerminalOptions, Terminal } from "xterm";
import "xterm/css/xterm.css";
import { FitAddon } from "xterm-addon-fit";
import { SearchAddon } from "xterm-addon-search";
import { WebLinksAddon } from "xterm-addon-web-links";
import { debounce } from "lodash";
import { AccessTokenRes, HostModel, HostTerminalConfig } from "@/models/host";
import "./single-terminal-component.less";
import { Button, Form, Input, Modal, Select, Space, Switch, Tag } from "antd";
import Draggable, { DraggableData, DraggableEvent } from "react-draggable";
import { SettingOutlined } from "@ant-design/icons";
import { SketchPicker } from "react-color";

interface TerminalComponentProps {
  wsUrl: string;
  modalHost: HostModel | null;
  dispatch: Dispatch;
  open: boolean;
  closeTerminal: () => void;
}

const TerminalComponent: React.FC<TerminalComponentProps> = ({
  wsUrl,
  modalHost,
  open,
  closeTerminal,
  dispatch,
}) => {
  const [disabled, setDisabled] = useState(true);
  const [bounds, setBounds] = useState({
    left: 0,
    top: 0,
    bottom: 0,
    right: 0,
  });
  const draggleRef = useRef<HTMLDivElement>(null);
  const [connectionStatus, setConnectionStatus] = useState<string>(
    TERMINAL_STATUS.NOT_CONNECT.label
  );
  const terminalRef = useRef<HTMLDivElement>(null);
  const socketRef = useRef<WebSocket | null>(null);
  const [term, setTerm] = useState<Terminal | null>(null);
  const [accessConfig, setAccessConfig] = useState<AccessTokenRes | null>(null);
  const [terminalConfig, setTerminalConfig] = useState<
    HostTerminalConfig | undefined
  >(undefined);
  const [supportPy, setSupportPy] = useState<string[]>([]);

  const fitAddon = useRef<FitAddon>(new FitAddon());
  const searchAddon = useRef<SearchAddon>(new SearchAddon());

  const [currentWebLinksAddon, setCurrentWebLinkAddon] =
    useState<WebLinksAddon | null>(null);
  const [visibleRightMenu, setVisibleRightMenu] = useState(false);
  const [rightMenuPosition, setRightMenuPosition] = useState({ x: 0, y: 0 });
  const [connStatus, setConnStatus] = useState<number>(
    TERMINAL_STATUS.NOT_CONNECT.value
  );
  const [clipboardContent, setClipboardContent] = useState<string>("");
  const [settingsModalVisible, setSettingsModalVisible] = useState(false);
  const [fontColorModalVisible, setFontColorModalVisible] = useState(false);
  const [backgroundColorModalVisible, setBackgroundColorModalVisible] =
    useState(false);
  const [form] = Form.useForm();
  const [fontColor, setFontColor] = useState<string>("#FFFFFF");
  const [backgroundColor, setBackgroundColor] = useState<string>("#212529");

  const onStart = (_event: DraggableEvent, uiData: DraggableData) => {
    const { clientWidth, clientHeight } = window.document.documentElement;
    const targetRect = draggleRef.current?.getBoundingClientRect();
    if (!targetRect) {
      return;
    }
    setBounds({
      left: -targetRect.left + uiData.x,
      right: clientWidth - (targetRect.right - uiData.x),
      top: -targetRect.top + uiData.y,
      bottom: clientHeight - (targetRect.bottom - uiData.y),
    });
  };

  const updateConnStatus = (label: string) => {
    setConnectionStatus(label);
  };

  const openSettingsModal = () => {
    setSettingsModalVisible(true);
  };

  const refreshConfig = () => {
    dispatch({
      type: "host/getTerminalConfig",
      payload: {
        hostId: modalHost?.hostId,
      },
      callback: (res: HostTerminalConfig) => {
        setTerminalConfig(res);
        form.setFieldsValue(res);
        setFontColor(res.fontColor || "#FFFFFF");
        setBackgroundColor(res.backgroundColor || "#212529");
      },
    });
  };

  const handleSettingsSubmit = (values: any) => {
    dispatch({
      type: "host/updateTerminalConfig",
      payload: {
        hostId: modalHost?.hostId,
        terminalType: values.terminalType,
        backgroundColor: values.backgroundColor,
        fontColor: values.fontColor,
        fontSize: values.fontSize,
        fontFamily: values.fontFamily,
        enableWebLink: values.enableWebLink,
      },
      callback: () => {
        refreshConfig();
        setSettingsModalVisible(false);
      },
    });
  };

  useEffect(() => {
    if (modalHost?.hostId) {
      refreshConfig();
      dispatch({
        type: "host/getTerminalAccessToken",
        payload: {
          hostId: modalHost?.hostId,
        },
        callback: (res: AccessTokenRes) => {
          setAccessConfig(res);
        },
      });
    }
    dispatch({
      type: "host/getSupportedPty",
      payload: {},
      callback: (res: string[]) => {
        setSupportPy(res);
      },
    });
  }, [dispatch, modalHost]);

  useEffect(() => {
    if (!accessConfig?.accessToken) {
      return;
    }

    const term = new Terminal({
      // ...defaultOptions,
      theme: {
        foreground: terminalConfig?.fontColor,
        background: terminalConfig?.backgroundColor,
      },
      fontFamily: terminalConfig?.fontFamily,
      fontSize: terminalConfig?.fontSize,
    });

    if (terminalConfig?.enableWebLink) {
      const webLinksAddon = new WebLinksAddon();
      term.loadAddon(webLinksAddon);
      setCurrentWebLinkAddon(webLinksAddon);
    }

    term.loadAddon(fitAddon.current);
    term.loadAddon(searchAddon.current);

    if (terminalRef.current) {
      term.open(terminalRef.current);
      fitAddon.current.fit();
    }

    setTerm(term);

    const fullUrl = `${wsUrl}${accessConfig?.accessToken}`;
    console.log("fullUrl", fullUrl);
    const socket = new WebSocket(fullUrl);
    socketRef.current = socket;

    socket.onopen = () => {
      const body = `${TERMINAL_CLIENT_OPERATOR.CONNECT.value}|${term.cols}|${term.rows}|${accessConfig?.terminalToken}`;
      console.log(body);
      socket.send(body);
    };

    socket.onmessage = (event) => {
      const msg = event.data;
      const code = msg.substring(0, 1);
      const len = msg.length;
      console.log(msg);
      switch (code) {
        case "0":
          term.write(msg.substring(2, len));
          break;
        case "1":
          setConnStatus(TERMINAL_STATUS.CONNECTED.value);
          updateConnStatus(TERMINAL_STATUS.CONNECTED.label);
          setInterval(() => {
            socket.send(TERMINAL_CLIENT_OPERATOR.PING.value); // send ping
          }, 15000);
          break;
        case "2":
          socket.send(TERMINAL_CLIENT_OPERATOR.PONG.value);
          break;
        default:
          break;
      }
    };

    socket.onerror = (event) => {
      console.error("WebSocket error observed:", event);
      term.write("\r\n\x1b[91mfailed to establish connection\x1b[0m");
      setConnStatus(TERMINAL_STATUS.ERROR.value);
      updateConnStatus(TERMINAL_STATUS.ERROR.label);
    };

    socket.onclose = (event) => {
      term.write(`\r\n\x1b[91m${event.reason}\x1b[0m`);
      term.options.cursorBlink = false;
      setConnStatus(TERMINAL_STATUS.DISCONNECTED.value);
      updateConnStatus(TERMINAL_STATUS.DISCONNECTED.label);
    };

    term.onResize((cols, rows) => {
      if (connStatus !== TERMINAL_STATUS.CONNECTED.value) {
        return;
      }
      const body = `${TERMINAL_CLIENT_OPERATOR.RESIZE.value}|${cols}|${rows}`;
      socket.send(body);
    });

    term.onData((data) => {
      if (socketRef.current) {
        const body = `${TERMINAL_CLIENT_OPERATOR.KEY.value}|${data}`;
        console.log("enter data", body);
        socketRef.current.send(body);
      }
    });

    const resizeHandler = debounce(() => {
      if (fitAddon) {
        fitAddon.current.fit();
        if (socketRef.current) {
          const { cols, rows } = term;
          socketRef.current.send(
            `${TERMINAL_CLIENT_OPERATOR.RESIZE.value}|${cols}|${rows}`
          );
        }
      }
    }, 100);

    window.addEventListener("resize", resizeHandler);

    // 注册自定义快捷键
    term.attachCustomKeyEventHandler((event) => {
      if (
        event.key === "c" &&
        event.ctrlKey &&
        event.shiftKey &&
        event.type === "keydown"
      ) {
        if (term.hasSelection()) {
          const selectedText = term.getSelection();
          setClipboardContent(selectedText);
          console.log("Selected text copied to clipboard:", selectedText);
        }
        return false; // 阻止默认行为
      }

      if (
        event.key === "v" &&
        event.ctrlKey &&
        event.shiftKey &&
        event.type === "keydown"
      ) {
        navigator.clipboard.readText().then((clipboardText) => {
          term.write(clipboardText);
        });
        return false; // 阻止默认行为
      }

      // 捕获 Ctrl+Shift+F 快捷键事件
      if (
        event.key === "f" &&
        event.ctrlKey &&
        event.shiftKey &&
        event.type === "keydown"
      ) {
        const searchTerm = prompt("Enter the text to search:"); // 或者使用自定义搜索框
        if (searchTerm) {
          searchAddon.current.findNext(searchTerm);
        }
        return false; // 阻止默认行为
      }

      return true; // 允许默认行为
    });

    return () => {
      window.removeEventListener("resize", resizeHandler);
      socketRef.current?.close();
      term.dispose();
    };
  }, [accessConfig]);

  const reconnect = () => {
    socketRef.current?.close();
    setConnStatus(TERMINAL_STATUS.NOT_CONNECT.value);
    updateConnStatus(TERMINAL_STATUS.NOT_CONNECT.label);
  };

  useEffect(() => {
    const handleGlobalClick = () => {
      setVisibleRightMenu(false);
    };

    document.addEventListener("click", handleGlobalClick);

    return () => {
      document.removeEventListener("click", handleGlobalClick);
    };
  }, []);

  const handleRightClick = (event: React.MouseEvent) => {
    event.preventDefault();
    setRightMenuPosition({ x: event.clientX, y: event.clientY });
    setVisibleRightMenu(true);

    navigator.clipboard.readText().then((clipText) => {
      setClipboardContent(clipText);
    });
  };

  const handleMenuClick = (key: string) => {
    setVisibleRightMenu(false);
    if (!term) return;
    switch (key) {
      case "selectAll":
        term.selectAll();
        term.focus();
        break;
      case "copy":
        if (clipboardContent) {
          navigator.clipboard.writeText(term.getSelection());
        }
        term.focus();
        break;
      case "paste":
        if (clipboardContent) {
          term.paste(clipboardContent);
        }
        term.focus();
        break;
      case "clear":
        term.clear();
        term.focus();
        break;
      case "toTop":
        term.scrollToTop();
        term.focus();
        break;
      case "toBottom":
        term.scrollToBottom();
        term.focus();
        break;
      case "openSearch":
        searchAddon.current.findNext("");
        term.focus();
        break;
      default:
        break;
    }
  };

  return (
    <Modal
      title={
        <Space
          style={{
            width: "100%",
            cursor: "move",
          }}
          onMouseOver={() => {
            if (disabled) {
              setDisabled(false);
            }
          }}
          onMouseOut={() => {
            setDisabled(true);
          }}
        >
          <span>主机终端</span>
          <span style={{ fontSize: 12 }}>
            {modalHost?.username + "@" + modalHost?.serverAddr}
          </span>
          {connectionStatus === TERMINAL_STATUS.DISCONNECTED.label && (
            <>
              <Tag color="gold">已断开</Tag>
            </>
          )}
          {connectionStatus === TERMINAL_STATUS.CONNECTED.label && (
            <>
              <Tag color="#87d068">#已连接</Tag>
            </>
          )}
          {connectionStatus === TERMINAL_STATUS.ERROR.label && (
            <>
              <Tag color="#f50">连接错误</Tag>
            </>
          )}
          <Button onClick={reconnect}>重连</Button>
          <Button
            style={{ marginLeft: 10 }}
            onClick={() => openSettingsModal()}
            icon={<SettingOutlined />}
          >
            设置
          </Button>
        </Space>
      }
      open={open}
      onCancel={closeTerminal}
      width="90vw"
      style={{ top: 24 }}
      modalRender={(modal) => (
        <Draggable
          disabled={disabled}
          bounds={bounds}
          nodeRef={draggleRef}
          onStart={(event, uiData) => onStart(event, uiData)}
        >
          <div ref={draggleRef}>{modal}</div>
        </Draggable>
      )}
      footer={null}
      bodyStyle={{ height: "70vh", overflowY: "auto" }}
    >
      <div
        className="terminal-body"
        style={{
          height: "100%",
          background: defaultOptions.theme?.background,
        }}
        onContextMenu={handleRightClick}
      >
        <div ref={terminalRef} className="terminal" />
        {visibleRightMenu && (
          <div
            className="right-menu"
            style={{ top: rightMenuPosition.y, left: rightMenuPosition.x }}
            onClick={(e) => e.stopPropagation()}
          >
            <div
              className="right-menu-item"
              onClick={() => handleMenuClick("selectAll")}
            >
              全选
            </div>
            <div
              className={`right-menu-item ${
                clipboardContent ? "" : "disabled"
              }`}
              onClick={() => handleMenuClick("copy")}
            >
              复制
            </div>
            <div
              className={`right-menu-item ${
                clipboardContent ? "" : "disabled"
              }`}
              onClick={() => handleMenuClick("paste")}
            >
              粘贴
            </div>
            <div
              className="right-menu-item"
              onClick={() => handleMenuClick("clear")}
            >
              清除
            </div>
            <div
              className="right-menu-item"
              onClick={() => handleMenuClick("toTop")}
            >
              到顶部
            </div>
            <div
              className="right-menu-item"
              onClick={() => handleMenuClick("toBottom")}
            >
              到底部
            </div>
            <div
              className="right-menu-item"
              onClick={() => handleMenuClick("openSearch")}
            >
              搜索
            </div>
          </div>
        )}
      </div>

      <Modal
        title="终端设置"
        open={settingsModalVisible}
        onCancel={() => setSettingsModalVisible(false)}
        footer={null}
      >
        <Form
          form={form}
          layout="vertical"
          initialValues={terminalConfig}
          onFinish={handleSettingsSubmit}
        >
          <Form.Item name="terminalType" label="终端类型">
            <Select>
              {supportPy.map((item) => (
                <Select.Option key={item} value={item}>
                  {item}
                </Select.Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item
            name="fontSize"
            label="字体大小"
            rules={[{ required: true, message: "请输入字体大小" }]}
          >
            <Input type="number" min={10} max={50} />
          </Form.Item>
          <Form.Item
            name="fontFamily"
            label="字体"
            rules={[{ required: true, message: "请输入字体" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="fontColor"
            label="字体颜色"
            rules={[{ required: true, message: "请选择字体颜色" }]}
          >
            <div>
              <div
                className="color-preview"
                style={{ backgroundColor: fontColor }}
                onClick={() => setFontColorModalVisible(true)}
              />
              <Modal
                title="选择字体颜色"
                open={fontColorModalVisible}
                onCancel={() => setFontColorModalVisible(false)}
                footer={null}
              >
                <SketchPicker
                  color={fontColor}
                  onChangeComplete={(color) => setFontColor(color.hex)}
                />
              </Modal>
            </div>
          </Form.Item>
          <Form.Item
            name="backgroundColor"
            label="背景颜色"
            rules={[{ required: true, message: "请选择背景颜色" }]}
          >
            <div>
              <div
                className="color-preview"
                style={{ backgroundColor: backgroundColor }}
                onClick={() => setBackgroundColorModalVisible(true)}
              />
              <Modal
                title="选择背景颜色"
                open={backgroundColorModalVisible}
                onCancel={() => setBackgroundColorModalVisible(false)}
                footer={null}
              >
                <SketchPicker
                  color={backgroundColor}
                  onChangeComplete={(color) => setBackgroundColor(color.hex)}
                />
              </Modal>
            </div>
          </Form.Item>
          <Form.Item
            name="enableWebLink"
            valuePropName="checked"
            label="启用网页链接"
          >
            <Switch />
          </Form.Item>
          <Form.Item>
            <Space>
              <Button type="primary" htmlType="submit">
                保存
              </Button>
              <Button onClick={() => setSettingsModalVisible(false)}>
                取消
              </Button>
            </Space>
          </Form.Item>
        </Form>
      </Modal>
    </Modal>
  );
};

export default connect()(TerminalComponent);

export const TERMINAL_STATUS = {
  NOT_CONNECT: {
    value: 0,
    label: "未连接",
    color: "#FFD43B",
  },
  CONNECTED: {
    value: 20,
    label: "已连接",
    color: "#4DABF7",
  },
  DISCONNECTED: {
    value: 30,
    label: "已断开",
    color: "#ADB5BD",
  },
  ERROR: {
    value: 40,
    label: "错误",
    color: "#E03131",
  },
};

export const TERMINAL_CLIENT_OPERATOR = {
  KEY: {
    value: "0",
  },
  CONNECT: {
    value: "1",
  },
  PING: {
    value: "2",
  },
  PONG: {
    value: "3",
  },
  RESIZE: {
    value: "4",
  },
  COMMAND: {
    value: "5",
  },
  CLEAR: {
    value: "6",
  },
};

export const defaultOptions: ITerminalOptions = {
  cursorStyle: "bar",
  cursorBlink: true,
  fastScrollModifier: "shift",
  fontSize: 14,
  fontFamily: "courier-new, courier, monospace",
  theme: {
    foreground: "#FFFFFF",
    background: "#212529",
  },
};
