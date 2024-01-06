// src/components/HostTable.tsx
import React, { useEffect, useState } from "react";
import { Table, Space, Form, Button, Input } from "antd";
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
      title: "主机名",
      dataIndex: "hostName",
      key: "hostName",
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
    // 其他列根据需要添加
    {
      title: "操作",
      key: "action",
      render: (text: any, record: HostModel) => (
        <Space size="middle">
          {/* 根据需要添加其他操作，比如查看、编辑、删除等 */}
          {/* <a onClick={() => handleView(record)}>查看</a> */}
        </Space>
      ),
    },
  ];

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
