import React from "react";
import { Form, Input, Button, Select } from "antd";
import { ScriptTemplateDTO } from "@/models/script-template";
import TextArea from "antd/lib/input/TextArea";

const { Option } = Select;

interface CreateScriptFormProps {
  initialValues?: ScriptTemplateDTO; // 编辑时的初始值
  onSave: (values: ScriptTemplateDTO) => void;
  onCancel: () => void;
  onUpdate: (values: ScriptTemplateDTO) => void; // 新增 onUpdate 方法用于更新
}

const CreateScriptForm: React.FC<CreateScriptFormProps> = ({
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
        name="templateName"
        label="脚本名称"
        rules={[{ required: true, message: "请输入脚本名称" }]}
      >
        <Input placeholder="请输入脚本名称" />
      </Form.Item>
      <Form.Item
        name="templateValue"
        label="脚本内容"
        rules={[{ required: true, message: "请输入脚本内容" }]}
      >
        <TextArea rows={4} placeholder="请输入脚本内容" />
      </Form.Item>
      <Form.Item
        name="description"
        label="脚本描述"
        rules={[{ required: true, message: "请输入脚本描述" }]}
      >
        <Input placeholder="请输入脚本描述" />
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

export default CreateScriptForm;
