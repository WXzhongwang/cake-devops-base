import React from "react";
import { Form, Input, Button } from "antd";
import { HostEnvironmentVariable } from "@/models/host-env";

const { TextArea } = Input;

interface CreateHostEnvironmentVariableFormProps {
  initialValues?: HostEnvironmentVariable; // 编辑时的初始值
  onSave: (values: HostEnvironmentVariable) => void;
  onCancel: () => void;
  onUpdate: (values: HostEnvironmentVariable) => void;
}

const CreateHostEnvironmentVariableForm: React.FC<
  CreateHostEnvironmentVariableFormProps
> = ({ initialValues, onSave, onCancel, onUpdate }) => {
  const [form] = Form.useForm();

  // 设置表单的初始值
  React.useEffect(() => {
    form.setFieldsValue(initialValues);
  }, [form, initialValues]);

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (initialValues) {
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
        name="attrKey"
        label="Key"
        rules={[{ required: true, message: "请输入Key" }]}
      >
        <Input placeholder="请输入Key" />
      </Form.Item>

      <Form.Item
        name="attrValue"
        label="Value"
        rules={[{ required: true, message: "请输入Value" }]}
      >
        <Input placeholder="请输入Value" />
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

export default CreateHostEnvironmentVariableForm;
