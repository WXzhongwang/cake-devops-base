import {
  CreateWebHookPayload,
  DeleteWebHookPayload,
  QueryWebHooksPayload,
  UpdateWebHookPayload,
} from "@/models/webhook";
import request from "@/services/request";

export async function fetchWebhooks(data: QueryWebHooksPayload) {
  return request("/api/devops/webhook/page", {
    method: "POST",
    data,
  });
}

export async function createWebhook(data: CreateWebHookPayload) {
  return request("/api/devops/webhook/create", {
    method: "POST",
    data,
  });
}

export async function updateWebhook(data: UpdateWebHookPayload) {
  return request("/api/devops/webhook/update", {
    method: "POST",
    data,
  });
}

export async function deleteWebhook(data: DeleteWebHookPayload) {
  console.log("payload", data);
  return request("/api/devops/webhook/delete", {
    method: "POST",
    data,
  });
}
