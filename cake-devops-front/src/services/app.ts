// src/services/app.ts
import request from "umi-request";
import { CreateAppPayload, QueryAppPayload } from "@/models/app";
export async function pageAppList(data: QueryAppPayload) {
  return request("/api/devops/app/pageApp", {
    method: "POST",
    data,
  });
}

export async function createApp(data: CreateAppPayload) {
  return request("/api/devops/app/createApp", {
    method: "POST",
    data,
  });
}
