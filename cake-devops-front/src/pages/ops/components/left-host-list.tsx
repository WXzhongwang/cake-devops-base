import React, { useState } from "react";
import { List } from "antd";
import { HostModel } from "@/models/host";

interface LeftHostListProps {
  hosts: HostModel[];
  onItemClick: (hostId: string) => void;
}

const LeftHostList: React.FC<LeftHostListProps> = ({ hosts, onItemClick }) => {
  // 获取第一个主机的 ID 作为初始的选中主机 ID
  const initialSelectedHostId = hosts.length > 0 ? hosts[0].hostId : null;
  // 定义状态保存当前选中的主机 ID
  const [selectedHostId, setSelectedHostId] = useState<string | null>(
    initialSelectedHostId
  );

  return (
    <List
      header={<div>机器列表</div>}
      dataSource={hosts}
      renderItem={(host) => (
        <List.Item
          onClick={() => {
            // 点击时更新选中的主机 ID
            setSelectedHostId(host.hostId);
            // 调用父组件传递的点击事件处理函数
            onItemClick(host.hostId);
          }}
          // 根据当前项的主机 ID 是否与选中的主机 ID 相匹配来动态设置样式
          style={{
            backgroundColor:
              host.hostId === selectedHostId ? "#f0f0f0" : "transparent",
          }}
        >
          <List.Item.Meta
            title={host.name}
            description={"IP:" + host.serverAddr}
          ></List.Item.Meta>
        </List.Item>
      )}
      pagination={{
        position: "bottom",
        pageSize: 10,
      }}
    />
  );
};

export default LeftHostList;
