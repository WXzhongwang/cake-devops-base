import React from "react";
import { Space, Tabs } from "antd";
import { connect, Dispatch } from "umi";
import { AppEnv } from "@/models/app";
import ServicePanel from "./service-panel";
import IngressPanel from "@/pages/ci/components/ingress-panel";

interface DomainHostConfigPanelProps {
  appEnv: AppEnv | undefined;
  appName: string;
  dispatch: Dispatch;
}

const DomainHostConfigPanel: React.FC<DomainHostConfigPanelProps> = ({
  appEnv,
  appName,
  dispatch,
}) => {
  const initialServices = appEnv?.service ? JSON.parse(appEnv.service) : [];
  const initialIngress = appEnv?.ingress ? JSON.parse(appEnv.ingress) : {};

  return (
    <Space direction="vertical" style={{ width: "100%" }}>
      {appEnv && (
        <Tabs
          items={[
            {
              label: "Service",
              key: "service",
              children: (
                <>
                  <ServicePanel
                    initialServices={initialServices}
                    selectedEnvironment={appEnv.envId}
                  ></ServicePanel>
                </>
              ),
            },
            {
              label: "Ingress",
              key: "ingress",
              children: (
                <>
                  <IngressPanel
                    ingress={initialIngress}
                    appName={appName}
                    selectedEnvironment={appEnv.envId}
                  ></IngressPanel>
                </>
              ),
            },
          ]}
        />
      )}
    </Space>
  );
};
export default connect()(DomainHostConfigPanel);
