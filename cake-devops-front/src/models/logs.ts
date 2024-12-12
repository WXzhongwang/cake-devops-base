// src/models/app.ts
import * as logService from "@/services/logs";
import { message } from "antd";
import { API, BaseAction } from "typings";
import { Effect } from "umi";

// 应用信息
export interface UserEventLogDTO {
  id: string;
  userId: string;
  userName: string;
  eventClassify: number;
  eventClassifyName: string;
  eventType: number;
  eventTypeName: string;
  logInfo: string;
  paramsJson: string;
  gmtCreate: Date;
  execResult: number;
}

interface QueryLogsAction extends BaseAction {
  type: "logs/queryLogs";
  payload: QueryLogPayload;
}

interface QueryEventClassifyAction extends BaseAction {
  type: "logs/getEventClassify";
}

interface QueryEventClassifyAction extends BaseAction {
  type: "logs/getEventClassify";
  payload: GetEventTypePayload;
}

export interface GetEventTypePayload {
  classify: string;
}

export interface QueryLogPayload {
  username: string;
  userId: string;
  startDate: Date;
  endDate: Date;
}

export interface UserEventLogModelType {
  namespace: "logs";
  state: {};
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
      const response: API.ResponseBody<API.Page<UserEventLogDTO>> = yield call(
        logService.queryLogs,
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
    *getEventClassify({ callback }: QueryEventClassifyAction, { call, put }) {
      const response = yield call(logService.getEventClassify);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *getEventType(
      { callback, payload }: QueryEventClassifyAction,
      { call, put }
    ) {
      const response = yield call(logService.getEventType, payload);
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

export default UserEventLogModel;
