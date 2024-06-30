import React, { useState, useEffect } from "react";
import {
  Typography,
  Table,
  Button,
  Card,
  Form,
  Input,
  Space,
  Tag,
  Drawer,
  message,
  Modal,
  Tooltip, // 引入 Tooltip 组件
  Popover, // 引入 Popover 组件
} from "antd";
import { PageContainer } from "@ant-design/pro-components";
import { connect, Dispatch, history } from "umi";
import {
  HostMonitorDTO,
  LoadVO,
  SystemProcessVO,
  DiskNameVO,
  BaseMetricVO,
} from "@/models/host-monitor";
import CreateHostForm from "./components/create-host-form";
import { CopyOutlined, ExclamationCircleOutlined } from "@ant-design/icons";
import HostMonitorConfigForm from "./components/host-monitor-config-form";
import AlarmConfigurationForm from "./components/alarm-configure-form";
import { AlarmGroupDTO } from "@/models/alarm-group";
import {
  AlarmConfigDTO,
  HostAlarmConfigWrapperDTO,
} from "@/models/host-alarm-config";
import SystemProcessTable from "./components/top-progress-table";

const { confirm } = Modal;
const { Paragraph } = Typography;

interface HostListProps {
  dispatch: Dispatch;
  hosts: HostMonitorDTO[];
  total: number;
  alarmGroups: AlarmGroupDTO[];
  currentAlarmConfig: HostAlarmConfigWrapperDTO;
}

