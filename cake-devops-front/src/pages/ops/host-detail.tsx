import React from "react";
import TerminalComponent from "./components/single-terminal-component";

const HostDetailPage = () => {
  // 获取主机的 WebSocket 地址，你需要根据实际数据结构获取正确的地址
  const wsUrl = `ws://${window.location.host}/api/ws/terminal`;

  return (
    <div>
      <h1>主机详情</h1>
      {/* 使用 TerminalComponent 组件 */}
      <TerminalComponent wsUrl={wsUrl} />
    </div>
  );
};

export default HostDetailPage;
