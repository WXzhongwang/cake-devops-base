import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Table, Space, Input, Select, Button, Form, Card, Tag } from "antd";
import { connect, Dispatch, history } from "umi";
import { AppInfo, Department } from "@/models/app";
import CreateAppDrawer from "./components/create-app-drawer";

const { Option } = Select;

interface AppListProps {
  dispatch: Dispatch;
  appList: { list: AppInfo[]; total: number };
  departments: Department[];
}

const AppList: React.FC<AppListProps> = ({
  dispatch,
  appList,
  departments,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [formattedDepartments, setFormattedDepartments] = useState<
    { label: string; value: string }[]
  >([]);
  const [filters, setFilters] = useState({
    appName: "",
    department: "",
    language: "",
  });

  const [createAppDrawerVisible, setCreateAppDrawerVisible] = useState(false);

  const showCreateAppDrawer = () => {
    setCreateAppDrawerVisible(true);
  };

  const hideCreateAppDrawer = () => {
    setCreateAppDrawerVisible(false);
  };

  const [form] = Form.useForm<{
    appName: string;
    department: string;
    language: string;
  }>();

  useEffect(() => {
    getAppList();
    getDepartments();
  }, [pagination, filters]);

  const getAppList = () => {
    dispatch({
      type: "app/getAppList",
      payload: { ...pagination, ...filters },
    });
  };

  const getDepartments = () => {
    dispatch({
      type: "app/getDepartments",
    });
  };

  const columns = [
    {
      title: "应用名称",
      dataIndex: "appName",
      key: "appName",
      render: (text: any, record: AppInfo) => (
        <a onClick={() => handleView(record)}>{record.appName}</a>
      ),
    },
    {
      title: "仓库",
      dataIndex: "repo",
      key: "repo",
      width: 200,
    },
    {
      title: "默认分支",
      dataIndex: "defaultBranch",
      key: "defaultBranch",
    },
    {
      title: "部门",
      dataIndex: "department",
      key: "department",
    },
    {
      title: "开发语言",
      dataIndex: "language",
      key: "language",
    },
    {
      title: "描述",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "开发模式",
      dataIndex: "developMode",
      key: "developMode",
    },
    {
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: AppInfo) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.status === "0" ? "success" : "error";
        const statusText = record.status === "0" ? "正常" : "停用";

        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: AppInfo) => (
        <Space size="middle">
          <a onClick={() => handleView(record)}>查看</a>
        </Space>
      ),
    },
  ];

  useEffect(() => {
    // 当部门列表更新时，格式化并设置Select的选项
    const options = departments?.map((dep: Department) => ({
      value: dep.value,
      label: dep.label,
    }));
    setFormattedDepartments(options);
  }, [departments]);

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleView = (record: AppInfo) => {
    // 处理查看操作
    console.log("查看应用详情", record);
    // 示例：跳转到详情页，使用 history.push
    history.push(`/apps/app/info/${record.appId}`);
  };

  return (
    <PageContainer title="应用中心">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            form={form}
            layout="inline"
            onFinish={(values) => {
              console.log(values);
              setFilters(values);
            }}
          >
            <Form.Item name="appName" label="应用名称">
              <Input placeholder="请输入应用名称" style={{ width: "120px" }} />
            </Form.Item>
            <Form.Item name="department" label="部门">
              <Select
                placeholder="请选择部门"
                style={{ width: "120px" }}
                allowClear
              >
                {formattedDepartments?.map((option) => (
                  <Option key={option.value} value={option.value}>
                    {option.label}
                  </Option>
                ))}
              </Select>
            </Form.Item>
            <Form.Item name="language" label="开发语言">
              <Select placeholder="请选择开发语言" style={{ width: "120px" }}>
                <Option value="java">Java</Option>
                <Option value="python">Python</Option>
                {/* 其他语言选项 */}
              </Select>
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button
                onClick={() => {
                  form.resetFields();
                  setFilters({
                    appName: "",
                    department: "",
                    language: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>

          {/* 创建应用按钮 */}
          <Button type="primary" onClick={showCreateAppDrawer}>
            创建应用
          </Button>

          {/* 添加应用抽屉 */}
          <CreateAppDrawer
            open={createAppDrawerVisible}
            onClose={hideCreateAppDrawer}
            departments={departments}
          />

          <Table
            columns={columns}
            dataSource={appList.list}
            rowKey={"appId"}
            pagination={{
              total: appList.total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>
    </PageContainer>
  );
};

export default connect(
  ({
    app,
  }: {
    app: {
      appList: { list: AppInfo[]; total: number };
      departments: Department[];
    };
  }) => {
    return {
      appList: app.appList,
      departments: app.departments,
    };
  }
)(AppList);
