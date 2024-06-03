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
  Divider,
  Input,
  message,
  Typography,
} from "antd";
import dayjs from "dayjs";
import { FileDetailDTO, ListDirDTO, OpenSessionDTO } from "@/models/sftp";

import {
  UploadOutlined,
  DeleteOutlined,
  DownloadOutlined,
  CopyOutlined,
  SwapOutlined,
  PlusOutlined,
  SyncOutlined,
  LeftOutlined,
  FolderOutlined,
  FileOutlined,
} from "@ant-design/icons";
import { connect, Dispatch, useParams } from "umi";

interface SftpManageProps {
  dispatch: Dispatch;
  open: OpenSessionDTO;
  files: FileDetailDTO[];
  dirs: ListDirDTO;
}

const SftpManagementPage: React.FC<SftpManageProps> = ({
  dispatch,
  open,
  files,
}) => {
  const { id } = useParams();
  const [directoryTreeData, setDirectoryTreeData] = useState<any[]>([]);
  const [selectedDirectoryKey, setSelectedDirectoryKey] = useState<
    string | null
  >(null);
  const [showHiddenFiles, setShowHiddenFiles] = useState<boolean>(false);
  const [selectedRows, setSelectedRows] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<string>(open?.home || "/");
  const [pathStack, setPathStack] = useState<string[]>([]);
  const [inputPath, setInputPath] = useState<string>(open?.home || "/");

  useEffect(() => {
    if (id) {
      openSession(id);
    }
  }, [id]);

  useEffect(() => {
    if (open?.sessionToken) {
      getDirTrees("/");
    }
    if (open?.home) {
      setCurrentPath(open.home);
      getFileList(open.home);
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
        // setAlarmDrawerVisible(true); // 打开抽屉
      },
    });
  };

  const getFileList = (dirPath: string) => {
    dispatch({
      type: "sftp/fetchFiles",
      payload: { path: dirPath, sessionToken: open.sessionToken, all: true },
    });
  };

  // 加载文件列表数据
  const loadFileListData = async (directoryKey: string) => {
    getFileList(directoryKey);
  };

  // 目录树节点选中事件
  const handleDirectoryNodeSelect = (selectedKeys: any, info: any) => {
    if (selectedKeys.length > 0) {
      const key = selectedKeys[0];
      setSelectedDirectoryKey(key);
      loadFileListData(key);
      setCurrentPath(info.node.title);
      setInputPath(info.node.key); // Update inputPath when selecting a directory
      setPathStack((prev) => [...prev, info.node.title]);
    }
  };

  // 切换是否显示隐藏文件
  const toggleShowHiddenFiles = () => {
    setShowHiddenFiles(!showHiddenFiles);
    // 过滤文件列表
    if (!showHiddenFiles) {
      setFileList((prevList) =>
        prevList.filter((file) => !file.name.startsWith("."))
      );
    } else {
      loadFileListData(selectedDirectoryKey as string);
    }
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
  const downloadSelectedFiles = () => {
    // TODO: 下载选中文件的逻辑
    message.success("下载成功");
  };

  // 复制选中文件路径
  const copySelectedFilesPath = () => {
    // TODO: 复制选中文件路径的逻辑
    message.success("路径复制成功");
  };

  // 显示传输列表
  const showTransferList = () => {
    // TODO: 显示传输列表的逻辑
  };

  // 创建文件或文件夹
  const createFileOrFolder = () => {
    // TODO: 创建文件或文件夹的逻辑
  };

  // 上传文件
  const uploadFile = () => {
    // TODO: 上传文件的逻辑
  };

  // 刷新文件列表
  const refreshFileList = () => {
    if (selectedDirectoryKey) {
      loadFileListData(selectedDirectoryKey);
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
                getFileList(record.path);
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
      render: () => <Button type="link">Action</Button>,
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys: any, selectedRows: any) => {
      setSelectedRows(selectedRows);
    },
    selectedRowKeys: selectedRows.map((row) => row.key),
  };

  // Handle input path change
  const handleInputPathChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputPath(e.target.value);
  };

  // Handle input path submit
  const handleInputPathSubmit = () => {
    setCurrentPath(inputPath);
    setSelectedDirectoryKey(inputPath);
    loadFileListData(inputPath);
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
  console.log("files", files);
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
              <Space style={{ marginBottom: 16 }}>
                {/* <Switch
                  checked={showHiddenFiles}
                  onChange={toggleShowHiddenFiles}
                  checkedChildren="显示隐藏文件"
                  unCheckedChildren="隐藏隐藏文件"
                /> */}
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
                <Button
                  icon={<SwapOutlined />}
                  onClick={showTransferList}
                  title="传输列表"
                ></Button>
                <Button
                  icon={<PlusOutlined />}
                  onClick={createFileOrFolder}
                  title="创建"
                ></Button>
                <Button
                  icon={<UploadOutlined />}
                  onClick={uploadFile}
                  title="上传"
                ></Button>
                <Button
                  icon={<SyncOutlined />}
                  onClick={refreshFileList}
                  title="刷新"
                ></Button>
              </Space>
              <Table
                dataSource={files}
                columns={columns}
                rowKey="path"
                rowSelection={{ ...rowSelection }}
              />
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
    };
  }) => ({
    files: sftp.files,
    open: sftp.open,
    dirs: sftp.dirs,
  })
)(SftpManagementPage);
