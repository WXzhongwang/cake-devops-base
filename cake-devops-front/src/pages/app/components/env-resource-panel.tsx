import React, { useState } from "react";
import Table, { ColumnsType } from "antd/lib/table";
import {
  Button,
  Form,
  Input,
  message,
  Popconfirm,
  Space,
  Typography,
} from "antd";
import { nanoid } from "nanoid";
import { ResourceStrategyDTO } from "@/models/app";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import { values } from "lodash";

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
  const [resourceForm] = Form.useForm();
  console.log("resourceStrategy", resourceStrategy);

  const handleEnvVarSubmit = () => {
    resourceForm.validateFields().then((values) => {
      dispatch({
        type: "app/modifyAppResources",
        payload: {
          envId: selectedEnvironment,
          resourceStrategyDTO: values,
        },
      });
      resourceForm.resetFields();
    });
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Form
        form={resourceForm}
        initialValues={resourceStrategy}
        layout="horizontal"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600, marginBottom: 16 }}
      >
        <Form.Item label="副本数" name="replicas">
          <Input type="number" max={10} style={{ width: "45%" }} />
        </Form.Item>

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
          <Button type="primary" onClick={handleEnvVarSubmit}>
            更新资源配置
          </Button>
        </Space>
      </Form>
    </Space>
  );
};

export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(EnvResourcePanel);
