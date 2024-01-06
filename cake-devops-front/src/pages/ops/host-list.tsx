import React, { useState, useEffect } from "react";
import { Row, Col, Button, Card, Form, Input, Space } from "antd";
import { PageContainer } from "@ant-design/pro-components";
import { connect, Dispatch } from "umi";
import HostGroupTree from "./components/host-group-tree";
import HostTable from "./components/host-table";
import CreateHostDrawer from "./components/create-host-drawer";
import { HostModel, HostGroupModel } from "@/models/host";

interface HostListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  hostGroups: HostGroupModel[];
  total: number;
}

const HostPage: React.FC<HostListProps> = ({
  dispatch,
  hosts,
  hostGroups,
  total,
}) => {
  const [createHostVisible, setCreateHostVisible] = useState(false);
  const [selectedGroup, setSelectedGroup] = useState<string | null>(null);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    name: "",
    hostName: "",
  });

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
    setCreateHostVisible(!createHostVisible);
  };

  // 处理新增主机的提交
  const handleAddHostSubmit = async (values: any) => {
    // 在这里可以执行创建应用的逻辑
    console.log("创建主机:", values);
    // 在这里可以调用相应的接口或 dispatch 创建应用的 action
    dispatch({
      type: "host/createHost",
      payload: values,
    });

    // 关闭抽屉
    handleCreateHostDrawer();
  };

  console.log("hostGroups", hostGroups);
  return (
    <PageContainer title="主机管理">
      <Row gutter={16}>
        <Col span={8}>
          <HostGroupTree data={hostGroups} onGroupSelect={handleGroupSelect} />
        </Col>
        <Col span={16}>
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
                  <Button type="primary" htmlType="submit">
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
              <CreateHostDrawer
                visible={createHostVisible}
                onClose={handleCreateHostDrawer}
                onSubmit={handleAddHostSubmit}
                hostGroups={hostGroups}
              />

              <HostTable
                data={hosts}
                total={total}
                pagination={pagination}
                onChangeHandle={handlePaginationChange}
                onSearch={onSearch}
              />
            </Space>
          </Card>
        </Col>
      </Row>
    </PageContainer>
  );
};

export default connect(({ host }) => ({
  hosts: host.hosts,
  hostGroups: host.hostGroups,
  total: host.total,
}))(HostPage);
