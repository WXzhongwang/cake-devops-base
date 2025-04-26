// src/pages/app/components/DeployHistoryLogsDrawer.tsx
import React from "react";
import { Drawer } from "antd";
import { DeployLogDTO } from "@/models/release";

interface DeployHistoryLogsDrawerProps {
  visible: boolean;
  onClose: () => void;
  deployHistoryLogs: DeployLogDTO[];
}

const DeployHistoryLogsDrawer: React.FC<DeployHistoryLogsDrawerProps> = ({
  visible,
  onClose,
  deployHistoryLogs,
}) => {
  return (
    <Drawer title="发布记录日志" width={1200} onClose={onClose} open={visible}>
      {/* 发布单详情 */}
      {deployHistoryLogs?.map((log: DeployLogDTO) => (
        <div>
          {log.time} {log.message}
        </div>
      ))}
    </Drawer>
  );
};

export default DeployHistoryLogsDrawer;
