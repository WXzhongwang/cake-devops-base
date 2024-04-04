import React from "react";
import { Form, Input, Button, Select, Upload, message } from "antd";
import { UploadOutlined } from "@ant-design/icons";
import { ServerKey } from "@/models/host";

const { Option } = Select;

interface CreateServerKeyFormProps {
  initialValues?: ServerKey; // 编辑时的初始值
  onSave: (values: ServerKey, fileBase64: string) => void;
  onCancel: () => void;
}

const CreateServerKeyForm: React.FC<CreateServerKeyFormProps> = ({
  initialValues,
  onSave,
  onCancel,
}) => {
  const [form] = Form.useForm();
  const [fileList, setFileList] = React.useState<any[]>([]);

  // 设置表单的初始值
  React.useEffect(() => {
    form.setFieldsValue(initialValues);
  }, [form, initialValues]);

  const handleFileChange = (info: any) => {
    let fileList = [...info.fileList];
    fileList = fileList.slice(-1); // 限制只能上传一个文件
    fileList = fileList.map((file: any) => {
      if (file.response) {
        file.url = file.response.url;
      }
      return file;
    });
    setFileList(fileList);
  };

  const handleSubmit = async () => {
    try {
      const values = await form.validateFields();
      if (fileList.length === 0) {
        message.error("请选择要上传的文件");
        return;
      }
      const file = fileList[0];
      const reader = new FileReader();
      reader.readAsDataURL(file.originFileObj);
      reader.onloadend = () => {
        const fileBase64 = reader.result as string;
        const pureBase64 = fileBase64.split(",")[1];
        onSave(values, pureBase64);
      };
    } catch (error) {
      console.error("表单验证失败:", error);
    }
  };

  return (
    <Form form={form} layout="vertical">
      <Form.Item
        name="displayName"
        label="显示名称"
        rules={[{ required: true, message: "请输入显示名称" }]}
      >
        <Input placeholder="请输入显示名称" />
      </Form.Item>

      <Form.Item
        name="accountType"
        label="账户类型"
        rules={[{ required: true, message: "请选择账户类型" }]}
      >
        <Select placeholder="请选择账户类型">
          <Option value={1}>管理员</Option>
          <Option value={2}>普通账户</Option>
        </Select>
      </Form.Item>
      <Form.Item
        label="协议"
        name="protocol"
        initialValue="SSH"
        rules={[{ required: true, message: "请选择协议" }]}
      >
        <Select placeholder="请选择协议" disabled>
          <Option value="SSH">SSH</Option>
        </Select>
      </Form.Item>

      <Form.Item
        name="active"
        label="活跃状态"
        rules={[{ required: true, message: "请选择活跃状态" }]}
      >
        <Select placeholder="请选择活跃状态">
          <Option value="1">活跃</Option>
          <Option value="0">不活跃</Option>
        </Select>
      </Form.Item>

      <Form.Item
        name="credentialContent"
        label="凭据内容"
        rules={[{ required: true, message: "请输入凭据内容" }]}
      >
        <Input.TextArea placeholder="请输入凭据内容" />
      </Form.Item>

      <Form.Item
        name="passphrase"
        label="密码短语"
        rules={[{ required: true, message: "请输入密码短语" }]}
      >
        <Input.Password placeholder="请输入密码短语" />
      </Form.Item>

      <Form.Item
        name="file"
        label="上传文件"
        rules={[{ required: true, message: "请选择要上传的文件" }]}
      >
        <Upload
          fileList={fileList}
          onChange={handleFileChange}
          beforeUpload={() => false}
        >
          <Button icon={<UploadOutlined />}>选择文件</Button>
        </Upload>
      </Form.Item>

      <Form.Item>
        <Button type="primary" onClick={handleSubmit}>
          {initialValues ? "更新" : "保存"}
        </Button>
        <Button onClick={onCancel} style={{ marginLeft: 8 }}>
          取消
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreateServerKeyForm;
