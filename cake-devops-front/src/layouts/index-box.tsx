import React, { useEffect, useState } from "react";
import { Dropdown, Menu, Badge, List, Button, message } from "antd";
import { MailOutlined, DeleteOutlined, EyeOutlined } from "@ant-design/icons";
import { WebSideMessageDTO } from "@/models/webside-message";
import { Dispatch, connect } from "umi";

interface IndexBoxProps {
  dispatch: Dispatch;
}

const IndexBox: React.FC<IndexBoxProps> = ({ dispatch }) => {
  const [unreadCount, setUnreadCount] = useState(0);
  const [maxId, setMaxId] = useState(0);
  const [webSideMessages, setWebSideMessages] = useState<WebSideMessageDTO[]>(
    []
  );

  const loadUnreadCount = () => {
    dispatch({
      type: "webSideMessage/getUnreadCount",
      payload: {},
      callback: (res: number) => {
        setUnreadCount(res);
      },
    });
  };

  const loadMessages = () => {
    dispatch({
      type: "webSideMessage/fetchMessages",
      payload: {
        maxId: maxId,
        page: 1,
        pageSize: 20,
        readStatus: 1,
      },
      callback: (res: WebSideMessageDTO[]) => {
        setWebSideMessages(Array.isArray(res) ? res : []);
      },
    });
  };

  useEffect(() => {
    loadUnreadCount();
    loadMessages();
  }, []);

  const handleMarkAllAsRead = () => {
    dispatch({
      type: "webSideMessage/setAllRead",
      payload: {},
      callback: () => {
        loadUnreadCount();
        loadMessages();
        message.success("全部标记为已读");
      },
    });
  };

  const handleDeleteAllRead = () => {
    dispatch({
      type: "webSideMessage/deleteAllRead",
      payload: {},
      callback: () => {
        loadMessages();
        message.success("删除全部已读站内信");
      },
    });
  };

  const handleMarkAsRead = (messageId: number) => {
    dispatch({
      type: "webSideMessage/read",
      payload: {
        messageIdList: [messageId],
      },
      callback: () => {
        loadUnreadCount();
        loadMessages();
        message.success("标记为已读");
      },
    });
  };

  const handleDeleteById = (messageId: number) => {
    dispatch({
      type: "webSideMessage/deleteMessage",
      payload: {
        messageIdList: [messageId],
      },
      callback: () => {
        loadUnreadCount();
        loadMessages();
        message.success("删除成功");
      },
    });
  };

  const menu = (
    <Menu>
      <Menu.Item key="markAllAsRead" onClick={handleMarkAllAsRead}>
        <EyeOutlined /> 标记已读
      </Menu.Item>
      <Menu.Item key="deleteAllRead" onClick={handleDeleteAllRead}>
        <DeleteOutlined /> 删除全部已读
      </Menu.Item>
      <Menu.Divider />
      {webSideMessages.length > 0 ? (
        webSideMessages.map((msg) => (
          <Menu.Item key={msg.id}>
            <List.Item
              actions={[
                <Button type="link" onClick={() => handleMarkAsRead(msg.id)}>
                  标记为已读
                </Button>,
                <Button type="link" onClick={() => handleDeleteById(msg.id)}>
                  删除
                </Button>,
              ]}
            >
              {msg.sendMessage}
            </List.Item>
          </Menu.Item>
        ))
      ) : (
        <Menu.Item key="noMessages">
          <List.Item>
            <span>没有站内信</span>
          </List.Item>
        </Menu.Item>
      )}
    </Menu>
  );

  return (
    <Dropdown overlay={menu} trigger={["click"]}>
      <Badge count={unreadCount}>
        <MailOutlined style={{ fontSize: "20px" }} />
      </Badge>
    </Dropdown>
  );
};

export default connect()(IndexBox);
