import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import {
  Button,
  Card,
  Form,
  Input,
  Space,
  Table,
  Drawer,
  Tooltip,
  Badge,
} from "antd";
import { connect, Dispatch } from "umi";
import { WebhookConfig } from "@/models/webhook";
import {
  fetchWebhooks,
  createWebhook,
  updateWebhook,
} from "@/services/webhook";
import CreateWebHookForm from "./components/create-webhook-form";
import { DingdingOutlined } from "@ant-design/icons"; // 引入钉钉图标

interface WebHookListProps {
  dispatch: Dispatch;
  webhooks: WebhookConfig[];
  total: number;
}

const WebHookList: React.FC<WebHookListProps> = ({
  dispatch,
  webhooks,
  total,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    name: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingWebHook, setEditingWebHook] = useState<
    WebhookConfig | undefined
  >(undefined);
  const [form] = Form.useForm();

  useEffect(() => {
    getWebHooks();
  }, [pagination, filters]);

  const getWebHooks = () => {
    dispatch({
      type: "webhook/queryWebHooks",
      payload: { ...pagination, ...filters },
    });
  };

  const handleEdit = (webhook: WebhookConfig) => {
    setEditingWebHook(webhook);
    setDrawerVisible(true);
  };

  const handleDelete = (webhookId: number) => {
    dispatch({
      type: "webhook/deleteWebHook",
      payload: { id: webhookId },
    });
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddWebHook = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingWebHook(undefined);
  };

  const handleSaveWebHook = async (values: WebhookConfig) => {
    try {
      if (editingWebHook) {
        await updateWebhook({ ...values, id: editingWebHook.id });
      } else {
        await createWebhook(values);
      }
      getWebHooks();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("保存WebHook失败:", error);
    }
  };

  const columns = [
    {
      title: "名称",
      dataIndex: "webhookName",
      key: "webhookName",
    },
    {
      title: "URL",
      dataIndex: "webhookUrl",
      key: "webhookUrl",
    },
    {
      title: "类型",
      dataIndex: "webhookType",
      key: "webhookType",
      render: (text: any, record: WebhookConfig) => {
        return record.webhookType === "10" ? (
          <Tooltip title="钉钉">
            <DingdingOutlined style={{ fontSize: "18px", color: "#1683e9" }} />
          </Tooltip>
        ) : (
          <Badge status="default" text="未知" />
        );
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: WebhookConfig) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="WebHook列表">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              // 将表单的搜索条件发送给后端进行过滤
              setFilters(values);
            }}
          >
            <Form.Item name="name" label="名称">
              <Input placeholder="请输入名称" />
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
                    name: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddWebHook}>
            新增WebHook
          </Button>
          <Table
            columns={columns}
            dataSource={webhooks}
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

      <Drawer
        title={editingWebHook ? "编辑WebHook" : "新增WebHook"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateWebHookForm
          initialValues={editingWebHook}
          onSave={handleSaveWebHook}
          onUpdate={handleSaveWebHook} // 添加更新方法
          onCancel={handleCloseDrawer}
        />
      </Drawer>
    </PageContainer>
  );
};

export default connect(
  ({
    webhook,
  }: {
    webhook: {
      webhooks: WebhookConfig[];
      total: number;
    };
  }) => ({
    webhooks: webhook.webhooks,
    total: webhook.total,
  })
)(WebHookList);
