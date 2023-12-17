// AppDetail.tsx
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
} from "antd";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import { connect, Dispatch, useParams } from "umi";
import { AppInfo, AppEnv } from "@/models/app";
import dayjs from "dayjs";

const { Paragraph } = Typography;
const { Option } = Select;
interface AppDetailProps {
  dispatch: Dispatch;
  appDetail: AppInfo | null;
}

const AppDetail: React.FC<AppDetailProps> = ({ dispatch, appDetail }) => {
  const { id } = useParams();
  const [drawerVisible, setDrawerVisible] = useState(false);

  const [form] = Form.useForm<{
    appName: string;
    department: string;
    language: string;
  }>();

  useEffect(() => {
    // 在组件挂载时，调用 model 的获取应用详情接口
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  // 显示抽屉的方法
  const showDrawer = () => {
    setDrawerVisible(true);
  };

  // 关闭抽屉的方法
  const closeDrawer = () => {
    setDrawerVisible(false);
  };

  // 提交抽屉表单的方法
  const onFinish = (values: any) => {
    console.log("Received values:", values);
    // 在这里调用提交环境信息的接口
    // ...

    // 关闭抽屉
    closeDrawer();
  };

  return (
    <Card
      title={`${appDetail?.appName} 详情页`}
      extra={<Button onClick={showDrawer}>添加环境</Button>}
    >
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

      {/* 添加抽屉 */}
      <Drawer
        title="添加环境"
        width={500}
        onClose={closeDrawer}
        open={drawerVisible}
        bodyStyle={{ paddingBottom: 80 }}
      >
        <Form layout="vertical" form={form} onFinish={onFinish}>
          {/* 在这里添加表单项，例如环境名称、环境类型、选择集群、域名等 */}
          <Form.Item
            name="envName"
            label="环境名称"
            rules={[{ required: true, message: "请输入环境名称" }]}
          >
            <Input placeholder="请输入环境名称" />
          </Form.Item>
          <Form.Item
            name="envType"
            label="环境类型"
            rules={[{ required: true, message: "请选择环境类型" }]}
          >
            <Select placeholder="请选择环境类型">
              <Option value="测试">测试</Option>
              <Option value="预发">预发</Option>
              <Option value="线上">线上</Option>
            </Select>
          </Form.Item>
          <Form.List
            name="domains"
            rules={[
              {
                validator: async (_, domains) => {
                  if (!domains || domains.length > 3) {
                    return Promise.reject(new Error("最多配置3个域名"));
                  }
                },
              },
            ]}
          >
            {(fields, { add, remove }, { errors }) => (
              <>
                {fields.map((field, index) => (
                  <Form.Item
                    style={{ width: "100%" }}
                    label={index === 0 ? "域名" : ""}
                    required={false}
                    key={field.key}
                  >
                    <Form.Item
                      {...field}
                      validateTrigger={["onChange", "onBlur"]}
                      rules={[
                        {
                          required: true,
                          whitespace: true,
                          message: "请输入合法的域名",
                        },
                      ]}
                      noStyle
                    >
                      <Input style={{ width: 260 }} placeholder="请输入域名" />
                    </Form.Item>
                    {fields.length > 1 ? (
                      <MinusCircleOutlined
                        style={{ margin: "0 8px" }}
                        className="dynamic-delete-button"
                        onClick={() => remove(field.name)}
                      />
                    ) : null}
                  </Form.Item>
                ))}
                <Form.Item>
                  <Button
                    type="dashed"
                    onClick={() => add()}
                    style={{ width: "60%" }}
                    icon={<PlusOutlined />}
                  >
                    增加域名
                  </Button>
                  <Form.ErrorList errors={errors} />
                </Form.Item>
              </>
            )}
          </Form.List>
          <Form.Item
            name="replicas"
            label="副本数"
            rules={[{ required: true, message: "请输入副本数" }]}
          >
            <Input type="number" placeholder="请输入副本数" />
          </Form.Item>
          <Form.Item
            name="cpu"
            label="CPU"
            rules={[{ required: true, message: "请输入CPU核数" }]}
          >
            <Input type="number" placeholder="请输入CPU核数" />
          </Form.Item>
          <Form.Item
            name="memory"
            label="内存"
            rules={[{ required: true, message: "请输入内存大小" }]}
          >
            <Input type="number" placeholder="请输入内存大小" />
          </Form.Item>
          <Form.Item
            name="maxCpu"
            label="最大CPU"
            rules={[{ required: true, message: "请输入最大CPU核数" }]}
          >
            <Input type="number" placeholder="请输入最大CPU核数" />
          </Form.Item>
          <Form.Item
            name="maxMemory"
            label="最大内存"
            rules={[{ required: true, message: "请输入最大内存大小" }]}
          >
            <Input type="number" placeholder="请输入最大内存大小" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </Drawer>
    </Card>
  );
};

export default connect(({ app }: { app: { appDetail: AppInfo } }) => ({
  appDetail: app.appDetail,
}))(AppDetail);
