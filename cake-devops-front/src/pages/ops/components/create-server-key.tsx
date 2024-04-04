import React, {useState} from "react";
import {Button, Drawer, Form, Input, Radio, Select, Upload,} from "antd";
import {HostModel} from "@/models/host";
import {InboxOutlined} from "@ant-design/icons";

const {Option} = Select;

interface ServerAccountDrawerProps {
    visible: boolean;
    servers: HostModel[];
    onClose: () => void;
    onSave: (values: any, fileBase64: any) => void;
}

const ServerAccountDrawer: React.FC<ServerAccountDrawerProps> = ({
                                                                     visible,
                                                                     servers,
                                                                     onClose,
                                                                     onSave,
                                                                 }) => {
    const [form] = Form.useForm();
    const [fileBase64, setFileBase64] = useState("");
    const getBase64FromFile = (file: File): Promise<string> => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();

            reader.onload = () => {
                const base64String = reader.result as string;

                // 去除DataURL中的额外信息
                const pureBase64 = base64String.split(',')[1];

                resolve(pureBase64);
            };

            reader.onerror = (error) => {
                reject(error);
            };

            // 开始读取文件
            reader.readAsDataURL(file);
        });
    };

    const handleSave = async () => {
        try {
            const values = await form.validateFields();
            console.log("Form values:", values);
            // 获取文件的 Base64 编码
            if (!values.file) {
                console.error("请至少选择一个文件上传");
                return;
            }
            console.log("File:", values.file);
            // const base64String = await getBase64FromFile(values.file.originFileObj);
            onSave(values, fileBase64);
            onClose();
        } catch (error) {
            console.error("Validation failed:", error);
        }
    };

    const handleFileChange = async (info: any) => {
        const {file} = info;

        // 检查文件是否为空
        if (!file) {
            console.error("请至少选择一个文件上传");
            return;
        }

        if (!file) return;
        try {
            const base64String = await getBase64FromFile(file.originFileObj);
            // @ts-ignore
            setFileBase64(base64String);
        } catch (error) {
            console.error("Failed to convert file to Base64:", error);
        }

    };

    return (
        <Drawer
            title="新增秘钥"
            width={600}
            onClose={onClose}
            open={visible}
            destroyOnClose
        >
            <Form form={form} onFinish={handleSave} layout="vertical">
                <Form.Item
                    label="显示名称"
                    name="displayName"
                    rules={[{required: true, message: "请输入显示名称"}]}
                >
                    <Input placeholder="请输入显示名称"/>
                </Form.Item>
                <Form.Item
                    label="账户类型"
                    name="accountType"
                    rules={[{required: true, message: "请选择账户类型"}]}
                >
                    <Radio.Group>
                        <Radio value="0">普通账户</Radio>
                        <Radio value="1">管理员</Radio>
                    </Radio.Group>
                </Form.Item>
                <Form.Item
                    label="协议"
                    name="protocol"
                    initialValue="SSH"
                    rules={[{required: true, message: "请选择协议"}]}
                >
                    <Input disabled/>
                </Form.Item>
                <Form.Item
                    label="活跃状态"
                    name="active"
                    rules={[{required: true, message: "请选择活跃状态"}]}
                >
                    <Select placeholder="请选择活跃状态">
                        <Option value={true}>活跃</Option>
                        <Option value={false}>不活跃</Option>
                    </Select>
                </Form.Item>
                <Form.Item
                    label="上传文件"
                    name="file"
                    valuePropName="file"
                    getValueFromEvent={(file: any) => file}
                    extra="支持上传 .txt, .json, .jpg,  文件格式"
                >
                    <Upload.Dragger multiple={false} onChange={handleFileChange}>
                        <p className="ant-upload-drag-icon">
                            <InboxOutlined/>
                        </p>
                        <p className="ant-upload-text">点击或拖拽文件到此处上传</p>
                        <p className="ant-upload-hint">仅支持单个文件上传</p>
                    </Upload.Dragger>
                </Form.Item>
                <Form.Item
                    label="凭据内容"
                    name="credential"
                    rules={[{required: true, message: "请输入凭据内容"}]}
                >
                    <Input.TextArea placeholder="请输入凭据内容"/>
                </Form.Item>
                <Form.Item
                    label="密码短语"
                    name="passphrase"
                    rules={[{required: true, message: "请输入密码短语"}]}
                >
                    <Input.Password placeholder="请输入密码短语"/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        保存
                    </Button>
                </Form.Item>
            </Form>
        </Drawer>
    );
};

export default ServerAccountDrawer;
