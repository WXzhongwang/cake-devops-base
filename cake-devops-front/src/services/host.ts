// services/host.ts
import request from "@/services/request";
import {
  QueryHostPayload,
  CreateHostPayload,
  UpdateHostPayload,
  CreateHostGroupPayload,
  UpdateHostGroupPayload,
  CreateServerKeyPayload,
  UpdateServerKeyPayload,
  DeleteServerKeyPayload,
  QueryServerKeysPayload,
} from "@/models/host";

export async function fetchHosts(data: QueryHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/pageHost", {
    method: "POST",
    data,
  });
}

export async function createHost(data: CreateHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/createHost", {
    method: "POST",
    data,
  });
}

export async function updateHost(data: UpdateHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/updateHost", {
    method: "POST",
    data,
  });
}

export async function fetchHostGroups() {
  return request("/api/devops/host-group/tree", {
    method: "POST",
  });
}

export async function createHostGroup(data: CreateHostGroupPayload) {
  return request("/api/devops/host-group/createGroup", {
    method: "POST",
    data,
  });
}

export async function updateHostGroup(data: UpdateHostGroupPayload) {
  return request("/api/devops/host-group/createGroup", {
    method: "POST",
    data,
  });
}

export async function createServerKey(data: CreateServerKeyPayload) {
  console.log("payload", data);
  return request("/api/devops/server-key/create", {
    method: "POST",
    data,
  });
}

export async function updateServerKey(data: UpdateServerKeyPayload) {
  console.log("payload", data);
  return request("/api/devops/server-key/update", {
    method: "POST",
    data,
  });
}

export async function deleteServerKey(data: DeleteServerKeyPayload) {
  console.log("payload", data);
  return request("/api/devops/server-key/delete", {
    method: "POST",
    data,
  });
}

export async function queryServerKeys(data: QueryServerKeysPayload) {
  console.log("payload", data);
  return request("/api/devops/server-key/page", {
    method: "POST",
    data,
  });
}
