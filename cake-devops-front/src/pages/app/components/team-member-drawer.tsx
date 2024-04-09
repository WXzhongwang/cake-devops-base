// TeamMembersDrawer.tsx

import React, { useEffect, useState } from "react";
import { Dispatch, connect, useParams } from "umi";
import {
  Drawer,
  Table,
  Tag,
  Space,
  Button,
  Popconfirm,
  Modal,
  Form,
  Select,
  message,
  Input,
} from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import { AppMemberDTO } from "@/models/app";
import { AppAccountDTO } from "@/models/user";

const { Option } = Select;

interface TeamMembersDrawerProps {
  dispatch: Dispatch;
  onClose: () => void;
  open: boolean;
  // 其他需要的 props
  appMembers: {
    total: number;
    list: AppMemberDTO[];
  };
  userList: AppAccountDTO[];
}

const mapRoleToChinese = (role: string) => {
  switch (role) {
    case "OWNER":
      return "拥有者";
    case "DEVELOPER":
      return "开发";
    case "TESTER":
      return "测试";
    case "OPERATOR":
      return "运维";
    case "ARCHITECT":
      return "架构师";
    case "REPORTER":
      return "告警接收";
    case "CHECKER":
      return "部署审批";
    default:
      return role;
  }
};

const TeamMembersDrawer: React.FC<TeamMembersDrawerProps> = ({
  dispatch,
  onClose,
  open,
  appMembers,
  userList,
}) => {
  const [editRoleModalVisible, setEditRoleModalVisible] = useState(false);
  const [addMemberModalVisible, setAddMemberModalVisible] = useState(false); // 新增人员模态窗状态
  const { id } = useParams();

  const [editRoleForm] = Form.useForm();
  const [addMemberForm] = Form.useForm();

  useEffect(() => {
    // 在组件挂载时获取用户列表
    dispatch({
      type: "user/queryMembers",
      payload: {},
    });
  }, []);

  console.log("appMembers", appMembers);
  const columns = [
    {
      title: "人员名称",
      dataIndex: "name",
      render: (_: string, record: AppMemberDTO) =>
        record.accountDTO.accountName,
    },
    {
      title: "人员角色",
      dataIndex: "roles",
      key: "roles",
      render: (roles: string[]) => (
        <>
          {roles.map((role) => (
            <Tag key={role}>{mapRoleToChinese(role)}</Tag>
          ))}
        </>
      ),
    },
    {
      title: "操作",
      key: "action",
      width: 100, // 设置列宽
      render: (text: string, record: any) => (
        <Space size="middle">
          <Button
            type="link"
            icon={<EditOutlined />}
            onClick={() => handleEditRole(record)}
          />
          <Popconfirm
            title="确定删除该人员吗？"
            onConfirm={() => handleDeleteMember(record)}
            okText="是"
            cancelText="否"
          >
            <Button type="link" icon={<DeleteOutlined />} />
          </Popconfirm>
        </Space>
      ),
    },
  ];

  // 新增人员模态窗显示控制方法
  const showAddMemberModal = () => {
    setAddMemberModalVisible(true);
  };

  const [selectedMember, setSelectedMember] = useState<AppMemberDTO | null>(
    null
  );

  const showEditRoleModal = (record: AppMemberDTO) => {
    setSelectedMember(record);
    setEditRoleModalVisible(true);
    editRoleForm.setFieldsValue({
      roles: record.roles,
    });
  };

  const handleEditRole = (record: AppMemberDTO) => {
    showEditRoleModal(record);
  };

  const handleDeleteMember = (record: any) => {
    // 处理删除人员的逻辑
    console.log(`删除人员：${record.name}`);
    dispatch({
      type: "app/deleteMember",
      payload: { memberId: record?.memberId },
      callback: () => {
        message.success("人员删除成功");
      },
    });
    onClose();
  };

  const handleUpdateRole = (values: any) => {
    editRoleForm.validateFields().then((values) => {
      dispatch({
        type: "app/updateMember",
        payload: { ...values, memberId: selectedMember?.memberId },
        callback: () => {
          message.success("更新成功");
        },
      });
      setEditRoleModalVisible(false);
      editRoleForm.resetFields();
      onClose();
    });
  };

  // 新增人员方法
  const handleAddMember = (values: any) => {
    addMemberForm.validateFields().then((values) => {
      dispatch({
        type: "app/addMember",
        payload: { ...values, appId: id },
        callback: () => {
          message.success("添加成功");
        },
      });

      setAddMemberModalVisible(false);
      addMemberForm.resetFields();
      onClose();
    });
  };

  return (
    <Drawer
      title="项目成员"
      width={600}
      onClose={onClose}
      open={open}
      bodyStyle={{ paddingBottom: 80 }}
      extra={
        <Button type="primary" onClick={showAddMemberModal}>
          添加成员
        </Button>
      }
    >
      <Table
        dataSource={appMembers.list}
        columns={columns}
        rowKey="memberId"
        pagination={false}
      />

      <Modal
        title="编辑角色"
        open={editRoleModalVisible}
        onCancel={() => setEditRoleModalVisible(false)}
        onOk={handleUpdateRole}
      >
        <Form form={editRoleForm} layout="vertical">
          <Form.Item
            label="人员角色"
            name="roles"
            rules={[
              {
                required: true,
                message: "请选择人员角色",
              },
            ]}
          >
            <Select style={{ width: "100%" }} mode="multiple">
              <Option value="OWNER">拥有者</Option>
              <Option value="DEVELOPER">开发</Option>
              <Option value="TESTER">测试</Option>
              <Option value="OPERATOR">运维</Option>
              <Option value="ARCHITECT">架构师</Option>
              <Option value="REPORTER">告警接收</Option>
              <Option value="CHECKER">部署审批</Option>
            </Select>
          </Form.Item>
        </Form>
      </Modal>

      <Modal
        title="添加人员"
        open={addMemberModalVisible}
        onCancel={() => setAddMemberModalVisible(false)}
        onOk={addMemberForm.submit}
      >
        <Form form={addMemberForm} layout="vertical" onFinish={handleAddMember}>
          <Form.Item
            label="项目成员"
            name="accountId"
            rules={[
              {
                required: true,
                message: "请选择人员",
              },
            ]}
          >
            <Select style={{ width: "100%" }}>
              {userList.map((user: AppAccountDTO) => (
                <Option key={user.id} value={user.id}>
                  {user.accountName}
                </Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item
            label="人员角色"
            name="roles"
            rules={[
              {
                required: true,
                message: "请选择人员角色",
              },
            ]}
          >
            <Select style={{ width: "100%" }} mode="multiple">
              <Option value="OWNER">拥有者</Option>
              <Option value="DEVELOPER">开发</Option>
              <Option value="TESTER">测试</Option>
              <Option value="OPERATOR">运维</Option>
              <Option value="ARCHITECT">架构师</Option>
              <Option value="REPORTER">告警接收</Option>
              <Option value="CHECKER">部署审批</Option>
            </Select>
          </Form.Item>
        </Form>
      </Modal>
    </Drawer>
  );
};

export default connect(({ user }) => {
  return {
    userList: user.members,
  };
})(TeamMembersDrawer);
