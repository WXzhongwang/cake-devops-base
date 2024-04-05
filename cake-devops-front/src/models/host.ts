// src/models/host.ts

import { Effect, Reducer, Subscription } from "umi";
import * as api from "@/services/host";
import { message } from "antd";

export interface ServerKey {
  id: number;
  displayName: string;
  accountType: number;
  protocol: string;
  active: string;
  credential: string;
  publicKey: string;
  passphrase: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface BaseAction {
  callback?: () => void;
}

export interface CreateServerKeyPayload {
  displayName: string;
  accountType: number;
  protocol: string;
  active: string;
  credential: string;
  publicKey: string;
  passphrase: string;
}

export interface DeleteServerKeyPayload {
  serverKeyId: string;
}

export interface QueryServerKeysPayload {
  pageNo: number;
  pageSize: number;
  displayname: string;
  active: number;
  accountType: number;
}

export interface UpdateServerKeyPayload {
  id: string;
  displayName?: string;
  accountType?: number;
  protocol?: string;
  active?: string;
  credential?: string;
  publicKey?: string;
  passphrase?: string;
}

export interface HostModel {
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

export interface QueryHostPayload {
  hostGroupsIds?: string[];
  name: string;
  hostName: string;
  pageNo: number;
  pageSize: number;
}

export interface CreateHostPayload {
  name: string;
  hostName: string;
  serverAddr: string;
  port: number;
  authType: number;
  username: string;
  pwd: string;
  proxyId: string;
  keyId: string;
}

export interface CopyHostPayload {
  hostId: string;
}

export interface DeleteHostPayload {
  hostId: string;
}

export interface PingHostPayload {
  hostId: string;
}

export interface UpdateHostPayload {
  hostId: string;
  name?: string;
  hostName?: string;
  serverAddr?: string;
  port?: number;
  authType: number;
  username?: string;
  pwd?: string;
  proxyId: string;
  keyId: string;
}

export interface HostGroupModel {
  hostGroupId: string;
  name: string;
  parentId: string | null;
  sort: number;
  children?: HostGroupModel[]; // 树形结构，包含子节点
}

export interface CreateHostGroupPayload {
  name: string;
  parentId: string | null;
  sort: number;
}

export interface UpdateHostGroupPayload {
  hostGroupId: string;
  name?: string;
  parentId?: string | null;
  sort?: number;
}

export interface HostModelState {
  hosts: HostModel[];
  hostGroups: HostGroupModel[];
  total: number;
  serverKeys: ServerKey[];
  serverKeyTotal: number;
}

interface QueryHostAction {
  type: "host/fetchHosts";
  payload: QueryHostPayload;
}
interface CreateHostAction extends BaseAction {
  type: "host/createHost";
  payload: CreateHostPayload;
}

interface PingHostAction extends BaseAction {
  type: "host/pingHost";
  payload: PingHostPayload;
}

interface CopyHostAction extends BaseAction {
  type: "host/copyHost";
  payload: CopyHostPayload;
}
interface DeleteHostAction extends BaseAction {
  type: "host/deleteHost";
  payload: DeleteHostPayload;
}

interface CreateServerKeyAction {
  type: "host/createServerAccount";
  payload: CreateServerKeyPayload;
}

interface UpdateServerKeyAction {
  type: "host/updateServerAccount";
  payload: UpdateServerKeyPayload;
}

interface DeleteServerKeyAction {
  type: "host/deleteServerAccount";
  payload: DeleteServerKeyPayload;
}

interface QueryServerKeysAction {
  type: "host/queryServerAccounts";
  payload: QueryServerKeysPayload;
}

export interface HostModelType {
  namespace: "host";
  state: HostModelState;
  effects: {
    fetchHosts: Effect;
    createHost: Effect;
    updateHost: Effect;
    pingHost: Effect;
    copyHost: Effect;
    fetchHostGroups: Effect;
    createHostGroup: Effect;
    updateHostGroup: Effect;
    createServerKey: Effect;
    updateServerKey: Effect;
    deleteServerKey: Effect;
    queryServerKeys: Effect;
  };
  reducers: {
    saveHosts: Reducer<HostModelState>;
    saveHostGroups: Reducer<HostModelState>;
    saveServerKeys: Reducer<HostModelState>;
  };
}

const HostModel: HostModelType = {
  namespace: "host",

  state: {
    hosts: [],
    total: 0,
    hostGroups: [],
    serverKeys: [],
    serverKeyTotal: 0,
  },

  effects: {
    *fetchHosts({ payload }: QueryHostAction, { call, put }) {
      // 调用 API 获取主机数据
      const response = yield call(api.fetchHosts, payload);
      // 触发保存主机数据的 reducer
      yield put({
        type: "saveHosts",
        payload: response.content,
      });
    },

    *createHost({ payload, callback }: CreateHostAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.createHost, payload);
      const { success, msg } = response;
      // 创建成功后重新获取主机数据
      if (success) {
        yield put({ type: "fetchHosts" });
      } else {
        message.error(msg);
      }
    },

    *pingHost({ payload, callback }: PingHostAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.pingHost, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *copyHost({ payload, callback }: CopyHostAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.copyHost, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success) {
        // 更新成功后重新获取主机数据
        yield put({ type: "fetchHosts" });
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *deleteHost({ payload, callback }: DeleteHostAction, { call, put }) {
      // 调用 API 创建主机
      const response = yield call(api.deleteHost, payload);
      const { success, msg } = response;
      if (success) {
        // 更新成功后重新获取主机数据
        yield put({ type: "fetchHosts" });
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *updateHost({ payload }, { call, put }) {
      // 调用 API 更新主机
      yield call(api.updateHost, payload);
      // 更新成功后重新获取主机数据
      yield put({ type: "fetchHosts" });
    },

    *fetchHostGroups(_, { call, put }) {
      // 调用 API 获取主机分组数据
      const response = yield call(api.fetchHostGroups);
      // 触发保存主机分组数据的 reducer
      yield put({
        type: "saveHostGroups",
        payload: response.content,
      });
    },

    *createHostGroup({ payload }, { call, put }) {
      // 调用 API 创建主机分组
      yield call(api.createHostGroup, payload);
      // 创建成功后重新获取主机分组数据
      yield put({ type: "fetchHostGroups" });
    },

    *updateHostGroup({ payload }, { call, put }) {
      // 调用 API 更新主机分组
      yield call(api.updateHostGroup, payload);
      // 更新成功后重新获取主机分组数据
      yield put({ type: "fetchHostGroups" });
    },

    *createServerKey({ payload }, { call, put }) {
      // 调用 API 创建主机账号
      yield call(api.createServerKey, payload);
      // 创建成功后重新获取主机账号数据
      yield put({
        type: "queryServerKeys",
        payload: { pageNo: 1, pageSize: 10 },
      });
    },

    *updateServerKey({ payload }, { call, put }) {
      // 调用 API 更新主机账号
      yield call(api.updateServerKey, payload);
      // 更新成功后重新获取主机账号数据
      yield put({
        type: "queryServerKeys",
        payload: { pageNo: 1, pageSize: 10 },
      });
    },

    *deleteServerKey({ payload }, { call, put }) {
      // 调用 API 删除主机账号
      yield call(api.deleteServerKey, payload);
      // 删除成功后重新获取主机账号数据
      yield put({
        type: "queryServerKeys",
        payload: { pageNo: 1, pageSize: 10 },
      });
    },

    *queryServerKeys({ payload }, { call, put }) {
      // 调用 API 分页查询主机账号
      const response = yield call(api.queryServerKeys, payload);
      // 触发保存主机账号数据的 reducer
      yield put({ type: "saveServerKeys", payload: response.content });
    },
  },

  reducers: {
    saveHosts(state, action) {
      return {
        ...state,
        hosts: action.payload.items,
        total: action.payload.total,
      };
    },

    saveHostGroups(state, action) {
      return { ...state, hostGroups: action.payload };
    },
    saveServerKeys(state, action) {
      return {
        ...state,
        serverKeys: action.payload.items,
        serverKeyTotal: action.payload.total,
      };
    },
  },
};

export default HostModel;
