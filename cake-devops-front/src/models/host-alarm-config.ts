// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/host-alarm-config";
import { message } from "antd";
import { BaseAction } from "typings";

export interface AlarmInfo {
  id: number;
  alarmType: number;
  alarmValue: number;
  alarmTime: Date;
}

export interface HostAlarmConfigWrapperDTO {
  alarmConfigList: HostAlarmConfigDTO[];
  alarmGroupList: HostAlarmGroupDTO[];
}

export interface AlarmConfigDTO {
  hostId: string;
  cpu: HostAlarmConfigDTO;
  memory: HostAlarmConfigDTO;
  groupIdList: number[];
}

export interface HostAlarmConfigDTO {
  hostId: string;
  alarmType: number;
  alarmThreshold: number;
  triggerThreshold: number;
  notifySilence: number;
}

export interface HostAlarmGroupDTO {
  hostId: string;
  alarmGroupId: number;
}

export interface GetHostAlarmConfigPayload {
  hostId: string;
}

export interface PageHostAlarmHistoryPayload {
  hostId: string;
  startDate: Date;
  alarmType: number;
  endDate: Date;
  minValue: number;
  maxValue: number;
  pageNo: number;
  pageSize: number;
}

export interface ConfigureHostAlarmConfigPayload {
  hostId: string;
  cpu: HostAlarmConfigDTO;
  memory: HostAlarmConfigDTO;
  groupIdList: number[];
}

export interface HostAlarmConfigModelState {
  hostAlarmConfig?: HostAlarmConfigWrapperDTO;
  alarms: AlarmInfo[];
  alarmTotal: number;
}

interface GetHostAlarmConfigAction extends BaseAction {
  type: "hostAlarmConfig/getConfig";
  payload: GetHostAlarmConfigPayload;
}

interface ConfigureHostAlarmConfigAction extends BaseAction {
  type: "hostAlarmConfig/configure";
  payload: ConfigureHostAlarmConfigPayload;
}

interface PageHostAlarmHistoryAction extends BaseAction {
  type: "hostAlarmConfig/pageAlarms";
  payload: PageHostAlarmHistoryPayload;
}

export interface HostAlarmConfigModelType {
  namespace: "hostAlarmConfig";
  state: HostAlarmConfigModelState;
  effects: {
    getConfig: Effect;
    configure: Effect;
    pageAlarms: Effect;
  };
  reducers: {
    saveAlarmConfig: Reducer<HostAlarmConfigModelState>;
    saveAlarms: Reducer<HostAlarmConfigModelState>;
  };
}

const AlarmGroupModel: HostAlarmConfigModelType = {
  namespace: "hostAlarmConfig",
  state: {
    hostAlarmConfig: undefined,
    alarms: [],
    alarmTotal: 0,
  },
  effects: {
    *getConfig({ payload, callback }: GetHostAlarmConfigAction, { call, put }) {
      const response = yield call(api.getAlarmConfig, payload);
      const { success, msg } = response;
      if (success) {
        yield put({
          type: "saveAlarmConfig",
          payload: response.content,
        });
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *configure(
      { payload, callback }: ConfigureHostAlarmConfigAction,
      { call, put }
    ) {
      const response = yield call(api.configureAlarmConfig, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *pageAlarms({ payload }: PageHostAlarmHistoryAction, { call, put }) {
      // 调用 API 获取数据
      const response = yield call(api.pageAlarms, payload);
      console.log(response.content);
      yield put({
        type: "saveAlarms",
        payload: response.content,
      });
    },
  },

  reducers: {
    saveAlarmConfig(state, action) {
      return {
        ...state,
        hostAlarmConfig: action.payload,
      };
    },
    saveAlarms(state, action) {
      return {
        ...state,
        alarms: action.payload.items,
        alarmTotal: action.payload.total,
      };
    },
  },
};

export default AlarmGroupModel;
