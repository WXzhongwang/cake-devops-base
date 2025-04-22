import React, { useState } from "react";
import { Button, Form, Input, message, Space, Tabs, Typography } from "antd";
import { ResourceStrategyDTO } from "@/models/app";
import { connect, Dispatch } from "umi";
import { UserInfo } from "@/models/user";

interface EnvVarConfigPanelProps {
  resourceStrategy: ResourceStrategyDTO | undefined;
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

const EnvResourcePanel: React.FC<EnvVarConfigPanelProps> = ({
  resourceStrategy,
  selectedEnvironment,
  dispatch,
}) => {
  const [replicasForm] = Form.useForm();
  const [resourceForm] = Form.useForm();

  const scale = () => {
    replicasForm.validateFields().then((values) => {
      console.log("update replicas", values);
      if (values.replicas > 10) {
        message.error("副本数不得超过10");
        return;
      }
      dispatch({
        type: "app/scale",
        payload: {
          envId: selectedEnvironment,
          replicas: values.replicas,
        },
      });
      replicasForm.resetFields();
      message.success("更新成功");
      window.location.reload();
    });
  };

  const modifyAppResources = () => {
    resourceForm.validateFields().then((values) => {
      dispatch({
        type: "app/modifyAppResources",
        payload: {
          envId: selectedEnvironment,
          resourceStrategyDTO: values,
        },
      });
      resourceForm.resetFields();
      message.success("更新成功");
      window.location.reload();
    });
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Tabs
        items={[
          {
            label: "副本数",
            key: "replicas",
            children: (
              <>
                <Form
                  form={replicasForm}
                  initialValues={resourceStrategy}
                  layout="horizontal"
                  labelCol={{ span: 8 }}
                  wrapperCol={{ span: 16 }}
                  style={{ maxWidth: 600, marginBottom: 16 }}
                >
                  <Form.Item label="副本数" name="replicas">
                    <Input
                      type="number"
                      max={10}
                      style={{ width: "45%" }}
                      min={0}
                    />
                  </Form.Item>
                  <Space style={{ marginBottom: 16 }}>
                    <Button type="primary" onClick={scale}>
                      扩缩容
                    </Button>
                  </Space>
                </Form>
              </>
            ),
          },
          {
            label: "资源更新",
            key: "resource",
            children: (
              <>
                <Form
                  form={resourceForm}
                  initialValues={resourceStrategy}
                  layout="horizontal"
                  labelCol={{ span: 8 }}
                  wrapperCol={{ span: 16 }}
                  style={{ maxWidth: 600, marginBottom: 16 }}
                >
                  <Form.Item label="CPU">
                    <Input.Group compact>
                      <Form.Item name="cpu" noStyle>
                        <Input
                          style={{ width: "45%" }}
                          type="text"
                          placeholder="最小CPU，毫核"
                          suffix="M"
                        />
                      </Form.Item>
                      <span
                        style={{
                          width: "30px",
                          height: "30px",
                          display: "inline-flex",
                          alignItems: "center",
                          textAlign: "center",
                          justifyContent: "center",
                        }}
                      >
                        -
                      </span>
                      <Form.Item name="maxCpu" noStyle>
                        <Input
                          style={{ width: "45%" }}
                          type="text"
                          placeholder="最大CPU，毫核"
                          suffix="M"
                        />
                      </Form.Item>
                    </Input.Group>
                  </Form.Item>

                  <Form.Item label="Memory">
                    <Input.Group compact>
                      <Form.Item name="memory" noStyle>
                        <Input
                          style={{ width: "45%" }}
                          placeholder="最小内存"
                          suffix="M"
                        />
                      </Form.Item>
                      <span
                        style={{
                          width: "30px",
                          height: "30px",
                          display: "inline-flex",
                          alignItems: "center",
                          textAlign: "center",
                          justifyContent: "center",
                        }}
                      >
                        -
                      </span>
                      <Form.Item name="maxMemory" noStyle>
                        <Input
                          style={{ width: "45%" }}
                          placeholder="最大内存"
                          suffix="M"
                        />
                      </Form.Item>
                    </Input.Group>
                  </Form.Item>

                  <Space style={{ marginBottom: 16 }}>
                    <Button type="primary" onClick={modifyAppResources}>
                      更新资源配置
                    </Button>
                  </Space>
                </Form>
              </>
            ),
          },
        ]}
      ></Tabs>
    </Space>
  );
};

export default connect(({ user }: { user: { userData: UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(EnvResourcePanel);
