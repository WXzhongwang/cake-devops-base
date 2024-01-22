// src/components/HostTable.tsx
import React, { useEffect, useState } from "react";
import { history } from "umi";
import { Table, Space, Form, Button, Input, Tag } from "antd";
import { HostModel } from "@/models/host"; // 请根据实际情况导入你的数据模型

interface HostTableProps {
  data: HostModel[];
  total: number;
  pagination: {
    pageNo: number;
    pageSize: number;
  };
  onChangeHandle: (pageNo: number, pageSize: number) => void;
  onSearch: (filters: { name: string; hostName: string }) => void;
}

const HostTable: React.FC<HostTableProps> = ({
  data,
  total,
  pagination,
  onChangeHandle,
}) => {
  const columns = [
    // 定义你的表格列
    {
      title: "实例名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "主机名称",
      dataIndex: "hostName",
      key: "hostName",
    },
    {
      title: "服务地址",
      dataIndex: "serverAddr",
      key: "serverAddr",
    },
    {
      title: "端口",
      dataIndex: "port",
      key: "port",
    },
    {
      title: "用户名",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: HostModel) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.status === "0" ? "success" : "error";
        const statusText = record.status === "0" ? "正常" : "停用";

        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },

    // 其他列根据需要添加
    {
      title: "操作",
      key: "action",
      render: (text: any, record: HostModel) => (
        <Space size="middle">
          <Button onClick={() => handleViewClick(record.hostId)}>查看</Button>
        </Space>
      ),
    },
  ];

  // 处理点击查看操作
  const handleViewClick = (hostId: string) => {
    // 构造主机详情页面的路由路径
    const hostDetailPath = `/host/detail/${hostId}`;

    // 跳转到主机详情页面
    history.push(hostDetailPath);
  };

  return (
    <Table
      columns={columns}
      dataSource={data}
      rowKey={"hostId"}
      pagination={{
        total: total,
        current: pagination.pageNo,
        pageSize: pagination.pageSize,
        onChange: onChangeHandle,
      }}
    />
  );
};

export default HostTable;
