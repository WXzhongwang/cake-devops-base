import React, { useState } from "react";
import { PageContainer } from "@ant-design/pro-layout";
import {
  Card,
  Row,
  Col,
  Tree,
  Table,
  Button,
  Space,
  Tooltip,
  Checkbox,
  Switch,
  Breadcrumb,
  Divider,
} from "antd";
import {
  FolderOutlined,
  FileOutlined,
  UploadOutlined,
  DeleteOutlined,
  DownloadOutlined,
  CopyOutlined,
  SwapOutlined,
  PlusOutlined,
  SyncOutlined,
  LeftOutlined,
  UnorderedListOutlined,
} from "@ant-design/icons";

// 假设您有一个函数用于获取目录树数据和文件列表数据
const getDirectoryTree = async () => {
  // TODO: 发送请求获取目录树数据
  return [
    {
      title: "Folder 1",
      key: "1",
      children: [
        { title: "File 1", key: "1-1", isLeaf: true },
        {
          title: "Folder 2",
          key: "1-2",
          children: [{ title: "File 2", key: "1-2-1", isLeaf: true }],
        },
      ],
    },
    { title: "File 3", key: "2", isLeaf: true },
  ];
};

const getFileList = async (directoryKey: string) => {
  // TODO: 发送请求获取文件列表数据
  return [
    { name: "File 1", type: "file", size: "10 KB", key: "1" },
    { name: "Folder 2", type: "folder", size: "-", key: "2" },
    { name: "File 3", type: "file", size: "20 KB", key: "3" },
  ];
};

const SftpManagementPage: React.FC = () => {
  const [directoryTreeData, setDirectoryTreeData] = useState<any[]>([]);
  const [fileList, setFileList] = useState<any[]>([]);
  const [selectedDirectoryKey, setSelectedDirectoryKey] = useState<
    string | null
  >(null);
  const [showHiddenFiles, setShowHiddenFiles] = useState<boolean>(false);
  const [selectedRows, setSelectedRows] = useState<any[]>([]);
  const [currentPath, setCurrentPath] = useState<string>("");

  // 加载目录树数据
  const loadDirectoryTreeData = async () => {
    const data = await getDirectoryTree();
    setDirectoryTreeData(data);
  };

  // 加载文件列表数据
  const loadFileListData = async (directoryKey: string) => {
    const data = await getFileList(directoryKey);
    setFileList(data);
  };

  // 目录树节点选中事件
  const handleDirectoryNodeSelect = (selectedKeys: any, info: any) => {
    setSelectedDirectoryKey(selectedKeys[0]);
    loadFileListData(selectedKeys[0]);
    setCurrentPath(info.node.title);
  };

  // 目录树节点展开事件
  const handleDirectoryNodeExpand = () => {
    // Do something when directory node expands
  };

  // 切换是否显示隐藏文件
  const toggleShowHiddenFiles = () => {
    setShowHiddenFiles(!showHiddenFiles);
  };

  // 返回上一级目录
  const goBack = () => {
    // TODO: 返回上一级目录的逻辑
  };

  // 删除选中文件
  const deleteSelectedFiles = () => {
    // TODO: 删除选中文件的逻辑
  };

  // 下载选中文件
  const downloadSelectedFiles = () => {
    // TODO: 下载选中文件的逻辑
  };

  // 复制选中文件路径
  const copySelectedFilesPath = () => {
    // TODO: 复制选中文件路径的逻辑
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
    // TODO: 刷新文件列表的逻辑
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

export default SftpManagementPage;
