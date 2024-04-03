import React, {useEffect, useState} from "react";
import {PageContainer} from "@ant-design/pro-components";
import {Button, Card, Form, Input, Select, Space, Table, Tag} from "antd";
import {connect, Dispatch, history} from "umi";
import {HostModel, ServerKey} from "@/models/host";
import ServerAccountDrawer from "./components/create-server-key";

const {Option} = Select;

interface ServerAccountListProps {
    dispatch: Dispatch;
    hosts: HostModel[];
    serverKeys: ServerKey[];
    serverKeyTotal: number;
}

const ServerAccountList: React.FC<ServerAccountListProps> = ({
                                                                 dispatch,
                                                                 serverKeys,
                                                                 serverKeyTotal,
                                                                 hosts,
                                                             }) => {
    const [pagination, setPagination] = useState({pageNo: 1, pageSize: 10});
    const [filters, setFilters] = useState({
        displayName: "",
        active: "1",
    });
    const [drawerVisible, setDrawerVisible] = useState(false);
    // const [servers, setServers] = useState<HostModel[]>([]);
    const [form] = Form.useForm();

    useEffect(() => {
        // 获取服务器列表
        dispatch({type: "host/fetchHosts"});
        //    setServers(hosts);
    }, []);

    const handleAddKey = () => {
        setDrawerVisible(true);
    };

    const handleCloseDrawer = () => {
        setDrawerVisible(false);
        form.resetFields();
    };

    const handleSaveKey = (values: ServerKey, fileBase64: string) => {
        console.log("Form values:", values);
        const content = {
            command: values,
            fileBase64: fileBase64
        }
        dispatch({type: "host/createServerKey", payload: content});
    };

    useEffect(() => {
        getServerKeys();
    }, [pagination, filters]);

    const getServerKeys = () => {
        dispatch({
            type: "host/queryServerKeys", // 请根据实际的 model 和 effect 路径调整
            payload: {...pagination, ...filters},
        });
    };

    const columns = [
        {
            title: "显示名称",
            dataIndex: "displayName",
            key: "displayName",
        },
        {
            title: "账户类型",
            dataIndex: "accountType",
            key: "accountType",
            render: (text: any, record: ServerKey) => (
                <Tag color={record.accountType === 1 ? "red" : "blue"}>
                    {record.accountType === 1 ? "管理员" : "普通账户"}
                </Tag>
            ),
        },
        {
            title: "协议",
            dataIndex: "protocol",
            key: "protocol",
        },
        {
            title: "活跃状态",
            dataIndex: "active",
            key: "active",
            render: (text: any, record: ServerKey) => (
                <Tag color={record.active ? "green" : "gray"}>
                    {record.active ? "活跃" : "不活跃"}
                </Tag>
            ),
        },
        {
            title: "操作",
            key: "action",
            render: (text: any, record: ServerKey) => (
                <Space size="middle">
                    <a onClick={() => handleView(record)}>查看</a>
                </Space>
            ),
        },
    ];

    const handlePaginationChange = (page: number, pageSize?: number) => {
        setPagination({pageNo: page, pageSize: pageSize || 10});
    };

    const handleView = (record: ServerKey) => {
        // 处理查看操作
        console.log("查看主机秘钥详情", record);
        // 示例：跳转到详情页，使用 history.push
        history.push(`/host/account/info/${record.id}`);
    };

    console.log("hosts", hosts);

    return (
        <PageContainer title="主机秘钥管理">
            <Card>
                <Space size="middle" direction="vertical" style={{width: "100%"}}>
                    <Form
                        layout="inline"
                        onFinish={(values) => {
                            console.log(values);
                            setFilters(values);
                        }}
                    >
                        <Form.Item name="displayName" label="用户名">
                            <Input placeholder="请输入用户名"/>
                        </Form.Item>
                        <Form.Item name="active" label="活跃状态">
                            <Select placeholder="请选择活跃状态" allowClear>
                                <Option value={true}>活跃</Option>
                                <Option value={false}>不活跃</Option>
                            </Select>
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit">
                                查询
                            </Button>
                            <Button
                                onClick={() => {
                                    setFilters({
                                        displayName: "",
                                        active: "1",
                                    });
                                }}
                            >
                                重置
                            </Button>
                        </Form.Item>
                    </Form>

                    <Button type="primary" onClick={handleAddKey}>
                        新增秘钥
                    </Button>

                    <Table
                        columns={columns}
                        dataSource={serverKeys}
                        rowKey={"id"}
                        pagination={{
                            total: serverKeyTotal, // 请替换为实际的总数
                            current: pagination.pageNo,
                            pageSize: pagination.pageSize,
                            onChange: handlePaginationChange,
                        }}
                    />
                </Space>
            </Card>

            <ServerAccountDrawer
                visible={drawerVisible}
                servers={hosts}
                onClose={handleCloseDrawer}
                onSave={handleSaveKey}
            />
        </PageContainer>
    );
};

export default connect(
    ({
         host,
     }: {
        host: {
            serverKeys: ServerKey[];
            serverKeyTotal: number;
            hosts: HostModel[];
        };
    }) => {
        return {
            hosts: host.hosts,
            serverKeys: host.serverKeys,
            serverKeyTotal: host.serverKeyTotal,
        };
    }
)(ServerAccountList);
