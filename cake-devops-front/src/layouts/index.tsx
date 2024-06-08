import React, { useEffect, useState } from "react";
import {
  PageContainer,
  ProLayout,
  ProSettings,
  SettingDrawer,
} from "@ant-design/pro-layout";
import { ConfigProvider, Dropdown, Input } from "antd";
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
import {
  LogoutOutlined,
  GithubFilled,
  InfoCircleFilled,
  PlusCircleFilled,
  QuestionCircleFilled,
  SearchOutlined,
} from "@ant-design/icons";
import defaultProps from "./_default";
import { API } from "typings";
import { ProConfigProvider, ProCard } from "@ant-design/pro-components";
interface LayoutProps {
  dispatch: Dispatch;
  isLogin: boolean;
  userData: API.UserInfo;
}

const Layout: React.FC<LayoutProps> = ({ dispatch, isLogin, userData }) => {
  const [settings, setSetting] = useState<Partial<ProSettings> | undefined>({
    fixSiderbar: true,
    layout: "top",
    splitMenus: false,
    navTheme: "light",
    contentWidth: "Fluid",
    colorPrimary: "#1677FF",
    siderMenuType: "sub",
  });
  const [pathname, setPathname] = useState("/apps");

  const getUserInfo = () => {
    dispatch({
      type: "user/getUserInfo",
    });
  };

  useEffect(() => {
    getUserInfo();
  }, []);

  return (
    <div
      id="main-pro-layout"
      style={{
        height: "100vh",
        overflow: "auto",
      }}
    >
      <ProLayout
        {...settings}
        title="Cake"
        menu={{
          type: "group",
          collapsedShowGroupTitle: true,
        }}
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
        actionsRender={(props) => {
          if (props.isMobile) return [];
          return [
            <InfoCircleFilled key="InfoCircleFilled" />,
            <QuestionCircleFilled key="QuestionCircleFilled" />,
            <GithubFilled key="GithubFilled" />,
          ];
        }}
        menuFooterRender={(props) => {
          if (props?.collapsed) return undefined;
          return (
            <div
              style={{
                textAlign: "center",
                paddingBlockStart: 12,
              }}
            >
              <div>© 2024 Made with 钟望</div>
              <div>by Cake</div>
            </div>
          );
        }}
        menuItemRender={(menuItemProps, defaultDom) => {
          if (menuItemProps.isUrl || menuItemProps.children) {
            return defaultDom;
          }
          if (menuItemProps.path && location.pathname !== menuItemProps.path) {
            return (
              <Link
                to={menuItemProps.path}
                target={menuItemProps.target}
                onClick={() => {
                  setPathname(menuItemProps.path || "/apps");
                }}
              >
                {defaultDom}
              </Link>
            );
          }
          return defaultDom;
        }}
      >
        <Outlet />

        {/* <SettingDrawer
          pathname={pathname}
          enableDarkTheme
          getContainer={(e: any) => {
            if (typeof window === "undefined") return e;
            return document.getElementById("main-pro-layout");
          }}
          settings={settings}
          onSettingChange={(changeSetting) => {
            setSetting(changeSetting);
          }}
          disableUrlParams={false}
        /> */}
      </ProLayout>
    </div>
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
