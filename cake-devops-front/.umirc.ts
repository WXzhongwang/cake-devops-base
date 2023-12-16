import { defineConfig } from "umi";

export default defineConfig({
  base: "/",
  plugins: ["@umijs/plugins/dist/react-query", "@umijs/plugins/dist/dva"],
  reactQuery: {},
  routes: [
    { path: "/", redirect: "/apps" },
    { path: "/apps", component: "app/app-list", name: "应用中心" },
  ],
  npmClient: "pnpm",
  dva: {},
  // proxy: {
  //   "/api": {
  //     target: "http://localhost:9195",
  //     changeOrigin: true,
  //     pathRewrite: { "^/api": "" },
  //   },
  // },
  mock: {},
});
