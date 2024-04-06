// AlarmConfigurationForm.tsx

import React from "react";
import { Form, Button, Tabs, Input, Tooltip, Space } from "antd";
import { QuestionCircleOutlined } from "@ant-design/icons";

const { TabPane } = Tabs;

interface Props {
  onSubmit: (values: any) => void;
  onCancel: () => void;
}

const AlarmConfigurationForm: React.FC<Props> = ({ onSubmit, onCancel }) => {
  const handleFormSubmit = (values: any) => {
    onSubmit(values);
  };

  return (
    <Form
      onFinish={handleFormSubmit}
      labelCol={{ span: 8 }}
      wrapperCol={{ span: 16 }}
      labelAlign="left"
    >
      <Tabs defaultActiveKey="cpu">
        <TabPane tab="CPU报警配置" key="cpu">
          <Form.Item
            label={
              <span>
                报警阈值百分比&nbsp;
                <Tooltip title="配置CPU使用率报警阈值">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="cpuThreshold"
            extra="配置CPU使用率报警阈值"
          >
            <Input addonAfter="%" />
          </Form.Item>
          <Form.Item
            label={
              <span>
                通知阈值&nbsp;
                <Tooltip title="CPU使用率连续达到报警阈值N次时则会通知报警联系组">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="cpuNotificationThreshold"
            extra="CPU使用率连续达到报警阈值N次时则会通知报警联系组"
          >
            <Input addonAfter="次" />
          </Form.Item>
          <Form.Item
            label={
              <span>
                沉默时间&nbsp;
                <Tooltip title="触发通知后的N分钟内, 再次达到报警阈值则不会触发通知">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="cpuSilenceTime"
            extra="触发通知后的N分钟内, 再次达到报警阈值则不会触发通知"
          >
            <Input addonAfter="分钟" />
          </Form.Item>
        </TabPane>
        <TabPane tab="内存使用率配置" key="memory">
          <Form.Item
            label={
              <span>
                报警阈值百分比&nbsp;
                <Tooltip title="设置内存使用率达到多少时触发报警">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="memoryThreshold"
            extra="设置内存使用率达到多少时触发报警"
          >
            <Input addonAfter="%" />
          </Form.Item>
          <Form.Item
            label={
              <span>
                通知阈值&nbsp;
                <Tooltip title="内存使用率连续达到报警阈值N次时则会通知报警联系组">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="memoryNotificationThreshold"
            extra="内存使用率连续达到报警阈值N次时则会通知报警联系组"
          >
            <Input addonAfter="次" />
          </Form.Item>
          <Form.Item
            label={
              <span>
                沉默时间&nbsp;
                <Tooltip title="触发通知后的N分钟内, 再次达到报警阈值则不会触发通知">
                  <QuestionCircleOutlined />
                </Tooltip>
              </span>
            }
            name="memorySilenceTime"
            extra="触发通知后的N分钟内, 再次达到报警阈值则不会触发通知"
          >
            <Input addonAfter="分钟" />
          </Form.Item>
        </TabPane>
        <TabPane tab="报警联系组配置" key="alertGroup">
          <Form.Item name="alertGroup" label="报警联系组">
            <Input />
          </Form.Item>
        </TabPane>
      </Tabs>
      <Form.Item>
        <Space>
          <Button type="primary" htmlType="submit">
            保存
          </Button>

          <Button onClick={onCancel}>取消</Button>
        </Space>
      </Form.Item>
    </Form>
  );
};

export default AlarmConfigurationForm;
