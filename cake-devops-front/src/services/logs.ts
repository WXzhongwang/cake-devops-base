import request from "@/services/request";

import { GetEventTypePayload, QueryLogPayload } from "@/models/logs";

export async function queryLogs(data: QueryLogPayload) {
  return request("/api/devops/logs/query-logs", {
    method: "POST",
    data,
  });
}

export async function getEventClassify() {
  return request("/api/devops/logs/get-classify", {
    method: "GET",
  });
}

export async function getEventType(data: GetEventTypePayload) {
  return request(
    "/api/devops/logs/get-event-type?eventClassify=" + data.classify,
    {
      method: "GET",
    }
  );
}
