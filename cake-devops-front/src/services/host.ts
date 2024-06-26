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
  CopyHostPayload,
  PingHostPayload,
  DeleteHostPayload,
} from "@/models/host";

export async function fetchHosts(data: QueryHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/page", {
    method: "POST",
    data,
  });
}

export async function createHost(data: CreateHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/create", {
    method: "POST",
    data,
  });
}

export async function copyHost(data: CopyHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/copy", {
    method: "POST",
    data,
  });
}

export async function deleteHost(data: DeleteHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/delete", {
    method: "POST",
    data,
  });
}

export async function pingHost(data: PingHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/ping", {
    method: "POST",
    data,
  });
}

export async function updateHost(data: UpdateHostPayload) {
  console.log("payload", data);
  return request("/api/devops/host/update", {
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
  return request("/api/devops/host-group/create", {
    method: "POST",
    data,
  });
}

export async function updateHostGroup(data: UpdateHostGroupPayload) {
  return request("/api/devops/host-group/update", {
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

export async function getTerminalAccessToken(data: any) {
  console.log("payload", data);
  return request("/api/devops/host/terminal/access?hostId=" + data.hostId);
}

export async function getSupportedPty() {
  return request("/api/devops/host/terminal/support/pty");
}

export async function getConfig(data: any) {
  return request("/api/devops/host/terminal/get-config?hostId=" + data.hostId);
}

export async function updateConfig(data: any) {
  console.log("payload", data);
  return request("/api/devops/host/terminal/update", {
    method: "POST",
    data,
  });
}

export async function queryLog(data: any) {
  console.log("payload", data);
  return request("/api/devops/host/terminal/log/list", {
    method: "POST",
    data,
  });
}

export async function getScreenPath(data: any) {
  return request("/api/devops/host/terminal/log/screen?id=" + data.id);
}
