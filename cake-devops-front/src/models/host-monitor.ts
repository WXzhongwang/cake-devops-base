// src/models/host.ts

import { Effect } from "umi";
import * as api from "@/services/host-monitor";
import { message } from "antd";
import { API, BaseAction } from "typings";

export interface HostMonitorDTO {
  hostId: string;
  monitorStatus: number;
  monitorUrl: string;
  accessToken: string;
  agentVersion: string;
  host: HostInfoModel;
}

export interface HostInfoModel {
  hostId: string;
  name: string;
  hostName: string;
  serverAddr: string;
  port: number;
  authType: number;
  username: string;
  pwd: string;
  status: string;
  proxyId: string;
  keyId: string;
}

export interface SystemProcessVO {
  pid: number;
  name: string;
  user: string;
  cpuUsage: number;
  memoryUsage: string;
  openFile: number;
  uptime: string;
  commandLine: string;
}

export interface DiskNameVO {
  name: string;
  seq: string;
}

export interface OSInfoVO {
  pid: string;
  hostname: string;
  cpuLogicalCore: number;
  totalMemory: string;
  osName: string;
  cpuName: string;
  cpuPhysicalCore: number;
  uptime: string;
  username: string;
}

export interface BaseMetricVO {
  status: boolean;
  version: string;
  isWindows: boolean;
  load: LoadVO;
  disks: DiskStatVO[];
  machineId: string;
  processes: SystemProcessVO[];
}

export interface DiskStatVO {
  usageSpace: string;
  freeSpace: string;
  usage: string;
  name: string;
  totalSpace: string;
}

export interface LoadVO {
  fifteenMinuteLoad: number;
  oneMinuteLoad: number;
  fiveMinuteLoad: number;
}

export interface TimeMetricVO {
  time: number;
  value: number;
}

export interface MetricVO {
  avg: number;
  min: number;
  max: number;
  metrics: TimeMetricVO[];
}

export interface CpuStatisticsVO {
  usage: MetricVO;
}

export interface MemoryStatisticsVO {
  usage: MetricVO;
  size: MetricVO;
}

export interface DiskStatisticsVO {
  writeSpeed: MetricVO;
  readSpeed: MetricVO;
  writeCount: MetricVO;
  usageTime: MetricVO;
  readCount: MetricVO;
}

export interface NetStatisticsVO {
  sentSpeed: MetricVO;
  sentPacket: MetricVO;
  recvSpeed: MetricVO;
  recvPacket: MetricVO;
}

export interface QueryHostMonitorPayload {
  name: string;
  hostName: string;
  pageNo: number;
  pageSize: number;
}

export interface UpdateHostMonitorPayload {
  hostId: string;
  url: string;
  accessToken: string;
}

export interface TestConnectionPayload {
  url: string;
  accessToken: string;
}

export interface SyncMonitorAgentPayload {
  hostId: string;
  url: string;
  accessToken: string;
}

export interface PingPayload {
  hostId: string;
}

export interface LoadPayload {
  hostId: string;
}

export interface CheckStatusPayload {
  hostId: string;
}

export interface InstallPayload {
  hostId: string;
  upgrade: boolean;
}

interface PingAction extends BaseAction {
  type: "hostMonitor/sendPing";
  payload: PingPayload;
}

interface GetDiskNameAction extends BaseAction {
  type: "hostMonitor/getDiskName";
  payload: LoadPayload;
}

interface LoadAction extends BaseAction {
  type: "hostMonitor/load";
  payload: LoadPayload;
}

interface TopAction extends BaseAction {
  type: "hostMonitor/top";
  payload: LoadPayload;
}

interface MetricsAction extends BaseAction {
  type: "hostMonitor/metrics";
  payload: LoadPayload;
}
interface DiskStatisticsAction extends BaseAction {
  type: "hostMonitor/getDiskStatistics";
  payload: LoadPayload;
}

interface CpuStatisticsAction extends BaseAction {
  type: "hostMonitor/getCpuStatistics";
  payload: LoadPayload;
}

