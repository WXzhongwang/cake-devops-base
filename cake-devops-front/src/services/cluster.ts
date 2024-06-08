// src/services/app.ts
import request from "@/services/request";

import { CreateClusterPayload, ConnectClusterPayload } from "@/models/cluster";

export async function listAll() {
  return request("/api/devops/cluster/list-all", {
    method: "GET",
    headers: {
      Accept: "application/json", // 设置 Accept 头为 application/json
      // 其他头部信息...
    },
  });
}

export async function createCluster(data: CreateClusterPayload) {
  return request("/api/devops/cluster/create", {
    method: "POST",
    data,
  });
}

export async function connect(data: ConnectClusterPayload) {
  return request("/api/devops/cluster/create", {
    method: "POST",
    data,
  });
}
