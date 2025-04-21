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
  esbuildMinifyIIFE: true, // fatal - [esbuildHelperChecker] Found conflicts in esbuild helpers: Rn (872.async.js, umi.js), please set esbuildMinifyIIFE: true in your config file.
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
    { path: "/apps/app/info/:id", component: "app/app-detail" },
    { path: "/apps/app/deploy/:id", component: "app/deploy" },
    { path: "/apps/host/detail/:id", component: "ops/host-detail" },
    {
      path: "/apps/host/alarm/history/:id",
      component: "ops/host-alarm-history",
    },
    {
      path: "/apps/ops",
      routes: [
        {
          path: "/apps/ops/cluster",
          component: "ops/cluster-list",
          name: "集群管理",
        },
        {
          path: "/apps/ops/host",
          component: "ops/host-list",
          name: "主机管理",
        },
        {
          path: "/apps/ops/server-key",
          component: "ops/server-key",
          name: "主机秘钥管理",
        },
        {
          path: "/apps/ops/server-proxy",
          component: "ops/server-proxy",
          name: "主机代理管理",
        },
        {
          path: "/apps/ops/server-env",
          component: "ops/server-env",
          name: "主机环境变量",
        },
        {
          path: "/apps/ops/server-monitor",
          component: "ops/server-monitor",
          name: "主机监控",
        },
        {
          path: "/apps/ops/sftp-manage/:id",
          component: "ops/sftp-manage",
          layout: false,
        },
      ],
    },
    {
      path: "/apps/system",
      routes: [
        {
          path: "/apps/system/webhook-config",
          component: "system/webhook-config",
          name: "Webhook配置",
        },
        {
          path: "/apps/system/alarm-group",
          component: "system/alarm-group",
          name: "告警组",
        },
        {
          path: "/apps/system/script-template",
          component: "system/script-template",
          name: "脚本管理",
        },
        {
          path: "/apps/system/batch-exec",
          component: "system/batch-exec",
          name: "批量执行",
        },
        {
          path: "/apps/system/system-log",
          component: "system/system-log",
          name: "系统日志",
        },
      ],
    },
    { path: "/404", component: "system/not-found" }, // 显式声明 /404 路由
    { path: "/*", redirect: "/404" }, // 通配符路由，重定向到 /404 路由
  ],
  npmClient: "pnpm",
  dva: {},
  proxy: {
    "/api": {
      target: "http://127.0.0.1:8300",
      changeOrigin: true,
      pathRewrite: { "^/api": "/api" },
      logLevel: "debug",
      onError(err, req, res) {
        console.error("Proxy error:", err);
      },
    },
    "/api/ws": {
      target: "http://127.0.0.1:8300", // 后端服务器地址
      ws: true,
      changeOrigin: true,
      pathRewrite: { "^/api/ws": "/api/ws" },
      secure: false,
      logLevel: "debug",
      onProxyReqWs: (proxyReq, req, socket, options, head) => {
        // 添加一些WebSocket相关的请求头
        proxyReq.setHeader("Connection", "upgrade");
        proxyReq.setHeader("Upgrade", "websocket");
      },
    },
    "/api/keep-alive": {
      target: "http://127.0.0.1:8300", // 后端服务器地址
      ws: true,
      changeOrigin: true,
      pathRewrite: { "^/api/keep-alive": "/api/keep-alive" },
      secure: false,
      logLevel: "debug",
      onProxyReqWs: (proxyReq, req, socket, options, head) => {
        // 添加一些WebSocket相关的请求头
        proxyReq.setHeader("Connection", "upgrade");
        proxyReq.setHeader("Upgrade", "websocket");
      },
    },
  },
  mock: false,
});
