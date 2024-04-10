// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/script-template";
import { BaseAction } from "typings";
import { message } from "antd";

export interface ScriptTemplateDTO {
  id: number;
  templateName: string;
  templateValue: string;
  description: string;
  gmtCreate: Date;
  gmtModified: Date;
}

export interface QueryScriptPayload {
  pageNo: number;
  pageSize: number;
  templateName: string;
}

export interface CreateScriptPayload {
  templateName: string;
  templateValue: string;
  description: string;
}

export interface DeleteScriptPayload {
  id: number;
}

export interface UpdateScriptPayload extends CreateScriptPayload {
  id: number;
}

export interface ScriptTemplateModelState {
  scripts: ScriptTemplateDTO[];
  total: number;
}

interface QueryScriptAction {
  type: "script/queryScripts";
  payload: QueryScriptPayload;
}

interface CreateScriptAction extends BaseAction {
  type: "script/createScript";
  payload: CreateScriptPayload;
}

interface UpdateScriptAction extends BaseAction {
  type: "script/updateScript";
  payload: UpdateScriptPayload;
}

interface DeleteScriptAction extends BaseAction {
  type: "script/deleteScript";
  payload: DeleteScriptPayload;
}

export interface ScriptTemplateModelType {
  namespace: "script";
  state: ScriptTemplateModelState;
  effects: {
    queryScripts: Effect;
    createScript: Effect;
    updateScript: Effect;
    deleteScript: Effect;
  };
  reducers: {
    saveScripts: Reducer<ScriptTemplateModelType>;
  };
}

const ScriptTemplateModel: ScriptTemplateModelType = {
  namespace: "script",
  state: {
    scripts: [],
    total: 0,
  },

  effects: {
    *queryScripts({ payload }: QueryScriptAction, { call, put }) {
      const response = yield call(api.fetchScripts, payload);
      yield put({
        type: "saveScripts",
        payload: response.content,
      });
    },

    *createScript({ payload, callback }: CreateScriptAction, { call, put }) {
      const response = yield call(api.createScript, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *updateScript({ payload, callback }: UpdateScriptAction, { call, put }) {
      const response = yield call(api.updateScript, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback();
      } else {
        message.error(msg);
      }
    },

    *deleteScript({ payload, callback }: DeleteScriptAction, { call, put }) {
      const response = yield call(api.deleteScript, payload);
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

  reducers: {
    saveScripts(state, action) {
      return {
        ...state,
        scripts: action.payload.items,
        total: action.payload.total,
      };
    },
  },
};

export default ScriptTemplateModel;
