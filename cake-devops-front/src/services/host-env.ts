// src/services/host-env.ts

import {
  FetchVariablePayload,
  CreateVariablePayload,
  UpdateVariablePayload,
  DeleteVariablePayload,
} from "@/models/host-env";
import request from "@/services/request";

// 获取主机环境变量列表
export async function fetchVariables(data: FetchVariablePayload) {
  console.log("payload", data);
  return request("/api/devops/host-env/page", {
    method: "POST",
    data,
  });
}

// 添加主机环境变量
export async function addVariable(payload: CreateVariablePayload) {
  return request("/api/devops/host-env/create", {
    method: "POST",
    data: payload,
  });
}

// 更新主机环境变量
export async function updateVariable(payload: UpdateVariablePayload) {
  return request(`/api/devops/host-env/update`, {
    method: "POST",
    data: payload,
  });
}

// 删除主机环境变量
export async function deleteVariable(payload: DeleteVariablePayload) {
  return request(`/api/devops/host-env/delete`, {
    method: "POST",
    data: payload,
  });
}
