// src/components/HostAccountDrawer.tsx

import React from "react";
import { Drawer, Form, Select, Button, Input } from "antd";
import { HostModel, ServerAccount } from "@/models/host";

const { Option } = Select;

interface ServerAccountDrawerProps {
  visible: boolean;
  servers: HostModel[];
  onClose: () => void;
  onSave: (values: ServerAccount) => void;
}

const ServerAccountDrawer: React.FC<ServerAccountDrawerProps> = ({
  visible,
  servers,
  onClose,
  onSave,
}) => {
  const [form] = Form.useForm();

  const handleSave = async () => {
    try {
      const values = await form.validateFields();
      console.log("Form values:", values);
      onSave(values);
      onClose();
    } catch (error) {
      console.error("Validation failed:", error);
    }
  };

  return (
    <Drawer
      title="新增账号"
      width={600}
      onClose={onClose}
      open={visible}
      destroyOnClose
    >
      <Form
        form={form}
        onFinish={handleSave}
        // labelCol={{ span: 6 }}
        // wrapperCol={{ span: 18 }}
        layout="vertical"
      >
        <Form.Item
          label="关联服务器"
          name="serverId"
          rules={[{ required: true, message: "请选择关联服务器" }]}
        >
          <Select
            showSearch
            placeholder="请选择或输入搜索"
            optionFilterProp="value"
            filterOption={(input, option) =>
              option?.value
                ?.toString()
                .toLowerCase()
                .indexOf(input.toLowerCase()) !== -1
            }
          >
            {servers?.map((server, index) => (
              <Option key={server.hostId} value={server.hostId}>
                主机名：{server.hostName}
                <br></br>
                名称：{server.name}
                <br></br>
                IP：{server.serverAddr}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          label="认证模式"
          name="authMode"
          rules={[{ required: true, message: "请输入认证模式" }]}
        >
          <Input placeholder="请输入认证模式" />
        </Form.Item>
        <Form.Item label="显示名称" name="displayName">
          <Input placeholder="请输入显示名称" />
        </Form.Item>
        <Form.Item
          label="账户类型"
          name="accountType"
          rules={[{ required: true, message: "请选择账户类型" }]}
        >
          <Select placeholder="请选择账户类型">
            <Option value={0}>普通账户</Option>
            <Option value={1}>管理员</Option>
          </Select>
        </Form.Item>
        <Form.Item label="协议" name="protocol">
          <Input placeholder="请输入协议" />
        </Form.Item>
        <Form.Item
          label="活跃状态"
          name="active"
          rules={[{ required: true, message: "请选择活跃状态" }]}
        >
          <Select placeholder="请选择活跃状态">
            <Option value={true}>活跃</Option>
            <Option value={false}>不活跃</Option>
          </Select>
        </Form.Item>
        <Form.Item label="凭据内容" name="credential">
          <Input.TextArea placeholder="请输入凭据内容" />
        </Form.Item>
        <Form.Item label="publicKey" name="publicKey">
          <Input placeholder="请输入publicKey" />
        </Form.Item>
        <Form.Item label="密码短语" name="passphrase">
          <Input placeholder="请输入密码短语" />
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
          <Button type="primary" htmlType="submit">
            保存
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default ServerAccountDrawer;
