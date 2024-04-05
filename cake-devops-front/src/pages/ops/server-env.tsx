import React, { useState, useEffect } from "react";
import {
  Row,
  Col,
  Table,
  Select,
  Space,
  Button,
  Form,
  Input,
  Drawer,
} from "antd";
import { HostEnvironmentVariable } from "@/models/host-env";
import { Dispatch, connect } from "umi";
import { HostModel } from "@/models/host";
import LeftHostList from "./components/left-host-list";
import CreateHostEnvironmentVariableForm from "./components/create-host-env-form";
import dayjs from "dayjs";

const { Option } = Select;

interface HostEnvironmentVariableListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  hostsTotal: number;
  hostEnvs: HostEnvironmentVariable[];
  hostEnvsTotal: number;
}

const HostEnvironmentVariablesPage: React.FC<
  HostEnvironmentVariableListProps
> = ({ dispatch, hosts, hostsTotal, hostEnvs, hostEnvsTotal }) => {
  const [selectedMachine, setSelectedMachine] = useState<string>("");
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    name: "",
  });

  const [drawerVisible, setDrawerVisible] = useState<boolean>(false);
  const [selectedEnv, setSelectedEnv] = useState<
    HostEnvironmentVariable | undefined
  >(undefined);

  useEffect(() => {
    // 页面加载时发起主机数据的获取请求
    dispatch({ type: "host/fetchHosts", payload: { pageNo: 1, pageSize: 10 } });
  }, [dispatch]);

  useEffect(() => {
    if (hosts.length > 0) {
      setSelectedMachine(hosts[0].hostId);
      loadEnvironmentVariables(hosts[0].hostId);
    }
  }, [hosts]);

  const loadEnvironmentVariables = (hostId: string) => {
    // 根据主机ID发起请求加载右侧环境变量数据
    dispatch({
      type: "hostEnv/fetchVariables",
      payload: { hostId },
    });
  };

  const handleHostItemClick = (hostId: string) => {
    setSelectedMachine(hostId); // 设置选中的主机ID
    loadEnvironmentVariables(hostId); // 调用加载环境变量数据的函数
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAddNew = () => {
    setSelectedEnv(undefined);
    setDrawerVisible(true);
  };

  const handleEdit = (env: HostEnvironmentVariable) => {
    setSelectedEnv(env);
    setDrawerVisible(true);
  };

  const onCloseDrawer = () => {
    setDrawerVisible(false); // 关闭抽屉
  };

  const handleDelete = (envId: string) => {
    // 发起删除请求
    dispatch({
      type: "hostEnv/deleteVariable",
      payload: { envId: envId },
    });
  };

  const handleSaveHostEnv = (values: HostEnvironmentVariable) => {
    const data = {
      ...values,
      hostId: selectedMachine,
    };
    dispatch({
      type: "hostEnv/addVariable",
      payload: data,
    });
    setDrawerVisible(false); // 关闭抽屉
  };

  const handleUpdateHostEnv = (values: HostEnvironmentVariable) => {
    const data = {
      ...values,
      id: selectedEnv!.id,
    };
    dispatch({
      type: "hostEnv/updateVariable",
      payload: data,
    });
    setDrawerVisible(false); // 关闭抽屉
  };

  const columns = [
    {
      title: "Key",
      dataIndex: "attrKey",
      key: "attrKey",
    },
    {
      title: "Value",
      dataIndex: "attrValue",
      key: "attrValue",
    },
    {
      title: "描述",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "创建时间",
      key: "gmtCreate",
      render: (text: string, record: HostEnvironmentVariable) => (
        <>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "修改时间",
      key: "gmtModified",
      render: (text: string, record: HostEnvironmentVariable) => (
        <>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (text: string, record: HostEnvironmentVariable) => (
        <span>
          <a onClick={() => handleEdit(record)}>编辑</a>
          <span style={{ margin: "0 8px" }}>|</span>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </span>
      ),
    },
  ];

  return (
    <Row gutter={16}>
      <Col span={6}>
        {/* 左侧主机列表 */}
        <LeftHostList hosts={hosts} onItemClick={handleHostItemClick} />
      </Col>
      <Col span={18}>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              // 将表单的搜索条件发送给后端进行过滤
              setFilters(values);
            }}
          >
            <Form.Item name="name" label="变量名称">
              <Input placeholder="变量名称" />
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
                  setFilters({
                    name: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAddNew}>
            新增环境变量
          </Button>
          <Table
            columns={columns}
            dataSource={hostEnvs}
            rowKey={"id"}
            pagination={{
              total: hostEnvsTotal,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>

        <Drawer
          title={selectedEnv ? "编辑环境变量" : "新增环境变量"}
          width={360}
          open={drawerVisible}
          onClose={onCloseDrawer}
          destroyOnClose={true}
        >
          <CreateHostEnvironmentVariableForm
            initialValues={selectedEnv}
            onSave={handleSaveHostEnv}
            onCancel={onCloseDrawer}
            onUpdate={handleUpdateHostEnv}
          />
        </Drawer>
      </Col>
    </Row>
  );
};

export default connect(({ host, hostEnv }) => ({
  hosts: host.hosts,
  hostsTotal: host.total,
  hostEnvs: hostEnv.hostEnvs,
  hostEnvsTotal: hostEnv.hostEnvsTotal,
}))(HostEnvironmentVariablesPage);
