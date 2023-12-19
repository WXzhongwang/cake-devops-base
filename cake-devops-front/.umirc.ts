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
          path: "/ops",
          component: "app/app-list",
          name: "运维中心",
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
