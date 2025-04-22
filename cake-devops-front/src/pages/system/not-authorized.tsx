import React from "react";
import { Result, Button } from "antd";
import { history } from "umi";

const NotAuthorized: React.FC = () => {
  const goBackHome = () => {
    history.push("/");
  };

  return (
    <Result
      status="403"
      title="403"
      subTitle="对不起，您没有权限访问此页面。"
      extra={
        <Button type="primary" onClick={goBackHome}>
          返回首页
        </Button>
      }
    />
  );
};

export default NotAuthorized;