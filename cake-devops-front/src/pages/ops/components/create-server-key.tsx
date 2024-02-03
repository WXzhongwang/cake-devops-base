import React, { useState } from "react";
import { Drawer, Form, Select, Button, Input, Radio } from "antd";
import { HostModel, ServerKey } from "@/models/host";

const { Option } = Select;

interface ServerAccountDrawerProps {
  visible: boolean;
  servers: HostModel[];
  onClose: () => void;
  onSave: (values: ServerKey) => void;
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
      title="新增秘钥"
      width={600}
      onClose={onClose}
      open={visible}
      destroyOnClose
    >
      <Form form={form} onFinish={handleSave} layout="vertical">
        <Form.Item
          label="显示名称"
          name="displayName"
          rules={[{ required: true, message: "请输入显示名称" }]}
        >
          <Input placeholder="请输入显示名称" />
        </Form.Item>
        <Form.Item
          label="账户类型"
          name="accountType"
          rules={[{ required: true, message: "请选择账户类型" }]}
        >
          <Radio.Group>
            <Radio value="0">普通账户</Radio>
            <Radio value="1">管理员</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item
          label="协议"
          name="protocol"
          initialValue="SSH"
          rules={[{ required: true, message: "请选择协议" }]}
        >
          <Input disabled />
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
        <Form.Item
          label="凭据内容"
          name="credential"
          rules={[{ required: true, message: "请输入凭据内容" }]}
        >
          <Input.TextArea placeholder="请输入凭据内容" />
        </Form.Item>
        <Form.Item
          label="密码短语"
          name="passphrase"
          rules={[{ required: true, message: "请输入密码短语" }]}
        >
          <Input.Password placeholder="请输入密码短语" />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            保存
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default ServerAccountDrawer;
