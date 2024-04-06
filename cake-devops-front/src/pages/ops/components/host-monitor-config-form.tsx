import React, { useEffect } from "react";
import { Form, Input, Select, Button, Space } from "antd";
import { HostMonitorDTO } from "@/models/host-monitor";

const { Option } = Select;

interface HostMonitorConfigFormProps {
  initialValues?: HostMonitorDTO;
  onSubmit: (values: HostMonitorDTO) => void;
  onCancel: () => void;
}

const HostMonitorConfigForm: React.FC<HostMonitorConfigFormProps> = ({
  initialValues,
  onSubmit,
  onCancel,
}) => {
  const [form] = Form.useForm();

  useEffect(() => {
    if (initialValues) {
      form.setFieldsValue(initialValues);
    }
  }, [initialValues, form]);

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      onSubmit(values);
    } catch (error) {
      console.error("表单验证失败:", error);
    }
  };

  return (
    <Form form={form} layout="vertical">
      <Form.Item
        name="monitorUrl"
        label="插件地址"
        rules={[{ required: true, message: "请输入插件地址" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="accessToken"
        label="Token"
        rules={[{ required: true, message: "请输入Token" }]}
      >
        <Input />
      </Form.Item>
      <Form.Item>
        <Space>
          <Button type="primary" onClick={handleSubmit}>
            提交
          </Button>
          <Button onClick={onCancel}>取消</Button>
        </Space>
      </Form.Item>
    </Form>
  );
};

export default HostMonitorConfigForm;
