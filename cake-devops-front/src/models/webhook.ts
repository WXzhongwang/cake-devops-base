// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/webhook";
import { API, BaseAction } from "typings";
import { message } from "antd";

export interface WebhookConfig {
  id: number;
  webhookName: string;
  webhookUrl: string;
  webhookType: string;
  webhookConfig: string;
}

export interface QueryWebHooksPayload {
  pageNo: number;
  pageSize: number;
}

export interface CreateWebHookPayload {
  webhookName: string;
  webhookUrl: string;
  webhookType: string;
  webhookConfig: string;
}

export interface DeleteWebHookPayload {
  id: number;
}

export interface UpdateWebHookPayload extends CreateWebHookPayload {
  id: number;
}

export interface WebHookModelState {
  // webhooks: WebhookConfig[];
  // total: number;
}

interface QueryWebHooksAction extends BaseAction {
  type: "webhook/queryWebHooks";
  payload: QueryWebHooksPayload;
}

interface CreateWebHookAction extends BaseAction {
  type: "webhook/createWebHook";
  payload: CreateWebHookPayload;
}

interface UpdateWebHookAction extends BaseAction {
  type: "webhook/updateWebHook";
  payload: UpdateWebHookPayload;
}

interface DeleteWebHookAction extends BaseAction {
  type: "webhook/deleteWebHook";
  payload: DeleteWebHookPayload;
}

export interface WebHookModelType {
  namespace: "webhook";
  state: WebHookModelState;
  effects: {
    fetchWebHooks: Effect;
    createWebHook: Effect;
    updateWebHook: Effect;
    deleteWebHook: Effect;
  };
  reducers: {};
}

const WebHookModel: WebHookModelType = {
  namespace: "webhook",

  state: {
    // webhooks: [],
    // total: 0,
  },

  effects: {
    *queryWebHooks({ payload, callback }: QueryWebHooksAction, { call, put }) {
      const response: API.ResponseBody<API.Page<WebhookConfig>> = yield call(
        api.fetchWebhooks,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },

    *createWebHook({ payload, callback }: CreateWebHookAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.createWebhook,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *updateWebHook({ payload, callback }: UpdateWebHookAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.updateWebhook,
        payload
      );
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *deleteWebHook({ payload, callback }: DeleteWebHookAction, { call, put }) {
      const response = yield call(api.deleteWebhook, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {},
};

export default WebHookModel;
