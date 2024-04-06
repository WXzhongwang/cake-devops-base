// src/services/user.ts
import { QueryAccountPayload, QueryAppAccountPayload } from "@/models/user";
import request from "@/services/request";

export async function logout() {
  return request("/sso/logout", { method: "GET" });
}

export async function queryAppMembers(data: QueryAppAccountPayload) {
  return request("/api/devops/user/pageAppMembers", {
    method: "POST",
    data,
  });
}

export async function queryMembers(data: QueryAccountPayload) {
  return request("/api/devops/user/queryMembers", {
    method: "POST",
    data,
  });
}

export async function getUserInfo() {
  return request("/api/devops/user/getUser", {
    method: "GET",
    headers: {
      Accept: "application/json", // 设置 Accept 头为 application/json
      // 其他头部信息...
    },
  });
}
