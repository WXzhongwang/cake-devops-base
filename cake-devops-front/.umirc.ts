import { defineConfig } from "umi";

const { BUILD_ENV } = process.env;
const name = process.env.npm_package_name;
const version = process.env.npm_package_version;
let publicPath = "/";

switch (BUILD_ENV) {
  case "test":
    publicPath =
      publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/test/${name}/${version}/`;
    break;
  case "pre":
    publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/pre/${name}/${version}/`;
    break;
  case "prod":
    publicPath = `https://rany-ops.oss-cn-hangzhou.aliyuncs.com/fe-res/prod/${name}/${version}/`;
    break;
  default:
    break;
}

export default defineConfig({
  base: "/",
  publicPath: publicPath,
  plugins: ["@umijs/plugins/dist/react-query", "@umijs/plugins/dist/dva"],
  reactQuery: {},
  extraBabelPlugins:
    process.env.NODE_ENV === "prod" ? ["babel-plugin-dynamic-import-node"] : [],

  // 全部路由配置
  routes: [
    { path: "/", redirect: "/apps" },
    { path: "/apps", component: "app/app-list", name: "应用中心" },
    { path: "/app/info/:id", component: "app/app-detail" },
    { path: "/app/deploy/:id", component: "app/deploy" },
    { path: "/host/detail/:id", component: "ops/host-detail" },
    { path: "/host/alarm/history/:id", component: "ops/host-alarm-history" },
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
          path: "/ops/server-key",
          component: "ops/server-key",
          name: "主机秘钥管理",
        },
        {
          path: "/ops/server-proxy",
          component: "ops/server-proxy",
          name: "主机代理管理",
        },
        {
          path: "/ops/server-env",
          component: "ops/server-env",
          name: "主机环境变量",
        },
        {
          path: "/ops/server-monitor",
          component: "ops/server-monitor",
          name: "主机监控",
        },
        {
          path: "/ops/sftp-manage/:id",
          component: "ops/sftp-manage",
          layout: false,
        },
      ],
    },
    {
      path: "/system",
      routes: [
        {
          path: "/system/webhook-config",
          component: "system/webhook-config",
          name: "Webhook配置",
        },
        {
          path: "/system/alarm-group",
          component: "system/alarm-group",
          name: "告警组",
        },
        {
          path: "/system/script-template",
          component: "system/script-template",
          name: "脚本管理",
        },
        {
          path: "/system/system-log",
          component: "system/system-log",
          name: "系统日志",
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
      ws: true,
      pathRewrite: { "^/api": "/api" },
    },
  },
  mock: false,
});
