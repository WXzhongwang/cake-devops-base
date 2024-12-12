import React, { useEffect, useState } from "react";
import {
  Button,
  Card,
  DatePicker,
  Form,
  Input,
  Select,
  Space,
  Table,
  Tag,
} from "antd";
import { connect, Dispatch } from "umi";
import { PageContainer } from "@ant-design/pro-components";
import { UserEventLogDTO } from "@/models/logs";
import moment from "moment";
import dayjs from "dayjs";
import { API } from "typings";

const { RangePicker } = DatePicker;
const { Option } = Select;

interface ClusterListProps {
  dispatch: Dispatch;
}

const LogPage: React.FC<ClusterListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [logs, setLogs] = useState<API.Page<UserEventLogDTO>>();
  const [eventClassifyList, setEventClassifyList] =
    useState<Record<string, string>>();
  const [eventTypeList, setEventTypeList] = useState<Record<string, string>>();
  const [filters, setFilters] = useState({
    userName: "",
    eventClassify: null,
    eventType: null,
    startDate: null,
    endDate: null,
  }); // 初始化筛选条件为空对象
  const [form] = Form.useForm();

  const queryLogs = () => {
    dispatch({
      type: "logs/queryLogs",
      payload: {
        ...filters,
        ...pagination,
      },
      callback: (res: API.Page<UserEventLogDTO>) => {
        setLogs(res);
      },
    });
  };

  const getEventClassify = () => {
    dispatch({
      type: "logs/getEventClassify",

      callback: (res: Record<string, string>) => {
        setEventClassifyList(res);
      },
    });
  };

  const getEventType = (classify: number) => {
    dispatch({
      type: "logs/getEventType",
      payload: { classify: classify },
      callback: (res: Record<string, string>) => {
        setEventTypeList(res);
      },
    });
  };

  useEffect(() => {
    getEventClassify();
  }, []);

  useEffect(() => {
    queryLogs();
  }, [filters, pagination]);

  const columns = [
    {
      title: "用户名",
      dataIndex: "username",
      key: "userName",
    },
    {
      title: "事件分类",
      dataIndex: "eventClassifyName",
      key: "eventClassifyName",
    },
    {
      title: "事件类型",
      dataIndex: "eventTypeName",
      key: "eventTypeName",
    },
    {
      title: "日志时间",
      key: "gmtCreate",
      render: (text: string, record: UserEventLogDTO) => (
        <>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "操作状态",
      dataIndex: "execResult",
      key: "execResult",
      render: (text: any, record: UserEventLogDTO) => {
        const tagColor = record.execResult === 1 ? "success" : "error";
        const statusText = record.execResult === 1 ? "成功" : "失败";
        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },
    {
      title: "操作",
      key: "actions",
      render: (text: any, record: UserEventLogDTO) => (
        <Space size="middle">查看</Space>
      ),
    },
  ];

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const onFinish = (values: any) => {
    handleFilterChange(values);
  };

  const handleFilterChange = (changedValues: any) => {
    const { timeRange, ...otherValues } = changedValues;
    if (timeRange && timeRange.length === 2) {
      const [startDate, endDate] = timeRange;
      setFilters((prevFilters) => ({
        ...prevFilters,
        startDate: startDate
          ? startDate.format("YYYY-MM-DD HH:mm:ss")
          : undefined,
        endDate: endDate ? endDate.format("YYYY-MM-DD HH:mm:ss") : undefined,
        ...otherValues,
      }));
    } else {
      setFilters((prevFilters) => ({
        ...prevFilters,
        startDate: undefined,
        endDate: undefined,
        ...otherValues,
      }));
    }
  };

  return (
    <PageContainer title="系统日志">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form form={form} layout="inline" onFinish={onFinish}>
            <Form.Item name="userName" label="请输入用户名称">
              <Input placeholder="请输入用户名称" />
            </Form.Item>

            <Form.Item label="事件分类" name="eventClassify">
              <Select
                placeholder="请选择事件分类"
                onChange={(value) => {
                  setFilters((prevFilters) => ({
                    ...prevFilters,
                    eventClassify: value,
                    eventType: null, // 清空之前选择的事件类型
                  }));
                  getEventType(value); // 获取新的事件类型
                }}
                style={{ width: 150 }}
              >
                {Object.entries(eventClassifyList || {}).map(([key, value]) => (
                  <Option key={key} value={key}>
                    {value}
                  </Option>
                ))}
              </Select>
            </Form.Item>

            <Form.Item label="操作内容" name="eventType">
              <Select placeholder="请选择操作内容" style={{ width: 150 }}>
                {Object.entries(eventTypeList || {}).map(([key, value]) => (
                  <Option key={key} value={key}>
                    {value}
                  </Option>
                ))}
              </Select>
            </Form.Item>

            <Form.Item name="timeRange" label="报警时间范围">
              <RangePicker
                showTime
                placeholder={["开始时间", "结束时间"]}
                defaultValue={[
                  moment(dayjs().subtract(7, "day").toDate()),
                  moment(new Date()),
                ]}
              />
            </Form.Item>

            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button htmlType="button" onClick={() => form.resetFields()}>
                重置
              </Button>
            </Form.Item>
          </Form>
          <Table
            columns={columns}
            dataSource={logs?.items}
            rowKey="id"
            pagination={{
              total: logs?.total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};
export default connect(({}) => ({}))(LogPage);
