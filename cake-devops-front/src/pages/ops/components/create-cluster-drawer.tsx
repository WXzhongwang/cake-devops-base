import React, { useState } from "react";
import { Form, Input, Button, Select, message, Tag, Space, Drawer } from "antd";
import { CreateClusterPayload, ConnectClusterPayload } from "@/models/cluster";

interface CreateClusterFormProps {
  handleCreateClusterDrawer: () => void;
  onConnect: (values: ConnectClusterPayload) => void;
  createClusterVisible: boolean;
  onSubmit: (values: CreateClusterPayload) => void;
}

const { Option } = Select;

const CreateClusterDrawer: React.FC<CreateClusterFormProps> = ({
  onSubmit,
  onConnect,
  createClusterVisible,
  handleCreateClusterDrawer,
}) => {
  const [form] = Form.useForm();
  const [tags, setTags] = useState<string[]>([]);
  const [isConnectionTested, setConnectionTested] = useState(false);

  const handleTestConnection = async () => {
    try {
      form
        .validateFields(["connectString", "token", "clusterType"])
        .then((values) => {
          onConnect(values as ConnectClusterPayload);
          setConnectionTested(true);
          message.success("连接测试成功");
        });
    } catch (error) {
      message.error("连接测试失败，请检查输入信息");
    }
  };

  const handleSubmit = () => {
    form.validateFields().then((values) => {
      onSubmit(values as CreateClusterPayload);
      form.resetFields();
      setConnectionTested(false);
    });
  };

  const handleTagClose = (removedTag: string) => {
    const newTags = tags.filter((tag) => tag !== removedTag);
    setTags(newTags);
  };

  const handleTagAdd = () => {
    const newTag = form.getFieldValue("newTag");
    if (newTag && !tags.includes(newTag)) {
      setTags([...tags, newTag]);
      form.setFieldsValue({ newTag: "" }); // 清空输入框
    }
  };

  return (
    <Drawer
      title="新增集群"
      width={400}
      onClose={handleCreateClusterDrawer}
      open={createClusterVisible}
    >
      <Form form={form} layout="vertical">
        <Form.Item
          name="name"
          label="集群名称"
          rules={[{ required: true, message: "请输入集群名称" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="connectString"
          label="连接字符串"
          rules={[{ required: true, message: "请输入连接字符串" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="token"
          label="令牌"
          rules={[{ required: true, message: "请输入令牌" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="clusterType"
          label="集群类型"
          rules={[{ required: true, message: "请选择集群类型" }]}
        >
          <Select placeholder="请选择集群类型">
            <Option value="k8s">K8S</Option>
            <Option value="aliyun">阿里云</Option>
            <Option value="tencent">腾讯云</Option>
          </Select>
        </Form.Item>
        <Form.Item label="标签">
          <Space>
            {tags.map((tag) => (
              <Tag key={tag} closable onClose={() => handleTagClose(tag)}>
                {tag}
              </Tag>
            ))}
          </Space>
          <Input
            style={{ width: "100px", marginLeft: "8px" }}
            value={form.getFieldValue("newTag")}
            onChange={(e) => form.setFieldsValue({ newTag: e.target.value })}
            onPressEnter={handleTagAdd}
          />
          <Button type="primary" onClick={handleTagAdd}>
            添加标签
          </Button>
        </Form.Item>
        <Form.Item>
          <Button type="primary" onClick={handleTestConnection}>
            测试连接
          </Button>
          <Button
            type="primary"
            onClick={handleSubmit}
            disabled={!isConnectionTested}
          >
            添加集群
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateClusterDrawer;
