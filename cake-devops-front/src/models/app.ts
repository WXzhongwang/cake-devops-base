// src/models/app.ts
import * as appService from "@/services/app";
import { Effect, Reducer, ConnectProps } from "umi";

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
  // status: string;
  // isDeleted: string;
  // gmtCreate: Date;
  // gmtModified: Date;
  // creator: string;
  // modifier: string;
}

export interface AppState {
  appList: {
    total: number;
    list: AppInfo[];
  };
}

interface CreateAppAction {
  type: "app/createApp";
  payload: CreateAppPayload;
}

interface QueryAppAction {
  type: "app/getAppList";
  payload: QueryAppPayload;
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
    appList: {
      total: 0,
      list: [],
    },
  },
  effects: {
    *getAppList({ payload }: QueryAppAction, { call, put }) {
      const response = yield call(appService.pageAppList, payload);
      console.log("acc", response);
      yield put({
        type: "setAppList",
        payload: {
          list: response.content.items,
          total: response.content.total,
        },
      });
    },
    *createApp({ payload }: CreateAppAction, { call, put }) {
      yield call(appService.createApp, payload);
      yield put({ type: "getAppList" });
    },
  },
  reducers: {
    setAppList(state, action) {
      console.log("acc", action);
      return { ...state, appList: { ...state.appList, ...action.payload } };
    },
  },
};

export default AppModel;
