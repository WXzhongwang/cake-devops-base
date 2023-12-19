import {
  ChromeFilled,
  CrownFilled,
  SmileFilled,
  ApiFilled,
  TabletFilled,
} from "@ant-design/icons";

export default {
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
        path: "/cluster",
        name: "运维中心",
        icon: <CrownFilled />,
        routes: [
          {
            path: "/ops/cluster",
            name: "集群管理",
            icon: <TabletFilled />,
            component: "ops/cluster",
          },
          {
            path: "/ops/host",
            name: "主机管理",
            icon: <ApiFilled />,
            component: "ops/host",
          },
          {
            path: "/ops/host-group",
            name: "主机组管理",
            icon: <ApiFilled />,
            component: "ops/host-group",
          },
        ],
      },
      // {
      //   path: "https://ant.design",
      //   name: "Ant Design 官网外链",
      //   icon: <ChromeFilled />,
      // },
    ],
  },
  location: {
    pathname: "/",
  },
};
