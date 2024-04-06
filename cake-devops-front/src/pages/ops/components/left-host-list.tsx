import React, { useEffect, useState } from "react";
import { Input, List, Pagination } from "antd";
import { HostModel } from "@/models/host";
import { Dispatch, connect } from "umi";

interface LeftHostListProps {
  dispatch: Dispatch;
  hosts: HostModel[];
  hostsTotal: number;
  onItemClick: (hostId: string) => void;
}

const LeftHostList: React.FC<LeftHostListProps> = ({
  dispatch,
  onItemClick,
  hosts,
  hostsTotal,
}) => {
  const [searchText, setSearchText] = useState<string>("");
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  // 获取第一个主机的 ID 作为初始的选中主机 ID
  const initialSelectedHostId = hosts.length > 0 ? hosts[0].hostId : null;
  // 定义状态保存当前选中的主机 ID
  const [selectedHostId, setSelectedHostId] = useState<string | null>(
    initialSelectedHostId
  );

  useEffect(() => {
    // 页面加载时发起主机数据的获取请求
    dispatch({
      type: "host/fetchHosts",
      payload: {
        pageNo: pagination.pageNo,
        pageSize: pagination.pageSize,
        name: searchText,
      },
    });
  }, [dispatch, pagination, searchText]);

  useEffect(() => {
    if (hosts.length > 0) {
      onItemClick(hosts[0].hostId);
    }
  }, [hosts]);

  const handleSearch = (value: string) => {
    setSearchText(value);
    setPagination({ pageNo: 1, pageSize: 10 }); // Reset current page when search performed
  };

  const handlePageChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  return (
    <div>
      <Input.Search
        placeholder="搜索主机名称"
        value={searchText}
        onChange={(e) => handleSearch(e.target.value)}
        style={{ marginBottom: 16 }}
      />
      <List
        dataSource={hosts}
        pagination={{
          total: hostsTotal,
          current: pagination.pageNo,
          pageSize: pagination.pageSize,
          onChange: handlePageChange,
        }}
        renderItem={(host) => (
          <List.Item
            onClick={() => {
              setSelectedHostId(host.hostId);
              // 调用父组件传递的点击事件处理函数
              onItemClick(host.hostId);
            }}
            style={{
              backgroundColor:
                host.hostId === selectedHostId ? "#f0f0f0" : "transparent",
            }}
          >
            <List.Item.Meta
              title={host.name}
              description={"IP:" + host.serverAddr}
            />
          </List.Item>
        )}
      />
    </div>
  );
};

export default connect(({ host }) => ({
  hosts: host.hosts,
  hostsTotal: host.total,
}))(LeftHostList);
