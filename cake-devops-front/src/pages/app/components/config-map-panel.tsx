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
import { connect } from "umi";
import { API } from "typings";

interface ConfigMapItem {
  id: string;
  label: string;
  value: string;
  editable?: boolean;
}

interface ConfigMapConfigPanelProps {
  initialConfigMap: ConfigMapItem[];
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

interface EditableCellProps extends React.HTMLAttributes<HTMLElement> {
  editing: boolean;
  dataIndex: string;
  title: any;
  inputType: "text";
  record: ConfigMapItem;
  index: number;
  children: React.ReactNode;
}

const EditableCell: React.FC<EditableCellProps> = ({
  editing,
  dataIndex,
  title,
  inputType,
  record,
  index,
  children,
  ...restProps
}) => {
  return (
    <td {...restProps}>
      {editing ? (
        <Form.Item
          name={dataIndex}
          style={{ margin: 0 }}
          rules={[
            {
              required: true,
              message: `请输入${title}!`,
            },
          ]}
        >
          <Input />
        </Form.Item>
      ) : (
        children
      )}
    </td>
  );
};

const ConfigMapConfigPanel: React.FC<ConfigMapConfigPanelProps> = ({
  initialConfigMap,
  selectedEnvironment,
  dispatch,
}) => {
  const [form] = Form.useForm();
  const [data, setData] = useState<ConfigMapItem[]>(initialConfigMap);
  const [editingKey, setEditingKey] = useState<String | undefined>("");
  const isEditing = (record: ConfigMapItem) => record.id === editingKey;

  const edit = (record: Partial<ConfigMapItem> & { id: React.Key }) => {
    form.setFieldsValue({ ...record });
    setEditingKey(record.id);
  };
  const cancel = () => {
    setEditingKey("");
  };

  const addNewRow = () => {
    const newRow: ConfigMapItem = {
      id: nanoid(),
      label: "",
      value: "",
      editable: true,
    };

    if (editingKey) {
      message.warn("请先保存");
      return;
    }
    setEditingKey(newRow.id);
    setData([...data, newRow]);
  };

  const save = async (id: React.Key) => {
    try {
      const row = (await form.validateFields()) as ConfigMapItem;

      const newData = [...data];
      const index = newData.findIndex((item) => id === item.id);
      if (index > -1) {
        const item = newData[index];
        newData.splice(index, 1, {
          ...item,
          ...row,
        });
        setData(newData);
        setEditingKey("");
      } else {
        newData.push(row);
        setData(newData);
        setEditingKey("");
      }
    } catch (errInfo) {
      console.log("Validate Failed:", errInfo);
    }
  };

  const deleteRow = (index: number) => {
    const newData = [...data];
    newData.splice(index, 1);
    setData(newData);
  };

  const columns = [
    {
      title: "Key",
      dataIndex: "label",
      editable: true,
    },
    {
      title: "Value",
      dataIndex: "value",
      editable: true,
    },
    {
      title: "操作",
      dataIndex: "operation",
      width: "200px",
      render: (_: any, record: ConfigMapItem, index: number) => {
        const editable = isEditing(record);
        return editable ? (
          <Space size={"middle"}>
            <Typography.Link
              onClick={() => save(record.id)}
              style={{ marginRight: 8 }}
            >
              保存
            </Typography.Link>
            <Popconfirm title="取消修改?" onConfirm={cancel}>
              <a>取消</a>
            </Popconfirm>
          </Space>
        ) : (
          <Space size={"middle"}>
            <Typography.Link
              disabled={editingKey !== ""}
              onClick={() => edit(record)}
            >
              编辑
            </Typography.Link>
            <Typography.Link
              disabled={editingKey !== ""}
              onClick={() => deleteRow(index)}
            >
              删除
            </Typography.Link>
          </Space>
        );
      },
    },
  ];

  const mergedColumns = columns.map((col) => {
    if (!col.editable) {
      return col;
    }
    return {
      ...col,
      onCell: (record: ConfigMapItem) => ({
        record,
        inputType: "text",
        dataIndex: col.dataIndex,
        title: col.title,
        editing: isEditing(record),
      }),
    };
  });

  const handleConfigMapSubmit = (data: ConfigMapItem[]) => {
    // 提交配置项数据
    if (data.some((item) => !item.label || !item.value)) {
      message.warning("配置项不能为空");
      return;
    }
    // 将 configMapData 转换为对象
    const configMap = data.reduce(
      (acc, { label, value }) => {
        if (label) acc[label] = value;
        return acc;
      },
      {} as Record<string, string>
    );

    dispatch({
      type: "app/modifyAppEnvConfigMap",
      payload: {
        envId: selectedEnvironment,
        configMap: configMap,
      },
    });
  };

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Form form={form} component={false}>
        <Table
          bordered
          components={{
            body: {
              cell: EditableCell,
            },
          }}
          rowKey="id"
          dataSource={data}
          columns={mergedColumns}
          rowClassName="editable-row"
          style={{ marginBottom: 16 }}
          pagination={false}
        />
        <Space style={{ marginBottom: 16 }}>
          <Button onClick={() => addNewRow()}>添加配置项</Button>
          <Button type="primary" onClick={() => handleConfigMapSubmit(data)}>
            提交
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
})(ConfigMapConfigPanel);
