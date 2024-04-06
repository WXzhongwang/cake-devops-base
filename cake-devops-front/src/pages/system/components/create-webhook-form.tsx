import React from "react";
import { Form, Input, Button, Select } from "antd";
import { WebhookConfig } from "@/models/webhook"; // 根据您的实际路径进行调整

const { Option } = Select;

interface CreateWebhookFormProps {
  initialValues?: WebhookConfig; // 编辑时的初始值
  onSave: (values: WebhookConfig) => void;
  onCancel: () => void;
  onUpdate: (values: WebhookConfig) => void; // 新增 onUpdate 方法用于更新
}

const CreateWebhookForm: React.FC<CreateWebhookFormProps> = ({
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
        name="webhookName"
        label="Webhook名称"
        rules={[{ required: true, message: "请输入Webhook名称" }]}
      >
        <Input placeholder="请输入Webhook名称" />
      </Form.Item>

      <Form.Item
        name="webhookUrl"
        label="Webhook URL"
        rules={[{ required: true, message: "请输入Webhook URL" }]}
      >
        <Input placeholder="请输入Webhook URL" />
      </Form.Item>

      <Form.Item
        name="webhookType"
        label="Webhook类型"
        rules={[{ required: true, message: "请选择Webhook类型" }]}
      >
        <Select placeholder="请选择Webhook类型">
          <Option value="10">钉钉</Option>
        </Select>
      </Form.Item>

      <Form.Item name="webhookConfig" label="Webhook 签名">
        <Input placeholder="请输入Webhook 签名配置" />
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

export default CreateWebhookForm;
