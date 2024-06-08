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
  const [selectedRows, setSelectedRows] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<string>(open?.home || "/");
  const [homePath, setHomePath] = useState<string>(open?.path || "/");
  const [pathStack, setPathStack] = useState<string[]>([]);
  const [inputPath, setInputPath] = useState<string>(open?.home || "/");
  const [mkdirTouchForm] = Form.useForm();

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
      setCurrentPath(open.home);
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

  const getTransferList = () => {
    // setInterval(() => {
    dispatch({
      type: "sftp/getTransferList",
      payload: { sessionToken: open.sessionToken },
    });
    // }, 5000);
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
      setCurrentPath(info.node.title);
      setInputPath(info.node.key);
      setPathStack((prev) => [...prev, info.node.title]);
    }
  };

  // 切换是否显示隐藏文件
  const toggleShowHiddenFiles = () => {
    setShowHiddenFiles(!showHiddenFiles);
    // 过滤文件列表
    getFileList(selectedDirectoryKey as string, !showHiddenFiles);
  };

  // 返回上一级目录
  const goBack = () => {
    if (pathStack.length > 1) {
      pathStack.pop();
      const previousPath = pathStack[pathStack.length - 1];
      setCurrentPath(previousPath);
      setInputPath(previousPath); // Update inputPath when going back
      // TODO: 加载上一级目录的文件列表逻辑
    } else {
      message.warning("已经是根目录");
    }
  };

  // 删除选中文件
  const deleteSelectedFiles = () => {
    // TODO: 删除选中文件的逻辑
    message.success("删除成功");
  };

  // 下载选中文件
  const downloadSelectedFiles = (path: string) => {
    // TODO: 下载选中文件的逻辑
    dispatch({
      type: "sftp/downloadFile",
      payload: { paths: [path], sessionToken: open.sessionToken },
      callback: (res: { files: any[] }) => {
        message.success("下载成功");
      },
    });
  };

  const moveFile = (record: FileDetailDTO) => {};

  const updatePermission = (record: FileDetailDTO) => {};

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

  // 显示传输列表
  const showTransferList = () => {
    // TODO: 显示传输列表的逻辑
  };

  // 创建文件或文件夹
  const createFileOrFolder = (values: any) => {
    // TODO: 创建文件或文件夹的逻辑
    console.log("values", values);
    mkdirTouchForm.validateFields().then((values) => {
      if (values?.type === "file") {
        dispatch({
          type: "sftp/createFile",
          payload: { sessionToken: open.sessionToken, path: values.path },
          callback: () => {
            message.success("文件创建成功");
          },
        });
      } else {
        dispatch({
          type: "sftp/createFolder",
          payload: { sessionToken: open.sessionToken, path: values.path },
          callback: () => {
            message.success("文件夹创建成功");
          },
        });
      }

      showModal();
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
      render: (val, record) => {
        return (
          <Space
            onClick={() => {
              if (record.isDir) {
                getFileList(record.path, showHiddenFiles);
              }
            }}
          >
            {record.isDir ? <FolderOutlined /> : <FileOutlined />}
            <Typography.Link>{val}</Typography.Link>
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
            onClick={() => downloadSelectedFiles(record.path)}
            title="下载"
            size="small"
          />
          <Button
            icon={<DeleteOutlined />}
            onClick={() => deleteSelectedFiles()}
            title="删除"
            size="small"
          />
          <Button
            icon={<ScissorOutlined />}
            onClick={() => moveFile(record)}
            title="移动"
            size="small"
          />
          <Button
            icon={<EditOutlined />}
            onClick={() => updatePermission(record)}
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
    <Select defaultValue={currentPath}>
      <Option value={currentPath}>当前路径</Option>
      <Option value={homePath}>主目录</Option>
      <Option value={"/"}>根目录</Option>
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

  const updateTreeData = (list, key, children) =>
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
                {/* <Breadcrumb style={{ marginBottom: 16 }}>
                  <Breadcrumb.Item>
                    <Button
                      icon={<LeftOutlined />}
                      onClick={goBack}
                      disabled={pathStack.length <= 1}
                      type="link"
                    />
                  </Breadcrumb.Item>
                  <Breadcrumb.Item>/</Breadcrumb.Item>
                  <Breadcrumb.Item>{currentPath}</Breadcrumb.Item>
                </Breadcrumb> */}
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
                <Breadcrumb />
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
                    icon={<DeleteOutlined />}
                    onClick={deleteSelectedFiles}
                    disabled={selectedRows.length === 0}
                    title="删除"
                  ></Button>
                  <Button
                    icon={<DownloadOutlined />}
                    onClick={downloadSelectedFiles}
                    disabled={selectedRows.length === 0}
                    title="下载"
                  ></Button>
                  <Button
                    icon={<CopyOutlined />}
                    onClick={copySelectedFilesPath}
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
                            <Button size="small" icon={<ReloadOutlined />} />
                          </Tooltip>
                          <Tooltip title="开始所有">
                            <Button
                              size="small"
                              icon={<CaretRightOutlined />}
                            />
                          </Tooltip>
                          <Tooltip title="暂停所有">
                            <Button size="small" icon={<PauseOutlined />} />
                          </Tooltip>
                          <Tooltip title="重试所有">
                            <Button
                              size="small"
                              icon={<IssuesCloseOutlined />}
                            />
                          </Tooltip>
                          <Tooltip title="打包传输">
                            <Button size="small" icon={<FileZipOutlined />} />
                          </Tooltip>
                          <Tooltip title="清空">
                            <Button size="small" icon={<DeleteOutlined />} />
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
                                <Tooltip title="下载">
                                  <DownloadOutlined />
                                </Tooltip>
                              </Space>
                            </Space>
                          ))}
                        </Space>
                      </Space>
                    }
                  >
                    <Button
                      icon={<SwapOutlined />}
                      onClick={showTransferList}
                      title="传输列表"
                    ></Button>
                  </Popover>
                  <Button
                    icon={<PlusOutlined />}
                    onClick={showModal}
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
