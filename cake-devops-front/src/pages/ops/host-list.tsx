import React, { useState, useEffect } from "react";
import { Row, Col } from "antd";
import { PageContainer } from "@ant-design/pro-components";
import { connect, Dispatch } from "umi";
import HostGroupTree from "./components/host-group-tree";
import HostTable from "./components/host-table";
import { QueryHostPayload, HostModel, HostGroupModel } from "@/models/host";

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
  const [selectedGroup, setSelectedGroup] = useState<string | null>(null);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });

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

  return (
    <PageContainer title="主机管理">
      <Row gutter={16}>
        <Col span={8}>
          <HostGroupTree data={hostGroups} onGroupSelect={handleGroupSelect} />
        </Col>
        <Col span={16}>
          <HostTable
            data={hosts}
            total={total}
            pagination={pagination}
            onChangeHandle={handlePaginationChange}
          />
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
