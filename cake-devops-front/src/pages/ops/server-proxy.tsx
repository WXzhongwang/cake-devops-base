import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Button, Card, Form, Input, Select, Space, Table, Drawer } from "antd";
import { connect, Dispatch } from "umi";
import { ProxyModel } from "@/models/proxy";
import { createProxy, fetchProxies, updateProxy } from "@/services/proxy"; // 导入创建主机代理和查询主机代理列表的服务函数
import CreateProxyForm from "./components/create-proxy-form";

const { Option } = Select;

interface ProxyListProps {
  dispatch: Dispatch;
  proxies: ProxyModel[];
  total: number;
}

const ProxyList: React.FC<ProxyListProps> = ({ dispatch, proxies, total }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    proxyHost: "",
    proxyType: undefined,
    proxyUsername: undefined,
  });
  const [drawerVisible, setDrawerVisible] = useState(false); // 控制抽屉显示状态
  const [form] = Form.useForm(); // 创建表单实例
  const [editingProxy, setEditingProxy] = useState<ProxyModel | undefined>(
    undefined
  ); // 当前正在编辑的主机代理信息

  useEffect(() => {
    getProxies();
  }, [pagination, filters]);

  const getProxies = () => {
    dispatch({
      type: "proxy/queryProxies",
      payload: { ...pagination, ...filters },
    });
  };

  const handleEdit = (proxy: ProxyModel) => {
    setEditingProxy(proxy); // 设置编辑状态为当前点击的主机代理信息
    setDrawerVisible(true); // 打开抽屉
  };

  const handleDelete = (proxyId: number) => {
    dispatch({
      type: "proxy/deleteProxy",
      payload: {
        serverProxyId: proxyId,
      },
    });
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddProxy = () => {
    setDrawerVisible(true); // 点击新增按钮显示抽屉
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false); // 关闭抽屉
    form.resetFields(); // 重置表单字段
  };

  const handleSaveProxy = async (values: ProxyModel) => {
    try {
      // 调用服务函数创建主机代理
      await createProxy(values);
      getProxies(); // 创建成功后重新获取主机代理列表数据
      setDrawerVisible(false); // 关闭抽屉
      form.resetFields(); // 重置表单字段
    } catch (error) {
      console.error("创建主机代理失败:", error);
    }
  };

  // 新增 onUpdate 方法，用于更新代理信息
  const handleUpdateProxy = async (values: ProxyModel) => {
    try {
      // 从编辑的代理信息中获取代理的 ID
      const id = editingProxy?.id;
      await updateProxy({ ...values, id });
      getProxies();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("更新主机代理失败:", error);
    }
  };

  const columns = [
    {
      title: "代理主机",
      dataIndex: "proxyHost",
      key: "proxyHost",
    },
    {
      title: "代理端口",
      dataIndex: "proxyPort",
      key: "proxyPort",
    },
    {
      title: "代理用户",
      dataIndex: "proxyUsername",
      key: "proxyUsername",
    },
    {
      title: "代理类型",
      dataIndex: "proxyType",
      key: "proxyType",
      render: (text: any, record: ProxyModel) => {
        return record.proxyType === 1 ? "HTTP" : "HTTPS";
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: ProxyModel) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="主机代理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              // 将表单的搜索条件发送给后端进行过滤
              setFilters(values);
            }}
          >
            <Form.Item name="proxyHost" label="代理主机">
              <Input placeholder="请输入代理主机" />
            </Form.Item>
            <Form.Item name="proxyUsername" label="代理用户">
              <Input placeholder="请输入用户" />
            </Form.Item>
            <Form.Item name="proxyType" label="代理类型">
              <Select
                placeholder="请选择代理类型"
                style={{ width: 150 }}
                allowClear
              >
                <Option value={1}>HTTP</Option>
                <Option value={2}>HTTPS</Option>
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
                    proxyHost: "",
                    proxyType: undefined,
                    proxyUsername: undefined,
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddProxy}>
            新增代理
          </Button>
          <Table
            columns={columns}
            dataSource={proxies}
            rowKey="id"
            pagination={{
              total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>

      {/* 新增和编辑主机代理的抽屉 */}
      <Drawer
        title={editingProxy ? "编辑代理" : "新增代理"}
        width={400}
        visible={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateProxyForm
          onUpdate={handleUpdateProxy}
          initialValues={editingProxy}
          onSave={handleSaveProxy}
          onCancel={handleCloseDrawer}
        />
      </Drawer>
    </PageContainer>
  );
};

export default connect(
  ({
    proxy,
  }: {
    proxy: {
      proxies: ProxyModel[];
      total: number;
    };
  }) => ({
    proxies: proxy.proxies,
    total: proxy.total,
  })
)(ProxyList);
