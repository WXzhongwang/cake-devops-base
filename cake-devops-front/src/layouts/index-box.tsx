import React, { useEffect, useState } from "react";
import { Badge, List, Button, message, Drawer } from "antd";
import { MailOutlined, DeleteOutlined, EyeOutlined } from "@ant-design/icons";
import { WebSideMessageDTO } from "@/models/webside-message";
import { Dispatch, connect } from "umi";
import dayjs from "dayjs";

interface IndexBoxProps {
  dispatch: Dispatch;
}

const IndexBox: React.FC<IndexBoxProps> = ({ dispatch }) => {
  const [unreadCount, setUnreadCount] = useState(0);
  const [maxId, setMaxId] = useState(0);
  const [webSideMessages, setWebSideMessages] = useState<WebSideMessageDTO[]>(
    []
  );
  const [drawerVisible, setDrawerVisible] = useState(false);

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
        pageNo: 1,
        pageSize: 20,
        readStatus: 1,
      },
      callback: (res: any) => {
        const messages = res.items;
        setWebSideMessages(Array.isArray(messages) ? messages : []);
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

  const messageList = (
    <div>
      <div
        style={{
          padding: "16px 24px",
          borderBottom: "1px solid #f0f0f0",
          display: "flex",
          justifyContent: "space-between",
        }}
      >
        <Button
          type="text"
          icon={<EyeOutlined />}
          onClick={handleMarkAllAsRead}
          style={{ fontSize: "14px" }}
        >
          标记所有为已读
        </Button>
        <Button
          type="text"
          icon={<DeleteOutlined />}
          onClick={handleDeleteAllRead}
          style={{ fontSize: "14px" }}
        >
          删除全部已读
        </Button>
      </div>
      <List
        style={{ padding: "8px 0" }}
        dataSource={webSideMessages}
        renderItem={(msg) => (
          <List.Item
            style={{
              padding: "12px 24px", // 减小整体padding
              transition: "all 0.3s",
              cursor: "pointer",
              backgroundColor: msg.readStatus === 0 ? "#f0f7ff" : "transparent",
              borderBottom: "1px solid #f0f0f0",
            }}
            actions={[
              <Button
                type="text"
                size="small"
                onClick={(e) => {
                  e.stopPropagation();
                  handleMarkAsRead(msg.id);
                }}
                style={{ padding: "0 4px", height: "24px", fontSize: "12px" }} // 调整按钮大小
              >
                已读
              </Button>,
              <Button
                type="text"
                size="small"
                danger
                onClick={(e) => {
                  e.stopPropagation();
                  handleDeleteById(msg.id);
                }}
                style={{ padding: "0 4px", height: "24px", fontSize: "12px" }} // 调整按钮大小
              >
                删除
              </Button>,
            ]}
          >
            <List.Item.Meta
              title={
                <div
                  className="message-content"
                  style={{
                    fontSize: "13px", // 稍微减小字号
                    lineHeight: "1.4", // 减小行高
                    color: "#333",
                    marginBottom: "2px", // 减小底部间距
                  }}
                  dangerouslySetInnerHTML={{ __html: msg.sendMessage }}
                />
              }
              description={
                <div
                  style={{
                    fontSize: "12px",
                    color: "#999",
                    marginTop: "4px", // 减小顶部间距
                  }}
                >
                  {dayjs(msg.gmtCreateTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              }
            />
          </List.Item>
        )}
        locale={{ emptyText: "暂无消息" }}
      />
    </div>
  );

  return (
    <>
      <div
        onClick={() => setDrawerVisible(true)}
        style={{ cursor: "pointer", padding: "4px" }}
      >
        <Badge
          count={unreadCount}
          style={{
            backgroundColor: "#ff4d4f",
            boxShadow: "0 0 0 1px #fff",
          }}
        >
          <MailOutlined
            style={{ fontSize: "20px", color: "rgba(0,0,0,0.65)" }}
          />
        </Badge>
      </div>
      <Drawer
        title={
          <div
            style={{
              fontSize: "16px",
              fontWeight: 500,
              color: "#333",
            }}
          >
            站内信
          </div>
        }
        placement="right"
        onClose={() => setDrawerVisible(false)}
        open={drawerVisible}
        width={400}
        bodyStyle={{ padding: 0 }}
      >
        {messageList}
      </Drawer>
    </>
  );
};

export default connect()(IndexBox);
