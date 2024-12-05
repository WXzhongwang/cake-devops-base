import React, { useEffect, useState } from "react";
import {
  Row,
  Col,
  Button,
  Card,
  Form,
  Input,
  Space,
  Table,
  Drawer,
  Tag,
} from "antd";
import { connect, Dispatch } from "umi";
import { PageContainer } from "@ant-design/pro-components";
import { UserEventLogDTO } from "@/models/logs";

interface ClusterListProps {
  dispatch: Dispatch;
}

const LogPage: React.FC<ClusterListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [logs, setLogs] = useState<UserEventLogDTO[]>();
  const [filters, setFilters] = useState({
    userId: "",
    startDate: null,
    endDate: null,
  }); // 初始化筛选条件为空对象
  const [form] = Form.useForm();

  const queryLogs = () => {
    dispatch({
      type: "logs/queryLogs",
      payload: {},
    });
  };

  useEffect(() => {
    queryLogs();
  }, []);
  const columns = [
    {
      title: "用户名",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "事件分类",
      dataIndex: "envClassifyName",
      key: "envClassifyName",
    },
    {
      title: "事件类型",
      dataIndex: "envTypeName",
      key: "envTypeName",
    },
    {
      title: "状态",
      dataIndex: "execResult",
      key: "execResult",
      render: (text: any, record: UserEventLogDTO) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.execResult === 1 ? "success" : "error";
        const statusText = record.execResult === 2 ? "正常" : "停用";
        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },
    {
      title: "操作",
      key: "actions",
      render: (text: any, record: UserEventLogDTO) => (
        <Space size="middle"></Space>
      ),
    },
  ];

  return (
    <PageContainer title="系统日志">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Table
            columns={columns}
            dataSource={logs}
            rowKey="id"
            pagination={{ pageSize: 10 }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};
export default connect(({}) => ({}))(LogPage);
