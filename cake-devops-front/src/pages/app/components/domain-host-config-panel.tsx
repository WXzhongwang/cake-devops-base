import React, { useState } from "react";
import { Button, Form, Input, Space, Table, Tabs, Typography } from "antd";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { AppEnv, ServiceItem } from "@/models/app";
import ServicePanel from "./service-panel";

interface DomainHostConfigPanelProps {
  appEnv: AppEnv;
  dispatch: Dispatch;
}

const DomainHostConfigPanel: React.FC<DomainHostConfigPanelProps> = ({
  appEnv,
  dispatch,
}) => {
  const [serviceForm] = Form.useForm();
  const [ingressForm] = Form.useForm();
  const [domainsList, setDomainsList] = useState(appEnv.domains);
  const initialServices = appEnv.service ? JSON.parse(appEnv.service) : [];
  const [services, setServices] = useState(initialServices);
  // const [serviceName, setServiceName] = useState(appEnv.service);
  const [ingressName, setIngressName] = useState(appEnv.ingress);

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
                <Form
                  form={ingressForm}
                  initialValues={{ ingressName }}
                  layout="horizontal"
                  labelCol={{ span: 8 }}
                  wrapperCol={{ span: 16 }}
                  style={{ maxWidth: 600, marginBottom: 16 }}
                >
                  <Form.Item label="Ingress 名称" name="ingressName">
                    <Input placeholder="请输入Ingress名称" />
                  </Form.Item>

                  <Form.Item label="新域名" name="newDomain">
                    <Input placeholder="请输入新域名" />
                  </Form.Item>

                  <Button type="dashed" onClick={handleAddDomain}>
                    添加域名
                  </Button>

                  <Table
                    dataSource={domainsList.map((domain) => ({
                      key: domain,
                      domain,
                    }))}
                    columns={[
                      {
                        title: "域名",
                        dataIndex: "domain",
                        key: "domain",
                      },
                      {
                        title: "操作",
                        key: "action",
                        render: (_, record) => (
                          <Button
                            type="link"
                            onClick={() => handleDeleteDomain(record.domain)}
                          >
                            删除
                          </Button>
                        ),
                      },
                    ]}
                    pagination={false}
                  />

                  <Space style={{ marginTop: 16 }}>
                    <Button type="primary" onClick={handleIngressSubmit}>
                      更新Ingress信息
                    </Button>
                  </Space>
                </Form>
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
