// src/services/app.ts
import request from "@/services/request";
import {
  CreateReleasePayload,
  DeployPayload,
  PageReleasePayload,
  ClosePayload,
  PageDeployHistoryPayload,
  QueryPipeLogPayLoad,
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

export async function pageDeployHistory(data: PageDeployHistoryPayload) {
  return request("/api/devops/release/page-deploy-history", {
    method: "POST",
    data,
  });
}
export async function queryPipeLog(data: QueryPipeLogPayLoad) {
  console.log("payload", data);
  return request(
    `/api/devops/release/query-deploy-log?pipeKey=` + data.pipeKey
  );
}

export async function deploy(data: DeployPayload) {
  return request("/api/devops/release/deploy", {
    method: "POST",
    data,
  });
}

export async function close(data: ClosePayload) {
  return request("/api/devops/release/close", {
    method: "POST",
    data,
  });
}
