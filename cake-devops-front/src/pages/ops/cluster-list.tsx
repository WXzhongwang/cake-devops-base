import React, { useEffect, useState } from "react";
import {
  Row,
  Col,
  Button,
  Card,
  Form,
  Input,
  Space,
  Table,
  Drawer,
  Tag,
} from "antd";
import { connect, Dispatch } from "umi";
import { PageContainer } from "@ant-design/pro-components";
import { ClusterInfo, CreateClusterPayload } from "@/models/cluster";
import CreateClusterDrawer from "./components/create-cluster-drawer";

interface ClusterListProps {
  dispatch: Dispatch;
  clusters: ClusterInfo[];
}

const ClusterPage: React.FC<ClusterListProps> = ({ dispatch, clusters }) => {
  const [createClusterVisible, setCreateClusterVisible] = useState(false);
  const fetchClusters = () => {
    dispatch({
      type: "cluster/listAll",
      payload: {},
    });
  };

  useEffect(() => {
    fetchClusters();
  }, []);
  const columns = [
    {
      title: "集群名称",
      dataIndex: "clusterName",
      key: "clusterName",
    },
    {
      title: "版本",
      dataIndex: "version",
      key: "version",
    },
    {
      title: "集群类型",
      dataIndex: "clusterType",
      key: "clusterType",
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: ClusterInfo) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.status === "0" ? "success" : "error";
        const statusText = record.status === "0" ? "正常" : "停用";

        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },
    {
      title: "标签",
      dataIndex: "tags",
      key: "tags",
      render: (tags: string[]) => (
        <Space>
          {tags.map((tag, index) => (
            <Tag key={index} color="blue">
              {tag}
            </Tag>
          ))}
        </Space>
      ),
    },
    {
      title: "操作",
      key: "actions",
      render: (text: any, record: ClusterInfo) => (
        <Space size="middle">
          {/* Add actions like view, edit, delete based on your requirements */}
        </Space>
      ),
    },
  ];

  // 处理新增主机弹窗的显示和隐藏
  const handleCreateClusterDrawer = () => {
    setCreateClusterVisible(!createClusterVisible);
  };

  const handleCreateCluster = (values: CreateClusterPayload) => {
    dispatch({
      type: "cluster/createCluster",
      payload: values,
    });
    handleCreateClusterDrawer();
  };

  // 处理连接的提交
  const connectCluster = async (values: any) => {
    // 在这里可以连接创建的逻辑
    console.log("连接集群:", values);
    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "cluster/connectCluster",
      payload: values,
    });
  };

  return (
    <PageContainer title="集群管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Button type="primary" onClick={handleCreateClusterDrawer}>
            新增集群
          </Button>

          <CreateClusterDrawer
            onSubmit={handleCreateCluster}
            handleCreateClusterDrawer={handleCreateClusterDrawer}
            createClusterVisible={createClusterVisible}
            onConnect={connectCluster}
          />

          <Table
            columns={columns}
            dataSource={clusters}
            rowKey="clusterId"
            pagination={{ pageSize: 10 }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};
export default connect(({ cluster }) => ({
  clusters: cluster.clusterList,
}))(ClusterPage);
