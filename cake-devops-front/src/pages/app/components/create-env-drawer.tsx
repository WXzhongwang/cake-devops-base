import React from "react";
import { Drawer, Form, Select, Input, Radio, Button, Space } from "antd";
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import { ClusterInfo } from "@/models/cluster";

const { Option } = Select;

interface CreateEnvDrawerProps {
  open: boolean;
  onClose: () => void;
  onFinish: (values: any) => void;
  clusterList: ClusterInfo[];
}

const CreateEnvDrawer: React.FC<CreateEnvDrawerProps> = ({
  open,
  onClose,
  onFinish,
  clusterList,
}) => {
  const [form] = Form.useForm();

  return (
    <Drawer
      title="添加环境"
      width={500}
      onClose={onClose}
      open={open}
      bodyStyle={{ paddingBottom: 80 }}
    >
      <Form
        layout="vertical"
        form={form}
        initialValues={{
          needApproval: false,
          autoScaling: false,
        }}
        onFinish={onFinish}
      >
        {/* 在这里添加表单项，例如环境名称、环境类型、选择集群、域名等 */}
        <Form.Item
          name="envName"
          label="环境名称"
          rules={[{ required: true, message: "请输入环境名称" }]}
        >
          <Input placeholder="请输入环境名称" />
        </Form.Item>
        <Form.Item
          name="env"
          label="环境类型"
          rules={[{ required: true, message: "请选择环境类型" }]}
        >
          <Select placeholder="请选择环境类型">
            <Option value="TEST">测试</Option>
            <Option value="PRE">预发</Option>
            <Option value="PROD">线上</Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="clusterId"
          label="选择集群"
          rules={[{ required: true, message: "请选择集群" }]}
        >
          <Select placeholder="请选择集群">
            {clusterList &&
              clusterList.map((cluster) => (
                <Option key={cluster.clusterId} value={cluster.clusterId}>
                  {cluster.clusterName}({cluster.clusterType})
                </Option>
              ))}
          </Select>
        </Form.Item>

        <Form.List
          name="domains"
          rules={[
            {
              validator: async (_, domains) => {
                if (domains && domains.length > 3) {
                  return Promise.reject(new Error("最多配置3个域名"));
                }
              },
            },
          ]}
        >
          {(fields, { add, remove }, { errors }) => (
            <>
              {fields.map((field, index) => (
                <Form.Item
                  style={{ width: "100%" }}
                  label={index === 0 ? "域名" : ""}
                  required={false}
                  key={field.key}
                >
                  <Form.Item
                    {...field}
                    validateTrigger={["onChange", "onBlur"]}
                    rules={[
                      {
                        required: true,
                        whitespace: true,
                        message: "请输入合法的域名",
                      },
                    ]}
                    noStyle
                  >
                    <Input style={{ width: 260 }} placeholder="请输入域名" />
                  </Form.Item>

                  <MinusCircleOutlined
                    style={{ margin: "0 8px" }}
                    className="dynamic-delete-button"
                    onClick={() => remove(field.name)}
                  />
                </Form.Item>
              ))}
              <Form.Item>
                <Button
                  type="dashed"
                  onClick={() => add()}
                  style={{ width: "60%" }}
                  icon={<PlusOutlined />}
                >
                  增加域名
                </Button>
                <Form.ErrorList errors={errors} />
              </Form.Item>
            </>
          )}
        </Form.List>
        <Form.Item
          name="replicas"
          label="副本数"
          rules={[{ required: true, message: "请输入副本数" }]}
        >
          <Input type="number" placeholder="请输入副本数" />
        </Form.Item>
        {/* 其他表单项根据需要添加 */}
        <Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateEnvDrawer;
