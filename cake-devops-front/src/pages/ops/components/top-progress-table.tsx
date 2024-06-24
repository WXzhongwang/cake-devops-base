// src/components/HostTable.tsx
import React, { useEffect, useState } from "react";
import { Dispatch, connect } from "umi";
import { nanoid } from "nanoid";
import ReactEcharts from "echarts-for-react";

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
  Card,
  Carousel,
} from "antd";
import {
  BaseMetricVO,
  CpuStatisticsVO,
  DiskNameVO,
  DiskStatVO,
  DiskStatisticsVO,
  LoadVO,
  MemoryStatisticsVO,
  NetStatisticsVO,
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
  const [cpuStatisticsVO, setCpuStatisticsVO] = useState<CpuStatisticsVO>();
  const [netStatisticsVO, setNetStatisticsVO] = useState<NetStatisticsVO>();
  const [memoryStatisticsVO, setMemoryStatisticsVO] =
    useState<MemoryStatisticsVO>();
  const [diskStatisticsVO, setDiskStatisticsVO] = useState<DiskStatisticsVO>();
  const [monitorFilter, setMonitorFilter] = useState({
    granularity: 20, //1分钟
    startRange: dayjs().subtract(1, "day").startOf("second").valueOf() / 1000,
    endRange: dayjs().startOf("second").valueOf() / 1000,
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
        startRange: startDate
          ? Math.floor(startDate.valueOf() / 1000)
          : undefined,
        endRange: endDate ? Math.floor(endDate.valueOf() / 1000) : undefined,
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
      callback: (content: CpuStatisticsVO) => {
        setCpuStatisticsVO(content);
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
      callback: (content: MemoryStatisticsVO) => {
        setMemoryStatisticsVO(content);
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
      callback: (content: DiskStatisticsVO) => {
        setDiskStatisticsVO(content);
      },
    });

    dispatch({
      type: "hostMonitor/getNetStatistics",
      payload: {
        hostId: hostId,
        granularity: monitorFilter.granularity,
        startRange: monitorFilter.startRange,
        endRange: monitorFilter.endRange,
      },
      callback: (content: NetStatisticsVO) => {
        setNetStatisticsVO(content);
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

  const getLineChartOption = (data: any, labels: string[]) => ({
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: labels,
    },
    xAxis: {
      type: "category",
      data: data[labels[0]]?.metrics?.map((item: any) =>
        moment(item.time * 1000).format("YYYY-MM-DD HH:mm:ss")
      ),
    },
    yAxis: {
      type: "value",
    },
    series: labels.map((label: string) => ({
      name: label,
      data: data[label]?.metrics?.map((metric: any) => metric.value),
      type: "line",
      smooth: true,
    })),
  });

  const renderMetricCards = (data: any[]) => (
    <Carousel autoplay>
      {data.map((item, index) => (
        <Card title={item.title + "(" + item.suffix + ")"} size="small">
          <Row gutter={12}>
            <Col span={8}>
              <Statistic title="平均值" value={item?.data?.avg} />
            </Col>
            <Col span={8}>
              <Statistic title="最小值" value={item?.data?.min} />
            </Col>
            <Col span={8}>
              <Statistic title="最大值" value={item?.data?.max} />
            </Col>
          </Row>
        </Card>
      ))}
    </Carousel>
  );

  return (
    <Space size="middle" direction="vertical" style={{ width: "100%" }}>
      <Card title="负载" size="small" style={{ width: "100%" }}>
        <Row>
          <Col span={8}>
            <Statistic
              title="1分钟"
              value={basicMetricVO?.load?.oneMinuteLoad}
              suffix="%"
            />
          </Col>
          <Col span={8}>
            <Statistic
              title="5分钟"
              value={basicMetricVO?.load?.fiveMinuteLoad}
              suffix="%"
            />
          </Col>
          <Col span={8}>
            <Statistic
              title="15分钟"
              value={basicMetricVO?.load?.fifteenMinuteLoad}
              suffix="%"
            />
          </Col>
        </Row>
      </Card>

      <Row gutter={24}>
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
      </Row>
      <Row gutter={24}>
        <Col span={12}>
          {renderMetricCards([
            {
              title: "CPU使用率",
              data: cpuStatisticsVO?.usage,
              suffix: "%",
            },
          ])}
          <ReactEcharts
            option={getLineChartOption({ CPU使用率: cpuStatisticsVO?.usage }, [
              "CPU使用率",
            ])}
            style={{ height: 400, width: 460 }}
          />
        </Col>
        <Col span={12}>
          {renderMetricCards([
            {
              title: "内存使用率",
              data: memoryStatisticsVO?.usage,
              suffix: "%",
            },
            {
              title: "内存使用量",
              data: memoryStatisticsVO?.size,
              suffix: "MB",
            },
          ])}
          <ReactEcharts
            option={getLineChartOption(
              {
                内存使用率: memoryStatisticsVO?.usage,
                内存使用量: memoryStatisticsVO?.size,
              },
              ["内存使用率", "内存使用量"]
            )}
            style={{ height: 400, width: 460 }}
          />
        </Col>
      </Row>
      <Row gutter={24}>
        <Col span={12}>
          {renderMetricCards([
            {
              title: "上行速率",
              data: netStatisticsVO?.sentSpeed,
              suffix: "mbp/s",
            },
            {
              title: "下行速率",
              data: netStatisticsVO?.recvSpeed,
              suffix: "mbp/s",
            },
            {
              title: "上行包数",
              data: netStatisticsVO?.sentPacket,
              suffix: "个/s",
            },
            {
              title: "下行速率",
              data: netStatisticsVO?.recvPacket,
              suffix: "个/s",
            },
          ])}
          <ReactEcharts
            option={getLineChartOption(
              {
                上行速率: netStatisticsVO?.sentSpeed,
                下行速率: netStatisticsVO?.recvSpeed,
                上行包数: netStatisticsVO?.sentPacket,
                下行包数: netStatisticsVO?.recvPacket,
              },
              ["上行速率", "下行速率", "上行包数", "下行包数"]
            )}
            style={{ height: 400, width: 460 }}
          />
        </Col>
        <Col span={12}>
          {renderMetricCards([
            {
              title: "硬盘读速度",
              data: diskStatisticsVO?.readCount,
              suffix: "kb/s",
            },
            {
              title: "硬盘写速度",
              data: diskStatisticsVO?.writeSpeed,
              suffix: "kb/s",
            },
            {
              title: "硬盘读次数",
              data: diskStatisticsVO?.readCount,
              suffix: "次/s",
            },
            {
              title: "硬盘读次数",
              data: diskStatisticsVO?.writeCount,
              suffix: "次/s",
            },
            {
              title: "使用时间",
              data: diskStatisticsVO?.usageTime,
              suffix: "ms",
            },
          ])}
          <ReactEcharts
            option={getLineChartOption(
              {
                硬盘读速度: diskStatisticsVO?.readSpeed,
                硬盘写速度: diskStatisticsVO?.writeSpeed,
                硬盘读次数: diskStatisticsVO?.readCount,
                硬盘写次数: diskStatisticsVO?.writeCount,
                使用时间: diskStatisticsVO?.usageTime,
              },
              [
                "硬盘写速度",
                "硬盘读速度",
                "硬盘读次数",
                "硬盘写次数",
                "使用时间",
              ]
            )}
            style={{ height: 400, width: 460 }}
          />
        </Col>
      </Row>

      <Table
        size="small"
        columns={columns}
        dataSource={basicMetricVO?.processes}
        rowKey={"pid"}
        scroll={{ x: "max-content" }}
        pagination={false}
      />

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
    </Space>
  );
};

export default connect(({ host }) => ({}))(SystemProcessTable);
