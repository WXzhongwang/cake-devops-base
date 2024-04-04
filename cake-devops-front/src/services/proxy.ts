// services/proxy.ts
import request from "@/services/request";
import {
  QueryProxiesPayload,
  CreateProxyPayload,
  UpdateProxyPayload,
  DeleteProxyPayload,
} from "@/models/proxy";

export async function fetchProxies(data: QueryProxiesPayload) {
  console.log("payload", data);
  return request("/api/devops/proxy/page", {
    method: "POST",
    data,
  });
}

export async function createProxy(data: CreateProxyPayload) {
  console.log("payload", data);
  return request("/api/devops/proxy/create", {
    method: "POST",
    data,
  });
}

export async function updateProxy(data: UpdateProxyPayload) {
  console.log("payload", data);
  return request("/api/devops/proxy/update", {
    method: "POST",
    data,
  });
}

export async function deleteProxy(data: DeleteProxyPayload) {
  console.log("payload", data);
  return request("/api/devops/proxy/delete", {
    method: "POST",
    data,
  });
}
