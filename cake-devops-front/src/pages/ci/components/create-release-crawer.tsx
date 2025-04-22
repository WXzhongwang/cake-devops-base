// src/pages/app/components/CreateReleaseDrawer.tsx
import React from "react";
import { Drawer, Form, Input, Select, Button, DatePicker, message } from "antd";
import moment from "moment";
import { nanoid } from "nanoid";
import { BranchInfo } from "@/models/app";
import { FormInstance } from "antd/lib/form";

interface CreateReleaseDrawerProps {
  visible: boolean;
  onClose: () => void;
  appId: string | undefined;
  selectedEnvironment: string | null;
  listAppBranch: (search: string) => void;
  branches: BranchInfo[];
  handleAddRelease: (values: any) => void;
}

const CreateReleaseDrawer: React.FC<CreateReleaseDrawerProps> = ({
  visible,
  onClose,
  appId,
  selectedEnvironment,
  listAppBranch,
  branches,
  handleAddRelease,
}) => {
  const [form] = Form.useForm();

  return (
    <Drawer title="添加发布单" width={600} onClose={onClose} open={visible}>
      <Form
        layout="vertical"
        form={form}
        initialValues={{
          releaseDate: "",
          releaseBranch: "",
          docAddress: "",
          appId: appId,
          envId: selectedEnvironment,
        }}
        onFinish={(values) => handleAddRelease(values)}
      >
        <Form.Item label="预计发布日期" name="releaseDate">
          <DatePicker showTime defaultValue={moment()} />
        </Form.Item>
        <Form.Item
          label="发布分支"
          name="releaseBranch"
          rules={[{ required: true, message: "请输入发布分支" }]}
        >
          <Select placeholder="请选择分支" onSearch={listAppBranch}>
            {branches?.map((branch) => (
              <Select.Option key={branch.name} value={branch.name}>
                {branch.name}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          label="发布版本"
          name="releaseVersion"
          rules={[{ required: true, message: "请输入发布版本" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="文档地址"
          name="docAddress"
          rules={[{ required: true, message: "请输入文档地址" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="备注"
          name="comment"
          rules={[{ required: true, message: "请输入备注" }]}
        >
          <Input.TextArea />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default CreateReleaseDrawer;
