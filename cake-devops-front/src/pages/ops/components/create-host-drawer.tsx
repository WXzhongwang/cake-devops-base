import React, { useState } from "react";
import { Drawer, Form, Input, Select, Button, TreeSelect, Card } from "antd";
import { HostGroupModel } from "@/models/host";

const { Option } = Select;

interface AddHostDrawerProps {
  visible: boolean;
  onClose: () => void;
  onSubmit: (values: any) => void;
  hostGroups: HostGroupModel[];
  machineProxies: string[];
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
  machineProxies,
}) => {
  const [form] = Form.useForm();
  const [authMode, setAuthMode] = useState<string>(""); // 认证模式，默认为空

  return (
    <Drawer
      open={visible}
      title="新增主机"
      onClose={onClose}
      width={600}
      destroyOnClose
    >
      <Form form={form} layout="vertical">
        {/* 基础信息 */}
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
          name="hostGroupIds"
          label="机器组"
          rules={[{ required: true, message: "请选择机器组" }]}
        >
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
            treeNodeFilterProp="hostGroupId"
          >
            {renderTreeNodes(hostGroups)}
          </TreeSelect>
        </Form.Item>

        <Form.Item
          name="serverAddr"
          label="服务地址"
          rules={[{ required: true, message: "请输入服务地址" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="port"
          label="端口"
          rules={[{ required: true, message: "请输入端口" }]}
        >
          <Input type="number" />
        </Form.Item>
        {/* SSH 配置 */}
        {/* 选择机器代理 */}
        <Form.Item label="机器代理" name="proxyId">
          <Select placeholder="请选择机器代理">
            {machineProxies.map((proxy) => (
              <Option key={proxy} value={proxy}>
                {proxy}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          label="认证模式"
          name="authMode"
          rules={[{ required: true, message: "请选择认证模式" }]}
        >
          <Select
            placeholder="请选择认证模式"
            onChange={(value) => setAuthMode(value)}
          >
            <Option value="password">基础账号密码认证</Option>
            <Option value="privateKey">私钥认证</Option>
          </Select>
        </Form.Item>

        {authMode === "password" && (
          <>
            <Form.Item
              name="username"
              label="用户名"
              rules={[{ required: true, message: "请输入用户名" }]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="password"
              label="密码"
              rules={[{ required: true, message: "请输入用户密码" }]}
            >
              <Input.Password />
            </Form.Item>
          </>
        )}
        {authMode === "privateKey" && (
          <>
            <Form.Item
              name="keyId"
              label="私钥"
              rules={[{ required: true, message: "请选择私钥" }]}
            >
              <Input />
            </Form.Item>
          </>
        )}
        {/* 提交按钮 */}
        <Form.Item>
          <Button
            type="primary"
            onClick={() => {
              form.validateFields().then((values) => {
                const hostGroupIds = values.hostGroupIds.map(
                  (item: { value: string }) => item.value
                );
                // 构建仅包含 value 的数组
                const processedValues = { ...values, hostGroupIds };
                onSubmit(processedValues);
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
