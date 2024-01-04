// services/host.ts
import request from "@/services/request";
import {
  QueryHostPayload,
  CreateHostPayload,
  UpdateHostPayload,
  CreateHostGroupPayload,
  UpdateHostGroupPayload,
} from "@/models/host";

export async function fetchHosts(payload: QueryHostPayload) {
  return request("/api/devops/hosts/pageHost", {
    method: "POST",
    data: payload,
  });
}

export async function createHost(payload: CreateHostPayload) {
  return request("/api/devops/hosts/createHost", {
    method: "POST",
    data: payload,
  });
}

export async function updateHost(payload: UpdateHostPayload) {
  const { hostId, ...rest } = payload;
  return request("/api/devops/hosts/updateHost", {
    method: "POST",
    data: rest,
  });
}

export async function fetchHostGroups() {
  return request("/api/devops/host-group/tree", {
    method: "POST",
    data: {},
  });
}

export async function createHostGroup(payload: CreateHostGroupPayload) {
  return request("/api/devops/host-group/createGroup", {
    method: "POST",
    data: payload,
  });
}

export async function updateHostGroup(payload: UpdateHostGroupPayload) {
  return request("/api/devops/host-group/createGroup", {
    method: "POST",
    data: payload,
  });
}
