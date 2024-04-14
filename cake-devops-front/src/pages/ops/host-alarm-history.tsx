import React, { useEffect, useState } from "react";
import {
  Button,
  Card,
  DatePicker,
  Form,
  Input,
  InputNumber,
  Select,
  Space,
  Table,
  Tag,
} from "antd";
import { connect, Dispatch, useParams } from "umi";
import { AlarmInfo } from "@/models/host-alarm-config";
import { PageContainer } from "@ant-design/pro-components";
import dayjs from "dayjs";

const { Option } = Select;
const { RangePicker } = DatePicker;

interface AlarmListProps {
  dispatch: Dispatch;
  alarms: AlarmInfo[]; // 假设 AlarmInfo 是你的报警信息类型
  alarmsTotal: number;
}

const AlarmPage: React.FC<AlarmListProps> = ({
  dispatch,
  alarms,
  alarmsTotal,
}) => {
  const { id } = useParams<{ id: string }>(); // 获取hostId
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    alarmType: "",
    startTime: undefined,
    endTime: undefined,
    minValue: undefined,
    maxValue: undefined,
    hostId: id,
  });

  const fetchAlarms = () => {
    // 根据筛选条件获取告警列表
    dispatch({
      type: "hostAlarmConfig/pageAlarms",
      payload: {
        ...pagination,
        ...filters,
      },
    });
  };

  useEffect(() => {
    fetchAlarms();
  }, [filters, pagination]);

  const columns = [
    {
      title: "报警类型",
      dataIndex: "alarmType",
      key: "alarmType",
      render: (text: any, record: AlarmInfo) => {
        switch (record.alarmType) {
          case 10:
            return "CPU使用率";
          case 20:
            return "内存使用率";
          default:
            return "未知";
        }
      },
    },
    {
      title: "报警时间",
      dataIndex: "alarmTime",
      key: "alarmTime",
      render: (text: string, record: AlarmInfo) => (
        <>{dayjs(record?.alarmTime).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "报警值",
      dataIndex: "alarmValue",
      key: "alarmValue",
    },
  ];

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleFilterChange = (changedValues: any) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      ...changedValues,
    }));
  };

  const [form] = Form.useForm();
  const onFinish = (values: any) => {
    handleFilterChange(values);
  };

  console.log(alarms);

  return (
    <PageContainer title="告警管理">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          {/* 添加筛选组件 */}
          <Form form={form} layout="inline" onFinish={onFinish}>
            <Form.Item name="alarmType" label="报警类型">
              <Select placeholder="请选择报警类型">
                <Option value={10}>CPU使用率</Option>
                <Option value={20}>内存使用率</Option>
              </Select>
            </Form.Item>
            <Form.Item name="timeRange" label="报警时间范围">
              <RangePicker showTime placeholder={["开始时间", "结束时间"]} />
            </Form.Item>
            <Form.Item name="alarmValue" label="报警值范围">
              <Input.Group compact>
                <Form.Item name="minValue">
                  <InputNumber
                    placeholder="最小报警值"
                    style={{ width: 120 }}
                  />
                </Form.Item>
                <Form.Item name="maxValue">
                  <InputNumber
                    placeholder="最大报警值"
                    style={{ width: 120 }}
                  />
                </Form.Item>
              </Input.Group>
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button onClick={() => form.resetFields()}>重置</Button>
            </Form.Item>
          </Form>

          <Table
            columns={columns}
            dataSource={alarms}
            rowKey="id"
            pagination={{
              total: alarmsTotal,
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

export default connect(({ hostAlarmConfig }) => ({
  alarms: hostAlarmConfig.alarms,
  alarmTotal: hostAlarmConfig.alarmTotal,
}))(AlarmPage);
