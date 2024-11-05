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
import { ServiceItem } from "@/models/app";

interface ServicePanelProps {
  initialServices: WrapServiceItem[];
  selectedEnvironment: string | undefined | null;
  dispatch: Dispatch;
}

interface WrapServiceItem extends ServiceItem {
  id: string;
  editable: boolean;
}

const ServicePanel: React.FC<ServicePanelProps> = ({
  initialServices,
  selectedEnvironment,
  dispatch,
}) => {
  const [form] = Form.useForm();
  const [data, setData] = useState<WrapServiceItem[]>(initialServices);
  const [editingItem, setEditingItem] = useState<WrapServiceItem | null>(null);
  const [modelOpen, setModelOpen] = useState<boolean>(false);

  const handleEdit = (item: WrapServiceItem) => {
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
      const newData = [...data];
      const index = newData.findIndex((item) => item.id === editingItem?.id);
      if (index !== -1) {
        newData.splice(index, 1, { ...editingItem, ...row });
      } else {
        newData.push({ ...row, id: nanoid(), editable: false });
      }
      setData(newData);
      setEditingItem(null);
      message.success("保存成功");

      // 调用接口保存数据
      dispatch({
        type: "app/saveService",
        payload: { ...row, environment: selectedEnvironment },
      });
    } catch (errInfo) {
      console.log("Validate Failed:", errInfo);
    }
  };

  const handleAddNew = () => {
    const newRow: WrapServiceItem = {
      id: nanoid(),
      serviceName: "",
      servicePort: 80,
      containerPort: 8300,
      serviceProtocol: "TCP",
      serviceType: "ClusterIP",
      editable: true,
    };
    // setData([...data, newRow]);
    // handleEdit(newRow); // 直接编辑新行
    form.setFieldsValue({ ...newRow });
    setModelOpen(!modelOpen);
  };

  const handleDelete = (id: string) => {
    const newData = data.filter((item) => item.id !== id);
    setData(newData);
    message.success("删除成功");

    // 调用接口删除数据
    dispatch({
      type: "app/deleteService",
      payload: { id, environment: selectedEnvironment },
    });
  };

  const columns = [
    { title: "服务名称", dataIndex: "serviceName" },
    { title: "服务端口", dataIndex: "servicePort" },
    { title: "容器端口", dataIndex: "containerPort" },
    { title: "服务协议", dataIndex: "serviceProtocol" },
    { title: "服务类型", dataIndex: "serviceType" },
    {
      title: "操作",
      dataIndex: "operation",
      render: (_: any, record: WrapServiceItem) => (
        <Space size={"middle"}>
          <Typography.Link onClick={() => handleEdit(record)}>
            编辑
          </Typography.Link>
          <Popconfirm
            title="确认删除吗?"
            onConfirm={() => handleDelete(record.id)}
          >
            <Typography.Link>删除</Typography.Link>
          </Popconfirm>
        </Space>
      ),
    },
  ];

  return (
    <Space style={{ width: "100%" }} direction="vertical">
      <Button
        type="primary"
        onClick={handleAddNew}
        style={{ marginBottom: 16 }}
      >
        新增Service
      </Button>

      <Table
        bordered
        rowKey="id"
        dataSource={data}
        columns={columns}
        pagination={false}
      />

      <Modal
        title={editingItem ? "编辑服务" : "新增服务"}
        open={modelOpen}
        onOk={handleSave}
        onCancel={handleCancel}
      >
        <Form form={form} layout="vertical">
          <Form.Item
            name="serviceName"
            label="服务名称"
            rules={[{ required: true, message: "请输入服务名称!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="servicePort"
            label="服务端口"
            rules={[{ required: true, message: "请输入服务端口!" }]}
          >
            <Input type="number" defaultValue={80} />
          </Form.Item>
          <Form.Item
            name="containerPort"
            label="容器端口"
            rules={[{ required: true, message: "请输入容器端口!" }]}
          >
            <Input type="number" defaultValue={8300} />
          </Form.Item>
          <Form.Item
            name="serviceProtocol"
            label="服务协议"
            rules={[{ required: true, message: "请输入服务协议!" }]}
          >
            <Select>
              <Select.Option value="TCP">TCP</Select.Option>
              <Select.Option value="UDP">UDP</Select.Option>
            </Select>
          </Form.Item>
          <Form.Item
            name="serviceType"
            label="服务类型"
            rules={[{ required: true, message: "请输入服务类型!" }]}
          >
            <Select>
              <Select.Option value="ClusterIP">ClusterIP</Select.Option>
              <Select.Option value="NodePort">NodePort</Select.Option>
              <Select.Option value="LoadBalancer">LoadBalancer</Select.Option>
            </Select>
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
})(ServicePanel);
