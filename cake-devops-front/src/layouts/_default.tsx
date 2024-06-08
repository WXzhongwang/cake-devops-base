import {
  ChromeFilled,
  CrownFilled,
  SmileFilled,
  ApiFilled,
  TabletFilled,
} from "@ant-design/icons";

export default {
  // 布局路由配置
  route: {
    path: "/",
    redirect: "/apps",
    routes: [
      {
        path: "/apps",
        icon: <SmileFilled />,
        component: "app/app-list",
        name: "应用中心",
      },
      {
        path: "/ops",
        name: "运维中心",
        icon: <CrownFilled />,
        routes: [
          {
            path: "/ops/cluster",
            name: "集群管理",
            icon: <TabletFilled />,
            component: "ops/cluster-list",
          },
          {
            path: "/ops/host",
            name: "主机管理",
            icon: <ApiFilled />,
            component: "ops/host-list",
          },
          {
            path: "/ops/server-key",
            name: "主机秘钥管理",
            icon: <ApiFilled />,
            component: "ops/server-key",
          },
          {
            path: "/ops/server-proxy",
            name: "主机代理管理",
            icon: <ApiFilled />,
            component: "ops/server-proxy",
          },
          {
            path: "/ops/server-env",
            name: "主机环境变量",
            icon: <ApiFilled />,
            component: "ops/server-env",
          },
          {
            path: "/ops/server-monitor",
            name: "主机监控",
            icon: <ApiFilled />,
            component: "ops/server-monitor",
          },
        ],
      },
      {
        path: "/system",
        name: "系统设置",
        icon: <CrownFilled />,
        routes: [
          {
            path: "/system/webhook-config",
            name: "Webhook",
            icon: <TabletFilled />,
            component: "system/webhook-config",
          },
          {
            path: "/system/alarm-group",
            name: "告警组",
            icon: <TabletFilled />,
            component: "system/alarm-group",
          },
          {
            path: "/system/script-template",
            name: "脚本管理",
            icon: <TabletFilled />,
            component: "system/script-template",
          },
          {
            path: "/system/system-log",
            name: "系统日志",
            icon: <TabletFilled />,
            component: "system/system-log",
          },
        ],
      },
    ],
  },
  location: {
    pathname: "/",
  },
  appList: [
    {
      icon: "https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg",
      title: "账号中心",
      desc: "UIC",
      url: "https://ant.design",
    },
    {
      icon: "https://gw.alipayobjects.com/zos/antfincdn/FLrTNDvlna/antv.png",
      title: "ACL",
      desc: "角色平台",
      url: "https://antv.vision/",
      target: "_blank",
    },
    {
      icon: "https://gw.alipayobjects.com/zos/antfincdn/upvrAjAPQX/Logo_Tech%252520UI.svg",
      title: "Pro Components",
      desc: "搜索中台",
      url: "https://procomponents.ant.design/",
    },
  ],
};
