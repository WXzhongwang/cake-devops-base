// src/models/host.ts

import { Effect, Reducer, Subscription } from "umi";
import * as api from "@/services/host";

export interface HostModel {
  hostId: string;
  name: string;
  hostname: string;
  port: number;
  username: string;
  pkey: string;
}

export interface QueryHostPayload {
  hostGroupsIds?: string[];
  pageNo: number;
  pageSize: number;
}

export interface CreateHostPayload {
  name: string;
  hostname: string;
  port: number;
  username: string;
  pkey: string;
}

export interface UpdateHostPayload {
  hostId: string;
  name?: string;
  hostname?: string;
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
}

interface QueryHostAction {
  type: "host/fetchHosts";
  payload: QueryHostPayload;
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
  };
  reducers: {
    saveHosts: Reducer<HostModelState>;
    saveHostGroups: Reducer<HostModelState>;
  };
}

const HostModel: HostModelType = {
  namespace: "host",

  state: {
    hosts: [],
    total: 0,
    hostGroups: [],
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
  },
};

export default HostModel;
