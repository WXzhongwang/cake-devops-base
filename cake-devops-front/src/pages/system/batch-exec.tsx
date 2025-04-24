import React, { useEffect, useState } from "react";
import { Card, Form, Input, Button, Select, Table, message } from "antd";
import { PageContainer } from "@ant-design/pro-layout";

const { TextArea } = Input;
import { API, BaseAction } from "typings";
import { CommandExecDTO } from "@/models/batch-exec";
import { connect, Dispatch } from "umi";
import { ScriptTemplateDTO } from "@/models/script-template";

interface BatchCommandProps {
  dispatch: Dispatch;
}
const BatchCommand: React.FC<BatchCommandProps> = ({ dispatch }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [commandPagination, setCommandPagination] = useState({
    pageNo: 1,
    pageSize: 10,
  });
  const [filters, setFilters] = useState({ name: "" });
  const [commandExecPage, setCommandExecPage] =
    useState<API.Page<CommandExecDTO>>();
  const [scriptPage, setScriptPage] = useState<API.Page<ScriptTemplateDTO>>();
  const fetchCommandExec = () => {
    dispatch({
      type: "commandExec/fetchCommandExec",
      payload: {
        ...filters,
        ...pagination,
      },
      callback: (res: API.Page<CommandExecDTO>) => {
        setCommandExecPage(res);
      },
    });
  };
  const queryScripts = () => {
    dispatch({
      type: "script/queryScripts",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<ScriptTemplateDTO>) => {
        setScriptPage(res);
      },
    });
  };

  useEffect(() => {
    queryScripts();
  }, [filters, pagination]);

  useEffect(() => {
    fetchCommandExec();
  }, [filters, pagination]);

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setCommandPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

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
              {/* {commandExecPage?.items.map((item) => (
                <Select.Option key={item.id} value={item.id}>
                  {item.host}
                </Select.Option>
              ))} */}
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
          dataSource={commandExecPage?.items}
          rowKey="host"
          style={{ marginTop: 24 }}
          pagination={{
            total: commandExecPage?.total,
            current: commandPagination.pageNo,
            pageSize: commandPagination.pageSize,
            onChange: handlePaginationChange,
          }}
        />
      </Card>
    </PageContainer>
  );
};

export default connect()(BatchCommand);
