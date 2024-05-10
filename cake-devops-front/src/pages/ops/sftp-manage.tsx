import React, { useState } from "react";
import { PageContainer } from "@ant-design/pro-layout";
import { Card, Row, Col, Tree, Table, Button, Space } from "antd";
import {
  FolderOutlined,
  FileOutlined,
  UploadOutlined,
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
  };

  // 目录树节点展开事件
  const handleDirectoryNodeExpand = () => {
    // Do something when directory node expands
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
              <Space style={{ marginBottom: 16 }}>
                <Button icon={<UploadOutlined />} type="primary">
                  上传文件
                </Button>
              </Space>
              <Table
                dataSource={fileList}
                columns={columns}
                rowKey="key"
                pagination={false}
              />
            </Card>
          </Col>
        </Row>
      </Card>
    </PageContainer>
  );
};

export default SftpManagementPage;
