import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Button, Card, Form, Input, Space, Table, message, Drawer } from "antd";
import { connect, Dispatch } from "umi";
import { ScriptTemplateDTO } from "@/models/script-template";
import CreateScriptForm from "./components/create-script-form";
import dayjs from "dayjs";

const { TextArea } = Input;
import { API, BaseAction } from "typings";

interface ScriptListProps {
  dispatch: Dispatch;
  scripts: ScriptTemplateDTO[];
  total: number;
}

const ScriptList: React.FC<ScriptListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    name: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingScript, setEditingScript] = useState<
    ScriptTemplateDTO | undefined
  >(undefined);
  const [scriptPage, setScriptPage] = useState<API.Page<ScriptTemplateDTO>>();

  const [form] = Form.useForm();

  useEffect(() => {
    queryScripts();
  }, [pagination, filters]);

  const queryScripts = () => {
    dispatch({
      type: "script/queryScripts",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<ScriptTemplateDTO>) => {
        setScriptPage(res);
      },
    });
  };

  const handleDelete = (id: number) => {
    dispatch({
      type: "script/deleteScript",
      payload: { id: id },
    });
    queryScripts();
  };

  const columns = [
    {
      title: "脚本名称",
      dataIndex: "templateName",
      key: "templateName",
    },
    {
      title: "脚本内容",
      dataIndex: "templateValue",
      key: "templateValue",
      ellipsis: true, // 文字溢出显示省略号
      width: 300, // 设置列宽度
      render: (text: string) => (
        <span
          style={{
            overflow: "hidden",
            textOverflow: "ellipsis",
            whiteSpace: "nowrap",
          }}
        >
          {text}
        </span>
      ),
    },
    {
      title: "脚本描述",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "创建时间",
      dataIndex: "gmtCreate",
      key: "gmtCreate",
      render: (text: string, record: ScriptTemplateDTO) => (
        <>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "更新时间",
      dataIndex: "gmtModified",
      key: "gmtModified",
      render: (text: string, record: ScriptTemplateDTO) => (
        <>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (_: any, record: ScriptTemplateDTO) => (
        <Space size="middle">
          <a onClick={() => handleEditScript(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  const handleAddScript = () => {
    setEditingScript(undefined);
    setDrawerVisible(true);
    form.resetFields();
  };

  const handleEditScript = (script: ScriptTemplateDTO) => {
    setEditingScript(script);
    setDrawerVisible(true);
    form.setFieldsValue(script);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleSaveScript = async (values: ScriptTemplateDTO) => {
    if (editingScript) {
      // 编辑脚本
      dispatch({
        type: "script/updateScript",
        payload: { ...values, id: editingScript.id },
        callback: () => {
          message.success("脚本编辑成功");
          queryScripts();
        },
      });
    } else {
      // 新增脚本
      dispatch({
        type: "script/createScript",
        payload: values,
        callback: () => {
          message.success("脚本添加成功");
          queryScripts();
        },
      });
    }
    setDrawerVisible(false);
  };

  return (
    <PageContainer title="脚本列表">
      <Card>
        <Space direction="vertical" style={{ width: "100%" }}>
          <Form
            form={form}
            layout="inline"
            onFinish={(values) => {
              setFilters({
                name: values.name,
              });
            }}
          >
            <Form.Item name="name" label="脚本名称">
              <Input placeholder="请输入脚本名称" />
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button htmlType="button" onClick={() => form.resetFields()}>
                重置
              </Button>
            </Form.Item>
          </Form>

          <Button type="primary" onClick={handleAddScript}>
            新增脚本
          </Button>

          <Table
            columns={columns}
            dataSource={scriptPage?.items}
            rowKey={"id"} // 假设每个脚本对象都有一个唯一的 id 字段
            pagination={{
              total: scriptPage?.total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />

          <Drawer
            title={editingScript ? "编辑脚本" : "新增脚本"}
            width={600}
            open={drawerVisible}
            onClose={handleCloseDrawer}
            destroyOnClose={true}
          >
            <CreateScriptForm
              initialValues={editingScript}
              onSave={handleSaveScript}
              onUpdate={handleSaveScript} // 添加更新方法
              onCancel={handleCloseDrawer}
            />
          </Drawer>
        </Space>
      </Card>
    </PageContainer>
  );
};

export default connect()(ScriptList);
