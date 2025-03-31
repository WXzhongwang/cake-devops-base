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
  message,
} from "antd";
import { connect, Dispatch } from "umi";
import { PageContainer } from "@ant-design/pro-components";
import { ClusterInfo, CreateClusterPayload } from "@/models/cluster";
import CreateClusterDrawer from "./components/create-cluster-drawer";

interface ClusterListProps {
  dispatch: Dispatch;
}

const ClusterPage: React.FC<ClusterListProps> = ({ dispatch }) => {
  const [createClusterVisible, setCreateClusterVisible] = useState(false);
  const [clusterList, setClusterList] = useState<ClusterInfo[]>([]);

  const fetchClusterList = () => {
    dispatch({
      type: "cluster/listAll",
      callback: (res: ClusterInfo[]) => {
        setClusterList(res);
      },
    });
  };

  useEffect(() => {
    fetchClusterList();
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
      render: (text: any, record: ClusterInfo) => <Space size="middle"></Space>,
    },
  ];

  const handleCreateClusterDrawer = () => {
    setCreateClusterVisible(!createClusterVisible);
  };

  const handleCreateCluster = (values: CreateClusterPayload) => {
    dispatch({
      type: "cluster/createCluster",
      payload: values,
      callback: (res: boolean) => {
        if (res) {
          message.success("创建成功");
        } else {
          message.error("创建失败");
        }
        handleCreateClusterDrawer();
      },
    });
  };

  const connectCluster = async (values: any) => {
    dispatch({
      type: "cluster/connectCluster",
      payload: values,
      callback: (res: boolean) => {
        if (res) {
          message.success("连接成功");
        } else {
          message.error("连接失败");
        }
      },
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
            dataSource={clusterList}
            rowKey="clusterId"
            pagination={{ pageSize: 10 }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};
export default connect()(ClusterPage);
