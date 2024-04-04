// src/models/proxy.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/proxy";

export interface ProxyModel {
  id: number;
  proxyHost: string;
  proxyPort: number;
  proxyUsername: string;
  proxyPassword: string;
  proxyType: number;
  description: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface QueryProxiesPayload {
  pageNo: number;
  pageSize: number;
  proxyHost: string;
  proxyType: number;
}

export interface CreateProxyPayload {
  proxyHost: string;
  proxyPort: number;
  proxyUsername: string;
  proxyPassword: string;
  proxyType: number;
  description: string;
}

export interface DeleteProxyPayload {
  proxyId: number;
}

export interface UpdateProxyPayload {
  id: number;
  proxyHost?: string;
  proxyPort?: number;
  proxyUsername?: string;
  proxyPassword?: string;
  proxyType?: number;
  description?: string;
}

export interface ProxyModelState {
  proxies: ProxyModel[];
  total: number;
}

interface QueryProxiesAction {
  type: "proxy/queryProxies";
  payload: QueryProxiesPayload;
}

interface CreateProxyAction {
  type: "proxy/createProxy";
  payload: CreateProxyPayload;
}

interface UpdateProxyAction {
  type: "proxy/updateProxy";
  payload: UpdateProxyPayload;
}

interface DeleteProxyAction {
  type: "proxy/deleteProxy";
  payload: DeleteProxyPayload;
}

export interface ProxyModelType {
  namespace: "proxy";
  state: ProxyModelState;
  effects: {
    fetchProxies: Effect;
    createProxy: Effect;
    updateProxy: Effect;
    deleteProxy: Effect;
  };
  reducers: {
    saveProxies: Reducer<ProxyModelState>;
  };
}

const ProxyModel: ProxyModelType = {
  namespace: "proxy",

  state: {
    proxies: [],
    total: 0,
  },

  effects: {
    *queryProxies({ payload }, { call, put }) {
      const response = yield call(api.fetchProxies, payload);
      yield put({
        type: "saveProxies",
        payload: response.content,
      });
    },

    *createProxy({ payload }, { call, put }) {
      yield call(api.createProxy, payload);
      yield put({ type: "fetchProxies" });
    },

    *updateProxy({ payload }, { call, put }) {
      yield call(api.updateProxy, payload);
      yield put({ type: "fetchProxies" });
    },

    *deleteProxy({ payload }, { call, put }) {
      yield call(api.deleteProxy, payload);
      yield put({ type: "fetchProxies" });
    },
  },

  reducers: {
    saveProxies(state, action) {
      return {
        ...state,
        proxies: action.payload.items,
        total: action.payload.total,
      };
    },
  },
};

export default ProxyModel;
