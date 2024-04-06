import {
  CreateAlarmGroupPayload,
  DeleteAlarmGroupPayload,
  QueryAlarmGroupPayload,
  UpdateAlarmGroupPayload,
} from "@/models/alarm-group";
import request from "@/services/request";

export async function fetchAlarmGroup(data: QueryAlarmGroupPayload) {
  return request("/api/devops/alarm-group/page", {
    method: "POST",
    data,
  });
}

export async function createAlarmGroup(data: CreateAlarmGroupPayload) {
  return request("/api/devops/alarm-group/create", {
    method: "POST",
    data,
  });
}

export async function updateAlarmGroup(data: UpdateAlarmGroupPayload) {
  return request("/api/devops/alarm-group/update", {
    method: "POST",
    data,
  });
}

export async function deleteAlarmGroup(data: DeleteAlarmGroupPayload) {
  console.log("payload", data);
  return request("/api/devops/alarm-group/delete", {
    method: "POST",
    data,
  });
}
