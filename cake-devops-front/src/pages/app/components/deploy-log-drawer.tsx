import { useEffect, useState } from "react";
import { Button, Drawer } from "antd";

interface DeployLogDrawerProps {
  open: boolean;
  onClose: () => void;
  pipeKey: string;
}

const DeployLogDrawer: React.FC<DeployLogDrawerProps> = ({
  open,
  onClose,
  pipeKey,
}) => {
  const [logContent, setLogContent] = useState("");

  useEffect(() => {
    let socket: WebSocket;

    if (open) {
      // 连接 WebSocket
      const connectWebSocket = () => {
        const connectionAddr = `ws://${window.location.host}/api/ws/devops/${pipeKey}`;
        socket = new WebSocket(connectionAddr); // 请根据实际情况修改 WebSocket 地址
        console.log("connect addr", connectionAddr);
        // 监听 WebSocket 连接打开事件
        socket.onopen = () => {
          console.log("WebSocket connected");
        };

        // 监听 WebSocket 接收到消息事件
        socket.onmessage = (event) => {
          const newLogContent = event.data;
          // 处理接收到的日志内容，例如追加到 logContent 中
          setLogContent((prevContent) => prevContent + newLogContent);
        };

        // 监听 WebSocket 连接关闭事件
        socket.onclose = () => {
          console.log("WebSocket closed");
          // 处理连接关闭的逻辑，例如重新连接等
        };
      };

      // 连接 WebSocket
      connectWebSocket();
    }

    // 在组件卸载时关闭 WebSocket 连接
    return () => {
      if (socket) {
        socket.close();
      }
    };
  }, [open]); // 请确保添加所有依赖项

  return (
    <Drawer title="查看发布日志" width={1200} onClose={onClose} open={open}>
      {/* 显示发布日志内容 */}
      <pre>{logContent}</pre>
    </Drawer>
  );
};

export default DeployLogDrawer;
