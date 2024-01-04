// src/components/HostGroupTree.tsx
import React from "react";
import { Tree } from "antd";
import { HostGroupTreeDTO } from "@/models/host";

interface HostGroupTreeProps {
  data: HostGroupTreeDTO[];
  onGroupSelect: (groupId: string) => void;
}

const HostGroupTree: React.FC<HostGroupTreeProps> = ({
  data,
  onGroupSelect,
}) => {
  const handleTreeSelect = (selectedKeys: string[]) => {
    if (selectedKeys.length > 0) {
      // 传递选择的分组信息给父组件
      onGroupSelect(selectedKeys[0]);
    }
  };

  return <Tree treeData={data} onSelect={handleTreeSelect} />;
};

export default HostGroupTree;
