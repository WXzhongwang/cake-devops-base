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
