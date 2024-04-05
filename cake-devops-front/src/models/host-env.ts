// src/models/host-env.ts

import { Effect, Reducer } from "umi";
import { message } from "antd";
import * as api from "@/services/host-env";

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

interface FetchVariablesAction {
  type: "hostEnv/fetchVariables";
  payload: FetchVariablePayload;
}

interface CreateVariablesAction {
  type: "hostEnv/create";
  payload: CreateVariablePayload;
}

interface UpdateVariablesAction {
  type: "hostEnv/update";
  payload: UpdateVariablePayload;
}

interface DeleteVariablesAction {
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
    *fetchVariables({ payload }: FetchVariablePayload, { call, put }) {
      const response = yield call(api.fetchVariables, payload);
      // 触发保存主机数据的 reducer
      yield put({
        type: "saveVariables",
        payload: response.content,
      });
    },

    *addVariable({ payload }: CreateVariablePayload, { call, put }) {
      yield call(api.addVariable, payload);
      message.success("添加成功");
      yield put({ type: "fetchVariables" });
    },

    *updateVariable({ payload }: UpdateVariablePayload, { call, put }) {
      yield call(api.updateVariable, payload);
      message.success("修改成功");
      yield put({ type: "fetchVariables" });
    },

    *deleteVariable({ payload }: DeleteVariablePayload, { call, put }) {
      yield call(api.deleteVariable, payload);
      message.success("删除成功");
      yield put({ type: "fetchVariables" });
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
