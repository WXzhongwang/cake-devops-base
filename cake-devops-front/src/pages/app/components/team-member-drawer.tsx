// TeamMembersDrawer.tsx

import React from "react";
import { Drawer, Table, Tag, Space, Button, Popconfirm } from "antd";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import { AppMemberDTO, AppAccountDTO } from "@/models/app";

interface TeamMembersDrawerProps {
  onClose: () => void;
  open: boolean;
  // 其他需要的 props
  appMembers: {
    total: number;
    list: AppMemberDTO[];
  };
}

const TeamMembersDrawer: React.FC<TeamMembersDrawerProps> = ({
  onClose,
  open,
  appMembers,
}) => {
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
            <Tag key={role}>{role}</Tag>
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

  const handleEditRole = (record: any) => {
    // 处理修改角色的逻辑
    console.log(`编辑角色：${record.name}`);
  };

  const handleDeleteMember = (record: any) => {
    // 处理删除人员的逻辑
    console.log(`删除人员：${record.name}`);
  };

  return (
    <Drawer
      title="项目成员"
      width={600}
      onClose={onClose}
      open={open}
      bodyStyle={{ paddingBottom: 80 }}
    >
      <Table
        dataSource={appMembers.list}
        columns={columns}
        rowKey="memberId"
        pagination={false}
      />
    </Drawer>
  );
};

export default TeamMembersDrawer;
