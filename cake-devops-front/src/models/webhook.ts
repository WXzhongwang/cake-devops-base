// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/webhook";

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
  webhooks: WebhookConfig[];
  total: number;
}

interface QueryWebHooksAction {
  type: "webhook/queryWebHooks";
  payload: QueryWebHooksPayload;
}

interface CreateWebHookAction {
  type: "webhook/createWebHook";
  payload: CreateWebHookPayload;
}

interface UpdateWebHookAction {
  type: "webhook/updateWebHook";
  payload: UpdateWebHookPayload;
}

interface DeleteWebHookAction {
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
  reducers: {
    saveWebHooks: Reducer<WebHookModelState>;
  };
}

const WebHookModel: WebHookModelType = {
  namespace: "webhook",

  state: {
    webhooks: [],
    total: 0,
  },

  effects: {
    *queryWebHooks({ payload }, { call, put }) {
      const response = yield call(api.fetchWebhooks, payload);
      yield put({
        type: "saveWebHooks",
        payload: response.content,
      });
    },

    *createWebHook({ payload }, { call, put }) {
      yield call(api.createWebhook, payload);
      yield put({ type: "fetchWebHooks" });
    },

    *updateWebHook({ payload }, { call, put }) {
      yield call(api.updateWebhook, payload);
      yield put({ type: "fetchWebHooks" });
    },

    *deleteWebHook({ payload }, { call, put }) {
      yield call(api.deleteWebhook, payload);
      yield put({ type: "fetchWebHooks" });
    },
  },

  reducers: {
    saveWebHooks(state, action) {
      return {
        ...state,
        webhooks: action.payload.items,
        total: action.payload.total,
      };
    },
  },
};

export default WebHookModel;
