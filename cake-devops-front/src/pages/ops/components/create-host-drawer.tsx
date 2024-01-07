// src/components/AddHostDrawer.tsx
import React from "react";
import { Drawer, Form, Input, Select, Button, TreeSelect } from "antd";
import { HostGroupModel } from "@/models/host";

interface AddHostDrawerProps {
  visible: boolean;
  onClose: () => void;
  onSubmit: (values: any) => void;
  hostGroups: HostGroupModel[];
}

const { TreeNode } = TreeSelect;

const renderTreeNodes = (data: HostGroupModel[]) => {
  return data?.map((item) => (
    <TreeNode value={item.hostGroupId} title={item.name} key={item.hostGroupId}>
      {item.children && item.children.length > 0
        ? renderTreeNodes(item.children)
        : null}
    </TreeNode>
  ));
};

const CreateHostDrawer: React.FC<AddHostDrawerProps> = ({
  visible,
  onClose,
  onSubmit,
  hostGroups,
}) => {
  const [form] = Form.useForm();

  return (
    <Drawer
      open={visible}
      title="新增主机"
      onClose={onClose}
      width={600}
      destroyOnClose
    >
      <Form form={form} layout="vertical">
        <Form.Item
          name="name"
          label="实例名称"
          rules={[{ required: true, message: "请输入实例名称" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="hostName"
          label="主机名称"
          rules={[{ required: true, message: "请输入主机名称" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="serverAddr"
          label="服务地址"
          rules={[{ required: true, message: "请输入服务地址" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="username"
          label="用户名"
          rules={[{ required: true, message: "请输入用户名" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="pkey"
          label="pkey"
          rules={[{ required: true, message: "请输入用户名" }]}
        >
          <Input.TextArea />
        </Form.Item>
        <Form.Item
          name="hostGroupIds"
          label="机器组"
          rules={[{ required: true, message: "请选择机器组" }]}
        >
          {/* <TreeSelect
            showSearch
            style={{ width: "100%" }}
            dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
            placeholder="请选择机器组"
            allowClear
            treeDefaultExpandAll
            treeCheckable
            showCheckedStrategy={TreeSelect.SHOW_CHILD}
            treeNodeFilterProp="title"
            multiple
          >
            {renderTreeNodes(hostGroups)}
          </TreeSelect> */}
          <TreeSelect
            showSearch
            style={{ width: "100%" }}
            dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
            placeholder="请选择机器组"
            allowClear
            multiple
            treeDefaultExpandAll
            showCheckedStrategy={TreeSelect.SHOW_ALL}
            treeCheckStrictly
          >
            {renderTreeNodes(hostGroups)}
          </TreeSelect>
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            onClick={() => {
              form.validateFields().then((values) => {
                onSubmit(values);
                form.resetFields();
              });
            }}
          >
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateHostDrawer;
