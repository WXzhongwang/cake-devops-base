// src/services/host-env.ts

import {
  GetHostAlarmConfigPayload,
  ConfigureHostAlarmConfigPayload,
  PageHostAlarmHistoryPayload,
} from "@/models/host-alarm-config";
import request from "@/services/request";

// 配置
export async function configureAlarmConfig(
  payload: ConfigureHostAlarmConfigPayload
) {
  return request("/api/devops/host-alarm/configure", {
    method: "POST",
    data: payload,
  });
}

export async function getAlarmConfig(data: GetHostAlarmConfigPayload) {
  console.log("payload", data);
  return request(`/api/devops/host-alarm/get-config?hostId=` + data.hostId);
}

export async function pageAlarms(payload: PageHostAlarmHistoryPayload) {
  return request("/api/devops/host-alarm/history", {
    method: "POST",
    data: payload,
  });
}
