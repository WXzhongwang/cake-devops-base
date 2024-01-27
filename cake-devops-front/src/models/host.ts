// src/models/host.ts

import { Effect, Reducer, Subscription } from "umi";
import * as api from "@/services/host";

export interface ServerAccount {
  serverAccountId: string;
  hostId: string;
  authMode: string;
  username: string;
  displayName: string;
  accountType: number;
  protocol: string;
  active: boolean;
  credential: string;
  publicKey: string;
  passphrase: string;
}

export interface CreateServerAccountPayload {
  hostId: string;
  authMode: string;
  username: string;
  displayName: string;
  accountType: number;
  protocol: string;
  active: boolean;
  credential: string;
  publicKey: string;
  passphrase: string;
}

export interface DeleteServerAccountPayload {
  serverAccountId: string;
}

export interface QueryServerAccountsPayload {
  pageNo: number;
  pageSize: number;
  authMode: string;
  username: string;
  accountType: string;
  protocol: string;
  active: string;
}

export interface UpdateServerAccountPayload {
  hostAccountId: string;
  authMode?: string;
  username?: string;
  displayName?: string;
  accountType?: number;
  protocol?: string;
  active?: boolean;
  credential?: string;
  publicKey?: string;
  passphrase?: string;
}

export interface HostModel {
  hostId: string;
  name: string;
  hostname: string;
  port: number;
  username: string;
  pkey: string;
  status: string;
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
  username: string;
  pkey: string;
}

export interface UpdateHostPayload {
  hostId: string;
  name?: string;
  hostName?: string;
  serverAddr?: string;
  port?: number;
  username?: string;
  pkey?: string;
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
  serverAccounts: ServerAccount[];
  serverAccountTotal: number;
}

interface QueryHostAction {
  type: "host/fetchHosts";
  payload: QueryHostPayload;
}

interface CreateServerAccountAction {
  type: "host/createServerAccount";
  payload: CreateServerAccountPayload;
}

interface UpdateServerAccountAction {
  type: "host/updateServerAccount";
  payload: UpdateServerAccountPayload;
}

interface DeleteServerAccountAction {
  type: "host/deleteServerAccount";
  payload: DeleteServerAccountPayload;
}

interface QueryServerAccountsAction {
  type: "host/queryServerAccounts";
  payload: QueryServerAccountsPayload;
}

export interface HostModelType {
  namespace: "host";
  state: HostModelState;
  effects: {
    fetchHosts: Effect;
    createHost: Effect;
    updateHost: Effect;
    fetchHostGroups: Effect;
    createHostGroup: Effect;
    updateHostGroup: Effect;
    createServerAccount: Effect;
    updateServerAccount: Effect;
    deleteServerAccount: Effect;
    queryServerAccounts: Effect;
  };
  reducers: {
    saveHosts: Reducer<HostModelState>;
    saveHostGroups: Reducer<HostModelState>;
    saveServerAccounts: Reducer<HostModelState>;
  };
}

const HostModel: HostModelType = {
  namespace: "host",

  state: {
    hosts: [],
    total: 0,
    hostGroups: [],
    hostAccounts: [],
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

    *createHost({ payload }, { call, put }) {
      // 调用 API 创建主机
      yield call(api.createHost, payload);
      // 创建成功后重新获取主机数据
      yield put({ type: "fetchHosts" });
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

    *createServerAccount({ payload }, { call, put }) {
      // 调用 API 创建主机账号
      yield call(api.createServerAccount, payload);
      // 创建成功后重新获取主机账号数据
      yield put({ type: "queryServerAccounts" });
    },

    *updateServerAccount({ payload }, { call, put }) {
      // 调用 API 更新主机账号
      yield call(api.updateServerAccount, payload);
      // 更新成功后重新获取主机账号数据
      yield put({ type: "queryServerAccounts" });
    },

    *deleteServerAccount({ payload }, { call, put }) {
      // 调用 API 删除主机账号
      yield call(api.deleteServerAccount, payload);
      // 删除成功后重新获取主机账号数据
      yield put({
        type: "queryServerAccounts",
        payload: { pageNo: 1, pageSize: 10 },
      });
    },

    *queryServerAccounts({ payload }, { call, put }) {
      // 调用 API 分页查询主机账号
      const response = yield call(api.queryServerAccounts, payload);
      // 触发保存主机账号数据的 reducer
      yield put({ type: "saveServerAccounts", payload: response.content });
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
    saveServerAccounts(state, action) {
      return {
        ...state,
        serverAccounts: action.payload.items,
        serverAccountTotal: action.payload.total,
      };
    },
  },
};

export default HostModel;
