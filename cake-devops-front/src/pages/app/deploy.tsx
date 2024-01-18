import React, { useCallback, useEffect, useState } from "react";
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
} from "antd";
import { connect, Dispatch, useParams, history } from "umi";
import { AppEnv, AppInfo } from "@/models/app";
import { ReleaseHistory } from "@/models/release";
import moment from "moment";
import dayjs from "dayjs";

interface ReleasePageProps {
  dispatch: Dispatch;
  appDetail: AppInfo | null;
  releases: {
    total: number;
    list: ReleaseHistory[];
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
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [selectedEnvironment, setSelectedEnvironment] = useState<
    string | undefined | null
  >(appDetail?.appEnvList?.[0]?.envId);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });

  const [form] = Form.useForm();

  const columns = [
    {
      title: "发布单号",
      dataIndex: "releaseNo",
      key: "releaseNo",
    },
    {
      title: "发布时间",
      dataIndex: "releaseDate",
      key: "releaseDate",
      render: (text: any, record: ReleaseHistory) => {
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
      title: "提交ID",
      dataIndex: "commitId",
      key: "commitId",
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
      render: (text: any, record: ReleaseHistory) => {
        return (
          <div>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</div>
        );
      },
    },
    {
      title: "发布状态",
      dataIndex: "releaseStatus",
      key: "releaseStatus",
      render: (text: any, record: ReleaseHistory) => {
        // const tagColor = record.releaseStatus === "0" ? "success" : "error";
        let statusText = undefined;
        if (record.releaseStatus === "AWAIT_APPROVAL") {
          statusText = "审批中";
        }
        if (record.releaseStatus === "READY") {
          statusText = "待发布";
        }
        if (record.releaseStatus === "PENDING") {
          statusText = "发布中";
        }
        if (record.releaseStatus === "FINISHED") {
          statusText = "已完成";
        }
        return statusText;
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: ReleaseHistory) => (
        // <Space size="middle">
        //   <a onClick={() => handleView(record)}>查看</a>
        // </Space>
        <div></div>
      ),
    },
  ];

  const pageRelease = useCallback(() => {
    if (selectedEnvironment) {
      dispatch({
        type: "release/pageRelease",
        payload: {
          ...pagination,
          appId: id,
          envId: selectedEnvironment,
        },
      });
    }
  }, [dispatch, pagination, id, selectedEnvironment]);

  useEffect(() => {
    dispatch({
      type: "app/getAppDetail",
      payload: { id },
    });
  }, [dispatch, id]);

  useEffect(() => {
    console.log(123);
    pageRelease();
  }, [pageRelease]);

  useEffect(() => {
    // 在appDetail更新时，如果selectedEnvironment为undefined，则设置默认值
    if (!selectedEnvironment && appDetail?.appEnvList) {
      setSelectedEnvironment(appDetail?.appEnvList?.[0].envId);
    }
  }, [selectedEnvironment, appDetail]);

  const handleDrawer = () => {
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
    });

    console.log("dispatch", dispatch);

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
              <Button onClick={handleDrawer}>立即发布</Button>
            </div>
          }
        >
          <Steps
            current={0}
            items={[
              {
                title: "审批校验",
                description: "审批校验",
              },
              {
                title: "封网校验",
                description: "封网校验",
                subTitle: "无封网要求",
              },
              {
                title: "代码检出",
                description: "代码检出",
              },
              {
                title: "打包机选择",
                description: "打包机选择",
              },
              {
                title: "Sonar代码扫描",
                description: "Sonar代码扫描",
              },
              {
                title: "构建推送镜像",
                description: "构建推送镜像",
              },
              {
                title: "部署",
                description: "部署",
              },
              {
                title: "合并主干",
                description: "合并主干",
              },
            ]}
          />
        </Card>
        <Card
          title="发布单"
          extra={
            <div>
              <Button onClick={handleDrawer}>添加发布单</Button>
            </div>
          }
        >
          <Table
            columns={columns}
            dataSource={releases.list}
            rowKey={"releaseId"}
            pagination={{
              total: releases.total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Card>
      </Space>

      {/* 抽屉 */}
      <Drawer
        title="添加发布单"
        width={600}
        onClose={handleDrawer}
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
    };
    release: {
      releases: {
        total: number;
        list: ReleaseHistory[];
      };
    };
  }) => ({
    appDetail: app.appDetail,
    releases: {
      total: release.releases.total,
      list: release.releases.list,
    },
  })
)(DeployPage);
