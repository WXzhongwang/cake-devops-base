import React, { useEffect, useState } from "react"; // 导入 useState
import TerminalComponent from "./components/single-terminal-component";
import { useParams } from "umi";

const HostDetailPage = () => {
  const [loading, setLoading] = useState(false); // 使用 useState 管理加载状态
  // 获取主机的 WebSocket 地址，你需要根据实际数据结构获取正确的地址
  const wsUrl = `ws://${window.location.host}/api/ws/terminal`;
  const { id } = useParams();
  useEffect(() => {
    if (id) {
      // openSession(id);
    }
  }, [id]);

  return (
    <div>
      <h1>主机详情</h1>
      {loading && <p>加载中...</p>}
      <TerminalComponent
        wsUrl={wsUrl}
        modalHost={null}
        open={false}
        closeTerminal={() => {}}
      />
    </div>
  );
};

export default HostDetailPage;
