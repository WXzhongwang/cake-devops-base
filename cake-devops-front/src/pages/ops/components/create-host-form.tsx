import React, { useState, useEffect } from "react";
import { Drawer, Form, Input, Select, Button, TreeSelect } from "antd";
import { HostGroupModel, ServerKey } from "@/models/host";
import { ProxyModel } from "@/models/proxy";
import serverKey from "../server-key";

const { Option } = Select;

interface CreateHostFormProps {
  initialValues?: any;
  hostGroups: HostGroupModel[];
  machineProxies: ProxyModel[];
  serverKeys: ServerKey[];
  onSubmit: (values: any) => void;
  onCancel: () => void;
  onUpdate: (values: any) => void;
}

const { TreeNode } = TreeSelect;

const CreateHostForm: React.FC<CreateHostFormProps> = ({
  initialValues,
  hostGroups,
  machineProxies,
  serverKeys,
  onSubmit,
  onCancel,
  onUpdate,
}) => {
  const [form] = Form.useForm();
  const [authMode, setAuthMode] = useState<number>(1);

  useEffect(() => {
    if (initialValues) {
      form.setFieldsValue(initialValues);
      setAuthMode(initialValues.authMode);
    }
  }, [initialValues, form]);

  const renderTreeNodes = (data: HostGroupModel[]) => {
    return data?.map((item) => (
      <TreeNode
        value={item.hostGroupId}
        title={item.name}
        key={item.hostGroupId}
      >
        {item.children && item.children.length > 0
          ? renderTreeNodes(item.children)
          : null}
      </TreeNode>
    ));
  };

  return (
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
      <Form.Item label="机器代理" name="proxyId">
        <Select placeholder="请选择机器代理">
          {machineProxies.map((proxy) => (
            <Option key={proxy.id} value={proxy.id}>
              {proxy.proxyHost + "【" + proxy.proxyUsername + "】"}
            </Option>
          ))}
        </Select>
      </Form.Item>
      <Form.Item
        name="username"
        label="用户名"
        rules={[{ required: true, message: "请输入用户名" }]}
      >
        <Input />
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
          <Option value={1}>基础账号密码认证</Option>
          <Option value={2}>私钥认证</Option>
        </Select>
      </Form.Item>
      {authMode === 1 && (
        <>
          <Form.Item
            name="pwd"
            label="密码"
            // rules={[{ required: true, message: "请输入用户密码" }]}
          >
            <Input.Password />
          </Form.Item>
        </>
      )}
      {authMode === 2 && (
        <>
          <Form.Item
            name="keyId"
            label="私钥"
            // rules={[{ required: true, message: "请选择私钥" }]}
          >
            <Select placeholder="请选择私钥">
              {serverKeys.map((serverKey) => (
                <Option key={serverKey.id} value={serverKey.id}>
                  {serverKey.displayName + "【" + serverKey.protocol + "】"}
                </Option>
              ))}
            </Select>
          </Form.Item>
        </>
      )}
      <Form.Item>
        <Button
          type="primary"
          onClick={() => {
            form.validateFields().then((values) => {
              const hostGroupIds = values.hostGroupIds.map(
                (item: { value: string }) => item.value
              );
              const processedValues = { ...values, hostGroupIds };
              if (initialValues) {
                onUpdate(processedValues);
              } else {
                console.log("submit", processedValues);
                onSubmit(processedValues);
              }
              form.resetFields();
            });
          }}
        >
          {initialValues ? "保存" : "提交"}
        </Button>
        <Button onClick={onCancel} style={{ marginLeft: 8 }}>
          取消
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreateHostForm;
