import request from "@/services/request";

import { QueryLogPayload } from "@/models/logs";

export async function queryLogs(data: QueryLogPayload) {
  return request("/api/devops/logs/query-logs", {
    method: "POST",
    data,
  });
}
