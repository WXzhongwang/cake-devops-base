import React, {
  useCallback,
  useEffect,
  useMemo,
  useState,
  useRef,
} from "react";
import { nanoid } from "nanoid";

import { PageContainer, PageLoading } from "@ant-design/pro-components";
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
  Result,
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
import ReleaseDetailsDrawer from "./components/release-detail-drawer";
import DeployHistoryLogsDrawer from "./components/deploy-history-logs-drawer";
import {
  getReleaseStatusText,
  getDeployStatusText,
  getApprovalStatusText,
} from "@/utils/release-utils";
import CreateReleaseDrawer from "./components/create-release-crawer";

const { Paragraph } = Typography;
const { Option } = Select;

interface ReleasePageProps {
  dispatch: Dispatch;
}

interface ReleasePage {
  total: number;
  items: ReleaseRecord[];
}

interface DeployHistoryPage {
  total: number;
  items: DeployHistoryDTO[];
}

const DeployPage: React.FC<ReleasePageProps> = ({ dispatch }) => {
  const { id } = useParams();
  // 发布单详情抽屉
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [historyPagination, setHistoryPagination] = useState({
    pageNo: 1,
    pageSize: 10,
  });
  const [appDetail, setAppDetail] = useState<AppInfo>();
  const [deployDisabled, setDeployDisabled] = useState(false);
  const [loading, setLoading] = useState(true);

  const [selectedEnvironment, setSelectedEnvironment] = useState<string | null>(
    null
  );

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
  const [appEnv, setAppEnv] = useState<AppEnv>();
  const [deployHistoryLogs, setDeployHistoryLog] = useState<DeployLogDTO[]>([]);
  const [branches, setBranches] = useState<BranchInfo[]>([]);
  const [releaseRecordPage, setReleaseRecordPage] = useState<ReleasePage>();
  const [deployHistoryPage, setDeployHistoryPage] =
    useState<DeployHistoryPage>();

  // 配置项数据
  const [configMapData, setConfigMapData] = useState<
    { id: string; label: string; value: string; editable?: boolean }[]
  >([]);
  const [envVarsData, setEnvVarsData] = useState<
    { id: string; label: string; value: string; editable?: boolean }[]
  >([]);

  const releaseRecordColumns = [
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

  const deployHistoryColumns = [
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
          <div>{dayjs(record?.startTime).format("YYYY-MM-DD HH:mm:ss")}</div>
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
          <a onClick={() => handleViewHistoryLog(record.pipeKey)}>查看日志</a>
        </Space>
      ),
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
    if (!appDetail) {
      return;
    }
    dispatch({
      type: "app/listBranch",
      payload: {
        appId: appDetail.appId,
        search: search || "",
      },
      callback: (content: BranchInfo[]) => {
        setBranches(content);
      },
    });
  };

  const getAppDetail = (id: string | undefined) => {
    if (!id) {
      return;
    }
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
      callback: (res: AppInfo) => {
        setAppDetail(res);
        if (res.appEnvList && res.appEnvList.length > 0) {
          setSelectedEnvironment(res.appEnvList[0].envId);
        }
      },
    });
  };

  const getCurrentAppEnv = (selectedEnvironment: string | null) => {
    if (!selectedEnvironment) {
      return;
    }
    dispatch({
      type: "app/getAppEnv",
      payload: {
        envId: selectedEnvironment,
      },
      callback: (content: AppEnv) => {
        setAppEnv(content);
      },
    });
  };

  const listAppPods = (selectedEnvironment: string | null) => {
    if (!selectedEnvironment) {
      return;
    }
    dispatch({
      type: "app/listAppPods",
      payload: { envId: selectedEnvironment },
      callback: (res: PodDTO[]) => {
        setPods(res);
      },
    });
  };

  const pageReleaseRecord = (
    appId: string,
    pageNo: number,
    pageSize: number,
    envId: string
  ) => {
    dispatch({
      type: "release/pageReleaseRecord",
      payload: {
        appId: appId,
        pageNo: pageNo,
        pageSize: pageSize,
        envId: envId,
      },
      callback: (res: ReleasePage) => {
        setReleaseRecordPage(res);
      },
    });
  };

  const pageDeployHistoryRecord = (
    appId: string,
    pageNo: number,
    pageSize: number,
    envId: string
  ) => {
    dispatch({
      type: "release/pageDeployHistory",
      payload: {
        pageNo: pageNo,
        pageSize: pageSize,
        appId: appId,
        envId: envId,
      },
      callback: (res: DeployHistoryPage) => {
        setDeployHistoryPage(res);
      },
    });
  };

  const pageRelease = useCallback(() => {
    if (selectedEnvironment && id) {
      getCurrentAppEnv(selectedEnvironment);
      listAppPods(selectedEnvironment);
      pageReleaseRecord(
        id,
        pagination.pageNo,
        pagination.pageSize,
        selectedEnvironment
      );

      const deployStatus = appEnv?.deployStatus;
      if (deployStatus?.toString() === "1") {
        setDeployDisabled(true);
        // 设置定时器，每 3 秒调用一次获取环境详情接口
        timerRef.current = setInterval(() => {
          getCurrentAppEnv(selectedEnvironment);
        }, 3000);
      } else {
        setDeployDisabled(false);
      }
    }
  }, [pagination, id, selectedEnvironment, appEnv?.deployStatus]);

  useEffect(() => {
    getAppDetail(id);
    setLoading(false);
  }, [id]);

  const pageDeployHistory = useCallback(() => {
    if (selectedEnvironment && id) {
      pageDeployHistoryRecord(
        id,
        historyPagination.pageNo,
        historyPagination.pageSize,
        selectedEnvironment
      );
    }
  }, [historyPagination, id, selectedEnvironment]);

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
    }
  }, [appEnv]);

  useEffect(() => {
    if (selectedEnvironment) {
      getCurrentAppEnv(selectedEnvironment);
    }
  }, [selectedEnvironment]);

  useEffect(() => {
    pageRelease();
    pageDeployHistory();
  }, [pageRelease, pageDeployHistory]);

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

  const handleAddRelease = (values: any) => {
    console.log("Form Values:", values);
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

  if (loading) {
    return <PageLoading />;
  }

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
      {!appDetail && (
        <Result
          status="error"
          title="警告"
          subTitle="未找到应用"
          extra={
            <Button type="primary" onClick={() => history.push(`/`)}>
              创建应用
            </Button>
          }
        />
      )}

      {appDetail && appDetail.appEnvList?.length == 0 && (
        <Result
          status="warning"
          title="警告"
          subTitle="当前应用没有环境，请创建环境。"
          extra={
            <Button
              type="primary"
              onClick={() => history.push(`/devops/ci/app/info/${id}`)}
            >
              创建环境
            </Button>
          }
        />
      )}

      {appDetail && appDetail.appEnvList?.length > 0 && (
        <Space size="small" direction="vertical" style={{ width: "100%" }}>
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
                header: "ConfigMap",
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
            {releaseRecordPage && (
              <Table
                columns={releaseRecordColumns}
                dataSource={releaseRecordPage.items}
                rowKey={"releaseId"}
                rowSelection={{
                  ...rowSelection,
                }}
                pagination={{
                  total: releaseRecordPage.total,
                  current: pagination.pageNo,
                  pageSize: pagination.pageSize,
                  onChange: (page, pageSize) => {
                    setPagination({
                      pageNo: page,
                      pageSize: pageSize || 10,
                    });
                  },
                }}
              />
            )}
          </Card>

          <Card title="发布历史">
            {deployHistoryPage && (
              <Table
                columns={deployHistoryColumns}
                dataSource={deployHistoryPage.items}
                rowKey={"pipeKey"}
                pagination={{
                  total: deployHistoryPage.total,
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
            )}
          </Card>

          <CreateReleaseDrawer
            visible={drawerVisible}
            onClose={handleCreateReleaseDrawer}
            appId={id}
            selectedEnvironment={selectedEnvironment}
            listAppBranch={listAppBranch}
            branches={branches}
            handleAddRelease={handleAddRelease}
          />

          <ReleaseDetailsDrawer
            visible={viewDrawerVisible}
            onClose={() => setViewDrawerVisible(false)}
            release={currentViewRelease}
          />

          <DeployHistoryLogsDrawer
            visible={historyLogDrawerVisible}
            onClose={() => setHistoryLogDrawerVisible(false)}
            deployHistoryLogs={deployHistoryLogs}
          />

          <DeployLogDrawer
            open={logDrawerVisible}
            onClose={() => {
              setLogDrawerVisible(false);
            }}
            pipeKey={pipeKey}
          />
        </Space>
      )}
    </PageContainer>
  );
};

export default connect()(DeployPage);
