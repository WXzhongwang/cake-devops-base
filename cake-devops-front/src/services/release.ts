// src/services/app.ts
import request from "@/services/request";
import {
  CreateReleasePayload,
  DeployPayload,
  PageReleasePayload,
} from "@/models/release";

export async function create(data: CreateReleasePayload) {
  return request("/api/devops/release/create", {
    method: "POST",
    data,
  });
}

export async function page(data: PageReleasePayload) {
  return request("/api/devops/release/page", {
    method: "POST",
    data,
  });
}

export async function deploy(data: DeployPayload) {
  return request("/api/devops/app/deploy", {
    method: "POST",
    data,
  });
}
