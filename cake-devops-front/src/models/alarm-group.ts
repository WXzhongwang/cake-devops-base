// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/alarm-group";
import { message } from "antd";
import { API, BaseAction } from "typings";

export interface AlarmGroupDTO {
  id: number;
  groupName: string;
  groupDescription: string;
  users: AlarmGroupUserDTO[];
  notifies: AlarmGroupNotifyDTO[];
  gmtCreate: Date;
  gmtModified: Date;
}

export interface AlarmGroupUserDTO {
  id: number;
  groupId: number;
  accountId: string;
  username?: string;
}

export interface AlarmGroupNotifyDTO {
  id: number;
  notifyId: number;
  notifyType: number;
  webHookName?: string;
}

export interface QueryAlarmGroupPayload {
  groupName: string;
  pageNo: number;
  pageSize: number;
}

export interface CreateAlarmGroupPayload {
  groupName: string;
  groupDescription: string;
  accountIds: string[];
  notifyIdList: number[];
}

export interface DeleteAlarmGroupPayload {
  alarmGroupId: number;
}

export interface UpdateAlarmGroupPayload extends CreateAlarmGroupPayload {
  alarmGroupId: number;
}

export interface AlarmGroupModelState {
  alarmGroups: AlarmGroupDTO[];
  total: number;
}

interface QueryAlarmGroupAction extends BaseAction {
  type: "alarmGroup/fetchAlarmGroups";
  payload: QueryAlarmGroupPayload;
}

interface CreateAlarmGroupAction extends BaseAction {
  type: "alarmGroup/create";
  payload: CreateAlarmGroupPayload;
}

interface UpdateAlarmGroupAction extends BaseAction {
  type: "alarmGroup/update";
  payload: UpdateAlarmGroupPayload;
}

interface DeleteAlarmGroupAction extends BaseAction {
  type: "alarmGroup/delete";
  payload: DeleteAlarmGroupPayload;
}

export interface AlarmGroupModelType {
  namespace: "alarmGroup";
  state: AlarmGroupModelState;
  effects: {
    fetchAlarmGroups: Effect;
    create: Effect;
    update: Effect;
    delete: Effect;
  };
  reducers: {
    saveAlarmGroup: Reducer<AlarmGroupModelState>;
  };
}

const AlarmGroupModel: AlarmGroupModelType = {
  namespace: "alarmGroup",

  state: {
    alarmGroups: [],
    total: 0,
  },

  effects: {
    *fetchAlarmGroups(
      { payload, callback }: QueryAlarmGroupAction,
      { call, put }
    ) {
      const response: API.ResponseBody<API.Page<AlarmGroupDTO>> = yield call(
        api.fetchAlarmGroup,
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

    *create({ payload, callback }: CreateAlarmGroupAction, { call, put }) {
      const response = yield call(api.createAlarmGroup, payload);
      const { success, msg } = response;
      if (success) {
        message.success("创建成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *update({ payload, callback }: UpdateAlarmGroupAction, { call, put }) {
      const response = yield call(api.updateAlarmGroup, payload);
      const { success, msg } = response;
      if (success) {
        message.success("更新成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *delete({ payload, callback }: DeleteAlarmGroupAction, { call, put }) {
      const response = yield call(api.deleteAlarmGroup, payload);
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
    saveAlarmGroup(state, action) {
      return {
        ...state,
        alarmGroups: action.payload.items,
        total: action.payload.total,
      };
    },
  },
};

export default AlarmGroupModel;
