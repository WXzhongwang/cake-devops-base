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
  return request("/api/devops/user/query-members", {
    method: "POST",
    data,
  });
}

export async function getUserInfo() {
  return request("/api/devops/user/get-current-user", {
    method: "GET",
  });
}
