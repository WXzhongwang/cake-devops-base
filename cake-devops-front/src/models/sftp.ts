import { Effect, Reducer } from "umi";
import * as api from "@/services/sftp";
import { BaseAction } from "typings";
import { message } from "antd";

export interface FileBaseCommand {
  sessionToken: string; // sessionToken
  all?: boolean; // 是否查询隐藏文件
}

export interface OpenSessionDTO {
  home: string;
  sessionToken: string;
  charset: string;
  path: string;
  files: FileDetailDTO[];
}

export interface ListDirDTO {
  path: string;
  files: FileDetailDTO[];
}

export interface FileTransferLogDTO {
  id: number;
  hostId: string;
  fileToken: string;
  type: number;
  remoteFile: string;
  current: number;
  size: number;
  progress: number;
  status: number;
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
  current: number; // 当前大小
  size: number; // 文件大小
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
export interface ReUploadFileRequest {
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

interface OpenSftpAction {
  type: "sftp/open";
  payload: OpenSftpRequest;
}

interface ListDirAction extends BaseAction {
  type: "sftp/listDir";
  payload: GetFileListRequest;
}

interface ListFileAction {
  type: "sftp/list";
  payload: GetFileListRequest;
}

interface CreateFolderAction extends BaseAction {
  type: "sftp/createFolder";
  payload: CreateFolderRequest;
}

interface CreateFileAction extends BaseAction {
  type: "sftp/createFile";
  payload: CreateFileRequest;
}

interface TruncateFileAction extends BaseAction {
  type: "sftp/truncateFile";
  payload: TruncateFileRequest;
}

interface MoveFileAction extends BaseAction {
  type: "sftp/moveFile";
  payload: MoveFileRequest;
}

interface RemoveFileAction extends BaseAction {
  type: "sftp/removeFile";
  payload: RemoveFileRequest;
}

interface ChangeFilePermissionsAction extends BaseAction {
  type: "sftp/changeFilePermissions";
  payload: ChangeFilePermissionsRequest;
}

interface ChangeFileOwnerAction extends BaseAction {
  type: "sftp/changeFileOwner";
  payload: ChangeFileOwnerRequest;
}

interface ChangeFileGroupAction extends BaseAction {
  type: "sftp/changeFileGroup";
  payload: ChangeFileGroupRequest;
}

interface CheckFileExistenceAction extends BaseAction {
  type: "sftp/checkFileExistence";
  payload: CheckFileExistenceRequest;
}

interface GetUploadAccessTokenAction extends BaseAction {
  type: "sftp/getUploadAccessToken";
  payload: GetUploadAccessTokenRequest;
}

interface UploadFileAction extends BaseAction {
  type: "sftp/uploadFile";
  payload: UploadFileRequest;
}

interface DownloadFileAction extends BaseAction {
  type: "sftp/downloadFile";
  payload: DownloadFileRequest;
}

interface PackageDownloadFileAction extends BaseAction {
  type: "sftp/packageDownloadFile";
  payload: PackageDownloadFileRequest;
}

interface PauseFileTransferAction extends BaseAction {
  type: "sftp/pauseFileTransfer";
  payload: PauseFileTransferRequest;
}

interface ResumeFileTransferAction extends BaseAction {
  type: "sftp/resumeFileTransfer";
  payload: ResumeFileTransferRequest;
}

interface RetryFailedTransferAction extends BaseAction {
  type: "sftp/retryFailedTransfer";
  payload: RetryFailedTransferRequest;
}

interface ReUploadFileAction extends BaseAction {
  type: "sftp/reUploadFile";
  payload: ReUploadFileRequest;
}

interface ReDownloadFileAction extends BaseAction {
  type: "sftp/reDownloadFile";
  payload: ReDownloadFileRequest;
}

interface PauseAllTransfersAction extends BaseAction {
  type: "sftp/pauseAllTransfers";
  payload: PauseAllTransfersRequest;
}
interface ResumeAllTransfersAction extends BaseAction {
  type: "sftp/resumeAllTransfers";
  payload: ResumeAllTransfersRequest;
}
interface RetryAllFailedTransfersAction extends BaseAction {
  type: "sftp/retryAllFailedTransfer";
  payload: RetryAllFailedTransfersRequest;
}
interface GetTransferListAction extends BaseAction {
  type: "sftp/getTransferList";
  payload: GetTransferListRequest;
}
interface DeleteSingleTransferAction extends BaseAction {
  type: "sftp/removeSingleTransfer";
  payload: DeleteSingleTransferRequest;
}

interface ClearAllTransfersAction extends BaseAction {
  type: "sftp/clearAllTransfers";
  payload: ClearAllTransfersRequest;
}

interface PackageAllFilesAction extends BaseAction {
  type: "sftp/packageAllCompletedFiles";
  payload: PackageAllFilesRequest;
}

export interface SftpModelState {
  files: FileDetailDTO[];
  open?: OpenSessionDTO;
  dirs: ListDirDTO | null;
  transferList: FileTransferLogDTO[];
}

export interface SftpModelType {
  namespace: "sftp";
  state: SftpModelState;
  effects: {
    open: Effect;
    fetchDirs: Effect;
    fetchList: Effect;
    createFolder: Effect;
    createFile: Effect;
    truncateFile: Effect;
    moveFile: Effect;
    removeFile: Effect;
    changeFilePermissions: Effect;
    changeFileOwner: Effect;
    changeFileGroup: Effect;
    checkFileExistence: Effect;
    getUploadAccessToken: Effect;
    uploadFile: Effect;
    downloadFile: Effect;
    packageDownloadFile: Effect;
    pauseFileTransfer: Effect;
    resumeFileTransfer: Effect;
    retryFailedTransfer: Effect;
    reUploadFile: Effect;
    reDownloadFile: Effect;
    pauseAllTransfers: Effect;
    resumeAllTransfers: Effect;
    retryAllFailedTransfers: Effect;
    getTransferList: Effect;
    removeSingleTransfer: Effect;
    clearAllTransfers: Effect;
    packageAllCompletedFiles: Effect;
  };
  reducers: {
    saveToken: Reducer<SftpModelState>;
    saveFiles: Reducer<SftpModelState>;
    saveDirs: Reducer<SftpModelState>;
    saveTransferList: Reducer<SftpModelState>;
  };
}

const SftpModel: SftpModelType = {
  namespace: "sftp",

  state: {
    files: [],
    dirs: null,
    transferList: [],
  },

  effects: {
    *open({ payload }: OpenSftpAction, { call, put }) {
      const response = yield call(api.openSftpConnection, payload);
      yield put({
        type: "saveToken",
        payload: response.content,
      });
    },

    *fetchDirs({ payload, callback }: ListDirAction, { call, put }) {
      const response = yield call(api.getDirList, payload);
      const { success, msg } = response;
      yield put({
        type: "saveDirs",
        payload: response.content,
      });
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *fetchFiles({ payload }: ListFileAction, { call, put }) {
      const response = yield call(api.getFileList, payload);
      yield put({
        type: "saveFiles",
        payload: response.content,
      });
    },

    *createFolder({ payload, callback }: CreateFolderAction, { call, put }) {
      const response = yield call(api.createFolder, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *createFile({ payload, callback }: CreateFileAction, { call, put }) {
      const response = yield call(api.createFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *truncateFile({ payload, callback }: TruncateFileAction, { call, put }) {
      const response = yield call(api.truncateFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *moveFile({ payload, callback }: MoveFileAction, { call, put }) {
      const response = yield call(api.moveFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *removeFile({ payload, callback }: RemoveFileAction, { call, put }) {
      const response = yield call(api.removeFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *changeFilePermissions(
      { payload, callback }: ChangeFilePermissionsAction,
      { call, put }
    ) {
      const response = yield call(api.changeFilePermissions, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *changeFileOwner(
      { payload, callback }: ChangeFileOwnerAction,
      { call, put }
    ) {
      const response = yield call(api.changeFileOwner, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *changeFileGroup(
      { payload, callback }: ChangeFileGroupAction,
      { call, put }
    ) {
      const response = yield call(api.changeFileGroup, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *checkFileExistence(
      { payload, callback }: CheckFileExistenceAction,
      { call, put }
    ) {
      const response = yield call(api.checkFileExistence, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *getUploadAccessToken(
      { payload, callback }: GetUploadAccessTokenAction,
      { call, put }
    ) {
      const response = yield call(api.getUploadAccessToken, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *uploadFile({ payload, callback }: UploadFileAction, { call, put }) {
      const response = yield call(api.uploadFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *downloadFile({ payload, callback }: DownloadFileAction, { call, put }) {
      const response = yield call(api.downloadFile, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
    *packageDownloadFile(
      { payload, callback }: PackageDownloadFileAction,
      { call, put }
    ) {
      const response = yield call(api.packageDownloadFile, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *pauseFileTransfer(
      { payload, callback }: PauseFileTransferAction,
      { call, put }
    ) {
      const response = yield call(api.pauseFileTransfer, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *resumeFileTransfer(
      { payload, callback }: ResumeFileTransferAction,
      { call, put }
    ) {
      const response = yield call(api.resumeFileTransfer, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *retryFailedTransfer(
      { payload, callback }: RetryFailedTransferAction,
      { call, put }
    ) {
      const response = yield call(api.retryFailedTransfer, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *reUploadFile({ payload, callback }: ReUploadFileAction, { call, put }) {
      const response = yield call(api.reUploadFile, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *reDownloadFile(
      { payload, callback }: ReDownloadFileAction,
      { call, put }
    ) {
      const response = yield call(api.reDownloadFile, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *pauseAllTransfers(
      { payload, callback }: PauseAllTransfersAction,
      { call, put }
    ) {
      const response = yield call(api.pauseAllTransfers, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *resumeAllTransfers(
      { payload, callback }: ResumeAllTransfersAction,
      { call, put }
    ) {
      const response = yield call(api.resumeAllTransfers, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *retryAllFailedTransfers(
      { payload, callback }: RetryAllFailedTransfersAction,
      { call, put }
    ) {
      const response = yield call(api.retryAllFailedTransfers, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getTransferList(
      { payload, callback }: GetTransferListAction,
      { call, put }
    ) {
      const response = yield call(api.getTransferList, payload);
      yield put({
        type: "saveTransferList",
        payload: response.content,
      });
    },
    *removeSingleTransfer(
      { payload, callback }: DeleteSingleTransferAction,
      { call, put }
    ) {
      const response = yield call(api.removeSingleTransfer, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *clearAllTransfers(
      { payload, callback }: ClearAllTransfersAction,
      { call, put }
    ) {
      const response = yield call(api.clearAllTransfers, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *packageAllCompletedFiles(
      { payload, callback }: PackageAllFilesAction,
      { call, put }
    ) {
      const response = yield call(api.packageAllCompletedFiles, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {
    saveToken(state, action) {
      return {
        ...state,
        open: action.payload,
      };
    },
    saveDirs(state, action) {
      return {
        ...state,
        dirs: action.payload,
      };
    },
    saveFiles(state, action) {
      return {
        ...state,
        files: action.payload.files,
      };
    },
    saveTransferList(state, action) {
      return {
        ...state,
        transferList: action.payload,
      };
    },
  },
};
SftpModel;
export default SftpModel;
