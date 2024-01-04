// components/create-app-drawer.tsx
import { Form, Input, Button, Drawer } from "antd";
import { HostInfo } from "@/models/host";

interface CreateHostDrawerProps {
  open: boolean;
  onClose: () => void;
  departments: { label: string; value: string }[];
  onCreate: (values: HostInfo) => void;
}

const CreateHostDrawer: React.FC<CreateHostDrawerProps> = ({
  open,
  onClose,
  departments,
  onCreate,
}) => {
  const [form] = Form.useForm();

  const handleFinish = (values: HostInfo) => {
    onCreate(values);
    form.resetFields();
  };

  return (
    <Drawer
      title="新增主机"
      placement="right"
      onClose={onClose}
      visible={open}
      width={400}
    >
      <Form
        form={form}
        onFinish={handleFinish}
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 18 }}
      >
        {/* 在此添加与主机信息相关的表单项 */}
        <Form.Item
          name="name"
          label="名称"
          rules={[{ required: true, message: "请输入主机名称" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="hostName"
          label="主机名"
          rules={[{ required: true, message: "请输入主机名" }]}
        >
          <Input />
        </Form.Item>
        {/* ... 其他主机信息的表单项 */}
        <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateHostDrawer;
