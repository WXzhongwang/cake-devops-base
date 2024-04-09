import React, { useEffect, useState } from "react";
import { Drawer, Form, Input, Select, Button, message } from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { Department } from "@/models/app";

const { Option } = Select;

interface CreateAppDrawerProps {
  dispatch: Dispatch;
  open: boolean;
  onClose: () => void;
  userData: API.UserInfo;
  departments: Department[] | [];
}

const CreateAppDrawer: React.FC<CreateAppDrawerProps> = ({
  dispatch,
  open,
  userData,
  onClose,
  departments,
}) => {
  const [form] = Form.useForm();
  const [formattedDepartments, setFormattedDepartments] = useState<
    { label: string; value: string }[]
  >([]);

  const handleCreateApp = (values: any) => {
    // 在这里可以执行创建应用的逻辑
    console.log("创建应用:", values);
    values.owner = userData.userId;
    // 在这里获取选中的部门对象
    const selectedDepartment = departments.find(
      (dep) => dep.value === values.department
    );
    // 如果存在选中的部门对象，将其相应属性添加到values中
    if (selectedDepartment) {
      values.departmentAbbr = selectedDepartment.abbr;
    }
    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "app/createApp",
      payload: values,
      callback: () => {
        message.success("应用创建成功");
      },
    });

    // 关闭抽屉
    onClose();
  };

  useEffect(() => {
    // 当部门列表更新时，格式化并设置Select的选项
    const options = departments?.map((dep: Department) => ({
      value: dep.value,
      label: dep.label,
    }));
    setFormattedDepartments(options);
  }, [departments]);

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
        <Form.Item name="department" label="部门">
          <Select placeholder="请选择部门" allowClear>
            {formattedDepartments?.map((option) => (
              <Option key={option.value} value={option.value}>
                {option.label}
              </Option>
            ))}
          </Select>
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

export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(CreateAppDrawer);
