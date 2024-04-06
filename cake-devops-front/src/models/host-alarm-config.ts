// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/host-alarm-config";
import { message } from "antd";
import { BaseAction } from "typings";

export interface HostAlarmConfigWrapperDTO {
  alarmConfigList: HostAlarmConfigDTO[];
  alarmGroupList: HostAlarmGroupDTO[];
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

export interface ConfigureHostAlarmConfigPayload {
  hostId: string;
  cpu: HostAlarmConfigDTO;
  memory: HostAlarmConfigDTO;
  groupIdList: number[];
}

export interface HostAlarmConfigModelState {
  hostAlarmConfig?: HostAlarmConfigWrapperDTO;
}

interface GetHostAlarmConfigAction extends BaseAction {
  type: "hostAlarmConfig/getConfig";
  payload: GetHostAlarmConfigPayload;
}

interface ConfigureHostAlarmConfigAction extends BaseAction {
  type: "hostAlarmConfig/configure";
  payload: ConfigureHostAlarmConfigPayload;
}

export interface HostAlarmConfigModelType {
  namespace: "hostAlarmConfig";
  state: HostAlarmConfigModelState;
  effects: {
    getConfig: Effect;
    configure: Effect;
  };
  reducers: {
    saveAlarmConfig: Reducer<HostAlarmConfigModelState>;
  };
}

const AlarmGroupModel: HostAlarmConfigModelType = {
  namespace: "hostAlarmConfig",
  state: {
    hostAlarmConfig: undefined,
  },
  effects: {
    *getConfig({ payload, callback }: GetHostAlarmConfigAction, { call, put }) {
      const response = yield call(api.getAlarmConfig, payload);
      yield put({
        type: "saveAlarmConfig",
        payload: response.content,
      });
    },

    *configure(
      { payload, callback }: ConfigureHostAlarmConfigAction,
      { call, put }
    ) {
      const response = yield call(api.configureAlarmConfig, payload);
      const { success, msg } = response;
      if (success) {
        message.success("配置成功");
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {
    saveAlarmConfig(state, action) {
      return {
        ...state,
        hostAlarmConfig: action.payload,
      };
    },
  },
};

export default AlarmGroupModel;
