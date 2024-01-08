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
} from "antd";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import { connect, Dispatch, useParams } from "umi";
import { AppInfo, AppEnv, AppMemberDTO } from "@/models/app";
import { ClusterInfo } from "@/models/cluster";
import CreateEnvDrawer from "./components/create-env-drawer";
import TeamMembersDrawer from "./components/team-member-drawer";
import dayjs from "dayjs";

const { Paragraph } = Typography;
const { Option } = Select;
interface AppDetailProps {
  dispatch: Dispatch;
  appDetail: AppInfo | null;
  appMembers: {
    total: number;
    list: AppMemberDTO[];
  };
  clusterList: ClusterInfo[] | [];
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

const AppDetail: React.FC<AppDetailProps> = ({
  dispatch,
  appDetail,
  clusterList,
  appMembers,
}) => {
  const { id } = useParams();
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [teamMembersDrawerVisible, setTeamMembersDrawerVisible] =
    useState(false);

  const [form] = Form.useForm<CreateEnvFormProps>();

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    pageAppMembers();
    dispatch({
      type: "cluster/listAll",
    });
  }, [dispatch, teamMembersDrawerVisible]);

  const pageAppMembers = () => {
    dispatch({
      type: "app/pageAppMembers",
      payload: { ...pagination, appId: id },
    });
  };

  // 显示抽屉的方法
  const switchDrawer = () => {
    setDrawerVisible(!drawerVisible);
  };

  // 关闭抽屉的方法
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
          domains: values.domains,
          resourceStrategy: {
            replicas: values.replicas,
            cpu: values.cpu,
            memory: values.memory,
            maxCpu: values.maxCpu,
            maxMemory: values.maxCpu,
          },
        },
      },
    });

    // 关闭抽屉
    switchDrawer();
  };

  console.log("clusters", clusterList);

  return (
    <Card
      title={`${appDetail?.appName} 详情页`}
      extra={
        <div>
          <Button onClick={switchDrawer}>添加环境</Button>
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
              {appDetail?.appName ?? "--"}
            </Paragraph>
          </Descriptions.Item>
          <Descriptions.Item label="仓库">
            <Paragraph
              copyable={{ tooltips: ["点击复制", "复制成功"] }}
              style={{ display: "inline" }}
            >
              {appDetail?.repo}
            </Paragraph>
          </Descriptions.Item>
          <Descriptions.Item label="语言">
            {appDetail?.language}
          </Descriptions.Item>
          <Descriptions.Item label="默认分支">
            {appDetail?.defaultBranch}
          </Descriptions.Item>
          <Descriptions.Item label="开发模式">
            {appDetail?.developMode}
          </Descriptions.Item>
          <Descriptions.Item label="部门">
            {appDetail?.department}
          </Descriptions.Item>
          <Descriptions.Item label="部门缩写">
            {appDetail?.departmentAbbreviation}
          </Descriptions.Item>
          <Descriptions.Item label="创建时间">
            {dayjs(appDetail?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}
          </Descriptions.Item>
          <Descriptions.Item label="更新时间">
            {dayjs(appDetail?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}
          </Descriptions.Item>
          {/* 添加其他属性 */}
        </Descriptions>
        <Row justify="start">
          {appDetail?.appEnvList.map((appEnv: AppEnv) => (
            <Col span={8} key={appEnv.envId}>
              <Card
                title={appEnv.envName}
                key={appEnv.envId}
                extra={<a href="#">详情</a>}
                style={{ width: 300, marginBottom: "16px" }}
              >
                <p>环境: {appEnv.env}</p>
                <p>
                  环境状态:{" "}
                  <Tag color={appEnv.status === "1" ? "success" : "error"}>
                    {appEnv.status === "1" ? "已启用" : "已停用"}
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
      </Space>

      {/* 添加抽屉 */}
      <CreateEnvDrawer
        onClose={switchDrawer}
        onFinish={onFinish}
        open={drawerVisible}
        clusterList={clusterList}
      />
      {/* 团队抽屉成员 */}
      <TeamMembersDrawer
        appMembers={appMembers}
        onClose={switchMemberDrawer}
        open={teamMembersDrawerVisible}
      />
    </Card>
  );
};

export default connect(
  ({
    app,
    cluster,
  }: {
    app: {
      appMembers: any;
      appDetail: AppInfo;
    };
    cluster: {
      clusterList: ClusterInfo[];
    };
  }) => ({
    appDetail: app.appDetail,
    appMembers: app.appMembers,
    clusterList: cluster.clusterList,
  })
)(AppDetail);
