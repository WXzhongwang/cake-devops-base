import React, { useEffect, useState } from "react";
import {
  Card,
  Form,
  Input,
  Button,
  Select,
  Table,
  message,
  Space,
  Modal,
  Tag,
  Tooltip,
} from "antd";
import { PageContainer } from "@ant-design/pro-layout";
import {
  PlayCircleOutlined,
  StopOutlined,
  DeleteOutlined,
  ReloadOutlined,
} from "@ant-design/icons";

const { TextArea } = Input;
import { API } from "typings";
import { CommandExecDTO, CommandExecStatusDTO } from "@/models/batch-exec";
import { connect, Dispatch } from "umi";
import { ScriptTemplateDTO } from "@/models/script-template";
import { HostModel } from "@/models/host";
import { filter } from "lodash";
import { render } from "react-dom";
import dayjs from "dayjs";

interface BatchCommandProps {
  dispatch: Dispatch;
}

const BatchCommand: React.FC<BatchCommandProps> = ({ dispatch }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [hostList, setHostList] = useState<API.Page<HostModel>>();
  const [selectedExecId, setSelectedExecId] = useState<number | null>(null);

  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [commandPagination, setCommandPagination] = useState({
    pageNo: 1,
    pageSize: 10,
  });
  const [filters, setFilters] = useState({ name: "" });
  const [commandExecPage, setCommandExecPage] =
    useState<API.Page<CommandExecDTO>>();
  const [scriptPage, setScriptPage] = useState<API.Page<ScriptTemplateDTO>>();

  // 获取主机列表
  const fetchHostList = () => {
    dispatch({
      type: "host/fetchHosts",
      payload: {
        pageNo: 1,
        pageSize: 1000,
        ...filters,
      },
      callback: (res: API.Page<HostModel>) => {
        setHostList(res);
      },
    });
  };

  const fetchCommandExec = () => {
    dispatch({
      type: "commandExec/fetchCommandExec",
      payload: {
        ...filters,
        ...commandPagination,
      },
      callback: (res: API.Page<CommandExecDTO>) => {
        setCommandExecPage(res);
        // 获取执行状态
        if (res?.items?.length > 0) {
          fetchExecStatus(res.items.map((item) => item.id));
        }
      },
    });
  };

  const fetchExecStatus = (idList: number[]) => {
    dispatch({
      type: "commandExec/fetchExecStatus",
      payload: {
        idList,
      },
      callback: (res: CommandExecStatusDTO[]) => {
        const statusMap: Record<number, CommandExecStatusDTO> = {};
        res.forEach((status) => {
          statusMap[status.id] = status;
        });
        setExecStatusMap(statusMap);
      },
    });
  };

  const queryScripts = () => {
    dispatch({
      type: "script/queryScripts",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<ScriptTemplateDTO>) => {
        setScriptPage(res);
      },
    });
  };

  useEffect(() => {
    fetchHostList();
    queryScripts();
    fetchCommandExec();
  }, []);

  useEffect(() => {
    fetchCommandExec();
  }, [commandPagination]);

  // // 定时刷新执行状态
  // useEffect(() => {
  //   const timer = setInterval(() => {
  //     if (commandExecPage?.items?.length > 0) {
  //       fetchExecStatus(commandExecPage.items.map((item) => item.id));
  //     }
  //   }, 5000);

  //   return () => clearInterval(timer);
  // }, [commandExecPage]);

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setCommandPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleExecute = async (values: any) => {
    setLoading(true);
    try {
      dispatch({
        type: "commandExec/createCommandExec",
        payload: {
          hostIds: values.hosts,
          command: values.command,
          description: values.description,
        },
        callback: (res: any) => {
          if (res) {
            message.success("命令已提交执行");
            form.resetFields();
            fetchCommandExec();
          }
        },
      });
    } catch (error) {
      message.error("执行失败");
    } finally {
      setLoading(false);
    }
  };

  const handleTerminate = (id: number) => {
    Modal.confirm({
      title: "确认停止执行",
      content: "确定要停止该执行任务吗？",
      onOk: () => {
        dispatch({
          type: "commandExec/terminateExec",
          payload: { id },
          callback: () => {
            message.success("已停止执行");
            fetchCommandExec();
          },
        });
      },
    });
  };

  const handleDelete = (id: number) => {
    Modal.confirm({
      title: "确认删除",
      content: "确定要删除该执行任务吗？",
      onOk: () => {
        dispatch({
          type: "commandExec/deleteCommand",
          payload: { idList: [id] },
          callback: (res: number) => {
            message.success(`成功删除${res}条记录`);
            fetchCommandExec();
          },
        });
      },
    });
  };

  const handleViewDetail = (record: CommandExecDTO) => {
    setSelectedExecId(record.id);
    // 获取详情
    dispatch({
      type: "commandExec/detail",
      payload: { id: record.id },
      callback: (res: CommandExecDTO) => {},
    });
  };

  const renderStatus = (status?: number) => {
    if (status === undefined || status === null) return <Tag>未知</Tag>;

    const statusMap: Record<number, { color: string; text: string }> = {
      10: { color: "blue", text: "未开始" },
      20: { color: "processing", text: "执行中" },
      30: { color: "success", text: "执行完成" },
      40: { color: "error", text: "执行异常" },
      50: { color: "warning", text: "执行终止" },
    };

    const statusInfo = statusMap[status] || {
      color: "default",
      text: `状态${status}`,
    };
    return <Tag color={statusInfo.color}>{statusInfo.text}</Tag>;
  };

  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      width: 80,
    },
    {
      title: "主机",
      dataIndex: "hostName",
      key: "hostName",
    },
    {
      title: "主机IP",
      dataIndex: "host",
      key: "host",
    },
    {
      title: "脚本类型",
      dataIndex: "execType",
      key: "execType",
    },
    {
      title: "退出码",
      dataIndex: "exitCode",
      key: "exitCode",
    },
    {
      title: "命令",
      dataIndex: "command",
      key: "command",
      ellipsis: true,
      render: (text: string) => (
        <Tooltip title={text}>
          <span>{text}</span>
        </Tooltip>
      ),
    },
    {
      title: "执行状态",
      key: "status",
      render: (_: any, record: CommandExecDTO) =>
        renderStatus(record.execStatus),
    },
    {
      title: "开始时间",
      dataIndex: "startTime",
      key: "startTime",
      render: (text: string) => (
        <>{dayjs(text).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "开始至今",
      dataIndex: "startTimeAgo",
      key: "startTimeAgo",
    },
    {
      title: "耗时(ms)",
      dataIndex: "used",
      key: "used",
    },
    {
      title: "使用时间",
      dataIndex: "keepTime",
      key: "keepTime",
    },
    {
      title: "结束时间",
      dataIndex: "endDate",
      key: "endDate",
      render: (text: string) => (
        <>{dayjs(text).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "结束至今",
      dataIndex: "endTimeAgo",
      key: "endTimeAgo",
    },
    {
      title: "操作",
      key: "action",
      render: (_: any, record: CommandExecDTO) => (
        <Space size="small">
          <Button
            type="link"
            size="small"
            onClick={() => handleViewDetail(record)}
          >
            查看
          </Button>
          {record.execStatus === 20 && (
            <Button
              type="link"
              size="small"
              danger
              onClick={() => handleTerminate(record.id)}
              icon={<StopOutlined />}
            >
              停止
            </Button>
          )}
          <Button
            type="link"
            size="small"
            danger
            onClick={() => handleDelete(record.id)}
            icon={<DeleteOutlined />}
          >
            删除
          </Button>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer
      extra={[
        <Button
          key="refresh"
          icon={<ReloadOutlined />}
          onClick={() => fetchCommandExec()}
        >
          刷新
        </Button>,
      ]}
    >
      <Card title="批量执行命令" style={{ marginBottom: 16 }}>
        <Form form={form} onFinish={handleExecute} layout="vertical">
          <Form.Item
            label="选择主机"
            name="hosts"
            rules={[{ required: true, message: "请选择执行主机" }]}
          >
            <Select
              mode="multiple"
              placeholder="请选择要执行的主机"
              style={{ width: "100%" }}
              optionFilterProp="children"
              showSearch
            >
              {hostList?.items.map((host) => (
                <Select.Option key={host.hostId} value={host.hostId}>
                  {host.name} ({host.hostName})
                </Select.Option>
              ))}
            </Select>
          </Form.Item>

          <Form.Item
            label="执行命令"
            name="command"
            rules={[{ required: true, message: "请输入要执行的命令" }]}
          >
            <div>
              <div style={{ marginBottom: 8 }}>
                <Select
                  placeholder="选择脚本模板"
                  style={{ width: "100%" }}
                  allowClear
                  showSearch
                  optionFilterProp="children"
                  onChange={(value, option: any) => {
                    if (value) {
                      form.setFieldsValue({ command: option.content });
                    }
                  }}
                >
                  {scriptPage?.items?.map((script) => (
                    <Select.Option
                      key={script.id}
                      value={script.id}
                      content={script.templateValue}
                    >
                      {script.templateName}
                    </Select.Option>
                  ))}
                </Select>
              </div>
              <Form.Item name="command" noStyle>
                <TextArea rows={4} placeholder="请输入要执行的命令" />
              </Form.Item>
            </div>
          </Form.Item>

          <Form.Item label="备注" name="description" initialValue={"暂无"}>
            <TextArea rows={1} placeholder="请输入指令备注" />
          </Form.Item>

          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              loading={loading}
              icon={<PlayCircleOutlined />}
            >
              执行
            </Button>
          </Form.Item>
        </Form>
      </Card>

      <Card title="执行历史">
        <Table
          columns={columns}
          dataSource={commandExecPage?.items}
          rowKey="id"
          pagination={{
            total: commandExecPage?.total,
            current: commandPagination.pageNo,
            pageSize: commandPagination.pageSize,
            onChange: handlePaginationChange,
            showSizeChanger: true,
          }}
        />
      </Card>
    </PageContainer>
  );
};

export default connect()(BatchCommand);
