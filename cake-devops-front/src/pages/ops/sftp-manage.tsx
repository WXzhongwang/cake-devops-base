import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Card,
  Row,
  Col,
  Tree,
  Table,
  Button,
  Space,
  Switch,
  Breadcrumb,
  Tooltip,
  Input,
  message,
  Typography,
  Popover,
  Upload,
  Progress,
  Tag,
  Modal,
  Form,
  Select,
  InputNumber,
} from "antd";
import dayjs from "dayjs";
import {
  FileDetailDTO,
  FileTransferLogDTO,
  ListDirDTO,
  OpenSessionDTO,
} from "@/models/sftp";

import {
  UploadOutlined,
  DownloadOutlined,
  CopyOutlined,
  SwapOutlined,
  PlusOutlined,
  SyncOutlined,
  InboxOutlined,
  ScissorOutlined,
  FolderOutlined,
  FileOutlined,
  EditOutlined,
  ReloadOutlined,
  CaretRightOutlined,
  PauseOutlined,
  IssuesCloseOutlined,
  FileZipOutlined,
  DeleteOutlined,
  BackwardOutlined,
  PlayCircleOutlined,
  RedoOutlined,
} from "@ant-design/icons";
import { connect, Dispatch, useParams } from "umi";

const { Option } = Select;

interface SftpManageProps {
  dispatch: Dispatch;
  open: OpenSessionDTO;
  files: FileDetailDTO[];
  dirs: ListDirDTO;
  transferList: FileTransferLogDTO[];
}

