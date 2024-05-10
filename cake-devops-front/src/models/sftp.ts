import { Effect, Reducer } from "umi";
import * as api from "@/services/script-template";
import { BaseAction } from "typings";
import { message } from "antd";

export interface FileBaseCommand {
  sessionToken: string; // sessionToken
  all?: boolean; // 是否查询隐藏文件
}

export interface FileDetailDTO {
  name: string; // 名称
  path: string; // 绝对路径
  size: string; // 文件大小
  sizeByte: number; // 文件大小（字节）
  attr: string; // 属性
  permission: number; // 10进制表现的8进制权限
  uid: number; // 用户ID
  gid: number; // 组ID
  modifyTime: Date; // 更新时间
  isDir: boolean; // 是否为目录
  isSafe: boolean; // 是否安全
}

export interface FileTransferLogDTO {
  id: number;
  machineId: number;
  fileToken: string;
  type: number; // 传输类型 10上传 20下载 30传输
  remoteFile: string; // 远程文件
  current: string; // 当前大小
  size: string; // 文件大小
  progress: number; // 当前进度
  status: number; // 传输状态 10未开始 20进行中 30已暂停 40已完成 50已取消 60传输异常
}

// SFTP 打开连接请求
export interface OpenSftpRequest {
  hostId: string; // 主机ID
}

// SFTP 获取文件夹列表请求
export interface GetFileListRequest extends FileBaseCommand {
  path: string; // 文件夹路径
}

// SFTP 获取文件夹列表响应
export interface GetFileListResponse {
  path: string;
  files: FileDetailDTO[];
}

// 创建文件夹请求
export interface CreateFolderRequest extends FileBaseCommand {
  path: string; // 文件夹路径
}

// 创建文件请求
export interface CreateFileRequest extends FileBaseCommand {
  path: string; // 文件路径
}

// 截断文件请求
export interface TruncateFileRequest extends FileBaseCommand {
  path: string; // 文件路径
}

// 移动文件请求
export interface MoveFileRequest extends FileBaseCommand {
  source: string; // 源文件路径
  target: string; // 目标文件路径
}

// 删除文件/文件夹请求
export interface RemoveFileRequest extends FileBaseCommand {
  paths: string[]; // 文件/文件夹路径数组
}

// 修改文件权限请求
export interface ChangeFilePermissionsRequest extends FileBaseCommand {
  path: string; // 文件路径
  permission: string; // 文件权限
}

// 修改文件所有者请求
export interface ChangeFileOwnerRequest extends FileBaseCommand {
  path: string; // 文件路径
  uid: string; // 用户ID
}

// 修改文件所有组请求
export interface ChangeFileGroupRequest extends FileBaseCommand {
  path: string; // 文件路径
  gid: string; // 组ID
}

// 检查文件是否存在请求
export interface CheckFileExistenceRequest extends FileBaseCommand {
  path: string; // 文件路径
  names: string[]; // 文件名数组
  size: number; // 文件大小
}

// 检查文件是否存在响应
export interface CheckFileExistenceResponse {
  paths: string[]; // 存在的文件路径数组
}

// 获取上传文件的accessToken请求
export interface GetUploadAccessTokenRequest {
  // 可根据需要定义请求中的字段
  sessionToken: string; // sessionToken
  hostId: string; // 机器id
  fileToken: string; // 文件token
  localPath: string; // 本地文件路径
  remotePath: string; // 远程文件路径
  size: number; // 文件大小
}

// 获取上传文件的accessToken响应
export interface GetUploadAccessTokenResponse {
  accessToken: string; // 上传文件的accessToken
}

// 上传文件请求
export interface UploadFileRequest extends FileBaseCommand {
  accessToken: string; // 上传文件的accessToken
  files: File[]; // 文件列表
}

// 下载文件请求
export interface DownloadFileRequest extends FileBaseCommand {
  paths: string[]; // 文件路径数组
}

// 打包下载文件请求
export interface PackageDownloadFileRequest extends FileBaseCommand {
  paths: string[]; // 文件路径数组
}

// 暂停文件传输请求
export interface PauseFileTransferRequest {
  fileToken: string; // 文件token
}

// 恢复文件传输请求
export interface ResumeFileTransferRequest {
  fileToken: string; // 文件token
}

// 传输失败重试请求
export interface RetryFailedTransferRequest {
  fileToken: string; // 文件token
}

// 重新上传文件请求
export interface ReuploadFileRequest {
  fileToken: string; // 文件token
}

// 重新下载文件请求
export interface ReDownloadFileRequest {
  fileToken: string; // 文件token
}

// 暂停所有传输请求
export interface PauseAllTransfersRequest {
  sessionToken: string; // 会话token
}

// 恢复所有传输请求
export interface ResumeAllTransfersRequest {
  sessionToken: string; // 会话token
}

// 传输失败重试所有请求
export interface RetryAllFailedTransfersRequest {
  sessionToken: string; // 会话token
}

// 获取传输列表请求
export interface GetTransferListRequest {
  sessionToken: string; // 会话token
}

// 获取传输列表响应
export interface GetTransferListResponse {
  // 可根据需要定义响应中的字段
}

// 删除单个传输记录请求
export interface DeleteSingleTransferRequest {
  fileToken: string; // 文件token
}

// 清空全部传输记录请求
export interface ClearAllTransfersRequest {
  sessionToken: string; // 会话token
}

// 打包全部已完成未删除的文件请求
export interface PackageAllFilesRequest {
  sessionToken: string; // 会话token
  packageType: number; // 打包类型
}
