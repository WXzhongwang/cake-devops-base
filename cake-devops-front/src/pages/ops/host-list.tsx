import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Table, Space, Input, Select, Button, Form, Card } from "antd";
import { connect, Dispatch, history } from "umi";
import { HostInfo } from "@/models/host";
 import CreateHostDrawer from "./components/create-host-drawer";

const { Option } = Select;

interface HostListProps {
  dispatch: Dispatch;
  hostList: { list: HostInfo[]; total: number };
  //   departments: Department[];
}

const AppList: React.FC<HostListProps> = ({
  dispatch,
  hostList,
  //  departments,
}) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [formattedDepartments, setFormattedDepartments] = useState<
    { label: string; value: string }[]
  >([]);
  const [filters, setFilters] = useState({
    hostName: "",
    hostGroupId: "",
    serverAddr: "",
  });

  const [createHostDrawerVisible, setCreateHostDrawerVisible] = useState(false);

  const showCreateHostDrawer = () => {
    setCreateHostDrawerVisible(true);
  };

  const hideCreateHostDrawer = () => {
    setCreateHostDrawerVisible(false);
  };

  const [form] = Form.useForm<{
    appName: string;
    department: string;
    language: string;
  }>();

  useEffect(() => {
    getHostList();
    getHostGroups();
  }, [pagination, filters]);

  const getHostList = () => {
    dispatch({
      type: "host/getHostList",
      payload: { ...pagination, ...filters },
    });
  };

  const getHostGroups = () => {
    dispatch({
      type: "hostGroup/getGroups",
    });
  };

  const columns = [
    {
      title: "主机名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "主机名",
      dataIndex: "hostName",
      key: "hostName",
    },
    {
      title: "服务器地址",
      dataIndex: "serverAddr",
      key: "serverAddr",
    },
    {
      title: "端口",
      dataIndex: "port",
      key: "port",
    },
    {
      title: "用户名",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "私钥",
      dataIndex: "pkey",
      key: "pkey",
    },
    // 可以根据需要添加其他列
    {
      title: "操作",
      key: "action",
      render: (text: any, record: HostInfo) => (
        <Space size="middle">
          <a onClick={() => handleView(record)}>查看</a>
        </Space>
      ),
    },

  // console.log("departments", departments);

  // useEffect(() => {
  //   // 当部门列表更新时，格式化并设置Select的选项
  //   const options = departments?.map((dep: Department) => ({
  //     value: dep.value,
  //     label: dep.label,
  //   }));
  //   setFormattedDepartments(options);
  // }, [departments]);

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleView = (record: HostInfo) => {
    // 处理查看操作
    console.log("查看主机详情", record);
    // 示例：跳转到详情页，使用 history.push
    // history.push(`/app-detail/${record.hostId}`);
  };

  return (
    <PageContainer>
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
            <Form.Item name="hostName" label="主机名称">
              <Input placeholder="请输入主机名称" />
            </Form.Item>
            <Form.Item name="hostGroupId" label="机组">
              <Select placeholder="请选择机组" allowClear>
                {formattedDepartments?.map((option) => (
                  <Option key={option.value} value={option.value}>
                    {option.label}
                  </Option>
                ))}
              </Select>
            </Form.Item>
            <Form.Item name="serverAddr" label="主机地址">
              <Input placeholder="请输入主机地址" />
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button
                onClick={() => {
                  form.resetFields();
                  setFilters({
                    hostName: "",
                    hostGroupId: "",
                    serverAddr: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>

          {/* 创建应用按钮 */}
          <Button type="primary" onClick={showCreateHostDrawer}>
            创建应用
          </Button>

          {/* 添加应用抽屉 */}
          <CreateHostDrawer
            open={createHostDrawerVisible}
            onClose={hideCreateHostDrawer}
            // groups={groups}
          />

          <Table
            columns={columns}
            dataSource={hostList.list}
            rowKey={"hostId"}
            pagination={{
              total: hostList.total,
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
