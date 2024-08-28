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
import { ServiceItem } from "@/models/app";
import { Dispatch } from "@/.umi/exports";

interface ServicePanelProps {
  initialServices: WrapServiceItem[];
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

interface WrapServiceItem extends ServiceItem {
  id: string;
  editable: boolean;
}

interface EditableCellProps extends React.HTMLAttributes<HTMLElement> {
  editing: boolean;
  dataIndex: string;
  title: any;
  inputType: "text";
  record: WrapServiceItem;
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
            ...(dataIndex === "label"
              ? [
                  {
                    pattern: /^[a-zA-Z_][a-zA-Z0-9_.-]*$/,
                    message: `服务名称必须以字母或下划线开头，并且只能包含字母、数字、下划线、短横线或点`,
                  },
                ]
              : []),
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

const ServicePanel: React.FC<ServicePanelProps> = ({
  initialServices,
  selectedEnvironment,
  dispatch,
}) => {
  const [form] = Form.useForm();
  const [data, setData] = useState<WrapServiceItem[]>(initialServices);
  const [editingKey, setEditingKey] = useState<String | undefined>("");
  const isEditing = (record: WrapServiceItem) => record.id === editingKey;

  const edit = (record: Partial<WrapServiceItem> & { id: React.Key }) => {
    form.setFieldsValue({ ...record });
    setEditingKey(record.id);
  };
  const cancel = () => {
    setEditingKey("");
  };

  const addNewRow = () => {
    const newRow: WrapServiceItem = {
      id: nanoid(),
      serviceName: "",
      servicePort: 80,
      containerPort: 8300,
      serviceProtocol: "TCP",
      serviceType: "CLUSTER-IP",
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
      const row = (await form.validateFields()) as WrapServiceItem;

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
    /**
     * 
     *   serviceName: string;
  servicePort: number;
  containerPort: number;
  serviceProtocol: string;
  serviceType: string;
  nodePort?: number;
     * 
    */
    {
      title: "ServiceName",
      dataIndex: "serviceName",
      editable: true,
    },
    {
      title: "servicePort",
      dataIndex: "servicePort",
      editable: true,
    },
    {
      title: "containerPort",
      dataIndex: "containerPort",
      editable: true,
    },
    {
      title: "serviceProtocol",
      dataIndex: "serviceProtocol",
      editable: true,
    },
    {
      title: "serviceType",
      dataIndex: "serviceType",
      editable: true,
    },
    {
      title: "nodePort",
      dataIndex: "serviceProtocol",
      editable: true,
    },
    {
      title: "操作",
      dataIndex: "operation",
      width: "200px",
      render: (_: any, record: WrapServiceItem, index: number) => {
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
      onCell: (record: WrapServiceItem) => ({
        record,
        inputType: "text",
        dataIndex: col.dataIndex,
        title: col.title,
        editing: isEditing(record),
      }),
    };
  });

  const handleConfigMapSubmit = (data: WrapServiceItem[]) => {
    // 提交配置项数据
    if (
      data.some(
        (item) =>
          !item.serviceName ||
          !item.servicePort ||
          !item.serviceProtocol ||
          !item.serviceType
      )
    ) {
      message.warning("配置信息不能为空");
      return;
    }
    // TODO: 更新
    message.success("更新成功");
    window.location.reload();
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
        {/* <Space style={{ marginBottom: 16 }}>
          <Button onClick={() => addNewRow()}>添加配置项</Button>
          <Button type="primary" onClick={() => handleConfigMapSubmit(data)}>
            提交
          </Button>
        </Space> */}
      </Form>
    </Space>
  );
};

export default connect(({ user }: { user: { userData: API.UserInfo } }) => {
  return {
    userData: user.userData,
  };
})(ServicePanel);
