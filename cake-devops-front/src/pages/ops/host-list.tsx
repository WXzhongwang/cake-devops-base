import React, { useState, useEffect } from "react";
import {
  Table,
  Row,
  Col,
  Button,
  Card,
  Form,
  Input,
  Space,
  Tag,
  Drawer,
} from "antd";
import { PageContainer } from "@ant-design/pro-components";
import { connect, Dispatch } from "umi";
import HostGroupTree from "./components/host-group-tree";
import CreateHostDrawer from "./components/create-host-drawer";
import { HostModel, HostGroupModel } from "@/models/host";
import { ProxyModel } from "@/models/proxy";
import { updateHost } from "@/services/host";
import CreateHostForm from "./components/create-host-form";

interface HostListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  hostGroups: HostGroupModel[];
  total: number;
  machineProxies: ProxyModel[];
}

const HostPage: React.FC<HostListProps> = ({
  dispatch,
  hosts,
  hostGroups,
  total,
  machineProxies,
}) => {
  const [createHostVisible, setCreateHostVisible] = useState(false);
  const [selectedGroup, setSelectedGroup] = useState<string | null>(null);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [drawerVisible, setDrawerVisible] = useState(false); // 控制抽屉显示状态
  const [filters, setFilters] = useState({
    name: "",
    hostName: "",
  });
  const [editingHost, setEditingHost] = useState<HostModel | undefined>(
    undefined
  ); // 当前正在编辑的主机信息

  const getHosts = () => {
    dispatch({
      type: "host/fetchHosts",
      payload: { ...pagination, ...filters },
    });
  };

  const handleEdit = (host: HostModel) => {
    setEditingHost(host); // 设置编辑状态为当前点击的主机信息
    setDrawerVisible(true); // 打开抽屉
  };

  const handleDelete = (hostId: string) => {
    dispatch({
      type: "host/delete",
      payload: {
        hostId,
      },
    });
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false); // 关闭抽屉
    form.resetFields(); // 重置表单字段
  };

  const [form] = Form.useForm<{
    name: string;
    hostName: string;
  }>();

  useEffect(() => {
    fetchHostGroups();
    let allGroupIds: string[] = [];
    // 当 selectedGroup 变化时，触发数据获取
    if (selectedGroup) {
      console.log("select group", selectedGroup);
      allGroupIds = getAllGroupIds(selectedGroup);
      console.log("choose group", allGroupIds);
    }
    dispatch({
      type: "host/fetchHosts",
      payload: {
        ...pagination,
        hostGroupsIds: allGroupIds,
        name: filters.name,
        hostName: filters.hostName,
      },
    });
  }, [pagination, selectedGroup, dispatch]);

  const fetchHostGroups = () => {
    dispatch({
      type: "host/fetchHostGroups",
      payload: {},
    });
  };

  const fetchMachineProxies = () => {
    dispatch({
      type: "proxy/queryProxies",
      payload: {},
    });
  };

  useEffect(() => {
    fetchMachineProxies(); // 调用获取主机代理列表的接口
  }, []);

  const handleGroupSelect = (groupId: string) => {
    setSelectedGroup(groupId);
  };

  const flattenTreeToList = (tree: HostGroupModel[]): HostGroupModel[] => {
    const result: HostGroupModel[] = [];

    const flatten = (node: HostGroupModel) => {
      result.push(node);
      if (node.children) {
        node.children.forEach(flatten);
      }
    };
    tree.forEach(flatten);
    return result;
  };

  const getAllGroupIds = (groupId: string): string[] => {
    const treeList = flattenTreeToList(hostGroups);

    const findNodeById = (id: string) =>
      treeList.find((group) => group.hostGroupId === id);

    const result: string[] = [];
    const findChildren = (nodeId: string) => {
      const node = findNodeById(nodeId);
      if (node) {
        result.push(nodeId);
        if (node.children && node.children.length > 0) {
          node.children.forEach((child) => {
            findChildren(child.hostGroupId);
          });
        }
      }
    };
    findChildren(groupId);
    return result;
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  // 定义搜索方法
  const onSearch = (searchFilters: { name: string; hostName: string }) => {
    setFilters(searchFilters);
    setPagination({ pageNo: 1, pageSize: 10 }); // 重置分页
  };

  // 处理新增主机弹窗的显示和隐藏
  const handleCreateHostDrawer = () => {
    setDrawerVisible(!drawerVisible);
  };

  // 处理新增主机的提交
  const handleSaveHost = async (values: any) => {
    // 在这里可以执行创建应用的逻辑
    console.log("创建主机:", values);
    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "host/createHost",
      payload: values,
    });
    getHosts(); // 创建成功后重新获取主机列表数据
    setDrawerVisible(false); // 关闭抽屉
    form.resetFields(); // 重置表单字段
  };

  const handleUpdateHost = async (values: HostModel) => {
    try {
      // 从编辑的主机信息中获取主机的 ID
      const hostId = editingHost?.hostId;
      await updateHost({ ...values, hostId });
      getHosts();
      setDrawerVisible(false);
      form.resetFields();
    } catch (error) {
      console.error("更新主机失败:", error);
    }
  };

  const columns = [
    // 定义你的表格列
    {
      title: "实例名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "主机名称",
      dataIndex: "hostName",
      key: "hostName",
    },
    {
      title: "服务地址",
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
      title: "状态",
      dataIndex: "status",
      key: "status",
      render: (text: any, record: HostModel) => {
        // 根据 status 的值返回相应的 Tag
        const tagColor = record.status === "0" ? "success" : "error";
        const statusText = record.status === "0" ? "正常" : "停用";

        return <Tag color={tagColor}>{statusText}</Tag>;
      },
    },

    // 其他列根据需要添加
    {
      title: "操作",
      key: "action",
      render: (text: any, record: HostModel) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.hostId)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="主机管理">
      <Row gutter={16}>
        <Col span={4}>
          <HostGroupTree data={hostGroups} onGroupSelect={handleGroupSelect} />
        </Col>
        <Col span={20}>
          <Card>
            <Space size="middle" direction="vertical" style={{ width: "100%" }}>
              <Form
                form={form}
                layout="inline"
                onFinish={(values) => {
                  console.log(values);
                  setFilters(values);
                  // 触发外层组件的搜索方法
                  onSearch(values);
                }}
              >
                <Form.Item name="name" label="实例名称">
                  <Input placeholder="请输入实例名称" />
                </Form.Item>
                <Form.Item name="hostName" label="主机名称">
                  <Input placeholder="请输入主机名称" />
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
                        name: "",
                        hostName: "",
                      });
                    }}
                  >
                    重置
                  </Button>
                </Form.Item>
              </Form>

              <Button type="primary" onClick={handleCreateHostDrawer}>
                新增主机
              </Button>

              {/* 新增和编辑主机的抽屉 */}
              <Drawer
                title={editingHost ? "编辑主机" : "新增主机"}
                width={400}
                open={drawerVisible}
                onClose={handleCloseDrawer}
                destroyOnClose={true}
              >
                <CreateHostForm
                  initialValues={editingHost}
                  hostGroups={hostGroups}
                  machineProxies={machineProxies}
                  onSubmit={handleSaveHost}
                  onUpdate={handleUpdateHost}
                  onCancel={handleCloseDrawer}
                />
              </Drawer>

              <Table
                columns={columns}
                dataSource={hosts}
                rowKey={"hostId"}
                pagination={{
                  total: total,
                  current: pagination.pageNo,
                  pageSize: pagination.pageSize,
                  onChange: handlePaginationChange,
                }}
              />
            </Space>
          </Card>
        </Col>
      </Row>
    </PageContainer>
  );
};

export default connect(({ host, proxy }) => ({
  hosts: host.hosts,
  hostGroups: host.hostGroups,
  total: host.total,
  machineProxies: proxy.proxies,
}))(HostPage);
