// services/proxy.ts
import request from "@/services/request";
import {
  CheckStatusPayload,
  InstallPayload,
  QueryHostMonitorPayload,
  SyncMonitorAgentPayload,
  TestConnectionPayload,
  UpdateHostMonitorPayload,
} from "@/models/host-monitor";

export async function fetch(data: QueryHostMonitorPayload) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/page", {
    method: "POST",
    data,
  });
}

export async function update(data: UpdateHostMonitorPayload) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/update", {
    method: "POST",
    data,
  });
}

export async function testConnect(data: TestConnectionPayload) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/test-connect", {
    method: "POST",
    data,
  });
}

export async function install(data: InstallPayload) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/install", {
    method: "POST",
    data,
  });
}

export async function sync(data: SyncMonitorAgentPayload) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/sync", {
    method: "POST",
    data,
  });
}

export async function checkStatus(data: CheckStatusPayload) {
  console.log("payload", data);
  return request(`/api/devops/host-monitor/check/` + data.hostId);
}
