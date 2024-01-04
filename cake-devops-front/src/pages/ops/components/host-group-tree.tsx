// src/components/HostGroupTree.tsx
import React from "react";
import { Tree } from "antd";
import { HostGroupModel } from "@/models/host";

interface HostGroupTreeProps {
  data: HostGroupModel[];
  onGroupSelect: (groupId: string) => void;
}

const convertDataToTree = (data: HostGroupModel[]): any => {
  return data.map((item) => {
    const treeNode: any = {
      key: item.hostGroupId,
      title: item.name,
    };

    if (item.children && item.children.length > 0) {
      treeNode.children = convertDataToTree(item.children);
    }

    return treeNode;
  });
};

const HostGroupTree: React.FC<HostGroupTreeProps> = ({
  data,
  onGroupSelect,
}) => {
  // const handleTreeSelect = (selectedKeys: string[]) => {
  //   if (selectedKeys.length > 0) {
  //     // 传递选择的分组信息给父组件
  //     onGroupSelect(selectedKeys[0]);
  //   }
  // };

  const handleTreeSelect = (selectedKeys: React.Key[], info: any) => {
    if (selectedKeys.length > 0) {
      // 传递选择的分组信息给父组件
      onGroupSelect(selectedKeys[0] as string);
    }
  };

  const treeData = convertDataToTree(data);

  return <Tree treeData={treeData} onSelect={handleTreeSelect} />;
};

export default HostGroupTree;
