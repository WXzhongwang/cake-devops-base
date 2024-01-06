import React, { useEffect, useMemo, useState } from "react";
import { Space, Tree, Input } from "antd";
import { HostGroupModel } from "@/models/host";
import { DownOutlined, SearchOutlined } from "@ant-design/icons";
import { DataNode } from "antd/lib/tree";

const { Search } = Input;

interface HostGroupTreeProps {
  data: HostGroupModel[];
  onGroupSelect: (groupId: string) => void;
}

const convertDataToTree = (data: HostGroupModel[]): any => {
  return data?.map((item) => {
    const treeNode: DataNode = {
      key: item.hostGroupId,
      title: item.name,
    };

    if (item.children && item.children.length > 0) {
      treeNode.children = convertDataToTree(item.children);
    }
    return treeNode;
  });
};

const dataList: { key: React.Key; title: string }[] = [];

const generateList = (data: DataNode[]) => {
  for (let i = 0; i < data.length; i++) {
    const node = data[i];
    const { title } = node;
    dataList.push({ key: node.key, title: title as string });
    if (node.children) {
      generateList(node.children);
    }
  }
};

const getParentKey = (key: React.Key, tree: DataNode[]): React.Key => {
  let parentKey: React.Key;
  for (let i = 0; i < tree.length; i++) {
    const node = tree[i];
    if (node.children) {
      if (node.children.some((item) => item.key === key)) {
        parentKey = node.key;
      } else if (getParentKey(key, node.children)) {
        parentKey = getParentKey(key, node.children);
      }
    }
  }
  return parentKey!;
};

const HostGroupTree: React.FC<HostGroupTreeProps> = ({
  data,
  onGroupSelect,
}) => {
  const [expandedKeys, setExpandedKeys] = useState<React.Key[]>([]);
  const [searchValue, setSearchValue] = useState("");
  const [autoExpandParent, setAutoExpandParent] = useState(true);

  const defaultTreeData = useMemo(() => convertDataToTree(data), [data]);

  useEffect(() => {
    generateList(defaultTreeData);
  }, [defaultTreeData]);

  const onExpand = (newExpandedKeys: React.Key[]) => {
    setExpandedKeys(newExpandedKeys);
    setAutoExpandParent(false);
  };

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    const newExpandedKeys = dataList
      .map((item) => {
        if (item.title.indexOf(value) > -1) {
          return getParentKey(item.key, defaultTreeData);
        }
        return null;
      })
      .filter(
        (item, i, self): item is React.Key =>
          !!(item && self.indexOf(item) === i)
      );
    setExpandedKeys(newExpandedKeys);
    setSearchValue(value);
    setAutoExpandParent(true);
  };

  const treeData = useMemo(() => {
    const loop = (data: DataNode[]): DataNode[] =>
      data.map((item) => {
        const strTitle = item.title as string;
        const index = strTitle.indexOf(searchValue);
        const beforeStr = strTitle.substring(0, index);
        const afterStr = strTitle.slice(index + searchValue.length);
        const title =
          index > -1 ? (
            <span>
              {beforeStr}
              <span className="site-tree-search-value">{searchValue}</span>
              {afterStr}
            </span>
          ) : (
            <span>{strTitle}</span>
          );
        if (item.children) {
          return { title, key: item.key, children: loop(item.children) };
        }

        return {
          title,
          key: item.key,
        };
      });

    return loop(defaultTreeData);
  }, [searchValue, defaultTreeData]);

  const handleTreeSelect = (selectedKeys: React.Key[], info: any) => {
    if (selectedKeys.length > 0) {
      // 传递选择的分组信息给父组件
      console.log("selected", selectedKeys[0]);
      onGroupSelect(selectedKeys[0] as string);
    }
  };

  return (
    <Space size="middle" direction="vertical" style={{ width: "100%" }}>
      <Search
        style={{ marginBottom: 8 }}
        placeholder="搜索指定机组"
        onChange={onChange}
      />
      <Tree
        showLine
        onExpand={onExpand}
        expandedKeys={expandedKeys}
        autoExpandParent={autoExpandParent}
        treeData={treeData}
        onSelect={handleTreeSelect}
      />
    </Space>
  );
};

export default HostGroupTree;