interface MemoryStatisticsAction extends BaseAction {
  type: "hostMonitor/getMemoryStatistics";
  payload: LoadPayload;
}

interface NetStatisticsAction extends BaseAction {
  type: "hostMonitor/getNetStatistics";
  payload: LoadPayload;
}

interface QueryHostMonitorAction extends BaseAction {
  type: "hostMonitor/fetch";
  payload: QueryHostMonitorPayload;
}

interface UpdateConfigAction extends BaseAction {
  type: "hostMonitor/update";
  payload: UpdateHostMonitorPayload;
}

interface TestConnectAction extends BaseAction {
  type: "hostMonitor/testConnect";
  payload: TestConnectionPayload;
}

interface InstallHostMonitorAction extends BaseAction {
  type: "hostMonitor/install";
  payload: InstallPayload;
}

interface SyncHostMonitorAction extends BaseAction {
  type: "hostMonitor/sync";
  payload: SyncMonitorAgentPayload;
}

interface CheckStatusAction extends BaseAction {
  type: "hostMonitor/checkStatus";
  payload: CheckStatusPayload;
}

export interface HostMonitorModelType {
  namespace: "hostMonitor";
  effects: {
    fetch: Effect;
    update: Effect;
    testConnect: Effect;
    install: Effect;
    sync: Effect;
    checkStatus: Effect;

    sendPing: Effect;
    metrics: Effect;
    load: Effect;
    top: Effect;
    getDiskName: Effect;
    getCpuStatistics: Effect;
    getMemoryStatistics: Effect;
    getNetStatistics: Effect;
    getDiskStatistics: Effect;
  };
}

const HostMonitorModel: HostMonitorModelType = {
  namespace: "hostMonitor",
  state: {},

  effects: {
    *fetch({ payload, callback }: QueryHostMonitorAction, { call, put }) {
      // 调用 API 获取主机数据
      const response: API.ResponseBody<API.Page<HostMonitorDTO>> = yield call(
        api.fetch,
        payload
      );
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *update({ payload, callback }: UpdateConfigAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.update,
        payload
      );
      const { success, msg } = response;
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },

    *testConnect({ payload, callback }: TestConnectAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.testConnect,
        payload
      );
      const { success, msg } = response;
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },

    *install({ payload, callback }: InstallHostMonitorAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.install,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *sync({ payload, callback }: SyncHostMonitorAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(api.sync, payload);
      const { success, msg } = response;
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *checkStatus({ payload, callback }: CheckStatusAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.checkStatus,
        payload
      );
      const { success, msg } = response;
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *sendPing({ payload, callback }: PingAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.sendPing,
        payload
      );
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *load({ payload, callback }: LoadAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.load, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *top({ payload, callback }: TopAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.top, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *metrics({ payload, callback }: MetricsAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<BaseMetricVO> = yield call(
        api.metrics,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getDiskName({ payload, callback }: GetDiskNameAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<DiskNameVO[]> = yield call(
        api.getDiskName,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getDiskStatistics(
      { payload, callback }: DiskStatisticsAction,
      { call, put }
    ) {
      // 调用 API 创建主机
      const response: API.ResponseBody<DiskStatisticsVO> = yield call(
        api.getDiskStatistics,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getCpuStatistics(
      { payload, callback }: CpuStatisticsAction,
      { call, put }
    ) {
      // 调用 API 创建主机
      const response: API.ResponseBody<CpuStatisticsVO> = yield call(
        api.getCpuStatistics,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getMemoryStatistics(
      { payload, callback }: MemoryStatisticsAction,
      { call, put }
    ) {
      // 调用 API 创建主机
      const response: API.ResponseBody<MemoryStatisticsVO> = yield call(
        api.getMemoryStatistics,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getNetStatistics(
      { payload, callback }: NetStatisticsAction,
      { call, put }
    ) {
      // 调用 API 创建主机
      const response: API.ResponseBody<NetStatisticsVO> = yield call(
        api.getNetStatistics,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
  },
};

export default HostMonitorModel;
