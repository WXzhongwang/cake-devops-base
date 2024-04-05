// components/CreateProxyForm.tsx

import React from "react";
import { Form, Input, Button, Select } from "antd";
import { ProxyModel } from "@/models/proxy";
import TextArea from "antd/lib/input/TextArea";

const { Option } = Select;

interface CreateProxyFormProps {
  initialValues?: ProxyModel; // 编辑时的初始值
  onSave: (values: ProxyModel) => void;
  onCancel: () => void;
  onUpdate: (values: ProxyModel) => void; // 新增 onUpdate 方法用于更新
}

const CreateProxyForm: React.FC<CreateProxyFormProps> = ({
  initialValues,
  onSave,
  onCancel,
  onUpdate,
}) => {
  const [form] = Form.useForm();

  // 设置表单的初始值
  React.useEffect(() => {
    form.setFieldsValue(initialValues);
  }, [form, initialValues]);

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (initialValues) {
        // 如果存在初始值，则触发更新操作
        onUpdate(values);
      } else {
        onSave(values);
      }
    } catch (error) {
      console.error("表单验证失败:", error);
    }
  };

  return (
    <Form form={form} layout="vertical" onFinish={handleSubmit}>
      <Form.Item
        name="proxyHost"
        label="代理主机"
        rules={[{ required: true, message: "请输入代理主机" }]}
      >
        <Input placeholder="请输入代理主机" />
      </Form.Item>

      <Form.Item
        name="proxyPort"
        label="代理端口"
        rules={[{ required: true, message: "请输入代理端口" }]}
      >
        <Input placeholder="请输入代理端口" type="number" />
      </Form.Item>

      <Form.Item
        name="proxyType"
        label="代理类型"
        rules={[{ required: true, message: "请选择代理类型" }]}
      >
        <Select placeholder="请选择代理类型">
          <Option value={1}>HTTP</Option>
          <Option value={2}>HTTPS</Option>
        </Select>
      </Form.Item>

      <Form.Item
        name="proxyUsername"
        label="代理用户名"
        rules={[{ required: true, message: "请输入代理用户名" }]}
      >
        <Input placeholder="请输入代理用户名" />
      </Form.Item>

      <Form.Item
        name="proxyPassword"
        label="代理密码"
        rules={[{ required: true, message: "请输入代理密码" }]}
      >
        <Input.Password placeholder="请输入代理密码" />
      </Form.Item>

      <Form.Item
        name="description"
        label="描述"
        rules={[
          {
            required: false,
            max: 30,
            message: "请输入不超过30个字符的描述",
          },
        ]}
      >
        <TextArea placeholder="请输入描述" rows={4} />
      </Form.Item>

      <Form.Item>
        <Button type="primary" htmlType="submit">
          {initialValues ? "更新" : "保存"}{" "}
          {/* 根据是否存在初始值显示不同的按钮文本 */}
        </Button>
        <Button onClick={onCancel} style={{ marginLeft: 8 }}>
          取消
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreateProxyForm;
