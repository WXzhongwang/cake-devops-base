import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Button,
  Card,
  Form,
  Input,
  Select,
  Space,
  Table,
  Tag,
  Drawer,
} from "antd";
import { connect, Dispatch, history } from "umi";
import { HostModel, ServerKey } from "@/models/host";
import CreateServerKeyForm from "./components/create-server-key-form"; // 导入编辑服务器秘钥表单组件

const { Option } = Select;

interface ServerAccountListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  serverKeys: ServerKey[];
  serverKeyTotal: number;
}

const ServerAccountList: React.FC<ServerAccountListProps> = ({
  dispatch,
  serverKeys,
  serverKeyTotal,
  hosts,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    displayName: "",
    active: "",
  });
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingServerKey, setEditingServerKey] = useState<
    ServerKey | undefined
  >(undefined); // 用于存储当前编辑的服务器秘钥信息
  const [form] = Form.useForm();

  const handleAddKey = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingServerKey(undefined); // 关闭抽屉时清空当前编辑的服务器秘钥信息
  };

  const handleSaveKey = (values: ServerKey, fileBase64: string) => {
    console.log("Form values:", values);
    const content = {
      ...values,
      fileBase64: fileBase64,
    };

    if (editingServerKey) {
      // 如果存在 editingServerKey，则执行更新操作
      dispatch({
        type: "host/updateServerKey",
        payload: { ...content, id: editingServerKey.id },
      });
    } else {
      // 否则执行新增操作
      dispatch({ type: "host/createServerKey", payload: content });
    }

    setDrawerVisible(false); // 关闭抽屉
  };

  const handleDelete = (serverKeyId: number) => {
    dispatch({
      type: "host/deleteServerKey",
      payload: {
        serverKeyId: serverKeyId,
      },
    });
  };

  useEffect(() => {
    getServerKeys();
  }, [pagination, filters]);

  const getServerKeys = () => {
    dispatch({
      type: "host/queryServerKeys",
      payload: { ...pagination, ...filters },
    });
  };

  const columns = [
    {
      title: "显示名称",
      dataIndex: "displayName",
      key: "displayName",
    },
    {
      title: "账户类型",
      dataIndex: "accountType",
      key: "accountType",
      render: (text: any, record: ServerKey) => (
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
      render: (text: any, record: ServerKey) => (
        <Tag color={record.active === "1" ? "green" : "gray"}>
          {record.active === "1" ? "活跃" : "不活跃"}
        </Tag>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: ServerKey) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleEdit = (record: ServerKey) => {
    setEditingServerKey(record); // 设置当前编辑的服务器秘钥信息
    setDrawerVisible(true); // 打开抽屉
  };

  return (
    <PageContainer title="主机秘钥">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              console.log(values);
              setFilters(values);
            }}
          >
            <Form.Item name="displayName" label="用户名">
              <Input placeholder="请输入用户名" />
            </Form.Item>
            <Form.Item name="active" label="活跃状态">
              <Select placeholder="请选择活跃状态" allowClear>
                <Option value={"1"}>活 跃</Option>
                <Option value={"0"}>不活跃</Option>
              </Select>
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button
                onClick={() => {
                  setFilters({
                    displayName: "",
                    active: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>

          <Button type="primary" onClick={handleAddKey}>
            新增秘钥
          </Button>

          <Table
            columns={columns}
            dataSource={serverKeys}
            rowKey={"id"}
            pagination={{
              total: serverKeyTotal,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>

      {/* 添加或编辑服务器秘钥的抽屉 */}
      <Drawer
        title={editingServerKey ? "编辑秘钥" : "新增秘钥"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateServerKeyForm
          initialValues={editingServerKey}
          onSave={handleSaveKey}
          onCancel={handleCloseDrawer}
        />
      </Drawer>
    </PageContainer>
  );
};

export default connect(
  ({
    host,
  }: {
    host: {
      serverKeys: ServerKey[];
      serverKeyTotal: number;
      hosts: HostModel[];
    };
  }) => {
    return {
      hosts: host.hosts,
      serverKeys: host.serverKeys,
      serverKeyTotal: host.serverKeyTotal,
    };
  }
)(ServerAccountList);