const HostPage: React.FC<HostListProps> = ({
  dispatch,
  hosts,
  total,
  alarmGroups,
  currentAlarmConfig,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [alarmDrawerVisible, setAlarmDrawerVisible] = useState(false);
  const [monitorViewDrawerVisible, setMonitorViewDrawerVisible] =
    useState(false);
  const [editingHostForAlarm, setEditingHostForAlarm] = useState<
    HostMonitorDTO | undefined
  >(undefined);
  const [filters, setFilters] = useState({
    name: "",
    hostName: "",
  });
  const [editingHost, setEditingHost] = useState<HostMonitorDTO | undefined>(
    undefined
  );

  const [topProgress, setTopProgress] = useState<SystemProcessVO[]>([]);
  const [disks, setDisks] = useState<DiskNameVO[]>([]);
  const [basicMetricVO, setBasicMetricVO] = useState<BaseMetricVO>();
  const [load, setLoad] = useState<LoadVO | undefined>();

  const handleOpenAlarmDrawer = (host: HostMonitorDTO) => {
    dispatch({
      type: "hostAlarmConfig/getConfig",
      payload: {
        hostId: host.hostId,
      },
      callback: () => {
        setAlarmDrawerVisible(true); // 打开抽屉
        setEditingHostForAlarm(host);
      },
    });
  };

  const handleCloseAlarmDrawer = () => {
    setAlarmDrawerVisible(false);
    setEditingHostForAlarm(undefined);
  };

  const handleAlarmFormSubmit = (values: any) => {
    console.log("Alarm form submitted with values:", values);
    // 处理表单提交逻辑
    const data = {
      cpu: {
        hostId: editingHostForAlarm?.hostId,
        alarmType: 10,
        alarmThreshold: values.cpuThreshold * 0.01,
        triggerThreshold: values.cpuNotificationThreshold,
        notifySilence: values.cpuSilenceTime,
      },
      memory: {
        hostId: editingHostForAlarm?.hostId,
        alarmType: 20,
        alarmThreshold: values.memoryThreshold * 0.01,
        triggerThreshold: values.memoryNotificationThreshold,
        notifySilence: values.memorySilenceTime,
      },
      hostId: editingHostForAlarm?.hostId,
      groupIdList: values.alertGroupIds,
    };

    dispatch({
      type: "hostAlarmConfig/configure",
      payload: data,
      callback: () => {
        message.success("配置成功");
      },
    });

    handleCloseAlarmDrawer();
  };

  const getHosts = () => {
    dispatch({
      type: "hostMonitor/fetch",
      payload: { ...pagination, ...filters },
    });
  };

  const getAlarmGroups = () => {
    dispatch({
      type: "alarmGroup/fetchAlarmGroups",
      payload: { pageNo: 1, pageSize: 50 },
    });
  };

  useEffect(() => {
    getAlarmGroups();
  }, []);

  useEffect(() => {
    getHosts();
  }, [pagination, filters]);

  const handleEdit = (host: HostMonitorDTO) => {
    setEditingHost(host); // 设置编辑状态为当前点击的主机信息
    setDrawerVisible(true); // 打开抽屉
  };

  const handleConnect = (host: HostMonitorDTO) => {
    dispatch({
      type: "hostMonitor/testConnect",
      payload: {
        accessToken: host.accessToken,
        url: host.monitorUrl,
      },
      callback: () => {
        message.success("连接成功");
      },
    });
  };

  const handleCheckStatus = (host: HostMonitorDTO) => {
    dispatch({
      type: "hostMonitor/checkStatus",
      payload: {
        hostId: host.hostId,
      },
      callback: () => {
        message.success("连接成功");
        getHosts();
      },
    });
  };

  const handleInstall = (hostId: string) => {
    dispatch({
      type: "hostMonitor/install",
      payload: {
        hostId,
        upgrade: false,
      },
      callback: () => {
        message.success("安装成功");
        getHosts();
      },
    });
  };

  const handleSync = (host: HostMonitorDTO) => {
    dispatch({
      type: "hostMonitor/sync",
      payload: {
        accessToken: host.accessToken,
        url: host.monitorUrl,
        hostId: host.hostId,
      },
      callback: () => {
        message.success("操作成功");
        getHosts();
      },
    });
  };

  const handleCloseDrawer = () => {
    setEditingHost(undefined);
    setDrawerVisible(false); // 关闭抽屉
    form.resetFields(); // 重置表单字段
  };

  const [form] = Form.useForm<{
    name: string;
    hostName: string;
  }>();

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  // 定义搜索方法
  const onSearch = (searchFilters: { name: string; hostName: string }) => {
    setFilters(searchFilters);
    setPagination({ pageNo: 1, pageSize: 10 }); // 重置分页
  };

  const handleUpdate = (values: HostMonitorDTO) => {
    const hostId = editingHost?.hostId;
    console.log("aaa", editingHost);
    dispatch({
      type: "hostMonitor/update",
      payload: {
        hostId: hostId,
        accessToken: values.accessToken,
        url: values.monitorUrl,
      },
      callback: () => {
        message.success("操作成功");
        getHosts();
      },
    });
    setDrawerVisible(false);
    form.resetFields();
  };

  const handleView = (record: HostMonitorDTO) => {
    // 示例：跳转到详情页，使用 history.push
    history.push(`/apps/host/alarm/history/${record.hostId}`);
  };

  const handleMonitorViewPanel = (record: HostMonitorDTO) => {
    setMonitorViewDrawerVisible(!monitorViewDrawerVisible);
    loadMetricData(record);
  };

  const loadMetricData = (record: HostMonitorDTO) => {
    //setEditingHostForAlarm(undefined);
    dispatch({
      type: "hostMonitor/ping",
      payload: { hostId: record.hostId },
      callback: () => {},
    });

    dispatch({
      type: "hostMonitor/getDiskName",
      payload: { hostId: record.hostId },
      callback: (content: DiskNameVO[]) => {
        setDisks(content);
      },
    });

    dispatch({
      type: "hostMonitor/metrics",
      payload: { hostId: record.hostId },
      callback: (content: BaseMetricVO) => {
        setBasicMetricVO(content);
      },
    });
  };

  const columns = [
    {
      title: "实例名称",
      dataIndex: "name",
      key: "name",
      render: (text: any, record: HostMonitorDTO) => {
        return (
          <Paragraph
            copyable={{ tooltips: ["点击复制", "复制成功"] }}
            style={{ display: "inline" }}
          >
            {record.host.name}
          </Paragraph>
        );
      },
    },
    {
      title: "主机名称",
      dataIndex: "hostName",
      key: "hostName",
      render: (text: any, record: HostMonitorDTO) => {
        return (
          <>
            {record.host.hostName}
            <br />
            IP:
            <Paragraph
              copyable={{ tooltips: ["点击复制", "复制成功"] }}
              style={{ display: "inline" }}
            >
              {record.host.serverAddr}
            </Paragraph>
          </>
        );
      },
    },
    {
      title: "插件状态",
      dataIndex: "monitorStatus",
      key: "monitorStatus",
      render: (text: any, record: HostMonitorDTO) => {
        // 根据不同的 monitorStatus 返回不同颜色的 Tag
        let tagColor = "";
        let tagText = "";
        switch (record.monitorStatus) {
          case 1:
            tagColor = "orange"; // 未启动
            tagText = "未启动";
            break;
          case 2:
            tagColor = "cyan"; // 启动中
            tagText = "启动中";
            break;
          case 3:
            tagColor = "green"; // 运行中
            tagText = "运行中";
            break;
          default:
            tagColor = "gray"; // 其他情况
        }
        return <Tag color={tagColor}>{tagText}</Tag>;
      },
    },
    {
      title: "插件地址",
      dataIndex: "monitorUrl",
      key: "monitorUrl",
    },
    {
      title: "Token",
      dataIndex: "accessToken",
      key: "accessToken",
    },
    {
      title: "agent版本",
      dataIndex: "agentVersion",
      key: "agentVersion",
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: HostMonitorDTO) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.host.status === "0" ? "success" : "error";
        const statusText = record.host.status === "0" ? "正常" : "停用";

        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },
    // 其他列根据需要添加
    {
      title: "操作",
      key: "action",
      render: (text: any, record: HostMonitorDTO) => (
        <Space size="small">
          <a onClick={() => handleConnect(record)}>测试</a>
          <a onClick={() => handleInstall(record.hostId)}>安装</a>
          <a onClick={() => handleEdit(record)}>插件配置</a>
          <a onClick={() => handleSync(record)}>同步</a>
          <a onClick={() => handleOpenAlarmDrawer(record)}>报警配置</a>
          <a onClick={() => handleView(record)}>报警历史</a>
          <a onClick={() => handleMonitorViewPanel(record)}>查看</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="主机监控">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            form={form}
            layout="inline"
            onFinish={(values) => {
              console.log(values);
              setFilters(values);
              // 触发外层组件的搜索方法
              onSearch(values);
            }}
          >
            <Form.Item name="name" label="实例名称">
              <Input placeholder="请输入实例名称" />
            </Form.Item>
            <Form.Item name="hostName" label="主机名称">
              <Input placeholder="请输入主机名称" />
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button
                onClick={() => {
                  form.resetFields();
                  setFilters({
                    name: "",
                    hostName: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>

          {/* 编辑插件的抽屉 */}
          <Drawer
            title={"编辑插件配置"}
            width={400}
            open={drawerVisible}
            onClose={handleCloseDrawer}
            destroyOnClose={true}
          >
            <HostMonitorConfigForm
              initialValues={editingHost}
              onSubmit={handleUpdate}
              onCancel={handleCloseDrawer}
            />
          </Drawer>

          <Drawer
            title="报警配置"
            width={600}
            open={alarmDrawerVisible}
            onClose={handleCloseAlarmDrawer}
            destroyOnClose={true}
          >
            <AlarmConfigurationForm
              initialValues={currentAlarmConfig}
              alarmGroups={alarmGroups}
              onSubmit={handleAlarmFormSubmit}
              onCancel={handleCloseAlarmDrawer}
            />
          </Drawer>

          <Drawer
            title="监控"
            width={1000}
            open={monitorViewDrawerVisible}
            onClose={() => {
              setMonitorViewDrawerVisible(!monitorViewDrawerVisible);
            }}
            destroyOnClose={true}
          >
            <SystemProcessTable
              hostId={basicMetricVO?.machineId}
              data={basicMetricVO?.processes}
              load={load}
              disks={disks}
              basicMetricVO={basicMetricVO}
            ></SystemProcessTable>
          </Drawer>

          <Table
            columns={columns}
            dataSource={hosts}
            rowKey={"hostId"}
            pagination={{
              total: total,
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

export default connect(({ hostMonitor, alarmGroup, hostAlarmConfig }) => ({
  hosts: hostMonitor.hosts,
  total: hostMonitor.total,
  alarmGroups: alarmGroup.alarmGroups,
  currentAlarmConfig: hostAlarmConfig.hostAlarmConfig,
}))(HostPage);
