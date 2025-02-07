import React, {
  useCallback,
  useEffect,
  useMemo,
  useState,
  useRef,
} from "react";
import { nanoid } from "nanoid";

import { PageContainer } from "@ant-design/pro-components";
import {
  Table,
  Space,
  Input,
  Select,
  Button,
  Form,
  Card,
  Tag,
  Steps,
  Drawer,
  DatePicker,
  message,
  Typography,
  Modal,
  Descriptions,
  Divider,
  Collapse,
  TabsProps,
  Tabs,
} from "antd";
import { connect, Dispatch, useParams, history } from "umi";
import {
  AppEnv,
  AppInfo,
  BranchInfo,
  PodDTO,
  ResourceStrategyDTO,
} from "@/models/app";
import {
  DeployHistoryDTO,
  DeployLogDTO,
  ReleaseRecord,
} from "@/models/release";
import moment from "moment";
import dayjs from "dayjs";
import { TableRowSelection } from "antd/lib/table/interface";
import DeployLogDrawer from "./components/deploy-log-drawer";
import EnvVarConfigPanel from "./components/env-vars-panel";
import ConfigMapConfigPanel from "./components/config-map-panel";
import EnvResourcePanel from "./components/env-resource-panel";
import DomainHostConfigPanel from "./components/domain-host-config-panel";

const { Paragraph } = Typography;
const { Option } = Select;

// 在组件中定义 getReleaseStatusText 函数
const getReleaseStatusText = (status: string) => {
  switch (status) {
    case "AWAIT_APPROVAL":
      return "审批中";
    case "READY":
      return "待发布";
    case "PENDING":
      return "发布中";
    case "FINISHED":
      return "已完成";
    case "FAILED":
      return "发布失败";
    case "CLOSED":
      return "已关闭";
    default:
      return "未知状态";
  }
};

const getDeployStatusText = (status: string) => {
  switch (status) {
    case "0":
      return "发布中";
    case "1":
      return "发布失败";
    case "2":
      return "发布成功";
    default:
      return "未知状态";
  }
};

const getApprovalStatusText = (status: string) => {
  switch (status) {
    case "PENDING":
      return "审批中";
    case "APPROVED":
      return "已同意";
    case "AUTO_APPROVED":
      return "免批通过";
    case "REPEALED":
      return "已撤销";
    case "REJECTED":
      return "已驳回";
    default:
      return "未知状态";
  }
};

