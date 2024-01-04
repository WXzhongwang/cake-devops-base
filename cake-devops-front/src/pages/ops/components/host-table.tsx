// src/components/HostTable.tsx
import React from "react";
import { Table } from "antd";
import { HostModel } from "@/models/host"; // 请根据实际情况导入你的数据模型

interface HostTableProps {
  data: HostModel[];
}

const columns = [
  // 定义你的表格列
];

const HostTable: React.FC<HostTableProps> = ({ data }) => {
  // 使用 Ant Design 的 Table 组件展示详细信息
  return <Table columns={columns} dataSource={data} />;
};

export default HostTable;
