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

interface TerminalComponentProps {
  wsUrl: string;
  modalHost: HostModel | null;
  dispatch: Dispatch;
}

const TerminalComponent: React.FC<TerminalComponentProps> = ({
  wsUrl,
  modalHost,
  dispatch,
}) => {
  const terminalRef = useRef<HTMLDivElement>(null);
  const socketRef = useRef<WebSocket | null>(null);
  const [term, setTerm] = useState<Terminal | null>(null);
  const [visibleRightMenu, setVisibleRightMenu] = useState(false);
  //   const [accessToken, setAccessToken] = useState<string | null>(null);
  const [accessConfig, setAccessConfig] = useState<AccessTokenRes | null>(null);
  const [terminalConfig, setTerminalConfig] =
    useState<HostTerminalConfig | null>(null);

  const defaultOptions: ITerminalOptions = {
    cursorStyle: "bar",
    cursorBlink: true,
    fastScrollModifier: "shift",
    fontSize: 14,
    //    rendererType: "canvas",
    fontFamily: "courier-new, courier, monospace",
    theme: {
      foreground: "#FFFFFF",
      background: "#212529",
    },
  };

  useEffect(() => {
    if (modalHost?.hostId) {
      dispatch({
        type: "host/getTerminalConfig",
        payload: {
          hostId: modalHost?.hostId,
        },
        callback: (res: HostTerminalConfig) => {
          setTerminalConfig(res);
        },
      });
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
        console.log(res);
      },
    });
  }, [dispatch, modalHost]);

  useEffect(() => {
    if (!accessToken) {
      // console.error("Access Token is not available.");
      return;
    }

    const term = new Terminal(defaultOptions);
    const fitAddon = new FitAddon();
    const searchAddon = new SearchAddon();
    const webLinksAddon = new WebLinksAddon();

    term.loadAddon(fitAddon);
    term.loadAddon(searchAddon);
    term.loadAddon(webLinksAddon);

    if (terminalRef.current) {
      term.open(terminalRef.current);
      fitAddon.fit();
    }

    setTerm(term);

    const fullUrl = `${wsUrl}${accessConfig?.accessToken}`;
    console.log("fullUrl", fullUrl);
    const socket = new WebSocket(fullUrl);
    socketRef.current = socket;

    socket.onopen = () => {
      const body = `1|${term.cols}|${term.rows}|${accessConfig?.terminalToken}`;
      console.log(body);
      socket.send(body);
    };

    socket.onmessage = (event) => {
      const msg = event.data;
      const code = msg.substring(0, 1);
      const len = msg.length;
      debugger;
      switch (code) {
        case "0":
          term.write(msg.substring(2, len));
          break;
        case "1":
          // handle connection
          break;
        case "2":
          // handle ping
          socket.send("PONG");
          break;
        default:
          break;
      }
    };

    socket.onerror = (event) => {
      console.error("WebSocket error observed:", event); // 打印详细的错误信息到控制台
      term.write("\r\n\x1b[91mfailed to establish connection\x1b[0m");
    };

    socket.onclose = (event) => {
      term.write(`\r\n\x1b[91m${event.reason}\x1b[0m`);
      term.options.cursorBlink = false; // 修改为设置 options 属性
    };

    term.onData((data) => {
      if (socketRef.current) {
        socketRef.current.send(data);
      }
    });

    const resizeHandler = debounce(() => {
      fitAddon.fit();
      if (socketRef.current) {
        const { cols, rows } = term;
        socketRef.current.send(`2|${cols}|${rows}`);
      }
    }, 100);

    window.addEventListener("resize", resizeHandler);

    return () => {
      window.removeEventListener("resize", resizeHandler);
      term.dispose();
      if (socketRef.current) {
        socketRef.current.close();
      }
    };
  }, [accessToken]);

  const handleRightClick = (event: React.MouseEvent) => {
    event.preventDefault();
    console.log("右键点击");
    setVisibleRightMenu(true);
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
        document.execCommand("copy");
        term.focus();
        break;
      case "paste":
        navigator.clipboard.readText().then((clipText) => {
          term.paste(clipText);
          term.focus();
        });
        break;
      case "clear":
        term.clear();
        term.clearSelection();
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
        // Implement search functionality
        break;
      default:
        break;
    }
  };

  //  console.log(`ws://localhost:8300/api/ws/ccc/ccc/ccc`);
  // const socket = new WebSocket(`ws://127.0.0.1:4100/api/ws`);
  // const socket = new WebSocket(`/api/ws`);
  // // const socket = new WebSocket(`ws://${window.location.host}/api/ws`);
  // // 修改为你的 WebSocket 服务器地址

  // socket.onopen = () => {
  //   console.log("WebSocket is open now.");
  //   socket.send("Hello Server!");
  //   // 定期发送ping消息
  //   setInterval(() => {
  //     if (socket.readyState === WebSocket.OPEN) {
  //       socket.send("ping");
  //     }
  //   }, 30000); // 每30秒发送一次ping
  // };

  // socket.onmessage = (event) => {
  //   const content = "Message from server: " + event.data;
  //   console.log(content);
  //   // 处理服务器的pong响应
  //   if (event.data === "pong") {
  //     console.log("Received pong");
  //   }
  // };

  // socket.onerror = (error) => {
  //   const textContent = "WebSocket error: " + JSON.stringify(error);
  //   console.log(textContent);
  // };

  // socket.onclose = (event) => {
  //   const textContent = "WebSocket connection closed: " + event.reason;
  //   console.log(textContent);
  // };

  return (
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
        <div className="right-menu">
          <div
            className="right-menu-item"
            onClick={() => handleMenuClick("selectAll")}
          >
            全选
          </div>
          <div
            className="right-menu-item"
            onClick={() => handleMenuClick("copy")}
          >
            复制
          </div>
          <div
            className="right-menu-item"
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
    // <div id="messages">Waiting for messages...</div>
  );
};

export default connect(({ host }: { host: any }) => ({
  host,
}))(TerminalComponent);
