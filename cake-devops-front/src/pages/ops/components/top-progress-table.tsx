// src/components/HostTable.tsx
import React, { useEffect, useState } from "react";
import { Dispatch, connect } from "umi";
import { nanoid } from "nanoid";
import {
  Table,
  Tooltip,
  Button,
  Typography,
  Space,
  Col,
  Row,
  Statistic,
  TabsProps,
  Tabs,
  Form,
  DatePicker,
  Select,
} from "antd";
import {
  BaseMetricVO,
  DiskNameVO,
  DiskStatVO,
  LoadVO,
  SystemProcessVO,
} from "@/models/host-monitor";
import moment from "moment";
import dayjs from "dayjs";

const { Text } = Typography;
const { Option } = Select;
const { RangePicker } = DatePicker;

interface SystemProcessPros {
  dispatch: Dispatch;
  hostId?: string;
  data?: SystemProcessVO[];
  load?: LoadVO;
  disks?: DiskNameVO[];
  basicMetricVO?: BaseMetricVO;
}

const SystemProcessTable: React.FC<SystemProcessPros> = ({
  dispatch,
  hostId,
  data,
  load,
  disks,
  basicMetricVO,
}) => {
  const [monitorFilter, setMonitorFilter] = useState({
    granularity: 20, //1分钟
    startRange: dayjs().subtract(1, "day").startOf("second").valueOf(),
    endRange: dayjs().startOf("second").valueOf(),
  });
  console.log("monitorFilter", monitorFilter);

  const [form] = Form.useForm();
  const handleFilterChange = (changedValues: any) => {
    console.log("changedValues", changedValues);
    const { timeRange, ...otherValues } = changedValues;
    console.log("timeRange", timeRange);
    if (timeRange && timeRange.length === 2) {
      const [startDate, endDate] = timeRange;
      setMonitorFilter((prevFilters) => ({
        ...prevFilters,
        startRange: startDate ? startDate.valueOf() : undefined,
        endRange: endDate ? endDate.valueOf() : undefined,
        ...otherValues,
      }));
    } else {
      setMonitorFilter((prevFilters) => ({
        ...prevFilters,
        startRange: undefined,
        endRange: undefined,
        ...otherValues,
      }));
    }
  };

  const columns = [
    {
      title: "进程",
      dataIndex: "pid",
      key: "pid",
    },
    {
      title: "名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "用户",
      dataIndex: "user",
      key: "user",
    },
    {
      title: "端口",
      dataIndex: "port",
      key: "port",
    },
    {
      title: "cpu使用率",
      dataIndex: "cpuUsage",
      key: "cpuUsage",
    },
    {
      title: "使用内存",
      dataIndex: "memoryUsage",
      key: "memoryUsage",
    },
    {
      title: "句柄数",
      dataIndex: "openFile",
      key: "openFile",
    },
    {
      title: "启用时长",
      dataIndex: "upTime",
      key: "upTime",
    },
    {
      title: "命令行",
      dataIndex: "commandLine",
      key: "commandLine",
      with: 300,
      render: (text: string, record: SystemProcessVO) => (
        <Text
          style={{
            width: 200,
            marginBottom: 0,
          }}
          copyable={{ tooltips: ["点击复制", "复制成功"] }}
          ellipsis={true}
        >
          {text}
        </Text>
      ),
    },
  ];

  const diskColumns = [
    // {
    //   title: "硬盘名称",
    //   dataIndex: "id",
    //   key: "id",
    // },
    {
      title: "硬盘名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "硬盘序列",
      dataIndex: "seq",
      key: "seq",
    },
  ];

  const onChange = () => {
    dispatch({
      type: "hostMonitor/getCpuStatistics",
      payload: {
        hostId: hostId,
        granularity: monitorFilter.granularity,
        startRange: monitorFilter.startRange,
        endRange: monitorFilter.endRange,
      },
      callback: (content: DiskNameVO[]) => {
        //setDisks(content);
      },
    });

    dispatch({
      type: "hostMonitor/getMemoryStatistics",
      payload: {
        hostId: hostId,
        granularity: monitorFilter.granularity,
        startRange: monitorFilter.startRange,
        endRange: monitorFilter.endRange,
      },
      callback: (content: DiskNameVO[]) => {
        // setDisks(content);
      },
    });

    dispatch({
      type: "hostMonitor/getDiskStatistics",
      payload: {
        hostId: hostId,
        granularity: monitorFilter.granularity,
        startRange: monitorFilter.startRange,
        endRange: monitorFilter.endRange,
      },
      callback: (content: DiskNameVO[]) => {
        //setDisks(content);
      },
    });
  };

  console.log(hostId);

  useEffect(() => {
    console.log("monitorFilter", monitorFilter);
    if (hostId) {
      onChange();
    }
  }, [hostId, monitorFilter]);

  const diskUsageColumns = [
    {
      title: "硬盘名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "硬盘总空间",
      dataIndex: "totalSpace",
      key: "totalSpace",
    },
    {
      title: "使用空间",
      dataIndex: "usageSpace",
      key: "usageSpace",
    },
    {
      title: "空闲空间",
      dataIndex: "freeSpace",
      key: "freeSpace",
    },
    {
      title: "使用率",
      dataIndex: "usage",
      key: "usage",
      render: (text: string, record: DiskStatVO) => <div>{text}%</div>,
    },
  ];

  return (
    <Space direction="vertical">
      <Row gutter={24}>
        <Col span={8}>
          <Statistic
            title="1分钟负载"
            value={basicMetricVO?.load?.oneMinuteLoad}
          />
        </Col>
        <Col span={8}>
          <Statistic
            title="5分钟负载"
            value={basicMetricVO?.load?.fiveMinuteLoad}
          />
        </Col>
        <Col span={8}>
          <Statistic
            title="15分钟负载"
            value={basicMetricVO?.load?.fifteenMinuteLoad}
          />
        </Col>
      </Row>
      <Space>
        <Table
          size="small"
          columns={columns}
          dataSource={basicMetricVO?.processes}
          rowKey={"pid"}
          scroll={{ x: "max-content" }}
          pagination={false}
        />
      </Space>
      <Row gutter={24}>
        <Col span={8}>
          <Table
            size="small"
            columns={diskColumns}
            dataSource={disks?.map((item) => ({
              ...item,
              rowKey: nanoid(),
            }))}
            rowKey={"rowKey"}
            scroll={{ x: "max-content" }}
            pagination={false}
          />
        </Col>
        <Col span={16}>
          <Table
            size="small"
            columns={diskUsageColumns}
            dataSource={basicMetricVO?.disks.map((item) => ({
              ...item,
              rowKey: nanoid(),
            }))}
            rowKey={"rowKey"}
            scroll={{ x: "max-content" }}
            pagination={false}
          />
        </Col>
      </Row>

      <Form
        form={form}
        layout="inline"
        onFinish={handleFilterChange}
        initialValues={{
          granularity: 20,
          timeRange: [
            moment(dayjs().subtract(1, "day").toDate()),
            moment(new Date()),
          ],
        }}
      >
        <Form.Item name="granularity" label="数据粒度">
          <Select placeholder="请选择数据粒度">
            <Option value={10}>10秒</Option>
            <Option value={12}>30秒</Option>
            <Option value={20}>1分钟</Option>
            <Option value={22}>5分钟</Option>
            <Option value={24}>10分钟</Option>
            <Option value={26}>30分钟</Option>
            <Option value={30}>1小时</Option>
            <Option value={32}>6小时</Option>
            <Option value={34}>12小时</Option>
            <Option value={40}>1天</Option>
            <Option value={50}>1周</Option>
          </Select>
        </Form.Item>
        <Form.Item name="timeRange" label="选择时间范围">
          <RangePicker
            showTime
            format="YYYY-MM-DD HH:mm:ss"
            placeholder={["开始时间", "结束时间"]}
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ marginRight: 8 }}>
            查询
          </Button>
          <Button onClick={() => form.resetFields()}>重置</Button>
        </Form.Item>
      </Form>
      {/* <Row gutter={24}>
        <Tabs defaultActiveKey="1" items={items} onChange={onChange} />
      </Row> */}
    </Space>
  );
};

// export default SystemProcessTable;
export default connect(({ host }) => ({}))(SystemProcessTable);
