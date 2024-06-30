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
        path: "/apps/ops",
        name: "运维中心",
        icon: <CrownFilled />,
        routes: [
          {
            path: "/apps/ops/cluster",
            name: "集群管理",
            icon: <TabletFilled />,
            component: "ops/cluster-list",
          },
          {
            path: "/apps/ops/host",
            name: "主机管理",
            icon: <ApiFilled />,
            component: "ops/host-list",
          },
          {
            path: "/apps/ops/server-key",
            name: "主机秘钥管理",
            icon: <ApiFilled />,
            component: "ops/server-key",
          },
          {
            path: "/apps/ops/server-proxy",
            name: "主机代理管理",
            icon: <ApiFilled />,
            component: "ops/server-proxy",
          },
          {
            path: "/apps/ops/server-env",
            name: "主机环境变量",
            icon: <ApiFilled />,
            component: "ops/server-env",
          },
          {
            path: "/apps/ops/server-monitor",
            name: "主机监控",
            icon: <ApiFilled />,
            component: "ops/server-monitor",
          },
        ],
      },
      {
        path: "/apps/system",
        name: "系统设置",
        icon: <CrownFilled />,
        routes: [
          {
            path: "/apps/system/webhook-config",
            name: "Webhook",
            icon: <TabletFilled />,
            component: "system/webhook-config",
          },
          {
            path: "/apps/system/alarm-group",
            name: "告警组",
            icon: <TabletFilled />,
            component: "system/alarm-group",
          },
          {
            path: "/apps/system/script-template",
            name: "脚本管理",
            icon: <TabletFilled />,
            component: "system/script-template",
          },
          {
            path: "/apps/system/system-log",
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
