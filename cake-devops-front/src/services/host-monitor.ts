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
  return request(`/api/devops/host-monitor/check?hostId=` + data.hostId);
}

export async function sendPing(data: any) {
  console.log("payload", data);
  // return request("/api/devops/host-monitor/endpoint/ping", {
  //   method: "POST",
  //   data,
  // });

  return request(
    `/api/devops/host-monitor/endpoint/ping?hostId=` + data.hostId
  );
}

export async function metrics(data: any) {
  console.log("payload", data);
  return request(
    `/api/devops/host-monitor/endpoint/metrics?hostId=` + data.hostId
  );
}

export async function load(data: any) {
  console.log("payload", data);
  return request(
    `/api/devops/host-monitor/endpoint/load?hostId=` + data.hostId
  );
}

export async function top(data: any) {
  console.log("payload", data);
  return request(`/api/devops/host-monitor/endpoint/top?hostId=` + data.hostId);
}

export async function getDiskName(data: any) {
  console.log("payload", data);
  return request(
    `/api/devops/host-monitor/endpoint/disk-name?hostId=` + data.hostId
  );
}

export async function getCpuStatistics(data: any) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/endpoint/chart-cpu", {
    method: "POST",
    data,
  });
}

export async function getMemoryStatistics(data: any) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/endpoint/chart-memory", {
    method: "POST",
    data,
  });
}

export async function getNetStatistics(data: any) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/endpoint/chart-memory", {
    method: "POST",
    data,
  });
}

export async function getDiskStatistics(data: any) {
  console.log("payload", data);
  return request("/api/devops/host-monitor/endpoint/chart-memory", {
    method: "POST",
    data,
  });
}
