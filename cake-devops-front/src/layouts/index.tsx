import React, { useCallback, useEffect, useState } from "react";
import { ProLayout, ProSettings } from "@ant-design/pro-layout";
import { Dropdown, Spin } from "antd"; // 引入 Spin 用于加载指示器
import { logout } from "@/services/user";
import { connect, Dispatch, history, Link, Outlet } from "umi";
import { LogoutOutlined } from "@ant-design/icons";
import defaultProps from "./_default";
import { API } from "typings";
import { MenuTreeDTO, UserRoleMenuDTO } from "@/models/user";
import Inbox from "./index-box";

interface LayoutProps {
  dispatch: Dispatch;
  isLogin: boolean;
  userData: API.UserInfo;
  menu: UserRoleMenuDTO;
}

interface MenuItem {
  path: string;
  name: string;
  icon?: React.ReactNode;
  children?: MenuItem[];
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
  const [menuData, setMenuData] = useState<MenuItem[]>([]);
  const [loading, setLoading] = useState(true); // 新增 loading 状态

  const getUserInfo = () => {
    dispatch({
      type: "user/getUserInfo",
    });
  };

  const queryUserMenu = async () => {
    dispatch({
      type: "user/queryMenu",
      callback: (content: UserRoleMenuDTO) => {
        console.log(content);
        const convertedMenuData = convertMenuTreeToProLayoutMenu(
          content.menuTree
        );
        setMenuData(convertedMenuData);
        setLoading(false); // 菜单数据加载完成后设置 loading 为 false
      },
    });
  };

  const getMenuData = useCallback(async () => menuData, [menuData]);

  const convertMenuTreeToProLayoutMenu = (
    menuTree: MenuTreeDTO[]
  ): MenuItem[] => {
    return menuTree.map((item) => ({
      path: item.path,
      name: item.name,
      icon: item.icon ? (
        <img src={item.icon} alt={`${item.name} icon`} />
      ) : undefined,
      children: item.children
        ? convertMenuTreeToProLayoutMenu(item.children)
        : undefined,
    }));
  };

  useEffect(() => {
    getUserInfo();
    queryUserMenu();
  }, []);

  if (loading) {
    // 如果还在加载中，显示加载指示器
    return (
      <div
        style={{
          height: "100vh",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Spin size="large" />
      </div>
    );
  }

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
          request: getMenuData,
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
                {defaultDom}
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
