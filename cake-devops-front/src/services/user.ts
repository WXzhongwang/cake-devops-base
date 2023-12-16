// src/services/user.ts
import request from "umi-request";

export async function logout() {
  return request("/sso/logout", { method: "GET" });
}

export async function getUserInfo() {
  return request("/api/devops/getUser", { method: "GET" });
}
