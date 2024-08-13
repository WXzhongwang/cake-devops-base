import React, {
  useCallback,
  useEffect,
  useMemo,
  useState,
  useRef,
} from "react";
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
  Collapse,
} from "antd";
import { connect, Dispatch, useParams, history } from "umi";
import { AppEnv, AppInfo } from "@/models/app";
import { ReleaseRecord } from "@/models/release";
import moment from "moment";
import dayjs from "dayjs";
import { TableRowSelection } from "antd/lib/table/interface";
import DeployLogDrawer from "./components/deploy-log-drawer";
import app from "mock/app";

const { Paragraph } = Typography;
const { Panel } = Collapse;

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
  dispatch: Dispatch;
  appDetail: AppInfo | null;
  releases: {
    total: number;
    list: ReleaseRecord[];
  };
  appEnv: AppEnv | null;
}
const DeployPage: React.FC<ReleasePageProps> = ({
  dispatch,
  appDetail,
  releases,
  appEnv,
}) => {
  const { id } = useParams();
  // 发布单详情抽屉
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [selectedEnvironment, setSelectedEnvironment] = useState<
    string | undefined | null
  >(appDetail?.appEnvList?.[0]?.envId);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [deployDisabled, setDeployDisabled] = useState(false);
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

  const [form] = Form.useForm();

  // 配置项数据
  // 配置项数据
  const [configMapData, setConfigMapData] = useState<
    { key: string; value: string; editable?: boolean }[]
  >([]);
  const [envVarsData, setEnvVarsData] = useState<
    { key: string; value: string; editable?: boolean }[]
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
        // const tagColor = record.releaseStatus === "0" ? "success" : "error";
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
          {record.releaseStatus !== "PENDING" && (
            <a onClick={() => handleConfirmClose(record)}>关闭</a>
          )}
        </Space>
      ),
    },
  ];

  // 行编辑状态切换
  const toggleEditRow = (index: number, type: "config" | "env") => {
    if (type === "config") {
      const newData = [...configMapData];
      newData[index].editable = !newData[index].editable;
      setConfigMapData(newData);
    } else {
      const newData = [...envVarsData];
      newData[index].editable = !newData[index].editable;
      setEnvVarsData(newData);
    }
  };

  // 保存编辑
  const saveRow = (index: number, type: "config" | "env") => {
    toggleEditRow(index, type); // 切换回不可编辑状态
  };
  // 添加新行
  const addNewRow = (type: "config" | "env") => {
    const newRow = { key: "", value: "", editable: true };
    if (type === "config") {
      setConfigMapData([...configMapData, newRow]);
    } else {
      setEnvVarsData([...envVarsData, newRow]);
    }
  };

  // 删除行
  const deleteRow = (index: number, type: "config" | "env") => {
    if (type === "config") {
      const newData = [...configMapData];
      newData.splice(index, 1);
      setConfigMapData(newData);
    } else {
      const newData = [...envVarsData];
      newData.splice(index, 1);
      setEnvVarsData(newData);
    }
  };

  // 修改行内容
  const updateRow = (
    index: number,
    key: string,
    value: string,
    type: "config" | "env"
  ) => {
    if (type === "config") {
      const newData = [...configMapData];
      newData[index] = { ...newData[index], key, value };
      setConfigMapData(newData);
    } else {
      const newData = [...envVarsData];
      newData[index] = { ...newData[index], key, value };
      setEnvVarsData(newData);
    }
  };

  const configColumns = (type: "config" | "env") => [
    {
      title: "Key 键",
      dataIndex: "key",
      render: (text: string, record: any, index: number) => (
        <Input
          value={text}
          disabled={!record.editable}
          onChange={(e) => updateRow(index, e.target.value, record.value, type)}
        />
      ),
    },
    {
      title: "Value 值",
      dataIndex: "value",
      render: (text: string, record: any, index: number) => (
        <Input
          value={text}
          disabled={!record.editable}
          onChange={(e) => updateRow(index, record.key, e.target.value, type)}
        />
      ),
    },
    {
      title: "操作",
      width: 200, // 设置固定宽度
      render: (_: any, __: any, index: number) => (
        <Space>
          {configMapData[index].editable ? (
            <>
              <Button onClick={() => saveRow(index, type)}>保存</Button>
              <Button onClick={() => deleteRow(index, type)}>删除</Button>
            </>
          ) : (
            <Button onClick={() => toggleEditRow(index, type)}>编辑</Button>
          )}
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

  const pageRelease = useCallback(() => {
    if (selectedEnvironment) {
      console.log("开始调用环境信息", selectedEnvironment);
      dispatch({
        type: "app/getAppEnv",
        payload: { envId: selectedEnvironment },
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

  useEffect(() => {
    pageRelease();
  }, [pageRelease]);

  useEffect(() => {
    if (appEnv) {
      const configMap = appEnv.configMap || {};
      const envVars = appEnv.envVars || {};
      setConfigMapData(
        Object.entries(configMap).map(([key, value]) => ({
          key,
          value,
          editable: false,
        }))
      );
      setEnvVarsData(
        Object.entries(envVars).map(([key, value]) => ({
          key,
          value,
          editable: false,
        }))
      );
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
  };

  const handleCreateReleaseDrawer = () => {
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

  const submitConfig = () => {
    // 提交配置项数据
    if (configMapData.some((item) => !item.key || !item.value)) {
      message.warning("配置项不能为空");
      return;
    }
    // 将 configMapData 转换为对象
    const configMap = configMapData.reduce(
      (acc, { key, value }) => {
        if (key) acc[key] = value;
        return acc;
      },
      {} as Record<string, string>
    );

    dispatch({
      type: "app/modifyAppEnvConfigMap",
      payload: {
        envId: selectedEnvironment,
        configMap: configMap,
      },
    });
  };

  const submitEnvVars = () => {
    // 提交环境变量数据
    if (envVarsData.some((item) => !item.key || !item.value)) {
      message.warning("环境变量不能为空");
      return;
    }
    // 将 configMapData 转换为对象
    const envVars = envVarsData.reduce(
      (acc, { key, value }) => {
        if (key) acc[key] = value;
        return acc;
      },
      {} as Record<string, string>
    );

    dispatch({
      type: "app/modifyAppEnvVars",
      payload: {
        envId: selectedEnvironment,
        envVars: envVars,
      },
    });
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
      <Space size="middle" direction="vertical" style={{ width: "100%" }}>
        <Card
          title={
            appDetail?.appName +
            ": [" +
            appEnv?.envName +
            "(" +
            appEnv?.env +
            ")" +
            "]"
          }
          extra={
            <div>
              {parsedProgress?.pipeKey && (
                <Button onClick={() => handleViewLogs(parsedProgress?.pipeKey)}>
                  查看发布日志
                </Button>
              )}

              <Button
                key="release"
                onClick={handleRelease}
                disabled={deployDisabled || !selectedRow}
              >
                立即发布
              </Button>
            </div>
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

        <Collapse defaultActiveKey={[]}>
          <Panel header={"配置项"} key="1">
            <Table
              columns={configColumns("config")}
              dataSource={configMapData}
              pagination={false}
              rowKey="key"
              size="small" // 使表格更小巧
              style={{ marginBottom: 16 }} // 添加表格底部间距
            />
            <Space style={{ marginBottom: 16 }}>
              <Button onClick={() => addNewRow("config")}>添加配置项</Button>
              <Button type="primary" onClick={submitConfig}>
                提交配置
              </Button>
            </Space>
          </Panel>
          <Panel header={"环境变量"} key="2">
            <Table
              columns={configColumns("env")}
              dataSource={envVarsData}
              pagination={false}
              rowKey="key"
              size="small" // 使表格更小巧
              style={{ marginBottom: 16 }} // 添加表格底部间距
            />
            <Space style={{ marginBottom: 16 }}>
              <Button onClick={() => addNewRow("env")}>添加环境变量</Button>
              <Button type="primary" onClick={submitEnvVars}>
                提交环境变量
              </Button>
            </Space>
          </Panel>
        </Collapse>

        <Card
          title="发布单"
          extra={
            <div>
              <Button onClick={handleCreateReleaseDrawer}>添加发布单</Button>
            </div>
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
      </Space>

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
            <Input />
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
    };
  }) => ({
    appDetail: app.appDetail,
    appEnv: app.appEnv,
    releases: {
      total: release.releases.total,
      list: release.releases.list,
    },
  })
)(DeployPage);
