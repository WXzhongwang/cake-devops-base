import React, { useEffect, useState } from "react";
import { Input, List, Pagination } from "antd";
import { HostModel } from "@/models/host";
import { Dispatch, connect } from "umi";
import { API } from "typings";

interface LeftHostListProps {
  dispatch: Dispatch;
  onItemClick: (hostId: string) => void;
}

const LeftHostList: React.FC<LeftHostListProps> = ({
  dispatch,
  onItemClick,
}) => {
  const [searchText, setSearchText] = useState<string>("");
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [selectedHostId, setSelectedHostId] = useState<string>("");
  const [hosts, setHosts] = useState<API.Page<HostModel>>();

  useEffect(() => {
    fetchHosts();
  }, []);

  const fetchHosts = () => {
    dispatch({
      type: "host/fetchHosts",
      payload: {
        pageNo: pagination.pageNo,
        pageSize: pagination.pageSize,
        name: searchText,
      },
      callback: (res: API.Page<HostModel>) => {
        setHosts(res);
      },
    });
  };

  useEffect(() => {
    fetchHosts();
  }, [dispatch, pagination, searchText]);

  useEffect(() => {
    if (hosts) {
      if (hosts?.items.length > 0) {
        setSelectedHostId(hosts?.items[0].hostId);
        onItemClick(hosts?.items[0].hostId);
      }
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
        dataSource={hosts?.items}
        pagination={{
          total: hosts?.total,
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

export default connect()(LeftHostList);
