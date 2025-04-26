import {
  BatchDeleteCommandAction,
  BatchDeleteCommandExecPayload,
  BatchListStatusCommandExecPayload,
  CreateCommandExecPayload,
  GetCommandExecPayload,
  QueryCommandExecPayload,
  TerminalCommandExecPayload,
  WriteCommandExecPayload,
} from "@/models/batch-exec";
import request from "@/services/request";

export async function fetchCommandExec(data: QueryCommandExecPayload) {
  return request("/api/devops/batch-exec/page", {
    method: "POST",
    data,
  });
}

export async function createCommandExec(data: CreateCommandExecPayload) {
  return request("/api/devops/batch-exec/submit", {
    method: "POST",
    data,
  });
}

export async function write(data: WriteCommandExecPayload) {
  return request("/api/devops/batch-exec/write", {
    method: "POST",
    data,
  });
}

export async function terminal(data: TerminalCommandExecPayload) {
  return request("/api/devops/batch-exec/terminal", {
    method: "POST",
    data,
  });
}

export async function listStatus(data: BatchListStatusCommandExecPayload) {
  return request("/api/devops/batch-exec/list-status", {
    method: "POST",
    data,
  });
}

export async function detail(data: GetCommandExecPayload) {
  return request("/api/devops/batch-exec/detail", {
    method: "POST",
    data,
  });
}

export async function batchDelete(data: BatchDeleteCommandExecPayload) {
  return request("/api/devops/batch-exec/delete", {
    method: "POST",
    data,
  });
}
