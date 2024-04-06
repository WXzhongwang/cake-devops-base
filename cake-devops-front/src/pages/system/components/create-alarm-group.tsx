import React, { useEffect } from "react";
import { Form, Input, Button, Select } from "antd";
import { AlarmGroupDTO } from "@/models/alarm-group";
import { WebhookConfig } from "@/models/webhook";
import { Dispatch } from "umi";
import { AppAccountDTO } from "@/models/user";

const { Option } = Select;

interface CreateAlarmGroupFormProps {
  initialValues?: AlarmGroupDTO; // 编辑时的初始值
  onSave: (values: AlarmGroupDTO) => void;
  onCancel: () => void;
  onUpdate: (values: AlarmGroupDTO) => void; // 新增 onUpdate 方法用于更新
  webhooks: WebhookConfig[]; // webhook 列表
  members: AppAccountDTO[]; // 人员列表
}

const CreateAlarmGroupForm: React.FC<CreateAlarmGroupFormProps> = ({
  initialValues,
  onSave,
  onCancel,
  onUpdate,
  webhooks,
  members,
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

  console.log("webhooks", webhooks);
  console.log("members", members);

  return (
    <Form form={form} layout="vertical" onFinish={handleSubmit}>
      <Form.Item
        name="groupName"
        label="告警组名称"
        rules={[{ required: true, message: "请输入告警组名称" }]}
      >
        <Input placeholder="请输入告警组名称" />
      </Form.Item>

      <Form.Item
        name="groupDescription"
        label="告警组描述"
        rules={[{ required: true, message: "请输入告警组描述" }]}
      >
        <Input placeholder="请输入告警组描述" />
      </Form.Item>

      <Form.Item
        name="notifyIdList"
        label="Webhooks"
        initialValue={
          initialValues
            ? initialValues.notifies.map((notify) => notify.notifyId)
            : []
        }
      >
        <Select mode="multiple" placeholder="请选择 Webhooks" allowClear>
          {webhooks?.map((webhook) => (
            <Option key={webhook.id} value={webhook.id}>
              {webhook.webhookName}
            </Option>
          ))}
        </Select>
      </Form.Item>

      <Form.Item
        name="accountIds"
        label="人员列表"
        initialValue={
          initialValues ? initialValues.users.map((user) => user.accountId) : []
        }
      >
        <Select mode="multiple" placeholder="请选择人员" allowClear>
          {members?.map((user) => (
            <Option key={user.id} value={user.id}>
              {user.accountName}
            </Option>
          ))}
        </Select>
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

export default CreateAlarmGroupForm;
