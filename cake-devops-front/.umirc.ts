import { defineConfig } from "umi";

export default defineConfig({
  plugins: ["@umijs/plugins/dist/react-query", "@umijs/plugins/dist/dva"],
  reactQuery: {},
  routes: [
    { path: "/", component: "index", name: "home" },
    { path: "/docs", component: "docs", name: "docs" },
    { path: "/apps", component: "app/app-list", name: "应用中心" },
  ],
  npmClient: "pnpm",
  dva: {},
  proxy: {
    "/api": {
      target: "http://localhost:9195",
      changeOrigin: true,
      pathRewrite: { "^/api": "" },
    },
  },
  mock: false,
});
