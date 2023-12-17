// src/services/user.ts
import { QueryAppAccountPayload } from "@/models/user";
import request from "umi-request";

export async function logout() {
  return request("/sso/logout", { method: "GET" });
}

export async function pageAppList(data: QueryAppAccountPayload) {
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
