import React, { useEffect, useState } from "react";
import {
  Descriptions,
  Typography,
  Card,
  Tag,
  Button,
  Drawer,
  Form,
  Select,
  Input,
  Radio,
  Space,
  Row,
  Col,
  message,
} from "antd";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import { connect, Dispatch, useParams, history } from "umi";
import { AppInfo, AppEnv, AppMemberDTO } from "@/models/app";
import { ClusterInfo } from "@/models/cluster";
import CreateEnvDrawer from "./components/create-env-drawer";
import TeamMembersDrawer from "./components/team-member-drawer";
import dayjs from "dayjs";

const { Paragraph } = Typography;
const { Option } = Select;
interface AppDetailProps {
  dispatch: Dispatch;
}

export interface AppMemberPage {
  total: number;
  items: AppMemberDTO[];
}

interface CreateEnvFormProps {
  envName: string;
  env: string;
  clusterId: string;
  domains: string[] | [];
  autoScaling: boolean;
  needApproval: boolean;
  replicas: number;
  cpu: string;
  memory: string;
  maxCpu: string;
  maxMemory: string;
}

const AppDetail: React.FC<AppDetailProps> = ({ dispatch }) => {
  const { id } = useParams();
  const [appDetail, setAppDetail] = useState<AppInfo>();
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [teamMembersDrawerVisible, setTeamMembersDrawerVisible] =
    useState(false);
  const [clusterList, setClusterList] = useState<ClusterInfo[]>([]);
  const [appMemberPage, setAppMemberPage] = useState<AppMemberPage>();

  const [form] = Form.useForm<CreateEnvFormProps>();

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
      callback: (res: AppInfo) => {
        setAppDetail(res);
      },
    });
  }, [dispatch, id]);

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    pageAppMembers();
    fetchClusterList();
  }, [teamMembersDrawerVisible]);

  const fetchClusterList = () => {
    dispatch({
      type: "cluster/listAll",
      callback: (res: ClusterInfo[]) => {
        setClusterList(res);
      },
    });
  };

  const pageAppMembers = () => {
    dispatch({
      type: "app/pageAppMembers",
      payload: { ...pagination, appId: id },
      callback: (res: AppMemberPage) => {
        setAppMemberPage(res);
      },
    });
  };

  // 显示环境抽屉的方法
  const switchDrawer = () => {
    setDrawerVisible(!drawerVisible);
  };

  // 关闭成员抽屉的方法
  const switchMemberDrawer = () => {
    setTeamMembersDrawerVisible(!teamMembersDrawerVisible);
  };

  // 提交抽屉表单的方法
  const onFinish = (values: CreateEnvFormProps) => {
    console.log("Received values:", values);
    dispatch({
      type: "app/createAppEnv",
      payload: {
        appId: id,
        env: {
          env: values.env,
          clusterId: values.clusterId,
          envName: values.envName,
          needApproval: values.needApproval,
          autoScaling: values.autoScaling,
          // domains: values.domains,
          domains: [],
          resourceStrategy: {
            replicas: values.replicas,
            cpu: values.cpu,
            memory: values.memory,
            maxCpu: values.maxCpu,
            maxMemory: values.maxCpu,
          },
        },
      },
      callback: () => {
        message.success("应用环境成功");
      },
    });

    // 关闭抽屉
    switchDrawer();
  };

  const handleDeploy = () => {
    // 示例：跳转到详情页，使用 history.push
    history.push(`/devops/ci/app/deploy/${id}`);
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical" size="large">
      {appDetail && (
        <Card
          title={`${appDetail.appName} 详情页`}
          extra={
            <div>
              <Button
                disabled={appDetail.appEnvList.length == 0}
                onClick={handleDeploy}
              >
                立即部署
              </Button>
              <Button onClick={switchMemberDrawer} style={{ marginLeft: 16 }}>
                项目成员
              </Button>
            </div>
          }
        >
          <Space style={{ width: "100%" }} direction="vertical" size="large">
            <Descriptions title="应用基础详情" bordered>
              <Descriptions.Item label="应用名称">
                <Paragraph
                  copyable={{ tooltips: ["点击复制", "复制成功"] }}
                  style={{ display: "inline" }}
                >
                  {appDetail.appName ?? "--"}
                </Paragraph>
              </Descriptions.Item>
              <Descriptions.Item label="仓库">
                <Paragraph
                  copyable={{ tooltips: ["点击复制", "复制成功"] }}
                  style={{ display: "inline" }}
                >
                  {appDetail.repo}
                </Paragraph>
              </Descriptions.Item>
              <Descriptions.Item label="语言">
                {appDetail.language}
              </Descriptions.Item>
              <Descriptions.Item label="默认分支">
                {appDetail.defaultBranch}
              </Descriptions.Item>
              <Descriptions.Item label="开发模式">
                {appDetail.developMode}
              </Descriptions.Item>
              <Descriptions.Item label="部门">
                {appDetail.department}
              </Descriptions.Item>
              <Descriptions.Item label="部门缩写">
                {appDetail.departmentAbbreviation}
              </Descriptions.Item>
              <Descriptions.Item label="创建时间">
                {dayjs(appDetail.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}
              </Descriptions.Item>
              <Descriptions.Item label="更新时间">
                {dayjs(appDetail.gmtModified).format("YYYY-MM-DD HH:mm:ss")}
              </Descriptions.Item>
            </Descriptions>
          </Space>

          <CreateEnvDrawer
            onClose={switchDrawer}
            onFinish={onFinish}
            open={drawerVisible}
            clusterList={clusterList}
          />
          <TeamMembersDrawer
            appMembers={appMemberPage}
            onClose={switchMemberDrawer}
            open={teamMembersDrawerVisible}
          />
        </Card>
      )}

      <Card
        title={`环境信息`}
        extra={
          <div>
            <Button onClick={switchDrawer}>添加环境</Button>
          </div>
        }
      >
        <Row justify="start">
          {appDetail &&
            appDetail.appEnvList?.map((appEnv: AppEnv) => (
              <Col span={8} key={appEnv.envId}>
                <Card
                  title={appEnv.envName}
                  key={appEnv.envId}
                  // extra={<a href="#">详情</a>}
                  style={{ width: 300, marginBottom: "16px" }}
                >
                  <p>环境: {appEnv.env}</p>
                  <p>
                    环境状态:{" "}
                    <Tag color={appEnv.status === "0" ? "success" : "error"}>
                      {appEnv.status === "0" ? "已启用" : "已停用"}
                    </Tag>
                  </p>
                  <div style={{ marginTop: "16px" }}>
                    <h5>资源策略</h5>
                    <Descriptions colon column={1} bordered size="small">
                      <Descriptions.Item label="副本数">
                        {appEnv.resourceStrategy.replicas}
                      </Descriptions.Item>
                      <Descriptions.Item label="CPU">
                        {appEnv.resourceStrategy.cpu}
                      </Descriptions.Item>
                      <Descriptions.Item label="内存">
                        {appEnv.resourceStrategy.memory}
                      </Descriptions.Item>
                      <Descriptions.Item label="最大CPU">
                        {appEnv.resourceStrategy.maxCpu}
                      </Descriptions.Item>
                      <Descriptions.Item label="最大内存">
                        {appEnv.resourceStrategy.maxMemory}
                      </Descriptions.Item>
                    </Descriptions>
                  </div>
                </Card>
              </Col>
            ))}
        </Row>
      </Card>
    </Space>
  );
};

export default connect(
  ({
    app,
    cluster,
  }: {
    app: {
      appMembers: any;
    };
    cluster: {
      clusterList: ClusterInfo[];
    };
  }) => ({
    appMembers: app.appMembers,
    clusterList: cluster.clusterList,
  })
)(AppDetail);
