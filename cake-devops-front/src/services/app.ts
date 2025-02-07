// src/services/app.ts
import request from "@/services/request";
import {
  CreateAppEnvPayload,
  CreateAppPayload,
  QueryAppPayload,
  QueryAppMemberPayload,
  UpdateAppMemberPayload,
  DeleteAppMemberPayload,
  AddAppMemberPayload,
  ListAppBranchPayload,
  ModifyAppEnvConfigMapPayload,
  ModifyAppEnvVarsPayload,
  ModifyAppResourcesPayload,
  AppServicePayload,
  DeleteAppServicePayload,
  SaveIngressPayload,
} from "@/models/app";
export async function pageAppList(data: QueryAppPayload) {
  return request("/api/devops/app/page", {
    method: "POST",
    data,
  });
}

export async function createApp(data: CreateAppPayload) {
  return request("/api/devops/app/create", {
    method: "POST",
    data,
  });
}

export async function updateMember(data: UpdateAppMemberPayload) {
  return request("/api/devops/app/update-member", {
    method: "POST",
    data,
  });
}

export async function addMember(data: AddAppMemberPayload) {
  return request("/api/devops/app/add-member", {
    method: "POST",
    data,
  });
}

export async function deleteMember(data: DeleteAppMemberPayload) {
  return request("/api/devops/app/delete-member", {
    method: "POST",
    data,
  });
}

export async function createAppEnv(data: CreateAppEnvPayload) {
  console.log(data);
  return request("/api/devops/app/create-env", {
    method: "POST",
    data,
  });
}

export async function getAppDetail(id: number) {
  return request(`/api/devops/app/get?id=${id}`);
}

export async function listAppBranch(data: ListAppBranchPayload) {
  return request(
    `/api/devops/app/list-branch?appId=${data.appId}&search=${data.search}`
  );
}

export async function modifyAppEnvConfigMap(
  data: ModifyAppEnvConfigMapPayload
) {
  return request(`/api/devops/app/modify-config-map`, {
    method: "POST",
    data,
  });
}

export async function modifyAppEnvVars(data: ModifyAppEnvVarsPayload) {
  return request(`/api/devops/app/modify-env-vars`, {
    method: "POST",
    data,
  });
}

export async function createService(data: AppServicePayload) {
  return request(`/api/devops/app/create-svc`, {
    method: "POST",
    data,
  });
}

export async function modifyService(data: AppServicePayload) {
  return request(`/api/devops/app/modify-svc`, {
    method: "POST",
    data,
  });
}

export async function deleteService(data: DeleteAppServicePayload) {
  return request(`/api/devops/app/delete-svc`, {
    method: "POST",
    data,
  });
}

export async function saveIngress(data: SaveIngressPayload) {
  return request(`/api/devops/app/modify-env-domains`, {
    method: "POST",
    data,
  });
}

export async function modifyAppEnvResource(data: ModifyAppResourcesPayload) {
  return request(`/api/devops/app/modify-env-resources`, {
    method: "POST",
    data,
  });
}

export async function scale(data: any) {
  return request(`/api/devops/app/scale`, {
    method: "POST",
    data,
  });
}

export async function getAppEnv(data: any) {
  return request(`/api/devops/app/get-app-env`, {
    method: "POST",
    data,
  });
}

export async function listAppPods(data: any) {
  return request(`/api/devops/app/list-app-pods`, {
    method: "POST",
    data,
  });
}

export async function getDepartments() {
  return request(`/api/devops/app/departments`);
}

export async function pageAppMembers(data: QueryAppMemberPayload) {
  return request(`/api/devops/app/page-app-members`, {
    method: "POST",
    data,
  });
}
