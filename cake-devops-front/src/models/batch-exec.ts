// src/models/app.ts
import * as batchExecService from "@/services/batch-exec";
import { Effect, Reducer } from "umi";
import { AppAccountDTO } from "./user";
import { API, BaseAction } from "typings";
import { message } from "antd";

export interface CommandExecDTO {
  id: number;
  exitCode: number;
  status: number;
  used: number;
  keepTime: string;
}

export interface CommandExecState {}

export interface QueryCommandExecAction extends BaseAction {
  type: "commandExec/fetchCommandExec";
  payload: QueryCommandExecPayload;
}

export interface CreateCommandExecAction extends BaseAction {
  type: "commandExec/createCommandExec";
  payload: CreateCommandExecPayload;
}

export interface WriteAction extends BaseAction {
  type: "commandExec/write";
  payload: WriteCommandExecPayload;
}

export interface TerminalAction extends BaseAction {
  type: "commandExec/terminal";
  payload: TerminalCommandExecPayload;
}

export interface BatchDeleteCommandAction extends BaseAction {
  type: "commandExec/batchDelete";
  payload: BatchDeleteCommandExecPayload;
}

export interface BatchListStatusCommandAction extends BaseAction {
  type: "commandExec/batchListStatus";
  payload: BatchListStatusCommandAction;
}

export interface CreateCommandExecPayload {
  hostIds: string[];
  command: string;
  description: string;
}

export interface WriteCommandExecPayload {
  id: number;
  command: string;
}

export interface TerminalCommandExecPayload {
  id: number;
}

export interface BatchDeleteCommandExecPayload {
  idList: number[];
}

export interface BatchListStatusCommandExecPayload {
  idList: number[];
}

export interface QueryCommandExecPayload {
  host: string;
  execStatus: number;
  execType: number;
  pageNo: number;
  pageSize: number;
  accountId: string;
  hostIds: string[];
}

const CommandExecModel = {
  namespace: "commandExec",
  state: {},
  effects: {
    *fetchCommandExec(
      { callback, payload }: QueryCommandExecAction,
      { call, put }
    ) {
      const response: API.ResponseBody<API.Page<CommandExecDTO>> = yield call(
        batchExecService.fetchCommandExec,
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
    *createCommandExec(
      { callback, payload }: CreateCommandExecAction,
      { call, put }
    ) {
      const response: API.ResponseBody<boolean> = yield call(
        batchExecService.createCommandExec,
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

    *write({ callback, payload }: WriteAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        batchExecService.write,
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

    *terminal({ callback, payload }: TerminalAction, { call, put }) {
      const response: API.ResponseBody<boolean> = yield call(
        batchExecService.terminal,
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

    *listStatus(
      { callback, payload }: BatchListStatusCommandAction,
      { call, put }
    ) {
      const response: API.ResponseBody<boolean> = yield call(
        batchExecService.listStatus,
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

    *batchDelete(
      { callback, payload }: BatchDeleteCommandAction,
      { call, put }
    ) {
      const response: API.ResponseBody<boolean> = yield call(
        batchExecService.batchDelete,
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

export default CommandExecModel;
