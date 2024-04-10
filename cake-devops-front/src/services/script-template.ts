import {
  CreateScriptPayload,
  DeleteScriptPayload,
  QueryScriptPayload,
  UpdateScriptPayload,
} from "@/models/script-template";
import request from "@/services/request";

export async function fetchScripts(data: QueryScriptPayload) {
  return request("/api/devops/script-template/page", {
    method: "POST",
    data,
  });
}

export async function createScript(data: CreateScriptPayload) {
  return request("/api/devops/script-template/create", {
    method: "POST",
    data,
  });
}

export async function updateScript(data: UpdateScriptPayload) {
  return request("/api/devops/script-template/update", {
    method: "POST",
    data,
  });
}

export async function deleteScript(data: DeleteScriptPayload) {
  console.log("payload", data);
  return request("/api/devops/script-template/delete", {
    method: "POST",
    data,
  });
}
