import React, { useState, useEffect } from "react";
import { Row, Col } from "antd";
import { connect, Dispatch } from "umi";
import HostGroupTree from "./components/host-group-tree";
import HostTable from "./components/host-table";
import { QueryHostPayload, HostModel, HostGroupModel } from "@/models/host";

interface HostListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  hostGroups: HostGroupModel[];
}

const HostPage: React.FC<HostListProps> = ({ dispatch, hosts, hostGroups }) => {
  const [selectedGroup, setSelectedGroup] = useState<string | null>(null);
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });

  useEffect(() => {
    let allGroupIds: string[] = [];
    // 当 selectedGroup 变化时，触发数据获取
    if (selectedGroup) {
      allGroupIds = getAllGroupIds(selectedGroup);
    }
    dispatch({
      type: "host/saveHosts",
      payload: {
        hostGroupIds: allGroupIds,
        pageNo: 1, // 页码
        pageSize: 10, // 每页条数
      },
    });
  }, [pagination, selectedGroup, dispatch]);

  const handleGroupSelect = (groupId: string) => {
    setSelectedGroup(groupId);
  };

  const getAllGroupIds = (groupId: string): string[] => {
    // 实现此函数以递归获取所有分组 ID
    // 可以使用 host.hostGroups 获取主机分组的列表
    // 并通过其 id 查找分组
    // 为简单起见，假设你有一个函数 findNodeById
    const findNodeById = (id: string) =>
      hostGroups.find((group) => group.hostGroupId === id);

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

  return (
    <Row gutter={16}>
      <Col span={8}>
        <HostGroupTree data={hostGroups} onGroupSelect={handleGroupSelect} />
      </Col>
      <Col span={16}>
        <HostTable data={hosts} />
      </Col>
    </Row>
  );
};

export default connect(({ host }) => ({ host }))(HostPage);
