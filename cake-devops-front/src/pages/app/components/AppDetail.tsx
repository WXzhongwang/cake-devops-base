// AppDetail.tsx
import React, { useEffect } from "react";
import { Descriptions, Typography, Card, Tag } from "antd";
import { connect, Dispatch, useParams } from "umi";
import { AppInfo, AppEnv } from "@/models/app";
import dayjs from "dayjs";

const { Paragraph, Title } = Typography;
interface AppDetailProps {
  dispatch: Dispatch;
  appDetail: AppInfo | null;
}

const AppDetail: React.FC<AppDetailProps> = ({ dispatch, appDetail }) => {
  const { id } = useParams();

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  return (
    <Card title={`${appDetail?.appName} 详情页`}>
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
          {/* <Paragraph
            copyable={{
              icon: [
                <SmileOutlined key="copy-icon" />,
                <SmileFilled key="copied-icon" />,
              ],
              tooltips: ["点击复制", "复制成功"],
            }}
          >
            {appDetail.repo}
          </Paragraph> */}
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
      <div style={{ marginTop: "20px" }} title="应用环境列表">
        {appDetail?.appEnvList.map((appEnv: AppEnv) => (
          <Card
            title={appEnv.envName}
            key={appEnv.id}
            extra={<a href="#">More</a>}
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
        ))}
      </div>
    </Card>
  );
};

export default connect(({ app }: { app: { appDetail: AppInfo } }) => ({
  appDetail: app.appDetail,
}))(AppDetail);
