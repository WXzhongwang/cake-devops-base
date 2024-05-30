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
  Tooltip,
  Switch,
  Breadcrumb,
  Divider,
  message,
} from "antd";
import { FileDetailDTO } from "@/models/sftp";

import {
  UploadOutlined,
  DeleteOutlined,
  DownloadOutlined,
  CopyOutlined,
  SwapOutlined,
  PlusOutlined,
  SyncOutlined,
  LeftOutlined,
} from "@ant-design/icons";
import { connect, Dispatch, useParams } from "umi";

// 假设您有一个函数用于获取目录树数据和文件列表数据
// const getDirectoryTree = async () => {
//   // TODO: 发送请求获取目录树数据
//   return [
//     {
//       title: "Folder 1",
//       key: "1",
//       children: [
//         { title: "File 1", key: "1-1", isLeaf: true },
//         {
//           title: "Folder 2",
//           key: "1-2",
//           children: [{ title: "File 2", key: "1-2-1", isLeaf: true }],
//         },
//       ],
//     },
//     { title: "File 3", key: "2", isLeaf: true },
//   ];
// };

const getFileList = async (directoryKey: string) => {
  // TODO: 发送请求获取文件列表数据
  return [
    { name: "File 1", type: "file", size: "10 KB", key: "1" },
    { name: "Folder 2", type: "folder", size: "-", key: "2" },
    { name: "File 3", type: "file", size: "20 KB", key: "3" },
  ];
};

interface SftpManageProps {
  dispatch: Dispatch;
  dirs: FileDetailDTO[];
  files: FileDetailDTO[];
  total: number;
}

const SftpManagementPage: React.FC<SftpManageProps> = ({
  dispatch,
  dirs,
  files,
  total,
  token,
}) => {
  const { id } = useParams();
  const [directoryTreeData, setDirectoryTreeData] = useState<any[]>([]);
  const [sessionToken, setSessionToken] = useState<any[]>([]);
  const [fileList, setFileList] = useState<any[]>([]);
  const [selectedDirectoryKey, setSelectedDirectoryKey] = useState<
    string | null
  >(null);
  const [showHiddenFiles, setShowHiddenFiles] = useState<boolean>(false);
  const [selectedRows, setSelectedRows] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<string>("");
  const [pathStack, setPathStack] = useState<string[]>([]);

  useEffect(() => {
    // 初始加载目录树数据
    // loadDirectoryTreeData();
    console.log("id:", id);
    open(id);
    getDirTrees();
  }, []);

  const getDirTrees = () => {
    dispatch({
      type: "sftp/listDir",
      payload: { path: "/" },
    });
  };

  const open = (id: number) => {
    dispatch({
      type: "sftp/open",
      payload: { hostId: id },
    });
  };

  // // 加载目录树数据
  // const loadDirectoryTreeData = async () => {
  //   const data = await getDirectoryTree();
  //   setDirectoryTreeData(data);
  // };

  // 加载文件列表数据
  const loadFileListData = async (directoryKey: string) => {
    const data = await getFileList(directoryKey);
    setFileList(data);
  };

  // 目录树节点选中事件
  const handleDirectoryNodeSelect = (selectedKeys: any, info: any) => {
    if (selectedKeys.length > 0) {
      const key = selectedKeys[0];
      setSelectedDirectoryKey(key);
      loadFileListData(key);
      setCurrentPath(info.node.title);
      setPathStack((prev) => [...prev, info.node.title]);
    }
  };

  // 目录树节点展开事件
  const handleDirectoryNodeExpand = () => {
    // 执行目录节点展开时的操作
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
    { title: "Name", dataIndex: "name", key: "name" },
    { title: "Type", dataIndex: "type", key: "type" },
    { title: "Size", dataIndex: "size", key: "size" },
    {
      title: "Action",
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

  return (
    <PageContainer title="SFTP">
      <Card>
        <Row gutter={24}>
          <Col span={8}>
            <Card title="目录树">
              <Tree
                showLine
                onSelect={handleDirectoryNodeSelect}
                onExpand={handleDirectoryNodeExpand}
                treeData={directoryTreeData}
              />
            </Card>
          </Col>
          <Col span={16}>
            <Card>
              <Space>
                <Breadcrumb>
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
                </Breadcrumb>
              </Space>
              <Divider />
              <Space style={{ marginBottom: 16 }}>
                <Switch
                  checked={showHiddenFiles}
                  onChange={toggleShowHiddenFiles}
                  checkedChildren="显示隐藏文件"
                  unCheckedChildren="隐藏隐藏文件"
                />
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
                dataSource={fileList}
                columns={columns}
                rowKey="key"
                pagination={false}
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
      dirs: FileDetailDTO[];
      files: FileDetailDTO[];
      total: number;
    };
  }) => ({
    dirs: sftp.dirs,
    files: sftp.files,
    total: sftp.total,
    token: sftp.token,
  })
)(SftpManagementPage);
