// src/components/HostGroupTree.tsx
import React, { useEffect, useState } from "react";
import { Space, Tree, Input } from "antd";
import { HostGroupModel } from "@/models/host";
import { DownOutlined, SearchOutlined } from "@ant-design/icons";

const { Search } = Input;

interface HostGroupTreeProps {
  data: HostGroupModel[];
  onGroupSelect: (groupId: string) => void;
}

const convertDataToTree = (data: HostGroupModel[], keyword: string): any => {
  // console.log("treeData", data);

  return data?.map((item) => {
    const treeNode: any = {
      key: item.hostGroupId,
      title: item.name,
      matched: true,
    };

    const title = (
      <span
        style={{ backgroundColor: treeNode.matched ? "yellow" : "transparent" }}
      >
        {treeNode.title}
      </span>
    );

    console.log("search key word", keyword);
    if (keyword.length > 0 && treeNode.title.includes(keyword)) {
      treeNode.matched = true;
    } else {
      treeNode.matched = false;
    }

    if (item.children && item.children.length > 0) {
      treeNode.children = convertDataToTree(item.children, keyword);
    }
    treeNode.title = title;

    return treeNode;
  });
};

const HostGroupTree: React.FC<HostGroupTreeProps> = ({
  data,
  onGroupSelect,
}) => {
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [treeData, setTreeData] = useState();

  const handleTreeSelect = (selectedKeys: React.Key[], info: any) => {
    if (selectedKeys.length > 0) {
      // 传递选择的分组信息给父组件
      console.log("selected", selectedKeys[0]);
      onGroupSelect(selectedKeys[0] as string);
    }
  };

  useEffect(() => {
    const treeData = convertDataToTree(data, searchKeyword);
    console.log("abc", treeData);
    setTreeData(treeData);
  }, [searchKeyword]);

  // const refreshTree = () => {

  // };

  return (
    <Space size="middle" direction="vertical" style={{ width: "100%" }}>
      <Search
        placeholder="搜索分组"
        allowClear
        enterButton={<SearchOutlined />}
        onChange={(e) => setSearchKeyword(e.target.value)}
      />
      <Tree
        showLine
        autoExpandParent={true}
        switcherIcon={<DownOutlined />}
        treeData={treeData}
        onSelect={handleTreeSelect}
      />
    </Space>
  );
};

export default HostGroupTree;
