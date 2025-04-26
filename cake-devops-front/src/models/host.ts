// src/models/host.ts

import { Effect, Reducer, Subscription } from "umi";
import * as api from "@/services/host";
import { message } from "antd";
import { API, BaseAction } from "typings";

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

export interface HostTerminalConfig {
  id: number;
  hostId: string;
  terminalType: number;
  backgroundColor: string;
  fontColor: string;
  fontSize: number;
  fontFamily: string;
  enableWebLink: number;
}

export interface AccessTokenRes {
  id: number;
  hostId: string;
  terminalType: number;
  backgroundColor: string;
  fontColor: string;
  fontSize: number;
  fontFamily: string;
  enableWebLink: number;

  accessToken: string;
  terminalToken: string;
  hostName: string;
  host: string;
  port: number;
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
  proxyId: number;
  keyId: number;
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

interface QueryHostAction extends BaseAction {
  type: "host/fetchHosts";
  payload: QueryHostPayload;
}
interface CreateHostAction extends BaseAction {
  type: "host/createHost";
  payload: CreateHostPayload;
}

interface UpdateHostAction extends BaseAction {
  type: "host/updateHost";
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

interface CreateServerKeyAction extends BaseAction {
  type: "host/createServerAccount";
  payload: CreateServerKeyPayload;
}

interface UpdateServerKeyAction extends BaseAction {
  type: "host/updateServerAccount";
  payload: UpdateServerKeyPayload;
}

interface DeleteServerKeyAction extends BaseAction {
  type: "host/deleteServerAccount";
  payload: DeleteServerKeyPayload;
}

interface QueryServerKeysAction extends BaseAction {
  type: "host/queryServerAccounts";
  payload: QueryServerKeysPayload;
}

const HostModel = {
  namespace: "host",
  state: {},

  effects: {
    *fetchHosts({ payload, callback }: QueryHostAction, { call, put }) {
      // 调用 API 获取主机数据
      const response: API.ResponseBody<API.Page<HostModel>> = yield call(
        api.fetchHosts,
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

    *createHost({ payload, callback }: CreateHostAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.createHost,
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

    *pingHost({ payload, callback }: PingHostAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.pingHost,
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

    *copyHost({ payload, callback }: CopyHostAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.copyHost,
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

    *deleteHost({ payload, callback }: DeleteHostAction, { call, put }) {
      // 调用 API 创建主机
      const response: API.ResponseBody<boolean> = yield call(
        api.deleteHost,
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

    *updateHost({ payload, callback }: UpdateHostAction, { call, put }) {
      // 调用 API 更新主机
      const response: API.ResponseBody<boolean> = yield call(
        api.updateHost,
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

    *fetchHostGroups({ callback }, { call, put }) {
      // 调用 API 获取主机分组数据
      const response: API.ResponseBody<HostGroupModel[]> = yield call(
        api.fetchHostGroups
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

    *createHostGroup({ payload, callback }, { call, put }) {
      // 调用 API 创建主机分组
      const response: API.ResponseBody<boolean> = yield call(
        api.createHostGroup,
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

    *updateHostGroup({ payload, callback }, { call, put }) {
      // 调用 API 更新主机分组
      const response: API.ResponseBody<boolean> = yield call(
        api.updateHostGroup,
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

    *createServerKey(
      { payload, callback }: CreateServerKeyAction,
      { call, put }
    ) {
      // 调用 API 创建主机账号
      const response: API.ResponseBody<boolean> = yield call(
        api.createServerKey,
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

    *updateServerKey(
      { payload, callback }: UpdateServerKeyAction,
      { call, put }
    ) {
      // 调用 API 更新主机账号
      const response: API.ResponseBody<boolean> = yield call(
        api.updateServerKey,
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

    *deleteServerKey(
      { payload, callback }: DeleteServerKeyAction,
      { call, put }
    ) {
      // 调用 API 删除主机账号
      const response: API.ResponseBody<boolean> = yield call(
        api.deleteServerKey,
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

    *queryServerKeys(
      { payload, callback }: QueryServerKeysAction,
      { call, put }
    ) {
      // 调用 API 分页查询主机账号
      const response: API.ResponseBody<API.Page<ServerKey>> = yield call(
        api.queryServerKeys,
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

    *getTerminalAccessToken({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response: API.ResponseBody<string> = yield call(
        api.getTerminalAccessToken,
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
    *getTerminalConfig({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response = yield call(api.getConfig, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *updateTerminalConfig({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response = yield call(api.updateConfig, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *queryLog({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response = yield call(api.queryLog, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getSupportedPty({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response = yield call(api.getSupportedPty, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getScreenPath({ payload, callback }: any, { call, put }) {
      // 调用 API 更新主机
      const response = yield call(api.getScreenPath, payload);
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

export default HostModel;
