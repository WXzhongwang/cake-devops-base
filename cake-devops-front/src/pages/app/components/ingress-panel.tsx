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
import { AppInfo } from "@/models/app";
import {
  CloseOutlined,
  MinusCircleOutlined,
  PlusOutlined,
} from "@ant-design/icons";

const { Title } = Typography;

interface IngressPanelProps {
  ingress: WrapIngressItem;
  appName: string;
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

interface WrapIngressItem {
  ingressName: string;
  rules: RuleItem[];
}

interface RuleItem {
  host: string;
  paths: PathItem[];
  tcp: PortItem[];
}

interface PathItem {
  path: string;
  pathType: string;
  serviceName: string;
  servicePort: number;
}
interface PortItem {
  port: string;
  serviceName: string;
  servicePort: number;
}

const IngressPanel: React.FC<IngressPanelProps> = ({
  ingress,
  appName,
  selectedEnvironment,
  dispatch,
}) => {
  console.log("init", appName);
  const [form] = Form.useForm();
  const [editingItem, setEditingItem] = useState<WrapIngressItem | null>(null);
  const [modelOpen, setModelOpen] = useState<boolean>(false);

  const handleEdit = (item: WrapIngressItem) => {
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
      setEditingItem(null);
      message.success("保存成功");

      // 调用接口保存数据
      dispatch({
        type: "app/saveIngress",
        payload: { ...row, environment: selectedEnvironment },
      });
    } catch (errInfo) {
      console.log("Validate Failed:", errInfo);
    }
  };

  // const handleAddHost = () => {
  //   const newRule: RuleItem = {
  //     host: "",
  //     http: {
  //       paths: [],
  //     },
  //   };
  //   form.setFieldsValue({
  //     rules: [...(form.getFieldValue("rules") || []), newRule],
  //   });
  // };

  // const handleRemoveHost = (index: number) => {
  //   const rules = form.getFieldValue("rules") || [];
  //   const newRules = rules.filter((_: RuleItem, i: number) => i !== index);
  //   form.setFieldsValue({ rules: newRules });
  // };

  // const handleAddPath = (hostIndex: number) => {
  //   const newPaths: PathItem[] = [
  //     ...(form.getFieldValue(`rules[${hostIndex}].http.paths`) || []),
  //     {
  //       path: "",
  //       pathType: "Exact",
  //       service: {
  //         serviceName: "",
  //         servicePort: 80,
  //       },
  //     },
  //   ];
  //   form.setFieldsValue({
  //     [`rules[${hostIndex}].http.paths`]: newPaths,
  //   });
  // };

  // const handleRemovePath = (hostIndex: number, pathIndex: number) => {
  //   const paths = form.getFieldValue(`rules[${hostIndex}].http.paths`) || [];
  //   const newPaths = paths.filter((_: PathItem, i: number) => i !== pathIndex);
  //   form.setFieldsValue({
  //     [`rules[${hostIndex}].http.paths`]: newPaths,
  //   });
  // };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Button
        type="primary"
        style={{ marginBottom: 16 }}
        onClick={() => handleEdit(ingress)}
      >
        编辑 Ingress
      </Button>
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
            ingressName: appName,
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
                                  name={[subField.name, "serviceName"]}
                                >
                                  <Input placeholder="serviceName" />
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[subField.name, "servicePort"]}
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

                    {/* Nest Form.List */}
                    <Form.Item label="tcp">
                      <Form.List name={[field.name, "ports"]}>
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
                                  name={[subField.name, "port"]}
                                >
                                  <Input placeholder="port" />
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[subField.name, "serviceName"]}
                                >
                                  <Input placeholder="serviceName" />
                                </Form.Item>
                                <Form.Item
                                  noStyle
                                  name={[subField.name, "servicePort"]}
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
                              + 添加Port
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

export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(IngressPanel);
