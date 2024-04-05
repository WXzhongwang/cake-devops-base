// src/models/host-env.ts

import { Effect, Reducer } from "umi";
import { message } from "antd";
import * as api from "@/services/host-env";

export interface BaseAction {
  callback?: () => void;
}

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

export interface HostEnvModelType {
  namespace: "hostEnv";
  state: HostEnvModelState;
  effects: {
    fetchVariables: Effect;
    addVariable: Effect;
    updateVariable: Effect;
    deleteVariable: Effect;
  };
  reducers: {
    saveVariables: Reducer<HostEnvModelState>;
  };
}

const HostEnvModel: HostEnvModelType = {
  namespace: "hostEnv",

  state: {
    hostEnvsTotal: 0,
    hostEnvs: [],
  },

  effects: {
    *fetchVariables({ payload }: FetchVariablesAction, { call, put }) {
      const response = yield call(api.fetchVariables, payload);
      // 触发保存主机数据的 reducer
      yield put({
        type: "saveVariables",
        payload: response.content,
      });
    },

    *addVariable({ payload, callback }: CreateVariablesAction, { call, put }) {
      const response = yield call(api.addVariable, payload);
      const { success, msg } = response;
      if (success) {
        message.success("添加成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *updateVariable(
      { payload, callback }: UpdateVariablesAction,
      { call, put }
    ) {
      const response = yield call(api.updateVariable, payload);
      const { success, msg } = response;
      if (success) {
        message.success("修改成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *deleteVariable(
      { payload, callback }: DeleteVariablesAction,
      { call, put }
    ) {
      const response = yield call(api.deleteVariable, payload);
      const { success, msg } = response;
      if (success) {
        message.success("删除成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {
    saveVariables(state, action) {
      return {
        ...state,
        hostEnvs: action.payload.items,
        hostEnvsTotal: action.payload.total,
      };
    },
  },
};

export default HostEnvModel;
