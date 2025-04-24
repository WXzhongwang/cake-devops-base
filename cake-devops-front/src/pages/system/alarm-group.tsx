import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Button, Card, Form, Input, Space, Table, Drawer } from "antd";
import { connect, Dispatch } from "umi";
import dayjs from "dayjs";
import CreateAlarmGroupForm from "./components/create-alarm-group";
import { AlarmGroupDTO } from "@/models/alarm-group";
import { WebhookConfig } from "@/models/webhook";
import { AppAccountDTO, AppAccountPage } from "@/models/user";
import { API } from "typings";

interface AlarmGroupListProps {
  dispatch: Dispatch;
}

const AlarmGroupList: React.FC<AlarmGroupListProps> = ({ dispatch }) => {
  const [pagination, setPagination] = useState({ pageNo: 1, pageSize: 10 });
  const [filters, setFilters] = useState({
    groupName: "",
  }); // 初始化筛选条件为空对象
  const [drawerVisible, setDrawerVisible] = useState(false);
  const [editingAlarmGroup, setEditingAlarmGroup] = useState<
    AlarmGroupDTO | undefined
  >(undefined);
  const [form] = Form.useForm();
  const [userList, setUserList] = useState<AppAccountDTO[]>([]);
  const [webhooks, setWebhooks] = useState<WebhookConfig[]>([]);
  const [alarmGroupPage, setAlarmGroupPage] =
    useState<API.Page<AlarmGroupDTO>>();

  useEffect(() => {
    getAlarmGroups();
  }, [pagination, filters]);

  useEffect(() => {
    dispatch({
      type: "webhook/queryWebHooks",
      payload: {
        pageNo: 1,
        pageSize: 10,
      },
      callback: (res: API.Page<WebhookConfig>) => {
        setWebhooks(res.items);
        fetchUserList();
      },
    });
  }, []);

  const fetchUserList = () => {
    dispatch({
      type: "user/queryMembers",
      payload: {},
      callback: (res: AppAccountPage) => {
        setUserList(res.items);
      },
    });
  };

  const getAlarmGroups = () => {
    dispatch({
      type: "alarmGroup/fetchAlarmGroups",
      payload: { ...pagination, ...filters },
      callback: (res: API.Page<AlarmGroupDTO>) => {
        setAlarmGroupPage(res);
      },
    });
  };

  const handleEdit = (alarmGroup: AlarmGroupDTO) => {
    setEditingAlarmGroup(alarmGroup);
    setDrawerVisible(true);
  };

  const handleDelete = (alarmGroupId: number) => {
    dispatch({
      type: "alarmGroup/delete",
      payload: { alarmGroupId: alarmGroupId },
      callback: () => {
        getAlarmGroups();
      },
    });
  };

  const handlePaginationChange = (page: number, pageSize?: number) => {
    setPagination({ pageNo: page, pageSize: pageSize || 10 });
  };

  const handleAdd = () => {
    setDrawerVisible(true);
  };

  const handleCloseDrawer = () => {
    setDrawerVisible(false);
    form.resetFields();
    setEditingAlarmGroup(undefined);
  };

  const handleSave = async (values: any) => {
    if (editingAlarmGroup) {
      dispatch({
        type: "alarmGroup/update",
        payload: {
          ...values,
          alarmGroupId: editingAlarmGroup.id,
        },
        callback: () => {
          // 新增成功后，更新选中的主机 ID，重新加载环境变量数据
          getAlarmGroups();
        },
      });
    } else {
      dispatch({
        type: "alarmGroup/create",
        payload: values,
        callback: () => {
          // 新增成功后，更新选中的主机 ID，重新加载环境变量数据
          getAlarmGroups();
        },
      });
    }
    setDrawerVisible(false);
    form.resetFields();
  };

  const columns = [
    {
      title: "告警组名称",
      dataIndex: "groupName",
      key: "groupName",
    },
    {
      title: "描述",
      dataIndex: "groupDescription",
      key: "groupDescription",
    },
    {
      title: "通知人",
      // dataIndex: "users",
      // key: "users",
      render: (record: AlarmGroupDTO) => {
        return <>{record.users?.map((user) => user.username).join(", ")}</>;
      },
    },
    {
      title: "WebHook",
      // dataIndex: "notifies",
      // key: "notifies",
      render: (record: AlarmGroupDTO) => {
        return (
          <>{record.notifies?.map((notify) => notify.webHookName).join(", ")}</>
        );
      },
    },
    {
      title: "创建时间",
      key: "gmtCreate",
      render: (text: string, record: AlarmGroupDTO) => (
        <>{dayjs(record?.gmtCreate).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "修改时间",
      key: "gmtModified",
      render: (text: string, record: AlarmGroupDTO) => (
        <>{dayjs(record?.gmtModified).format("YYYY-MM-DD HH:mm:ss")}</>
      ),
    },
    {
      title: "操作",
      key: "action",
      render: (text: any, record: AlarmGroupDTO) => (
        <Space size="middle">
          <a onClick={() => handleEdit(record)}>编辑</a>
          <a onClick={() => handleDelete(record.id)}>删除</a>
        </Space>
      ),
    },
  ];

  return (
    <PageContainer title="告警组列表">
      <Card>
        <Space size="middle" direction="vertical" style={{ width: "100%" }}>
          <Form
            layout="inline"
            onFinish={(values) => {
              setFilters(values);
            }}
          >
            <Form.Item name="groupName" label="名称">
              <Input placeholder="请输入名称" />
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
                style={{ marginRight: 8 }}
              >
                查询
              </Button>
              <Button
                onClick={() => {
                  setFilters({
                    groupName: "",
                  });
                }}
              >
                重置
              </Button>
            </Form.Item>
          </Form>
          <Button type="primary" onClick={handleAdd}>
            新增告警组
          </Button>
          <Table
            columns={columns}
            dataSource={alarmGroupPage?.items}
            rowKey="id"
            pagination={{
              total: alarmGroupPage?.total,
              current: pagination.pageNo,
              pageSize: pagination.pageSize,
              onChange: handlePaginationChange,
            }}
          />
        </Space>
      </Card>

      <Drawer
        title={editingAlarmGroup ? "编辑告警组" : "新增告警组"}
        width={400}
        open={drawerVisible}
        onClose={handleCloseDrawer}
        destroyOnClose={true}
      >
        <CreateAlarmGroupForm
          initialValues={editingAlarmGroup}
          onSave={handleSave}
          onUpdate={handleSave}
          onCancel={handleCloseDrawer}
          webhooks={webhooks}
          members={userList}
        />
      </Drawer>
    </PageContainer>
  );
};

export default connect()(AlarmGroupList);
