// src/components/HostAccountDrawer.tsx

import React from "react";
import { Drawer, Form, Select, Button } from "antd";
import { ServerAccount } from "@/models/host";

const { Option } = Select;

interface ServerAccountDrawerProps {
  visible: boolean;
  servers: string[];
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
      width={400}
      onClose={onClose}
      open={visible}
      destroyOnClose
    >
      <Form
        form={form}
        onFinish={handleSave}
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 18 }}
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
            {servers.map((server, index) => (
              <Option key={index} value={server}>
                {server}
              </Option>
            ))}
          </Select>
        </Form.Item>
        {/* 其他表单项 */}
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
