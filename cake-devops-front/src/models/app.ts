// src/models/app.ts
import * as appService from "@/services/app";
import { Effect, Reducer } from "umi";

// 定义创建应用的参数类型
export interface CreateAppPayload {
  appName: string;
  description: string;
  repo: string;
  defaultBranch: string;
  language: string;
  developMode: string;
  owner: number;
  departmentAbbreviation: string;
  department: string;
  healthCheck: string;
}

export interface CreateAppEnvPayload {
  appId: string;
  env: AppEnv;
}

export interface QueryAppPayload {
  appName: string;
  language: string;
  department: string;
  pageNo: number;
  pageSize: number;
}

// 应用信息
export interface AppInfo {
  id: string;
  appName: string;
  repo: string;
  defaultBranch: string;
  departmentAbbreviation: string;
  department: string;
  language: string;
  description: string;
  developMode: string;
  owner: string;
  healthCheck: string;
  gmtCreate: number;
  gmtModified: number;
  appEnvList: AppEnv[];
}

export interface AppEnv {
  id: string | null;
  appId: string | null;
  clusterId: string | null;
  env: string;
  envName: string;
  domains: string[];
  resourceStrategy: ResourceStrategyDTO;
  configMap: Record<string, string>;
  autoScaling: boolean;
  needApproval: boolean;
  status: string;
}

export interface ResourceStrategyDTO {
  replicas: number;
  cpu: string;
  memory: string;
  maxCpu: string;
  maxMemory: string;
}

export interface AppState {
  appList: {
    total: number;
    list: AppInfo[];
  };
  appDetail: AppInfo | null;
}

interface CreateAppAction {
  type: "app/createApp";
  payload: CreateAppPayload;
}

interface CreateAppEnvAction {
  type: "app/createAppEnv";
  payload: CreateAppEnvPayload;
}

interface QueryAppAction {
  type: "app/getAppList";
  payload: QueryAppPayload;
}

interface GetAppDetailAction {
  type: "app/getAppDetail";
  payload: { id: number };
}

export interface AppModelType {
  namespace: "app";
  state: AppState;
  effects: {
    getAppList: Effect;
    createApp: Effect;
  };
  reducers: {
    setAppList: Reducer<AppState>;
  };
}

const AppModel: AppModelType = {
  namespace: "app",
  state: {
    appDetail: null,
    appList: {
      total: 0,
      list: [],
    },
  },
  effects: {
    *getAppList({ payload }: QueryAppAction, { call, put }) {
      const response = yield call(appService.pageAppList, payload);
      console.log(response);

      if (response?.content) {
        yield put({
          type: "setAppList",
          payload: {
            list: response.content.items,
            total: response.content.total,
          },
        });
      }
    },
    *createApp({ payload }: CreateAppAction, { call, put }) {
      yield call(appService.createApp, payload);
      yield put({ type: "getAppList" });
    },

    *createAppEnv({ payload }: CreateAppEnvAction, { call, put }) {
      yield call(appService.createAppEnv, payload);
      yield put({ type: "getAppDetail" });
    },

    *getAppDetail({ payload }: GetAppDetailAction, { call, put }) {
      const response = yield call(appService.getAppDetail, payload.id);
      yield put({
        type: "setAppDetail",
        payload: response.content,
      });
    },
  },
  reducers: {
    setAppList(state, action) {
      return { ...state, appList: { ...state.appList, ...action.payload } };
    },
    setAppDetail(state, action) {
      return { ...state, appDetail: action.payload };
    },
  },
};

export default AppModel;
