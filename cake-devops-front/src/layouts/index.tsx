import React, { useEffect, useState } from "react";
import { MenuDataItem, ProLayout, ProSettings } from "@ant-design/pro-layout";
import { Dropdown } from "antd"; // 引入 Spin 用于加载指示器
import { logout } from "@/services/user";
import { connect, Dispatch, history, Link, Outlet } from "umi";
import * as allIcons from "@ant-design/icons";
import { LogoutOutlined } from "@ant-design/icons";
import defaultProps from "./_default";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import Inbox from "./index-box";

interface LayoutProps {
  dispatch: Dispatch;
}

const Layout: React.FC<LayoutProps> = ({ dispatch }) => {
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
  const [userData, setUserData] = useState<API.UserInfo>();

  const getUserInfo = () => {
    dispatch({
      type: "user/getUserInfo",
      callback: (res: API.UserInfo) => {
        setUserData(res);
      },
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
          console.log("converted:", convertedMenuData); // 确保这里打印出正确的菜单数据
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
        name: item.name,
        icon: getIcon(item.icon),
        path: item.path, // 确保有 path 字段
        children: item.children
          ? convertMenuTreeToProLayoutMenu(item.children)
          : undefined,
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
    let fixIconName = icon.slice(0, 1).toLocaleUpperCase() + icon.slice(1);
    // @ts-ignore
    // return React.createElement(allIcons[fixIconName] || allIcons[icon]);
    // @ts-ignore
    const IconComponent = allIcons[fixIconName];
    return IconComponent ? <IconComponent /> : undefined;
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
      {userData && (
        <ProLayout
          {...settings}
          title="Cake"
          menu={{
            type: "group",
            collapsedShowGroupTitle: true,
            request: queryUserMenu,
          }}
          waterMarkProps={{
            content: [userData.userName, userData.userId],
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
          actionsRender={(props) => {
            if (props.isMobile) return [];
            return [
              <div
                key="inbox"
                style={{
                  display: "flex",
                  alignItems: "center",
                  marginRight: 10,
                  padding: "0 8px",
                  height: "100%",
                  cursor: "pointer",
                }}
              >
                <Inbox />
              </div>,
            ];
          }}
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
            if (
              menuItemProps.path &&
              location.pathname !== menuItemProps.path
            ) {
              return (
                <Link
                  to={menuItemProps.path}
                  target={menuItemProps.target}
                  onClick={() => setPathname(menuItemProps.path || "/apps")}
                >
                  <div className="ant-pro-base-menu-horizontal-item-title">
                    {menuItemProps.icon} &nbsp;&nbsp;
                    {menuItemProps.name}
                  </div>
                </Link>
              );
            }
            return (
              <div className="ant-pro-base-menu-horizontal-item-title">
                {menuItemProps.icon} &nbsp;&nbsp;
                {menuItemProps.name}
              </div>
            );
          }}
        >
          <Outlet />
        </ProLayout>
      )}
    </div>
  );
};

export default connect()(Layout);
