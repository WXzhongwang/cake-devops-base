// src/models/proxy.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/proxy";
import { API, BaseAction } from "typings";
import { message } from "antd";

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

interface QueryProxiesAction extends BaseAction {
  type: "proxy/queryProxies";
  payload: QueryProxiesPayload;
}

interface CreateProxyAction extends BaseAction {
  type: "proxy/createProxy";
  payload: CreateProxyPayload;
}

interface UpdateProxyAction extends BaseAction {
  type: "proxy/updateProxy";
  payload: UpdateProxyPayload;
}

interface DeleteProxyAction extends BaseAction {
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
    // saveProxies: Reducer<ProxyModelState>;
  };
}

const ProxyModel: ProxyModelType = {
  namespace: "proxy",

  state: {
    proxies: [],
    total: 0,
  },

  effects: {
    *queryProxies({ payload, callback }: QueryProxiesAction, { call, put }) {
      const response: API.ResponseBody<API.Page<ProxyModel>> = yield call(
        api.fetchProxies,
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

    *createProxy({ payload, callback }: CreateProxyAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.createProxy,
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

    *updateProxy({ payload, callback }: UpdateProxyAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.updateProxy,
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

    *deleteProxy({ payload, callback }: DeleteProxyAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.deleteProxy,
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
  },

  reducers: {},
};

export default ProxyModel;
