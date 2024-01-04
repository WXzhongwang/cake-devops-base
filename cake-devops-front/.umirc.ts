import { defineConfig } from "umi";

export default defineConfig({
  base: "/",
  plugins: ["@umijs/plugins/dist/react-query", "@umijs/plugins/dist/dva"],
  reactQuery: {},
  routes: [
    { path: "/", redirect: "/apps" },
    { path: "/apps", component: "app/app-list", name: "应用中心" },
    { path: "/app-detail/:id", component: "app/app-detail" },
    {
      path: "/ops",
      routes: [
        {
          path: "/ops/cluster",
          component: "ops/cluster-list",
          name: "集群管理",
        },
        {
          path: "/ops/host",
          component: "ops/host-list",
          name: "主机管理",
        },
        {
          path: "/ops/host-group",
          component: "ops/host-group-list",
          name: "机组管理",
        },
      ],
    },
  ],
  npmClient: "pnpm",
  dva: {},
  proxy: {
    "/api": {
      target: "http://localhost:8300",
      changeOrigin: true,
      pathRewrite: { "^/api": "/api" },
    },
  },
  mock: false,
});
