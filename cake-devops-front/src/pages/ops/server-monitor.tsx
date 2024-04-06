import React, { useState, useEffect } from "react";
import {
  Typography,
  Table,
  Row,
  Col,
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
import { connect, Dispatch } from "umi";
import { HostMonitorDTO, HostInfoModel } from "@/models/host-monitor";
import CreateHostForm from "./components/create-host-form";
import { CopyOutlined, ExclamationCircleOutlined } from "@ant-design/icons";
import HostMonitorConfigForm from "./components/host-monitor-config-form";

const { confirm } = Modal;
const { Paragraph } = Typography;

interface HostListProps {
  dispatch: Dispatch;
  hosts: HostMonitorDTO[];
  total: number;
}

const HostPage: React.FC<HostListProps> = ({ dispatch, hosts, total }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [filters, setFilters] = useState({
    name: "",
    hostName: "",
  });
  const [editingHost, setEditingHost] = useState<HostMonitorDTO | undefined>(
    undefined
  ); // 当前正在编辑的主机信息

  const getHosts = () => {
    dispatch({
      type: "hostMonitor/fetch",
      payload: { ...pagination, ...filters },
    });
  };

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
        <Space size="middle">
          <a onClick={() => handleConnect(record)}>测试</a>
          <a onClick={() => handleInstall(record.hostId)}>安装</a>
          <a onClick={() => handleEdit(record)}>插件配置</a>
          <a onClick={() => handleSync(record)}>同步</a>
          {/* <a onClick={() => handleCheckStatus(record)}>检查状态</a> */}
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="主机监控管理">
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

export default connect(({ hostMonitor }) => ({
  hosts: hostMonitor.hosts,
  total: hostMonitor.total,
}))(HostPage);
