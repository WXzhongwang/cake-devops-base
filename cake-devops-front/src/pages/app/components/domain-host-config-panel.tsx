import React, { useState } from "react";
import { Button, Form, Input, Space, Table, Tabs, Typography } from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { AppEnv, ServiceItem } from "@/models/app";
import ServicePanel from "./service-panel";
import IngressPanel from "@/pages/app/components/ingress-panel";

interface DomainHostConfigPanelProps {
  appEnv: AppEnv;
  appName: string;
  dispatch: Dispatch;
}

const DomainHostConfigPanel: React.FC<DomainHostConfigPanelProps> = ({
  appEnv,
  appName,
  dispatch,
}) => {
  const initialServices = appEnv.service ? JSON.parse(appEnv.service) : [];
  const initialIngress = appEnv.ingress ? JSON.parse(appEnv.ingress) : {};

  return (
    <Space direction="vertical" style={{ width: "100%" }}>
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
    </Space>
  );
};
export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(DomainHostConfigPanel);
