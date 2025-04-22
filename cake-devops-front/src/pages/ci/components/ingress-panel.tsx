import React, { useState } from "react";
import Table, { ColumnsType } from "antd/lib/table";
import {
  Button,
  Card,
  Form,
  Input,
  message,
  Modal,
  Popconfirm,
  Select,
  Space,
  Typography,
} from "antd";
import { nanoid } from "nanoid";
import { connect, Dispatch } from "umi";
import { API } from "typings";
import appDetail from "../app-detail";
import { AppInfo, IngressDTO } from "@/models/app";
import {
  CloseOutlined,
  MinusCircleOutlined,
  PlusOutlined,
} from "@ant-design/icons";
import { UserInfo } from "@/models/user";

const { Title } = Typography;

interface IngressPanelProps {
  ingress: IngressDTO;
  appName: string;
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

const IngressPanel: React.FC<IngressPanelProps> = ({
  ingress,
  appName,
  selectedEnvironment,
  dispatch,
}) => {
  console.log("init", appName);
  const [form] = Form.useForm();
  const [editingItem, setEditingItem] = useState<IngressDTO | null>(null);
  const [modelOpen, setModelOpen] = useState<boolean>(false);

  const handleEdit = (item: IngressDTO) => {
    setEditingItem(item);
    setModelOpen(!modelOpen);
    form.setFieldsValue({ ...item });
  };

  const handleCancel = () => {
    setModelOpen(!modelOpen);
    setEditingItem(null);
  };

  const handleSave = async () => {
    try {
      const row = await form.validateFields();

      // 调用接口保存数据
      dispatch({
        type: "app/saveIngress",
        payload: {
          envId: selectedEnvironment,
          ingressDTO: row,
        },
        callback: (res: boolean) => {
          setEditingItem(null);
          setModelOpen(!modelOpen);
          message.success("保存成功");
        },
      });
    } catch (errInfo) {
      console.log("Validate Failed:", errInfo);
    }
  };

  // 格式化展示 Ingress 规则
  const formatRules = (rules: any[]) => {
    return rules?.map((rule, index) => (
      <Card key={index} size="small" title={`Rule ${index + 1}`}>
        <Typography.Text strong>Host:</Typography.Text> {rule.host}
        <br />
        <Typography.Text strong>Paths:</Typography.Text>
        {rule.paths.map((path: any, pathIndex: number) => (
          <div key={pathIndex}>
            <Typography.Text strong>Path Type:</Typography.Text> {path.pathType}
            <br />
            <Typography.Text strong>Path:</Typography.Text> {path.path}
            <br />
            <Typography.Text strong>Service Name:</Typography.Text>{" "}
            {path.backend.serviceName}
            <br />
            <Typography.Text strong>Service Port:</Typography.Text>{" "}
            {path.backend.servicePort}
            <br />
          </div>
        ))}
      </Card>
    ));
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Button
        type="primary"
        style={{ marginBottom: 16 }}
        onClick={() => handleEdit(ingress)}
      >
        编辑 Ingress
      </Button>
      <Card title={ingress.ingressName} style={{ width: "100%" }}>
        {formatRules(ingress.rules)}
      </Card>
      <Modal
        width={800}
        title={editingItem ? "编辑 Ingress" : "新增 Ingress"}
        open={modelOpen}
        onOk={handleSave}
        onCancel={handleCancel}
      >
        <Form
          form={form}
          initialValues={{
            ingressName: ingress.ingressName ?? appName,
            rules: ingress.rules,
          }}
          labelCol={{ span: 6 }}
          wrapperCol={{ span: 18 }}
          layout="horizontal"
        >
          <Form.Item
            name="ingressName"
            label="Ingress 名称"
            rules={[{ required: true, message: "请输入 Ingress 名称!" }]}
          >
            <Input type="text" disabled />
          </Form.Item>

          <Form.List name="rules">
            {(fields, { add, remove }) => (
              <div
                style={{ display: "flex", rowGap: 16, flexDirection: "column" }}
              >
                {fields.map((field) => (
                  <Card
                    size="small"
                    title={`Rule ${field.name + 1}`}
                    key={field.key}
                    extra={
                      <CloseOutlined
                        onClick={() => {
                          remove(field.name);
                        }}
                      />
                    }
                  >
                    <Form.Item label="host" name={[field.name, "host"]}>
                      <Input />
                    </Form.Item>

                    {/* Nest Form.List */}
                    <Form.Item label="http">
                      <Form.List name={[field.name, "paths"]}>
                        {(subFields, subOpt) => (
                          <div
                            style={{
                              display: "flex",
                              flexDirection: "column",
                              rowGap: 16,
                            }}
                          >
                            {subFields.map((subField) => (
                              <Space key={subField.key}>
                                <Form.Item
                                  noStyle
                                  name={[subField.name, "pathType"]}
                                >
                                  <Select placeholder="路径类型">
                                    <Select.Option value="Exact">
                                      Exact
                                    </Select.Option>
                                    <Select.Option value="Prefix">
                                      Prefix
                                    </Select.Option>
                                  </Select>
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[subField.name, "path"]}
                                >
                                  <Input placeholder="path" />
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[
                                    subField.name,
                                    "backend",
                                    "serviceName",
                                  ]}
                                >
                                  <Input placeholder="serviceName" />
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[
                                    subField.name,
                                    "backend",
                                    "servicePort",
                                  ]}
                                >
                                  <Input placeholder="servicePort" />
                                </Form.Item>
                                <CloseOutlined
                                  onClick={() => {
                                    subOpt.remove(subField.name);
                                  }}
                                />
                              </Space>
                            ))}
                            <Button
                              type="dashed"
                              onClick={() => subOpt.add()}
                              block
                            >
                              + 添加Path
                            </Button>
                          </div>
                        )}
                      </Form.List>
                    </Form.Item>
                  </Card>
                ))}

                <Button type="dashed" onClick={() => add()} block>
                  + 添加规则
                </Button>
              </div>
            )}
          </Form.List>

          <Form.Item noStyle shouldUpdate>
            {() => (
              <Typography>
                <pre>{JSON.stringify(form.getFieldsValue(), null, 2)}</pre>
              </Typography>
            )}
          </Form.Item>
        </Form>
      </Modal>
    </Space>
  );
};

export default connect(({ user }: { user: { userData: UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(IngressPanel);
