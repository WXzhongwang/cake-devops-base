// src/models/app.ts
import * as logService from "@/services/logs";
import { message } from "antd";
import { BaseAction } from "typings";
import { Effect, Reducer } from "umi";

// 应用信息
export interface UserEventLogDTO {
  id: string;
  execResult: number;
}

export interface UserEventLogState {}

interface QueryLogsAction extends BaseAction {
  type: "logs/queryLogs";
  payload: QueryLogPayload;
}

export interface QueryLogPayload {
  username: string;
  userId: string;
  startDate: Date;
  endDate: Date;
}

export interface UserEventLogModelType {
  namespace: "logs";
  state: UserEventLogState;
  effects: {
    queryLogs: Effect;
  };
  reducers: {};
}

const UserEventLogModel: UserEventLogModelType = {
  namespace: "logs",
  state: {
    clusterList: [],
  },
  effects: {
    *queryLogs({ payload, callback }: QueryLogsAction, { call, put }) {
      const response = yield call(logService.queryLogs, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
  },
  reducers: {},
};

export default UserEventLogModel;
