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
  const [serviceForm] = Form.useForm();
  const [ingressForm] = Form.useForm();
  const [domainsList, setDomainsList] = useState(appEnv.domains);
  const initialServices = appEnv.service ? JSON.parse(appEnv.service) : [];
  const initialIngress = appEnv.ingress ? JSON.parse(appEnv.ingress) : {};
  const handleServiceSubmit = () => {
    serviceForm.validateFields().then((values) => {
      dispatch({
        type: "app/updateService",
        payload: {
          serviceName: values.serviceName,
        },
      });
    });
  };

  const handleIngressSubmit = () => {
    ingressForm.validateFields().then((values) => {
      dispatch({
        type: "app/updateIngress",
        payload: {
          ingressName: values.ingressName,
          domains: domainsList,
        },
      });
    });
  };

  const handleAddDomain = () => {
    ingressForm.validateFields(["newDomain"]).then((values) => {
      setDomainsList([...domainsList, values.newDomain]);
      ingressForm.resetFields(["newDomain"]);
    });
  };

  const handleDeleteDomain = (domain: string) => {
    setDomainsList(domainsList.filter((item) => item !== domain));
  };

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
