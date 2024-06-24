import request from "@/services/request";

// 打开 SFTP 连接
export async function openSftpConnection(data: any) {
  return request("/api/devops/sftp/open", {
    method: "POST",
    data,
  });
}

// 获取文件夹列表
export async function getDirList(data: any) {
  return request("/api/devops/sftp/list-dir", {
    method: "POST",
    data,
  });
}

// 获取文件夹列表
export async function getFileList(data: any) {
  return request("/api/devops/sftp/list", {
    method: "POST",
    data,
  });
}

// 创建文件夹
export async function createFolder(data: any) {
  return request("/api/devops/sftp/mkdir", {
    method: "POST",
    data,
  });
}

// 创建文件
export async function createFile(data: any) {
  return request("/api/devops/sftp/touch", {
    method: "POST",
    data,
  });
}

// 截断文件
export async function truncateFile(data: any) {
  return request("/api/devops/sftp/truncate", {
    method: "POST",
    data,
  });
}

// 移动文件
export async function moveFile(data: any) {
  return request("/api/devops/sftp/move", {
    method: "POST",
    data,
  });
}

// 删除文件/文件夹
export async function removeFile(data: any) {
  return request("/api/devops/sftp/remove", {
    method: "POST",
    data,
  });
}

// 修改文件权限
export async function changeFilePermissions(data: any) {
  return request("/api/devops/sftp/chmod", {
    method: "POST",
    data,
  });
}

// 修改文件所有者
export async function changeFileOwner(data: any) {
  return request("/api/devops/sftp/chown", {
    method: "POST",
    data,
  });
}

// 修改文件所有组
export async function changeFileGroup(data: any) {
  return request("/api/devops/sftp/chgrp", {
    method: "POST",
    data,
  });
}

// 检查文件是否存在
export async function checkFileExistence(data: any) {
  return request("/api/devops/sftp/check-present", {
    method: "POST",
    data,
  });
}

// 获取上传文件的 accessToken
export async function getUploadAccessToken(data: any) {
  return request("/api/devops/sftp/upload/token", {
    method: "POST",
    data,
  });
}

// 上传文件
export async function uploadFile(data: any) {
  return request("/api/devops/sftp/upload/exec", {
    method: "POST",
    data,
  });
}

// 下载文件
export async function downloadFile(data: any) {
  return request("/api/devops/sftp/download/exec", {
    method: "POST",
    data,
  });
}

// 打包下载文件
export async function packageDownloadFile(data: any) {
  return request("/api/devops/sftp/package-download/exec", {
    method: "POST",
    data,
  });
}

// 暂停文件传输
export async function pauseFileTransfer(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/pause`, {
    method: "GET",
  });
}

// 恢复文件传输
export async function resumeFileTransfer(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/resume`, {
    method: "GET",
  });
}

// 传输失败重试
export async function retryFailedTransfer(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/retry`, {
    method: "GET",
  });
}

// 重新上传文件
export async function reUploadFile(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/re-upload`, {
    method: "GET",
  });
}

// 重新下载文件
export async function reDownloadFile(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/re-download`, {
    method: "GET",
  });
}

// 暂停所有传输
export async function pauseAllTransfers(data: any) {
  return request(`/api/devops/sftp/transfer/${data.sessionToken}/pause-all`, {
    method: "GET",
  });
}

// 恢复所有传输
export async function resumeAllTransfers(data: any) {
  return request(`/api/devops/sftp/transfer/${data.sessionToken}/resume-all`, {
    method: "GET",
  });
}

// 传输失败重试所有
export async function retryAllFailedTransfers(data: any) {
  return request(`/api/devops/sftp/transfer/${data.sessionToken}/retry-all`, {
    method: "GET",
  });
}

// 获取传输列表
export async function getTransferList(data: any) {
  return request(`/api/devops/sftp/transfer/${data.sessionToken}/list`, {
    method: "GET",
  });
}

// 删除单个传输记录
export async function removeSingleTransfer(data: any) {
  return request(`/api/devops/sftp/transfer/${data.fileToken}/remove`, {
    method: "GET",
  });
}

// 清空全部传输记录
export async function clearAllTransfers(data: any) {
  return request(`/api/devops/sftp/transfer/${data.sessionToken}/clear`, {
    method: "GET",
  });
}

// 传输打包全部已完成未删除的文件
export async function packageAllCompletedFiles(data: any) {
  return request(
    `/api/devops/sftp/transfer/${data.sessionToken}/${data.packageType}/package`,
    {
      method: "GET",
    }
  );
}

export async function down(payload: any) {
  return request(`/api/devops/download/sftp?logId=` + payload.logId, {
    method: "GET",
  });
}
