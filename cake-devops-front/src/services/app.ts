// src/services/app.ts
import request from "@/services/request";
import {
  CreateAppEnvPayload,
  CreateAppPayload,
  QueryAppPayload,
  QueryAppMemberPayload,
  UpdateAppMemberPayload,
  DeleteAppMemberPayload,
} from "@/models/app";
export async function pageAppList(data: QueryAppPayload) {
  return request("/api/devops/app/pageApp", {
    method: "POST",
    data,
  });
}

export async function createApp(data: CreateAppPayload) {
  return request("/api/devops/app/createApp", {
    method: "POST",
    data,
  });
}

export async function updateMember(data: UpdateAppMemberPayload) {
  return request("/api/devops/app/updateMember", {
    method: "POST",
    data,
  });
}

export async function deleteMember(data: DeleteAppMemberPayload) {
  return request("/api/devops/app/deleteMember", {
    method: "POST",
    data,
  });
}

export async function createAppEnv(data: CreateAppEnvPayload) {
  console.log(data);
  return request("/api/devops/app/createEnv", {
    method: "POST",
    data,
  });
}

export async function getAppDetail(id: number) {
  return request(`/api/devops/app/getApp?id=${id}`);
}

export async function getDepartments() {
  return request(`/api/devops/app/getDepartments`);
}

export async function pageAppMembers(data: QueryAppMemberPayload) {
  return request(`/api/devops/app/pageAppMembers`, {
    method: "POST",
    data,
  });
}
