// services/host.ts
import request from "@/services/request";
import {
  QueryHostPayload,
  CreateHostPayload,
  UpdateHostPayload,
  CreateHostGroupPayload,
  UpdateHostGroupPayload,
  CreateServerAccountPayload,
  UpdateServerAccountPayload,
  DeleteServerAccountPayload,
  QueryServerAccountsPayload,
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

export async function createServerAccount(data: CreateServerAccountPayload) {
  console.log("payload", data);
  return request("/api/devops/host-account/create", {
    method: "POST",
    data,
  });
}

export async function updateServerAccount(data: UpdateServerAccountPayload) {
  console.log("payload", data);
  return request("/api/devops/host-account/update", {
    method: "POST",
    data,
  });
}

export async function deleteServerAccount(data: DeleteServerAccountPayload) {
  console.log("payload", data);
  return request("/api/devops/host-account/delete", {
    method: "POST",
    data,
  });
}

export async function queryServerAccounts(data: QueryServerAccountsPayload) {
  console.log("payload", data);
  return request("/api/devops/host-account/page", {
    method: "POST",
    data,
  });
}
