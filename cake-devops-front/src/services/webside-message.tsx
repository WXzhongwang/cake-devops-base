import request from "@/services/request";

export async function getUnreadCount() {
  return request(`/api/devops/web-side-message/get-unread-count`, {
    method: "GET",
  });
}

export async function setAllRead() {
  return request(`/api/devops/web-side-message/set-all-read`, {
    method: "GET",
  });
}

export async function read(data: any) {
  return request("/api/devops/web-side-message/read", {
    method: "POST",
    data,
  });
}

export async function deleteAllRead() {
  return request(`/api/devops/web-side-message/delete-all-read`, {
    method: "POST",
  });
}

export async function deleteMessage(data: any) {
  return request("/api/devops/web-side-message/delete", {
    method: "POST",
    data,
  });
}

export async function fetchMessages(data: any) {
  return request("/api/devops/web-side-message/query", {
    method: "POST",
    data,
  });
}
