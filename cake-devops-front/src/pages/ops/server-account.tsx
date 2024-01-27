import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Table, Space, Input, Select, Button, Form, Card, Tag } from "antd";
import { connect, Dispatch, history } from "umi";
import { ServerAccount } from "@/models/host";

const { Option } = Select;

interface ServerAccountListProps {
  dispatch: Dispatch;
  serverAccounts: ServerAccount[];
  serverAccountTotal: number;
}

const ServerAccountList: React.FC<ServerAccountListProps> = ({
  dispatch,
  serverAccounts,
  serverAccountTotal,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    authMode: "",
    username: "",
    accountType: "",
    protocol: "",
    active: undefined,
  });

  useEffect(() => {
    getServerAccounts();
  }, [pagination, filters]);

  const getServerAccounts = () => {
    dispatch({
      type: "host/getServerAccounts", // 请根据实际的 model 和 effect 路径调整
      payload: { ...pagination, ...filters },
    });
  };

  const columns = [
    {
      title: "用户名",
      dataIndex: "username",
      key: "username",
      render: (text: any, record: ServerAccount) => (
        <a onClick={() => handleView(record)}>{record.username}</a>
      ),
    },
    {
      title: "显示名称",
      dataIndex: "displayName",
      key: "displayName",
    },
    {
      title: "账户类型",
      dataIndex: "accountType",
      key: "accountType",
      render: (text: any, record: ServerAccount) => (
        <Tag color={record.accountType === 1 ? "red" : "blue"}>
          {record.accountType === 1 ? "管理员" : "普通账户"}
        </Tag>
      ),
    },
    {
      title: "协议",
      dataIndex: "protocol",
      key: "protocol",
    },
    {
      title: "活跃状态",
      dataIndex: "active",
      key: "active",
      render: (text: any, record: ServerAccount) => (
        <Tag color={record.active ? "green" : "gray"}>
          {record.active ? "活跃" : "不活跃"}
        </Tag>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: ServerAccount) => (
        <Space size="middle">
          <a onClick={() => handleView(record)}>查看</a>
        </Space>
      ),
    },
  ];

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleView = (record: ServerAccount) => {
    // 处理查看操作
    console.log("查看主机账号详情", record);
    // 示例：跳转到详情页，使用 history.push
    history.push(`/host/account/info/${record.hostId}`);
  };

  return (
    <PageContainer title="主机账号管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              console.log(values);
              setFilters(values);
            }}
          >
            <Form.Item name="authMode" label="认证模式">
              <Input placeholder="请输入认证模式" />
            </Form.Item>
            <Form.Item name="username" label="用户名">
              <Input placeholder="请输入用户名" />
            </Form.Item>
            <Form.Item name="accountType" label="账户类型">
              <Select placeholder="请选择账户类型" allowClear>
                <Option value={0}>普通账户</Option>
                <Option value={1}>管理员</Option>
              </Select>
            </Form.Item>
            <Form.Item name="protocol" label="协议">
              <Input placeholder="请输入协议" />
            </Form.Item>
            <Form.Item name="active" label="活跃状态">
              <Select placeholder="请选择活跃状态" allowClear>
                <Option value={true}>活跃</Option>
                <Option value={false}>不活跃</Option>
              </Select>
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button
                onClick={() => {
                  setFilters({
                    authMode: "",
                    username: "",
                    accountType: "",
                    protocol: "",
                    active: undefined,
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>

          <Table
            columns={columns}
            dataSource={serverAccounts}
            rowKey={"serverAccountId"}
            pagination={{
              total: serverAccountTotal, // 请替换为实际的总数
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};

export default connect(
  ({
    host,
  }: {
    host: {
      serverAccounts: ServerAccount[];
      serverAccountTotal: number;
    };
  }) => {
    return {
      serverAccounts: host.serverAccounts,
      serverAccountTotal: host.serverAccountTotal,
    };
  }
)(ServerAccountList);