interface ReleasePageProps {
  appDetail: AppInfo;
  releases: {
    total: number;
    list: ReleaseRecord[];
  };
  deployHistory: {
    total: number;
    list: DeployHistoryDTO[];
  };
  appEnv: AppEnv;
  dispatch: Dispatch;
}
const DeployPage: React.FC<ReleasePageProps> = ({
  dispatch,
  appDetail,
  releases,
  deployHistory,
  appEnv,
}) => {
  const { id } = useParams();
  // 发布单详情抽屉
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [historyPagination, setHistoryPagination] = useState({
    pageNo: 1,
    pageSize: 10,
  });
  const [deployDisabled, setDeployDisabled] = useState(false);

  const [selectedEnvironment, setSelectedEnvironment] = useState<
    string | undefined | null
  >(appEnv?.envId);

  // 使用 useRef 创建一个变量来存储定时器的引用
  const timerRef = useRef<NodeJS.Timeout | null>(null);
  const [selectedRow, setSelectedRow] = useState<ReleaseRecord | null>(null);
  // 状态用于控制查看详情抽屉的显示与隐藏
  const [viewDrawerVisible, setViewDrawerVisible] = useState(false);
  // 记录当前查看的发布单数据
  const [currentViewRelease, setCurrentViewRelease] =
    useState<ReleaseRecord | null>(null);
  // 在组件中定义状态来控制是否显示查看发布日志按钮和抽屉
  const [logDrawerVisible, setLogDrawerVisible] = useState(false);
  // 新增 pipeKey 状态用于传递给查看发布日志的抽屉
  const [pipeKey, setPipeKey] = useState<string>("");
  // 在组件中定义状态来控制是否显示查看发布日志按钮和抽屉
  const [historyLogDrawerVisible, setHistoryLogDrawerVisible] = useState(false);

  const [resourceStrategy, setResourceStrategy] =
    useState<ResourceStrategyDTO>();

  const [form] = Form.useForm();
  const [pods, setPods] = useState<PodDTO[]>([]);
  const [deployHistoryLogs, setDeployHistoryLog] = useState<DeployLogDTO[]>([]);
  const [branches, setBranches] = useState<BranchInfo[]>([]);

  // 配置项数据
  const [configMapData, setConfigMapData] = useState<
    { id: string; label: string; value: string; editable?: boolean }[]
  >([]);
  const [envVarsData, setEnvVarsData] = useState<
    { id: string; label: string; value: string; editable?: boolean }[]
  >([]);

  const columns = [
    {
      title: "发布单号",
      dataIndex: "releaseNo",
      key: "releaseNo",
      render: (text: any, record: ReleaseRecord) => {
        return (
          <Paragraph
            copyable={{ tooltips: ["点击复制", "复制成功"] }}
            style={{ display: "inline" }}
          >
            {record?.releaseNo}
          </Paragraph>
        );
      },
    },
    {
      title: "预计发布时间",
      dataIndex: "releaseDate",
      key: "releaseDate",
      render: (text: any, record: ReleaseRecord) => {
        return (
          <div>{dayjs(record?.releaseDate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "发布分支",
      dataIndex: "releaseBranch",
      key: "releaseBranch",
    },
    {
      title: "发布版本",
      dataIndex: "releaseVersion",
      key: "releaseVersion",
    },
    {
      title: "创建时间",
      dataIndex: "gmtCreate",
      key: "gmtCreate",
      render: (text: any, record: ReleaseRecord) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "更新时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: any, record: ReleaseRecord) => {
        return (
          <div>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "发布状态",
      dataIndex: "releaseStatus",
      key: "releaseStatus",
      render: (text: any, record: ReleaseRecord) => {
        let statusText = getReleaseStatusText(record.releaseStatus);
        return statusText;
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: ReleaseRecord) => (
        <Space size="middle">
          {/* 查看按钮 */}
          <a onClick={() => handleViewDetails(record)}>查看</a>
          {/* 关闭按钮 */}
          {record.releaseStatus !== "PENDING" &&
            record.releaseStatus !== "FINISHED" && (
              <a onClick={() => handleConfirmClose(record)}>关闭</a>
            )}
        </Space>
      ),
    },
  ];

  const podColumns = [
    {
      title: "POD名称",
      dataIndex: "name",
      key: "name",
      render: (text: any, record: PodDTO) => {
        return (
          <Paragraph
            copyable={{ tooltips: ["点击复制", "复制成功"] }}
            style={{ display: "inline" }}
          >
            {record?.name}
          </Paragraph>
        );
      },
    },
    {
      title: "命名空间",
      dataIndex: "namespace",
      key: "namespace",
    },
    {
      title: "POD IP",
      dataIndex: "podIp",
      key: "podIp",
    },
    {
      title: "阶段",
      dataIndex: "phase",
      key: "phase",
    },
    {
      title: "节点名称",
      dataIndex: "nodeName",
      key: "nodeName",
    },
    {
      title: "开始时间",
      dataIndex: "startTime",
      key: "startTime",
      render: (text: any, record: PodDTO) => {
        return (
          <div>{dayjs(record?.startTime).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "是否就绪",
      dataIndex: "isReady",
      key: "isReady",
      render: (text: any, record: PodDTO) => {
        return record.ready ? "是" : "否";
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: PodDTO) => <Space size="middle"></Space>,
    },
  ];

  // 处理关闭按钮的点击事件
  const handleConfirmClose = (record: ReleaseRecord) => {
    // 弹出二次确认框
    Modal.confirm({
      title: "确认关闭",
      content: "确定要关闭审批单吗？",
      onOk: () => {
        // 确认关闭后，可以在此处调用 dispatch 进行相关操作
        // 调用关闭接口等
        dispatch({
          type: "release/close",
          payload: { releaseId: record.releaseId },
        });
        pageRelease();
      },
    });
  };

  // 处理查看按钮的点击事件
  const handleViewDetails = (record: ReleaseRecord) => {
    setCurrentViewRelease(record);
    setViewDrawerVisible(true);
  };

  const handleViewHistoryLog = (pipeKey: string) => {
    dispatch({
      type: "release/queryDeployLog",
      payload: {
        pipeKey: pipeKey,
      },
      callback: (content: DeployLogDTO[]) => {
        setDeployHistoryLog(content);
        setHistoryLogDrawerVisible(!historyLogDrawerVisible);
      },
    });
  };

  const listAppBranch = (search: string) => {
    dispatch({
      type: "app/listBranch",
      payload: {
        appId: appDetail.appId,
        search: search || "",
      },
      callback: (content: BranchInfo[]) => {
        console.log(content);
        setBranches(content);
      },
    });
  };

  const pageRelease = useCallback(() => {
    if (selectedEnvironment) {
      console.log("开始调用环境信息", selectedEnvironment);
      dispatch({
        type: "app/getAppEnv",
        payload: { envId: selectedEnvironment },
      });
      dispatch({
        type: "app/listAppPods",
        payload: { envId: selectedEnvironment },
        callback: (res: PodDTO[]) => {
          console.log("pods", res);
          setPods(res);
        },
      });

      dispatch({
        type: "release/pageRelease",
        payload: {
          ...pagination,
          appId: id,
          envId: selectedEnvironment,
        },
      });

      const deployStatus = appEnv?.deployStatus;
      if (deployStatus?.toString() === "1") {
        setDeployDisabled(true);
        // 设置定时器，每 3 秒调用一次获取环境详情接口
        timerRef.current = setInterval(() => {
          dispatch({
            type: "app/getAppEnv",
            payload: { envId: selectedEnvironment },
          });
        }, 3000);
      } else {
        setDeployDisabled(false);
      }
    }
  }, [dispatch, pagination, id, selectedEnvironment, appEnv?.deployStatus]);

  useEffect(() => {
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  const pageDeployHistory = useCallback(() => {
    if (selectedEnvironment) {
      console.log("开始调用环境信息", selectedEnvironment);
      dispatch({
        type: "release/pageDeployHistory",
        payload: {
          ...pagination,
          appId: id,
          envId: selectedEnvironment,
        },
      });
    }
  }, [dispatch, historyPagination, id, selectedEnvironment]);

  useEffect(() => {
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  useEffect(() => {
    pageRelease();
    pageDeployHistory();
  }, [pageRelease, pageDeployHistory]);

  useEffect(() => {
    if (appEnv) {
      const configMap = appEnv.configMap || {};
      const envVars = appEnv.envVars || {};
      setConfigMapData(
        Object.entries(configMap).map(([label, value]) => ({
          id: nanoid(),
          label,
          value,
          editable: false,
        }))
      );

      setEnvVarsData(
        Object.entries(envVars).map(([label, value]) => ({
          id: nanoid(),
          label,
          value,
          editable: false,
        }))
      );

      setResourceStrategy(appEnv.resourceStrategy);
      // setDomains(appEnv.domains);
    }
  }, [appEnv]);

  useEffect(() => {
    // 在appDetail更新时，如果selectedEnvironment为undefined，则设置默认值
    if (!selectedEnvironment && appDetail?.appEnvList) {
      setSelectedEnvironment(appDetail?.appEnvList?.[0].envId);
    }
  }, [selectedEnvironment, appDetail]);

  // 在组件卸载时清除定时器
  useEffect(() => {
    return () => {
      // 清除定时器
      if (timerRef.current !== null) {
        clearInterval(timerRef.current);
      }
    };
  }, []);

  const rowSelection: TableRowSelection<ReleaseRecord> = {
    type: "radio",
    onChange: (selectedRowKeys: React.Key[], selectedRows: ReleaseRecord[]) => {
      setSelectedRow(selectedRows[0]);
    },
  };

  const handleRelease = () => {
    if (!selectedRow) {
      // 提示用户选择发布单
      message.warning("请选择符合条件的变更单");
      return;
    }
    if (selectedRow.releaseStatus === "CLOSED") {
      // 提示用户选择发布单
      message.warning("已关闭的变更单不允许发布");
      return;
    }

    // 检查是否有符合条件的发布单
    dispatch({
      type: "release/deploy",
      payload: { releaseId: selectedRow.releaseId },
      callback: () => {
        message.success("开始发布");
        pageRelease();
      },
    });
    // 清空选择
    setSelectedRow(null);
    window.location.reload();
  };

  const handleCreateReleaseDrawer = () => {
    if (drawerVisible === false) {
      listAppBranch("");
    }
    setDrawerVisible(!drawerVisible);
  };

  const handleEnvironmentChange = (value: any) => {
    setSelectedEnvironment(value);
    console.log("env change", value);
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddRelease = (values: any) => {
    // 处理添加发布单的逻辑，可以在这里提交表单等
    console.log("Form Values:", values);
    // 提交表单后关闭抽屉
    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "release/createRelease",
      payload: { ...values, appId: id, envId: selectedEnvironment },
      callback: () => {
        message.success("添加成功");
        pageRelease();
      },
    });
    setDrawerVisible(false);
  };

  // 新增 handleViewLogs 方法
  const handleViewLogs = (pipeKey: string) => {
    console.log("pipeKey", pipeKey);
    setPipeKey(pipeKey);
    setLogDrawerVisible(true);
  };

  const parsedProgress = useMemo(() => {
    // 只在appEnv有数据时才进行处理
    if (appEnv && appEnv.progress) {
      return JSON.parse(appEnv.progress);
    }
    return null;
  }, [appEnv]);

  return (
    <PageContainer
      title="应用中心"
      extra={[
        <Select
          key="environment"
          value={selectedEnvironment}
          onChange={handleEnvironmentChange}
          style={{ width: 120 }}
        >
          {appDetail?.appEnvList?.map((appEnv: AppEnv) => (
            <Select.Option key={appEnv.envId} value={appEnv.envId}>
              {appEnv.envName}
            </Select.Option>
          ))}
        </Select>,
      ]}
    >
      {appDetail && (
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Card
            title={`${appDetail?.appName}:[${appEnv?.envName}(${appEnv?.env})]`}
            extra={
              <Space>
                {parsedProgress?.pipeKey && (
                  <Button
                    onClick={() => handleViewLogs(parsedProgress?.pipeKey)}
                  >
                    查看日志
                  </Button>
                )}
              </Space>
            }
          >
            {/* 存在发布进度 */}
            {parsedProgress && (
              <Steps
                current={parsedProgress.current}
                status={parsedProgress.status}
                size="small"
              >
                {parsedProgress.steps.map((step: any, index: number) => (
                  <Steps.Step
                    key={index}
                    title={step.title}
                    description={
                      step.description === "AWAIT_EXECUTE" ? (
                        <div>待执行</div>
                      ) : step.description === "EXECUTING" ? (
                        <div>执行中</div>
                      ) : step.description === "EXECUTED" ? (
                        <div>
                          已执行
                          <br></br>
                          耗时:{step.cost}秒
                        </div>
                      ) : (
                        step.description
                      )
                    }
                  />
                ))}
              </Steps>
            )}
          </Card>

          <Card title="Pod节点">
            <Table
              columns={podColumns}
              dataSource={pods}
              rowKey={"name"}
              pagination={false}
            />
          </Card>

          <Collapse defaultActiveKey={[]}>
            {[
              {
                header: "资源配置",
                key: 0,
                config: (
                  <EnvResourcePanel
                    resourceStrategy={resourceStrategy}
                    selectedEnvironment={selectedEnvironment}
                  ></EnvResourcePanel>
                ),
              },
              {
                header: "环境变量",
                key: 1,
                config: (
                  <EnvVarConfigPanel
                    initialEnvVars={envVarsData}
                    selectedEnvironment={selectedEnvironment}
                  />
                ),
              },
              {
                header: "配置项",
                key: 2,
                config: (
                  <ConfigMapConfigPanel
                    initialConfigMap={configMapData}
                    selectedEnvironment={selectedEnvironment}
                  />
                ),
              },
              {
                header: "服务域名配置",
                key: 3,
                config: (
                  <DomainHostConfigPanel
                    appEnv={appEnv}
                    appName={appDetail.appName}
                  ></DomainHostConfigPanel>
                ),
              },
            ].map((item) => (
              <Collapse.Panel key={item.key} header={item.header}>
                {item.config}
              </Collapse.Panel>
            ))}
          </Collapse>

          {/* 发布单 */}
          <Card
            title="发布单"
            extra={
              <Space>
                <Button
                  key="release"
                  onClick={handleRelease}
                  disabled={deployDisabled || !selectedRow}
                >
                  立即发布
                </Button>
                <Button onClick={handleCreateReleaseDrawer}>添加发布单</Button>
              </Space>
            }
          >
            <Table
              columns={columns}
              dataSource={releases.list}
              rowKey={"releaseId"}
              rowSelection={{
                ...rowSelection,
              }}
              pagination={{
                total: releases.total,
                current: pagination.pageNo,
                pageSize: pagination.pageSize,
                onChange: handlePaginationChange,
              }}
            />
          </Card>

          <Card title="发布历史">
            <Table
              columns={[
                {
                  title: "pipeKey",
                  dataIndex: "pipeKey",
                  key: "pipeKey",
                  render: (text: any, record: DeployHistoryDTO) => {
                    return (
                      <Paragraph
                        copyable={{ tooltips: ["点击复制", "复制成功"] }}
                        style={{ display: "inline" }}
                      >
                        {record?.pipeKey}
                      </Paragraph>
                    );
                  },
                },
                {
                  title: "开始时间",
                  dataIndex: "startTime",
                  key: "releaseDate",
                  render: (text: any, record: DeployHistoryDTO) => {
                    return (
                      <div>
                        {dayjs(record?.startTime).format("YYYY-MM-DD HH:mm:ss")}
                      </div>
                    );
                  },
                },
                {
                  title: "结束时间",
                  dataIndex: "gmtModified",
                  key: "gmtModified",
                  render: (text: any, record: DeployHistoryDTO) => {
                    return (
                      <div>
                        {record?.endTime &&
                          dayjs(record?.endTime).format("YYYY-MM-DD HH:mm:ss")}
                      </div>
                    );
                  },
                },
                {
                  title: "发布内容",
                  dataIndex: "content",
                  key: "content",
                },
                {
                  title: "发布人",
                  dataIndex: "creatorName",
                  key: "creatorName",
                },
                {
                  title: "发布状态",
                  dataIndex: "deployStatus",
                  key: "deployStatus",
                  render: (text: any, record: DeployHistoryDTO) => {
                    let statusText = getDeployStatusText(record.deployStatus);
                    return statusText;
                  },
                },
                {
                  title: "操作",
                  key: "action",
                  render: (text: any, record: DeployHistoryDTO) => (
                    <Space size="middle">
                      <a onClick={() => handleViewHistoryLog(record.pipeKey)}>
                        查看日志
                      </a>
                    </Space>
                  ),
                },
              ]}
              dataSource={deployHistory.list}
              rowKey={"pipeKey"}
              pagination={{
                total: deployHistory.total,
                current: historyPagination.pageNo,
                pageSize: historyPagination.pageSize,
                onChange: (page, pageSize) => {
                  setHistoryPagination({
                    pageNo: page,
                    pageSize: pageSize || 10,
                  });
                },
              }}
            />
          </Card>
        </Space>
      )}
      {/* 新建发布单抽屉 */}
      <Drawer
        title="添加发布单"
        width={600}
        onClose={handleCreateReleaseDrawer}
        open={drawerVisible}
      >
        {/* 表单 */}
        <Form
          layout="vertical"
          form={form}
          initialValues={{
            releaseDate: "",
            releaseBranch: "",
            docAddress: "",
            appId: id,
            envId: selectedEnvironment,
          }}
          onFinish={(values) => handleAddRelease(values)}
        >
          <Form.Item label="预计发布日期" name="releaseDate">
            <DatePicker showTime defaultValue={moment()} />
          </Form.Item>
          <Form.Item
            label="发布分支"
            name="releaseBranch"
            rules={[{ required: true, message: "请输入发布分支" }]}
          >
            <Select placeholder="请选择分支">
              {branches?.map((branch) => (
                <Option key={branch.name} value={branch.name}>
                  {branch.name}
                </Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item
            label="发布版本"
            name="releaseVersion"
            rules={[{ required: true, message: "请输入发布版本" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="文档地址"
            name="docAddress"
            rules={[{ required: true, message: "请输入文档地址" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="备注"
            name="comment"
            rules={[{ required: true, message: "请输入发布分支" }]}
          >
            <Input.TextArea />
          </Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form>
      </Drawer>

      {/* 发布单详情抽屉 */}
      <Drawer
        title="发布单详情"
        width={600}
        onClose={() => setViewDrawerVisible(false)}
        open={viewDrawerVisible}
      >
        {currentViewRelease && (
          <Space style={{ width: "100%" }} direction="vertical" size="small">
            <Descriptions
              labelStyle={{ width: "160px" }}
              column={1}
              bordered
              title="基础信息"
            >
              <Descriptions.Item label="发布单号">
                <Paragraph
                  copyable={{ tooltips: ["点击复制", "复制成功"] }}
                  style={{ display: "inline" }}
                >
                  {currentViewRelease.releaseNo}
                </Paragraph>
              </Descriptions.Item>
              <Descriptions.Item label="预计发布时间">
                {dayjs(currentViewRelease?.releaseDate).format(
                  "YYYY-MM-DD HH:mm:ss"
                )}
              </Descriptions.Item>
              <Descriptions.Item label="发布分支">
                {currentViewRelease.releaseBranch}
              </Descriptions.Item>
              <Descriptions.Item label="发布版本">
                {currentViewRelease.releaseVersion}
              </Descriptions.Item>
              <Descriptions.Item label="创建时间">
                {dayjs(currentViewRelease?.gmtCreate).format(
                  "YYYY-MM-DD HH:mm:ss"
                )}
              </Descriptions.Item>
              <Descriptions.Item label="更新时间">
                {dayjs(currentViewRelease?.gmtModified).format(
                  "YYYY-MM-DD HH:mm:ss"
                )}
              </Descriptions.Item>
              <Descriptions.Item label="发布状态">
                {getReleaseStatusText(currentViewRelease.releaseStatus)}
              </Descriptions.Item>
              {/* 其他字段按照需要添加 */}
            </Descriptions>

            {/* 审批详情 */}
            {currentViewRelease.approvalDTO && (
              <Descriptions
                labelStyle={{ width: "160px" }}
                column={1}
                bordered
                title="审批单详情"
              >
                <Descriptions.Item label="发布单号">
                  <Paragraph
                    copyable={{ tooltips: ["点击复制", "复制成功"] }}
                    style={{ display: "inline" }}
                  >
                    {currentViewRelease.approvalDTO.approvalId}
                  </Paragraph>
                </Descriptions.Item>
                <Descriptions.Item label="发起时间">
                  {dayjs(currentViewRelease.approvalDTO?.changeDate).format(
                    "YYYY-MM-DD HH:mm:ss"
                  )}
                </Descriptions.Item>

                <Descriptions.Item label="文档地址">
                  {currentViewRelease.approvalDTO?.docAddress}
                </Descriptions.Item>
                <Descriptions.Item label="审批状态">
                  {getApprovalStatusText(
                    currentViewRelease.approvalDTO.approvalStatus
                  )}
                </Descriptions.Item>
              </Descriptions>
            )}

            <Button
              onClick={() => setViewDrawerVisible(false)}
              style={{ marginTop: 16 }}
            >
              关闭
            </Button>
          </Space>
        )}
      </Drawer>

      {/* 发布单详情抽屉 */}
      <Drawer
        title="发布记录日志"
        width={1200}
        onClose={() => setHistoryLogDrawerVisible(false)}
        open={historyLogDrawerVisible}
      >
        {/* 发布单详情 */}
        {deployHistoryLogs?.map((log: DeployLogDTO) => (
          <div>
            {log.time} {log.message}
          </div>
        ))}
      </Drawer>

      <DeployLogDrawer
        open={logDrawerVisible}
        onClose={() => {
          setLogDrawerVisible(false);
        }}
        pipeKey={pipeKey}
      ></DeployLogDrawer>
    </PageContainer>
  );
};

export default connect(
  ({
    app,
    release,
  }: {
    app: {
      appDetail: AppInfo;
      appEnv: AppEnv;
    };
    release: {
      releases: {
        total: number;
        list: ReleaseRecord[];
      };
      deployHistory: {
        total: number;
        list: DeployHistoryDTO[];
      };
    };
  }) => ({
    appDetail: app.appDetail,
    appEnv: app.appEnv,
    releases: {
      total: release.releases.total,
      list: release.releases.list,
    },
    deployHistory: {
      total: release.deployHistory.total,
      list: release.deployHistory.list,
    },
  })
)(DeployPage);
