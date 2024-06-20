// src/models/host.ts

import { Effect, Reducer, Subscription } from "umi";
import * as api from "@/services/host-monitor";
import { message } from "antd";

export interface BaseAction {
  callback?: () => void;
}

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

export interface CheckStatusPayload {
  hostId: string;
}

export interface InstallPayload {
  hostId: string;
  upgrade: boolean;
}

export interface HostMonitorModelState {
  hosts: HostMonitorDTO[];
  total: number;
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
  state: HostMonitorModelState;
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
  reducers: {
    saveHostMonitors: Reducer<HostMonitorModelState>;
  };
}

const HostMonitorModel: HostMonitorModelType = {
  namespace: "hostMonitor",

  state: {
    hosts: [],
    total: 0,
  },

  effects: {
    *fetch({ payload }: QueryHostMonitorAction, { call, put }) {
      // 调用 API 获取主机数据
      const response = yield call(api.fetch, payload);
      // 触发保存主机数据的 reducer
      yield put({
        type: "saveHostMonitors",
        payload: response.content,
      });
    },

    *update({ payload, callback }: UpdateConfigAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.update, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *testConnect({ payload, callback }: TestConnectAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.testConnect, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *install({ payload, callback }: InstallHostMonitorAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.install, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *sync({ payload, callback }: SyncHostMonitorAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.sync, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
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
      const response = yield call(api.checkStatus, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *ping({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.sendPing, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *load({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.load, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *top({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.top, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *metrics({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.metrics, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getDiskName({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.getDiskName, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getDiskStatistics({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.getDiskStatistics, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getCpuStatistics({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.getCpuStatistics, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getMemoryStatistics({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.getMemoryStatistics, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *getNetStatistics({ payload, callback }, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.getNetStatistics, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {
    saveHostMonitors(state, action) {
      return {
        ...state,
        hosts: action.payload.items,
        total: action.payload.total,
      };
    },
  },
};

export default HostMonitorModel;
