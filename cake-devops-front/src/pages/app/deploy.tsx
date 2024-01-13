import React, { useEffect, useState } from "react";
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
  Drawer,
  DatePicker,
} from "antd";
import { connect, Dispatch, useParams, history } from "umi";
import { AppEnv, AppInfo } from "@/models/app";
import dayjs from "dayjs";
import moment from "moment";

interface AppDetailProps {
  dispatch: Dispatch;
  appDetail: AppInfo | null;
}
const DeployPage: React.FC<AppDetailProps> = ({ dispatch, appDetail }) => {
  const { id } = useParams();
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [selectedEnvironment, setSelectedEnvironment] = useState<
    string | undefined | null
  >(appDetail?.appEnvList?.[0]?.envId);

  // 新增状态来存储表单值
  const [formValues, setFormValues] = useState({
    releaseDate: undefined, // 默认值为当前时间
    releaseBranch: "",
    docAddress: "",
    appId: id,
    envId: selectedEnvironment,
  });

  useEffect(() => {
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  useEffect(() => {
    // 在appDetail更新时，如果selectedEnvironment为undefined，则设置默认值
    if (!selectedEnvironment && appDetail?.appEnvList) {
      setSelectedEnvironment(appDetail?.appEnvList?.[0].envId);
    }
  }, [selectedEnvironment, appDetail]);

  const addRelease = () => {
    // 处理添加发布单的逻辑，可以在这里打开抽屉等
    setDrawerVisible(true);
  };

  const handleDrawerClose = () => {
    setDrawerVisible(false);
  };

  const handleEnvironmentChange = (value: any) => {
    setSelectedEnvironment(value);
    // 处理环境切换的逻辑，可以在这里请求相应的数据等
  };

  const handleAddRelease = (formValues: any) => {
    // 处理添加发布单的逻辑，可以在这里提交表单等
    console.log("Form Values:", formValues);
    // 提交表单后关闭抽屉
    setDrawerVisible(false);
  };

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
          title="发布流水线"
          extra={
            <div>
              <Button onClick={addRelease}>立即发布</Button>
            </div>
          }
        ></Card>
        <Card
          title="发布历史"
          extra={
            <div>
              <Button onClick={addRelease}>添加发布单</Button>
            </div>
          }
        ></Card>
      </Space>

      {/* 抽屉 */}
      <Drawer
        title="添加发布单"
        width={600}
        onClose={handleDrawerClose}
        open={drawerVisible}
      >
        {/* 表单 */}
        <Form layout="vertical">
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
            label="文档地址"
            name="docAddress"
            rules={[{ required: true, message: "请输入文档地址" }]}
          >
            <Input />
          </Form.Item>
          <Button type="primary" onClick={handleAddRelease}>
            提交
          </Button>
        </Form>
      </Drawer>
    </PageContainer>
  );
};

export default connect(
  ({
    app,
  }: {
    app: {
      appDetail: AppInfo;
    };
  }) => ({
    appDetail: app.appDetail,
  })
)(DeployPage);
