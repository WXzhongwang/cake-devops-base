import React, { useCallback, useEffect, useState } from "react";
import { MenuDataItem, ProLayout, ProSettings } from "@ant-design/pro-layout";
import { Dropdown, Spin } from "antd"; // 引入 Spin 用于加载指示器
import { logout } from "@/services/user";
import { connect, Dispatch, history, Link, Outlet } from "umi";
import { LogoutOutlined } from "@ant-design/icons";
import defaultProps from "./_default";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import Inbox from "./index-box";
import * as allIcons from "@ant-design/icons";

interface LayoutProps {
  dispatch: Dispatch;
  isLogin: boolean;
  userData: API.UserInfo;
  menu: UserRoleMenuDTO;
}

const Layout: React.FC<LayoutProps> = ({ dispatch, isLogin, userData }) => {
  const [settings, setSetting] = useState<Partial<ProSettings>>({
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

  const queryUserMenu = async () => {
    return new Promise<MenuDataItem[]>((resolve) => {
      dispatch({
        type: "user/queryMenu",
        callback: (content: UserRoleMenuDTO) => {
          const convertedMenuData = convertMenuTreeToProLayoutMenu(
            content.menuTree
          );
          console.log("converted:", convertedMenuData);
          resolve(convertedMenuData);
        },
      });
    });
  };

  const convertMenuTreeToProLayoutMenu = (
    menuTree: MenuTreeDTO[]
  ): MenuDataItem[] => {
    return menuTree.map((item) => {
      return {
        key: item.path,
        label: item.name,
        icon: getIcon(item.icon),
        children: item.children
          ? convertMenuTreeToProLayoutMenu(item.children)
          : [],
      };
    });
  };

  const getIcon = (icon: string) => {
    if (!icon) {
      return undefined;
    }
    if (icon.startsWith("http")) {
      return <img src={icon} alt={icon} />;
    }
    let fixIconName =
      icon.slice(0, 1).toLocaleUpperCase() + icon.slice(1) + icon;
    // @ts-ignore
    return React.createElement(allIcons[fixIconName] || allIcons[icon]);
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
          request: queryUserMenu,
        }}
        waterMarkProps={{
          content: [userData?.userName, userData?.userId],
        }}
        {...defaultProps}
        avatarProps={{
          src: "https://gw.alipayobjects.com/zos/antfincdn/efFD%24IOql2/weixintupian_20170331104822.jpg",
          size: "small",
          title: userData?.userName,
          render: (props, dom) => (
            <Dropdown
              menu={{
                items: [
                  {
                    key: "logout",
                    icon: <LogoutOutlined />,
                    label: "退出登录",
                    onClick: async () => {
                      const res = await logout();
                      if (res.success) {
                        history.push("/apps");
                      }
                    },
                  },
                ],
              }}
            >
              {dom}
            </Dropdown>
          ),
        }}
        actionsRender={(props) =>
          props.isMobile ? [] : [<Inbox key="Inbox" />]
        }
        menuFooterRender={(props) =>
          props?.collapsed ? undefined : (
            <div style={{ textAlign: "center", paddingBlockStart: 12 }}>
              <div>© 2024 Made with 钟望</div>
              <div>by Cake</div>
            </div>
          )
        }
        menuItemRender={(menuItemProps, defaultDom) => {
          if (menuItemProps.isUrl || menuItemProps.children) {
            return defaultDom;
          }
          if (menuItemProps.path && location.pathname !== menuItemProps.path) {
            return (
              <Link
                to={menuItemProps.path}
                target={menuItemProps.target}
                onClick={() => setPathname(menuItemProps.path || "/apps")}
              >
                <div className="ant-pro-base-menu-horizontal-item-title">
                  {menuItemProps.icon}
                  {menuItemProps.name}
                </div>
              </Link>
            );
          }
          return defaultDom;
        }}
      >
        <Outlet />
      </ProLayout>
    </div>
  );
};

export default connect(
  ({
    user,
  }: {
    user: {
      isLogin: boolean;
      userData: API.UserInfo;
      menu: UserRoleMenuDTO;
    };
  }) => ({
    isLogin: user.isLogin,
    userData: user.userData,
    menu: user.menu,
  })
)(Layout);
