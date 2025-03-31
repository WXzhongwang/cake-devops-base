// src/pages/not-found.tsx
import React from "react";
import { Result, Button } from "antd";
import { history } from "umi";

const NotFound: React.FC = () => {
  const goBackHome = () => {
    history.push("/");
  };

  return (
    <Result
      status="404"
      title="404"
      subTitle="对不起, 页面未找到."
      extra={
        <Button type="primary" onClick={goBackHome}>
          返回首页
        </Button>
      }
    />
  );
};

export default NotFound;
