// src/models/host-env.ts

import { Effect, Reducer } from "umi";
import { message } from "antd";
import * as api from "@/services/host-env";
import { API, BaseAction } from "typings";

export interface HostEnvironmentVariable {
  id: string;
  attrKey: string;
  attrValue: string;
  description: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface FetchVariablePayload {
  hostId: string;
  attrKey: string;
  attrValue: string;
  pageNo: number;
  pageSize: number;
}

export interface CreateVariablePayload {
  attrKey: string;
  attrValue: string;
  description: string;
}

export interface UpdateVariablePayload {
  id: number;
  attrKey: string;
  attrValue: string;
  description: string;
}

export interface DeleteVariablePayload {
  id: number;
}

interface FetchVariablesAction extends BaseAction {
  type: "hostEnv/fetchVariables";
  payload: FetchVariablePayload;
}

interface CreateVariablesAction extends BaseAction {
  type: "hostEnv/create";
  payload: CreateVariablePayload;
}

interface UpdateVariablesAction extends BaseAction {
  type: "hostEnv/update";
  payload: UpdateVariablePayload;
}

interface DeleteVariablesAction extends BaseAction {
  type: "hostEnv/delete";
  payload: DeleteVariablePayload;
}

export interface HostEnvModelState {
  hostEnvs: HostEnvironmentVariable[];
  hostEnvsTotal: number;
}

const HostEnvModel = {
  namespace: "hostEnv",
  state: {},
  reducers: {},
  effects: {
    *fetchVariables(
      { payload, callback }: FetchVariablesAction,
      { call, put }
    ) {
      const response: API.ResponseBody<API.Page<HostEnvironmentVariable>> =
        yield call(api.fetchVariables, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *addVariable({ payload, callback }: CreateVariablesAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        api.addVariable,
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

    *updateVariable(
      { payload, callback }: UpdateVariablesAction,
      { call, put }
    ) {
      const response: API.ResponseBody<boolean> = yield call(
        api.updateVariable,
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

    *deleteVariable(
      { payload, callback }: DeleteVariablesAction,
      { call, put }
    ) {
      const response: API.ResponseBody<boolean> = yield call(
        api.deleteVariable,
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
};

export default HostEnvModel;