const SftpManagementPage: React.FC<SftpManageProps> = ({
  dispatch,
  open,
  files,
  transferList,
}) => {
  const { id } = useParams();
  const [directoryTreeData, setDirectoryTreeData] = useState<any[]>([]);
  const [selectedDirectoryKey, setSelectedDirectoryKey] = useState<
    string | null
  >(null);
  const [showHiddenFiles, setShowHiddenFiles] = useState<boolean>(false);
  const [mkdirModalVisible, setMkdirModalVisible] = useState<boolean>(false);
  const [moveModalVisible, setMoveModalVisible] = useState<boolean>(false);
  const [selectedRows, setSelectedRows] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<string>(open?.home || "/");
  const [homePath, setHomePath] = useState<string>(open?.path || "/");
  const [pathStack, setPathStack] = useState<string[]>(["/"]);
  const [inputPath, setInputPath] = useState<string>(open?.home || "/");
  const [mkdirTouchForm] = Form.useForm();
  const [basicPath, setBasicPath] = useState(currentPath);
  const [modalVisible, setModalVisible] = useState(false);
  const [currentFile, setCurrentFile] = useState<FileDetailDTO | null>(null);
  const [displayRwx, setDisplayRwx] = useState<string | null>(null);

  const [modalForm] = Form.useForm();
  const [moveModalForm] = Form.useForm();

  useEffect(() => {
    if (id) {
      openSession(id);
    }
  }, [id]);

  useEffect(() => {
    if (open?.sessionToken) {
      getDirTrees("/");
      getTransferList();
    }
    if (open?.home) {
      console.log("current stack", pathStack);
      setCurrentPath(open.home);
      console.log("init home", open.home);
      pathStack.push(open.home);
      console.log("current stack", pathStack);
      setBasicPath(open.home);
      setSelectedDirectoryKey(open.home);
      getFileList(open.home, showHiddenFiles);
    }
  }, [open]);

  const openSession = (id: string | undefined) => {
    if (id) {
      dispatch({
        type: "sftp/open",
        payload: { hostId: id },
      });
    }
  };

  const getDirTrees = (dirPath: string) => {
    dispatch({
      type: "sftp/fetchDirs",
      payload: { path: dirPath, sessionToken: open.sessionToken },
      callback: (_dir: { files: any[] }) => {
        const treeData = _dir?.files?.map((file) => ({
          title: <Typography.Text ellipsis={true}>{file.name}</Typography.Text>,
          key: file.path,
          isLeaf: !file.isDir,
          children: undefined,
        }));
        setDirectoryTreeData(treeData);
      },
    });
  };

  const retryFailedTransfer = () => {
    dispatch({
      type: "sftp/retryAllFailedTransfers",
      payload: { sessionToken: open.sessionToken },
    });
  };

  const clearAllTransfers = () => {
    dispatch({
      type: "sftp/clearAllTransfers",
      payload: { sessionToken: open.sessionToken },
    });
  };

  const pauseAllTransfers = () => {
    dispatch({
      type: "sftp/pauseAllTransfers",
      payload: { sessionToken: open.sessionToken },
    });
  };

  const resumeAllTransfers = () => {
    dispatch({
      type: "sftp/resumeAllTransfers",
      payload: { sessionToken: open.sessionToken },
    });
  };

  const getTransferList = () => {
    // setInterval(() => {
    dispatch({
      type: "sftp/getTransferList",
      payload: { sessionToken: open.sessionToken },
    });
    // }, 5000);
  };

  const handleAction = (item: FileTransferLogDTO, action: string) => {
    switch (action) {
      case "pause":
        // 暂停逻辑
        dispatch({
          type: "sftp/pauseFileTransfer",
          payload: {
            sessionToken: open.sessionToken,
            fileToken: item.fileToken,
          },
        });
        break;
      case "resume":
        dispatch({
          type: "sftp/resumeFileTransfer",
          payload: {
            sessionToken: open.sessionToken,
            fileToken: item.fileToken,
          },
        });
        break;
      case "download":
        // 下载逻辑
        // downloadSelectedFiles([item?.remoteFile]);
        break;
      case "retry":
        // 重新传输逻辑
        dispatch({
          type: "sftp/retryFailedTransfer",
          payload: {
            sessionToken: open.sessionToken,
            fileToken: item.fileToken,
          },
        });
        break;
      case "delete":
        // 删除逻辑
        dispatch({
          type: "sftp/removeSingleTransfer",
          payload: {
            sessionToken: open.sessionToken,
            fileToken: item.fileToken,
          },
        });
        break;
      default:
        break;
    }
  };

  const showModal = () => {
    setMkdirModalVisible(!mkdirModalVisible);
  };

  const getFileList = (dirPath: string, showHidden: boolean) => {
    dispatch({
      type: "sftp/fetchFiles",
      payload: {
        path: dirPath,
        sessionToken: open.sessionToken,
        all: showHidden,
      },
      callback: () => {
        setCurrentPath(dirPath);
      },
    });
  };

  // 加载文件列表数据
  const loadFileListData = async (
    directoryKey: string,
    showHidden: boolean
  ) => {
    getFileList(directoryKey, showHidden);
  };

  // 目录树节点选中事件
  const handleDirectoryNodeSelect = (selectedKeys: any, info: any) => {
    if (selectedKeys.length > 0) {
      const key = selectedKeys[0];
      setSelectedDirectoryKey(key);
      loadFileListData(key, showHiddenFiles);
      setCurrentPath(key);
      setInputPath(info.node.key);
      setPathStack((prev) => [...prev, info.node.key]);
    }
  };

  // 切换是否显示隐藏文件
  const toggleShowHiddenFiles = () => {
    setShowHiddenFiles(!showHiddenFiles);
    loadFileListData(selectedDirectoryKey as string, !showHiddenFiles);
  };

  // 返回上一级目录
  const goBack = () => {
    if (pathStack.length >= 1) {
      pathStack.pop();
      const previousPath = pathStack[pathStack.length - 1];
      console.log("pop stack", previousPath);
      setCurrentPath(previousPath);
      loadFileListData(previousPath, showHiddenFiles);
    } else {
      message.warning("已经是根目录");
    }
  };

  // 删除选中文件
  const deleteSelectedFiles = (files: string[]) => {
    dispatch({
      type: "sftp/removeFile",
      payload: {
        paths: files,
        sessionToken: open?.sessionToken,
      },
      callback: () => {
        message.success("删除成功");
        loadFileListData(currentPath, showHiddenFiles);
      },
    });
  };

  // 下载选中文件
  const downloadSelectedFiles = (path: string[]) => {
    dispatch({
      type: "sftp/downloadFile",
      payload: { paths: path, sessionToken: open.sessionToken },
      callback: (res: { files: any[] }) => {
        message.success("下载成功");
      },
    });
  };

  // 移动文件
  const moveFile = (source: string | undefined, target: string) => {
    if (source === null) {
      console.log("no chosen file", source);
      return;
    }
    dispatch({
      type: "sftp/moveFile",
      payload: {
        source: source,
        target: target,
        sessionToken: open.sessionToken,
      },
      callback: () => {
        message.success("移动成功");
        loadFileListData(currentPath, showHiddenFiles);
      },
    });
    setMoveModalVisible(false);
    moveModalForm.resetFields();
  };

  // 更新文件权限
  const updatePermission = (auth: number) => {
    dispatch({
      type: "sftp/changeFilePermissions",
      payload: {
        permission: auth,
        path: currentFile?.path,
        sessionToken: open.sessionToken,
      },
      callback: () => {
        message.success("修改文件权限成功");
        loadFileListData(currentPath, showHiddenFiles);
      },
    });
  };

  // 复制选中文件路径
  const copySelectedFilesPath = (path: string) => {
    // TODO: 复制选中文件路径的逻辑
    navigator.clipboard.writeText(path).then(
      () => {
        message.success("路径复制成功");
      },
      (err) => {
        message.error("复制失败");
      }
    );
  };

  // 创建文件或文件夹
  const createFileOrFolder = (values: any) => {
    mkdirTouchForm.validateFields().then((values) => {
      if (values?.type === "file") {
        dispatch({
          type: "sftp/createFile",
          payload: {
            sessionToken: open.sessionToken,
            path: `${basicPath}/${values.path}`,
          },
          callback: () => {
            message.success("文件创建成功");
          },
        });
      } else {
        dispatch({
          type: "sftp/createFolder",
          payload: {
            sessionToken: open.sessionToken,
            path: `${basicPath}/${values.path}`,
          },
          callback: () => {
            message.success("文件夹创建成功");
          },
        });
      }

      loadFileListData(currentPath, showHiddenFiles);
      setMkdirModalVisible(false);
      mkdirTouchForm.resetFields();
    });
  };

  // 上传文件
  const uploadFile = () => {
    // TODO: 上传文件的逻辑
  };

  // 刷新文件列表
  const refreshFileList = () => {
    if (selectedDirectoryKey) {
      loadFileListData(selectedDirectoryKey, showHiddenFiles);
    }
  };

  // 文件列表列配置
  const columns = [
    {
      title: "名称",
      dataIndex: "name",
      key: "name",
      render: (text: string, record: FileDetailDTO) => {
        return (
          <Space
            onClick={() => {
              if (record.isDir) {
                setCurrentPath(record.path);
                pathStack.push(record.path);
                console.log("push stack", pathStack);
                loadFileListData(record.path, showHiddenFiles);
              }
            }}
          >
            {record.isDir ? <FolderOutlined /> : <FileOutlined />}
            <Typography.Link>{record.name}</Typography.Link>
          </Space>
        );
      },
    },
    { title: "大小", dataIndex: "size", key: "size" },
    {
      title: "文件属性",
      dataIndex: "attr",
      key: "attr",
    },
    {
      title: "修改时间",
      dataIndex: "modifyTime",
      key: "modifyTime",
      render: (text: string, record: FileDetailDTO) => (
        <>{dayjs(record?.modifyTime).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (text: string, record: FileDetailDTO) => (
        <Space.Compact>
          <Button
            icon={<CopyOutlined />}
            onClick={() => copySelectedFilesPath(record.path)}
            title="复制路径"
            size="small"
          />
          <Button
            icon={<DownloadOutlined />}
            onClick={() => downloadSelectedFiles([record.path])}
            title="下载"
            size="small"
          />
          <Button
            icon={<DeleteOutlined />}
            onClick={() => deleteSelectedFiles([record.path])}
            title="删除"
            size="small"
          />
          <Button
            icon={<ScissorOutlined />}
            onClick={() => {
              setCurrentFile(record);
              setMoveModalVisible(true);
            }}
            title="移动"
            size="small"
          />
          <Button
            icon={<EditOutlined />}
            onClick={() => {
              setCurrentFile(record);
              setDisplayRwx(modeToRwx(record?.isDir, record?.permission));
              setModalVisible(true);
            }}
            title="修改权限"
            size="small"
          />
        </Space.Compact>
      ),
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys: any, selectedRows: any) => {
      setSelectedRows(selectedRows);
    },
    selectedRowKeys: selectedRows.map((row) => row.path),
  };

  const selectBefore = (
    <Select
      // defaultValue={currentPath}
      value={basicPath}
      onChange={(value) => {
        console.log(value);
        setBasicPath(value);
      }}
    >
      <Option value={currentPath}>当前路径</Option>
      <Option value={homePath}>主目录</Option>
      <Option value={""}>根目录</Option>
    </Select>
  );

  // Handle input path change
  const handleInputPathChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputPath(e.target.value);
  };

  // Handle input path submit
  const handleInputPathSubmit = () => {
    setCurrentPath(inputPath);
    setSelectedDirectoryKey(inputPath);
    loadFileListData(inputPath, showHiddenFiles);
    setPathStack((prev) => [...prev, inputPath]);
  };

  const updateTreeData = (list: any, key: any, children: any) =>
    list.map((node) => {
      if (node.key === key) {
        return {
          ...node,
          children,
        };
      }
      if (node.children) {
        return {
          ...node,
          children: updateTreeData(node.children, key, children),
        };
      }
      return node;
    });

  const onLoadData = ({ key, children }) =>
    new Promise<void>((resolve) => {
      if (children) {
        resolve();
        return;
      }
      dispatch({
        type: "sftp/fetchDirs",
        payload: { path: key, sessionToken: open.sessionToken },
        callback: (res: { files: any[] }) => {
          setDirectoryTreeData((origin) =>
            updateTreeData(
              origin,
              key,
              res.files.map((file) => ({
                title: (
                  <Typography.Text style={{ maxWidth: 300 }} ellipsis={true}>
                    {file.name}
                  </Typography.Text>
                ),
                key: file.path,
                isLeaf: !file.isDir,
                children: undefined,
              }))
            )
          );
          resolve();
        },
      });
    });
  return (
    <PageContainer title="SFTP">
      <Card>
        <Row gutter={24}>
          <Col span={8}>
            <Card title="目录树">
              <Space direction="vertical">
                <Input
                  value={inputPath}
                  onChange={handleInputPathChange}
                  onPressEnter={handleInputPathSubmit}
                />
                <Tree
                  onSelect={handleDirectoryNodeSelect}
                  treeData={directoryTreeData}
                  loadData={onLoadData}
                />
              </Space>
            </Card>
          </Col>
          <Col span={16}>
            <Card>
              <Space style={{ width: "100%", justifyContent: "space-between" }}>
                {pathToBreadcrumb(currentPath)}
                {/* 顶部操作按钮工具栏 */}
                <Space.Compact style={{ marginBottom: 16 }}>
                  <Space style={{ marginRight: 5 }}>
                    显示隐藏文件
                    <Switch
                      checked={showHiddenFiles}
                      onChange={toggleShowHiddenFiles}
                    />
                  </Space>
                  <Button
                    icon={<BackwardOutlined />}
                    onClick={() => goBack()}
                    disabled={currentPath === "/"}
                    title="删除"
                  ></Button>
                  <Button
                    icon={<DeleteOutlined />}
                    onClick={() =>
                      deleteSelectedFiles(selectedRows.map((row) => row.path))
                    }
                    disabled={selectedRows.length === 0}
                    title="删除"
                  ></Button>
                  <Button
                    icon={<DownloadOutlined />}
                    onClick={() =>
                      downloadSelectedFiles(selectedRows.map((row) => row.path))
                    }
                    disabled={selectedRows.length === 0}
                    title="下载"
                  ></Button>
                  <Button
                    icon={<CopyOutlined />}
                    onClick={() => copySelectedFilesPath(currentPath)}
                    title="复制路径"
                  ></Button>
                  <Popover
                    placement="bottomRight"
                    // open={true}
                    content={
                      <Space
                        style={{
                          width: "100%",
                        }}
                        direction="vertical"
                      >
                        <Space.Compact block>
                          <Tooltip title="刷新">
                            <Button
                              size="small"
                              icon={<ReloadOutlined />}
                              onClick={() => {
                                getTransferList();
                              }}
                            />
                          </Tooltip>
                          <Tooltip title="暂停所有">
                            <Button
                              size="small"
                              icon={<PauseOutlined />}
                              onClick={() => {
                                pauseAllTransfers();
                              }}
                            />
                          </Tooltip>
                          <Tooltip title="恢复所有">
                            <Button
                              size="small"
                              icon={<RedoOutlined />}
                              onClick={() => {
                                resumeAllTransfers();
                              }}
                            />
                          </Tooltip>
                          <Tooltip title="重试所有">
                            <Button
                              size="small"
                              onClick={() => {
                                retryFailedTransfer();
                              }}
                              icon={<IssuesCloseOutlined />}
                            />
                          </Tooltip>
                          <Tooltip title="打包传输">
                            <Button size="small" icon={<FileZipOutlined />} />
                          </Tooltip>
                          <Tooltip title="清空">
                            <Button
                              size="small"
                              icon={<DeleteOutlined />}
                              onClick={() => {
                                clearAllTransfers();
                              }}
                            />
                          </Tooltip>
                        </Space.Compact>
                        <Space
                          style={{
                            width: "100%",
                            maxHeight: 300,
                            overflowY: "auto",
                            padding: "0 20px 0 0",
                          }}
                          direction="vertical"
                        >
                          {transferList?.map((item) => (
                            <Space
                              style={{ width: "100%" }}
                              key={item.fileToken}
                              direction="vertical"
                              size="small"
                            >
                              <Tooltip title={item.remoteFile}>
                                <Typography.Text
                                  style={{ maxWidth: 360 }}
                                  ellipsis
                                >
                                  {item.remoteFile}
                                </Typography.Text>
                              </Tooltip>
                              <Space style={{ width: "100%" }} size={4}>
                                <Tag color={StatusColorMapper[item.status]}>
                                  {StatusMapper[item.status]}
                                </Tag>
                                <Progress
                                  style={{ width: 300 }}
                                  percent={item.progress}
                                  showInfo={false}
                                />
                                {item.status === 20 && (
                                  <Button
                                    onClick={() => handleAction(item, "pause")}
                                  >
                                    <Tooltip title="暂停">
                                      <PauseOutlined />
                                    </Tooltip>
                                  </Button>
                                )}
                                {item.status === 30 && (
                                  <Button
                                    onClick={() => handleAction(item, "resume")}
                                  >
                                    <Tooltip title="恢复">
                                      <PlayCircleOutlined />
                                    </Tooltip>
                                  </Button>
                                )}
                                {item.status === 40 && (
                                  <Button
                                    onClick={() =>
                                      handleAction(item, "download")
                                    }
                                  >
                                    <Tooltip title="下载">
                                      <DownloadOutlined />
                                    </Tooltip>
                                  </Button>
                                )}
                                {(item.status === 50 || item.status === 60) && (
                                  <Button
                                    onClick={() => handleAction(item, "retry")}
                                  >
                                    <Tooltip title="重新传输">
                                      <RedoOutlined />
                                    </Tooltip>
                                  </Button>
                                )}
                                <Button
                                  onClick={() => handleAction(item, "delete")}
                                >
                                  <Tooltip title="删除">
                                    <DeleteOutlined />
                                  </Tooltip>
                                </Button>
                              </Space>
                            </Space>
                          ))}
                        </Space>
                      </Space>
                    }
                  >
                    <Button
                      icon={<SwapOutlined />}
                      onClick={() => {
                        getTransferList();
                      }}
                      title="传输列表"
                    ></Button>
                  </Popover>
                  <Button
                    icon={<PlusOutlined />}
                    onClick={() => {
                      setMkdirModalVisible(!mkdirModalVisible);
                    }}
                    title="创建"
                  ></Button>
                  <Popover
                    placement="bottomRight"
                    content={
                      <Space style={{ width: "100%" }} direction="vertical">
                        <Space align="center">
                          文件夹： <Input style={{ width: 310 }} />
                        </Space>
                        <Space
                          style={{
                            justifyContent: "space-between",
                            width: "100%",
                          }}
                          align="center"
                        >
                          <Upload.Dragger
                            multiple
                            name="file"
                            action={"/upload/api"}
                          >
                            <p className="ant-upload-drag-icon">
                              <InboxOutlined />
                            </p>
                            <p
                              style={{ padding: "0 10px" }}
                              className="ant-upload-text"
                            >
                              单击或拖动文件到此区域进行上传
                            </p>
                          </Upload.Dragger>
                          <Space direction="vertical">
                            <Button type="primary">上传</Button>
                            <Button>清空</Button>
                          </Space>
                        </Space>
                      </Space>
                    }
                  >
                    <Button
                      icon={<UploadOutlined />}
                      onClick={uploadFile}
                      title="上传"
                    ></Button>
                  </Popover>
                  <Button
                    icon={<SyncOutlined />}
                    onClick={refreshFileList}
                    title="刷新"
                  ></Button>
                </Space.Compact>
              </Space>
              {/* 文件列表 */}
              <Table
                dataSource={files}
                columns={columns}
                rowKey="path"
                rowSelection={{ ...rowSelection }}
              />

              <Modal
                title="新建文件(夹)"
                open={mkdirModalVisible}
                onCancel={() => showModal()}
                onOk={mkdirTouchForm.submit}
              >
                <Form
                  form={mkdirTouchForm}
                  initialValues={{ type: "file" }}
                  layout="vertical"
                  onFinish={createFileOrFolder}
                >
                  <Form.Item
                    label="创建类型"
                    name="type"
                    rules={[
                      {
                        required: true,
                        message: "请输入具体创建类型",
                      },
                    ]}
                  >
                    <Select defaultValue={"file"}>
                      <Option value={"file"}>文件</Option>
                      <Option value={"dir"}>文件夹</Option>
                    </Select>
                  </Form.Item>
                  <Form.Item
                    label="文件"
                    name="path"
                    rules={[
                      {
                        required: true,
                        message: "请输入具体文件或文件夹名称",
                      },
                    ]}
                  >
                    <Input addonBefore={selectBefore} />
                  </Form.Item>
                </Form>
              </Modal>

              <Modal
                open={modalVisible}
                title="文件提权"
                width={300}
                onCancel={() => setModalVisible(false)}
                onOk={() => {
                  modalForm.validateFields().then((values) => {
                    updatePermission(values.auth);
                  });
                }}
              >
                <Form form={modalForm}>
                  <Form.Item label="文件路径">
                    <Typography.Text copyable>
                      {currentFile?.path}
                    </Typography.Text>
                  </Form.Item>

                  <Form.Item label="权限" name="auth">
                    <InputNumber
                      width={100}
                      max={777}
                      onChange={(value) => {
                        {
                          if (currentFile !== null) {
                            setDisplayRwx(modeToRwx(currentFile?.isDir, value));
                          }
                        }
                      }}
                    />
                  </Form.Item>
                  <Form.Item label="当前文件权限">{displayRwx}</Form.Item>
                </Form>
              </Modal>

              <Modal
                open={moveModalVisible}
                title="文件移动"
                width={300}
                onCancel={() => setMoveModalVisible(false)}
                onOk={() => {
                  moveModalForm.validateFields().then((values) => {
                    moveFile(currentFile?.path, values.target);
                  });
                }}
              >
                <Form form={moveModalForm}>
                  <Form.Item label="文件路径" name="source">
                    <Typography.Text copyable>
                      {currentFile?.path}
                    </Typography.Text>
                  </Form.Item>
                  <Form.Item
                    label="目标路径"
                    name="target"
                    rules={[
                      {
                        required: true,
                        message: "请输入移动后目标路径",
                      },
                    ]}
                  >
                    <Input type="text" />
                  </Form.Item>
                </Form>
              </Modal>
            </Card>
          </Col>
        </Row>
      </Card>
    </PageContainer>
  );
};

export default connect(
  ({
    sftp,
  }: {
    sftp: {
      open: OpenSessionDTO;
      files: FileDetailDTO[];
      dirs: ListDirDTO;
      transferList: FileTransferLogDTO[];
    };
  }) => ({
    files: sftp.files,
    open: sftp.open,
    dirs: sftp.dirs,
    transferList: sftp.transferList,
  })
)(SftpManagementPage);

const StatusMapper: Record<number, string> = {
  10: "未开始",
  20: "进行中",
  30: "已暂停",
  40: "已完成",
  50: "已取消",
  60: "传输异常",
};

const StatusColorMapper: Record<number, string> = {
  10: "default",
  20: "processing",
  30: "purple",
  40: "success",
  50: "warning",
  60: "error",
};

function modeToRwx(isDir: boolean | undefined, mode: number | null): string {
  if (mode === null || mode === undefined) {
    return "----------";
  }

  // 将字符串形式的mode转换为八进制数值
  const numericMode = parseInt(mode.toString(), 8);

  // 定义rwx权限位的掩码
  const rMask = 4; // 0400 -> 4
  const wMask = 2; // 0200 -> 2
  const xMask = 1; // 0100 -> 1

  // 根据掩码检查rwx权限
  const rUser = (numericMode & (rMask << 6)) !== 0; // 用户读权限
  const wUser = (numericMode & (wMask << 6)) !== 0; // 用户写权限
  const xUser = (numericMode & (xMask << 6)) !== 0; // 用户执行权限
  const rGroup = (numericMode & (rMask << 3)) !== 0; // 组读权限
  const wGroup = (numericMode & (wMask << 3)) !== 0; // 组写权限
  const xGroup = (numericMode & (xMask << 3)) !== 0; // 组执行权限
  const rOther = (numericMode & rMask) !== 0; // 其他读权限
  const wOther = (numericMode & wMask) !== 0; // 其他写权限
  const xOther = (numericMode & xMask) !== 0; // 其他执行权限

  // 根据权限构建字符串
  const user = `${rUser ? "r" : "-"}${wUser ? "w" : "-"}${xUser ? "x" : "-"}`;
  const group = `${rGroup ? "r" : "-"}${wGroup ? "w" : "-"}${
    xGroup ? "x" : "-"
  }`;
  const other = `${rOther ? "r" : "-"}${wOther ? "w" : "-"}${
    xOther ? "x" : "-"
  }`;

  // 根据是否是目录在最前面添加 'd' 或 '-'
  return (isDir ? "d" : "-") + user + group + other;
}

function pathToBreadcrumb(path: string) {
  const parts = path.split("/"); // 去除空字符串
  return (
    <Breadcrumb>
      {parts?.map((part, index) => (
        <Breadcrumb.Item key={index}>
          {part === null ? "/" : part}
        </Breadcrumb.Item>
      ))}
    </Breadcrumb>
  );
}
