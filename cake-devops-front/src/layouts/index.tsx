import React, { useEffect, useState } from "react";
import { ProLayout } from "@ant-design/pro-layout";
import { Dropdown } from "antd";
import { logout } from "@/services/user";
import {
  Link,
  Outlet,
  useAppData,
  useLocation,
  connect,
  Dispatch,
  history,
} from "umi";
import { LogoutOutlined } from "@ant-design/icons";
import defaultProps from "./_default";
import { API } from "typings";
interface LayoutProps {
  dispatch: Dispatch;
  isLogin: boolean;
  userData: API.UserInfo;
}

const Layout: React.FC<LayoutProps> = ({ dispatch, isLogin, userData }) => {
  const { clientRoutes } = useAppData();
  const location = useLocation();

  const getUserInfo = () => {
    dispatch({
      type: "user/getUserInfo",
    });
  };

  useEffect(() => {
    getUserInfo();
  }, []);

  return (
    <ProLayout
      layout="top"
      // route={clientRoutes[0]}
      // location={location}
      title="Cake"
      waterMarkProps={{
        content: [userData?.userName, userData?.userId],
      }}
      {...defaultProps}
      avatarProps={{
        src: "https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg",
        size: "small",
        title: userData?.userName,
        render: (props, dom) => {
          return (
            <Dropdown
              menu={{
                items: [
                  {
                    key: "logout",
                    icon: <LogoutOutlined />,
                    label: "退出登录",
                    onClick: async () => {
                      const res = await logout();
                    },
                  },
                ],
              }}
            >
              {dom}
            </Dropdown>
          );
        },
      }}
      menuItemRender={(menuItemProps, defaultDom) => {
        if (menuItemProps.isUrl || menuItemProps.children) {
          return defaultDom;
        }
        if (menuItemProps.path && location.pathname !== menuItemProps.path) {
          return (
            <Link to={menuItemProps.path} target={menuItemProps.target}>
              {defaultDom}
            </Link>
          );
        }
        return defaultDom;
      }}
      menu={{
        type: "group",
      }}
    >
      <Outlet />
    </ProLayout>
  );
};

export default connect(
  ({ user }: { user: { isLogin: boolean; userData: API.UserInfo } }) => {
    return {
      isLogin: user.isLogin,
      userData: user.userData,
    };
  }
)(Layout);
