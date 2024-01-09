import React, { useEffect, useState } from "react";
import { PageContainer } from "@ant-design/pro-components";
import { Table, Space, Input, Select, Button, Form, Card, Tag } from "antd";
import { connect, Dispatch, history } from "umi";

export default function Page() {
  const addRelease = () => {};
  return (
    <PageContainer title="应用中心">
      <Space size="middle" direction="vertical" style={{ width: "100%" }}>
        <Card title="发布流水线"></Card>
        <Card
          title="发布历史"
          extra={
            <div>
              <Button onClick={addRelease}>添加发布单</Button>
            </div>
          }
        ></Card>
      </Space>
    </PageContainer>
  );
}
