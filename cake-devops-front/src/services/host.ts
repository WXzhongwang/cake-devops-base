// services/host.ts
import request from "@/services/request";
import {
  QueryHostPayload,
  CreateHostPayload,
  UpdateHostPayload,
  CreateHostGroupPayload,
  UpdateHostGroupPayload,
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
