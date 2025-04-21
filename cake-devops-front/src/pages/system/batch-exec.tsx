import React, { useState } from "react";
import { Card, Form, Input, Button, Select, Table, message } from "antd";
import { PageContainer } from "@ant-design/pro-layout";

const { TextArea } = Input;

const BatchCommand: React.FC = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState<any[]>([]);

  const handleExecute = async (values: any) => {
    setLoading(true);
    try {
      // TODO: 调用后端接口执行命令
      message.success("命令已提交执行");
    } catch (error) {
      message.error("执行失败");
    }
    setLoading(false);
  };

  const columns = [
    {
      title: "主机",
      dataIndex: "host",
      key: "host",
    },
    {
      title: "执行状态",
      dataIndex: "status",
      key: "status",
    },
    {
      title: "执行结果",
      dataIndex: "result",
      key: "result",
      width: "50%",
    },
  ];

  return (
    <PageContainer>
      <Card>
        <Form form={form} onFinish={handleExecute} layout="vertical">
          <Form.Item
            label="选择主机"
            name="hosts"
            rules={[{ required: true, message: "请选择执行主机" }]}
          >
            <Select
              mode="multiple"
              placeholder="请选择要执行的主机"
              style={{ width: "100%" }}
            >
              {/* TODO: 动态加载主机列表 */}
            </Select>
          </Form.Item>

          <Form.Item
            label="执行命令"
            name="command"
            rules={[{ required: true, message: "请输入要执行的命令" }]}
          >
            <TextArea rows={4} placeholder="请输入要执行的命令" />
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit" loading={loading}>
              执行
            </Button>
          </Form.Item>
        </Form>

        <Table
          columns={columns}
          dataSource={results}
          rowKey="host"
          style={{ marginTop: 24 }}
        />
      </Card>
    </PageContainer>
  );
};

export default BatchCommand;
