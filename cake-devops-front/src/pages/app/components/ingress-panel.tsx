import React, { useState } from "react";
import Table, { ColumnsType } from "antd/lib/table";
import {
  Button,
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
  http: {
    paths: PathItem[];
  };
}

interface PathItem {
  path: string;
  pathType: string;
  service: ServiceConfigItem;
}

interface ServiceConfigItem {
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

  const handleAddHost = () => {
    const newRule: RuleItem = {
      host: "",
      http: {
        paths: [],
      },
    };
    form.setFieldsValue({
      rules: [...(form.getFieldValue("rules") || []), newRule],
    });
  };

  const handleRemoveHost = (index: number) => {
    const rules = form.getFieldValue("rules") || [];
    const newRules = rules.filter((_: RuleItem, i: number) => i !== index);
    form.setFieldsValue({ rules: newRules });
  };

  const handleAddPath = (hostIndex: number) => {
    const newPaths: PathItem[] = [
      ...(form.getFieldValue(`rules[${hostIndex}].http.paths`) || []),
      {
        path: "",
        pathType: "Exact",
        service: {
          serviceName: "",
          servicePort: 80,
        },
      },
    ];
    form.setFieldsValue({
      [`rules[${hostIndex}].http.paths`]: newPaths,
    });
  };

  const handleRemovePath = (hostIndex: number, pathIndex: number) => {
    const paths = form.getFieldValue(`rules[${hostIndex}].http.paths`) || [];
    const newPaths = paths.filter((_: PathItem, i: number) => i !== pathIndex);
    form.setFieldsValue({
      [`rules[${hostIndex}].http.paths`]: newPaths,
    });
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Title level={3}>{ingress.ingressName}</Title>
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
              <>
                {fields.map((field, hostIndex) => (
                  <div key={field.key}>
                    <Space align="baseline">
                      <Form.Item
                        {...field}
                        name={[field.name, "host"]}
                        label={`Host ${hostIndex + 1}`}
                        rules={[
                          {
                            required: true,
                            message: `请输入 Host ${hostIndex + 1}!`,
                          },
                        ]}
                      >
                        <Input />
                      </Form.Item>
                      <Button
                        type="dashed"
                        onClick={() => handleAddPath(hostIndex)}
                        block
                      >
                        添加 Path
                      </Button>
                      <Button
                        type="dashed"
                        onClick={() => handleRemoveHost(hostIndex)}
                        block
                      >
                        删除 Host
                      </Button>
                    </Space>
                    <Space direction="horizontal">
                      <Table
                        columns={[
                          { title: "pathType", dataIndex: "pathType" },
                          { title: "path", dataIndex: "path" },
                          {
                            title: "服务名称",
                            dataIndex: "service.serviceName",
                          },
                          {
                            title: "服务端口",
                            dataIndex: "service.servicePort",
                          },
                          {
                            title: "操作",
                            dataIndex: "operation",
                            render: (text, record, index) => (
                              <Space size={"middle"}>
                                <Typography.Link
                                  onClick={() =>
                                    handleRemovePath(hostIndex, index)
                                  }
                                >
                                  删除
                                </Typography.Link>
                              </Space>
                            ),
                          },
                        ]}
                        dataSource={form.getFieldValue(
                          `rules[${hostIndex}].http.paths`
                        )}
                        pagination={false}
                      />
                    </Space>
                  </div>
                ))}
                <Form.Item>
                  <Button type="dashed" onClick={() => handleAddHost()} block>
                    添加 Host
                  </Button>
                </Form.Item>
              </>
            )}
          </Form.List>
        </Form>
      </Modal>

      <Button type="primary" onClick={() => handleEdit(ingress)}>
        编辑 Ingress
      </Button>
    </Space>
  );
};

export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(IngressPanel);
