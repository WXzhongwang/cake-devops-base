import React from "react";
import { Drawer, Form, Input, Select, Button } from "antd";
import { connect, Dispatch } from "umi";

const { Option } = Select;

interface CreateAppDrawerProps {
  dispatch: Dispatch;
  open: boolean;
  onClose: () => void;
}

const CreateAppDrawer: React.FC<CreateAppDrawerProps> = ({
  dispatch,
  open,
  onClose,
}) => {
  const [form] = Form.useForm();

  const handleCreateApp = (values: any) => {
    // 在这里可以执行创建应用的逻辑
    console.log("创建应用:", values);

    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "app/createApp",
      payload: values,
    });

    // 关闭抽屉
    onClose();
  };

  return (
    <Drawer
      title="添加应用"
      width={400}
      onClose={onClose}
      open={open}
      destroyOnClose
    >
      <Form form={form} onFinish={handleCreateApp} layout="vertical">
        <Form.Item
          name="appName"
          label="应用名称"
          rules={[{ required: true, message: "请输入应用名称" }]}
        >
          <Input placeholder="请输入应用名称" />
        </Form.Item>
        <Form.Item
          name="repo"
          label="仓库地址"
          rules={[{ required: true, message: "请输入仓库地址" }]}
        >
          <Input placeholder="请输入仓库地址" />
        </Form.Item>
        <Form.Item
          name="defaultBranch"
          label="默认分支"
          rules={[{ required: true, message: "请输入默认分支" }]}
        >
          <Input placeholder="请输入默认分支" />
        </Form.Item>
        <Form.Item
          name="developMode"
          label="开发模式"
          rules={[{ required: true, message: "请选择开发模式" }]}
        >
          <Select placeholder="请选择开发模式">
            <Option value="Freedom">自由模式</Option>
            <Option value="GitFlow">标准模式</Option>
            <Option value="Branch">分支模式</Option>
            {/* 其他开发模式选项 */}
          </Select>
        </Form.Item>
        <Form.Item
          name="language"
          label="开发语言"
          rules={[{ required: true, message: "请选择开发语言" }]}
        >
          <Select placeholder="请选择开发语言">
            <Option value="JAVA">Java</Option>
            <Option value="PYTHON">Python</Option>
            <Option value="GO">GO</Option>
            {/* 其他语言选项 */}
          </Select>
        </Form.Item>
        <Form.Item
          name="healthCheck"
          label="健康检查地址"
          rules={[{ required: true, message: "请输入健康检查地址" }]}
        >
          <Input placeholder="请输入健康检查地址" />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default connect()(CreateAppDrawer);
