// src/models/app.ts
import * as appService from "@/services/app";
import { Effect, Reducer } from "umi";
import { AppAccountDTO } from "./user";
import { BaseAction } from "typings";
import { message } from "antd";

// 定义创建应用的参数类型
export interface CreateAppPayload {
  appName: string;
  description: string;
  repo: string;
  defaultBranch: string;
  language: string;
  developMode: string;
  owner: string;
  departmentAbbreviation: string;
  department: string;
  healthCheck: string;
  codePlatform: string;
  appExtend: string;
}

export interface CreateAppEnvPayload {
  appId: string;
  env: AppEnv;
}

export interface ListAppBranchPayload {
  appId: string;
  search: string;
}

export interface ModifyAppEnvVarsPayload {
  envId: string;
  envVars: Record<string, string> | null;
}

export interface ModifyAppResourcesPayload {
  envId: string;
  resourceStrategyDTO: ResourceStrategyDTO;
}

export interface ScalePayload {
  envId: string;
  replicas: number;
}

export interface ModifyAppEnvConfigMapPayload {
  envId: string;
  configMap: Record<string, string> | null;
}

export interface QueryAppPayload {
  appName: string;
  language: string;
  department: string;
  pageNo: number;
  pageSize: number;
}
export interface QueryAppMemberPayload {
  appId: string;
  pageNo: number;
  pageSize: number;
}

export interface UpdateAppMemberPayload {
  memberId: string;
  roles: string[];
  status: string;
}

export interface DeleteAppMemberPayload {
  memberId: string;
}

export interface AddAppMemberPayload {
  accountId: string;
  appId: string;
  roles: string[];
}

// 应用信息
export interface AppInfo {
  appId: string;
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
  status: string;
}

export interface Department {
  label: string;
  value: string;
  abbr: string;
}

export interface BranchInfo {
  name: string;
  protectBranch: boolean;
}

export interface AppEnv {
  envId: string | null;
  appId: string | null;
  clusterId: string | null;
  env: string;
  envName: string;
  domains: string[];
  resourceStrategy: ResourceStrategyDTO;
  configMap: Record<string, string> | null;
  envVars: Record<string, string> | null;
  autoScaling: boolean;
  needApproval: boolean;
  status: string | null;
  deployStatus: string;
  progress: string;
  deployment: string;
  service: string;
  ingress: string;
}

export interface ServiceItem {
  serviceName: string;
  servicePort: number;
  containerPort: number;
  serviceProtocol: string;
  serviceType: string;
  nodePort?: number;
}

export interface AppMemberDTO {
  memberId: string;
  accountId: string;
  accountDTO: AppAccountDTO;
  roles: string[];
}

export interface ResourceStrategyDTO {
  replicas: number;
  cpu: string;
  memory: string;
  maxCpu: string;
  maxMemory: string;
}

export interface PodDTO {
  name: string;
  namespace: string;
  podIp: string;
  phase: string;
  nodeName: String;
  startTime: string;
  ready: boolean;
}

export interface AppState {
  appList: {
    total: number;
    list: AppInfo[];
  };
  appDetail: AppInfo | null;
  departments: Department[] | [];
  appMembers: {
    total: number;
    list: AppMemberDTO[];
  };
  appEnv: AppEnv | null;
}

interface CreateAppAction extends BaseAction {
  type: "app/createApp";
  payload: CreateAppPayload;
}

interface ListAppBranchAction extends BaseAction {
  type: "app/listBranch";
  payload: ListAppBranchPayload;
}

interface CreateAppEnvAction extends BaseAction {
  type: "app/createAppEnv";
  payload: CreateAppEnvPayload;
}

interface ModifyAppEnvVarsAction extends BaseAction {
  type: "app/modifyAppEnvVars";
  payload: ModifyAppEnvVarsPayload;
}

interface ModifyAppEnvConfigMapAction extends BaseAction {
  type: "app/modifyAppEnvConfigMap";
  payload: ModifyAppEnvConfigMapPayload;
}

interface ModifyAppResourcesAction extends BaseAction {
  type: "app/modifyAppResource";
  payload: ModifyAppResourcesPayload;
}

interface ScaleAction extends BaseAction {
  type: "app/scale";
  payload: ScalePayload;
}

interface GetAppEnvAction extends BaseAction {
  type: "app/getAppEnv";
  payload: { envId: string };
}

interface ListAppPodsAction extends BaseAction {
  type: "app/listAppPods";
  payload: { envId: string; appId: string };
}

interface QueryAppAction extends BaseAction {
  type: "app/getAppList";
  payload: QueryAppPayload;
}

interface QueryDepartmentAction extends BaseAction {
  type: "app/getDepartments";
}

interface PageAppMembersAction extends BaseAction {
  type: "app/pageAppMembers";
  payload: QueryAppMemberPayload;
}

interface UpdateMemberAction extends BaseAction {
  type: "app/updateMember";
  payload: UpdateAppMemberPayload;
}

interface DeleteMemberAction extends BaseAction {
  type: "app/deleteMember";
  payload: DeleteAppMemberPayload;
}

interface AddMemberAction extends BaseAction {
  type: "app/addMember";
  payload: AddAppMemberPayload;
}

interface GetAppDetailAction extends BaseAction {
  type: "app/getAppDetail";
  payload: { id: number };
}

export interface AppModelType {
  namespace: "app";
  state: AppState;
  effects: {
    getAppList: Effect;
    createApp: Effect;
    getDepartments: Effect;
    pageAppMembers: Effect;
    updateMember: Effect;
    deleteMember: Effect;
    addMember: Effect;
    getAppEnv: Effect;
    modifyAppEnvVars: Effect;
    modifyAppEnvConfigMap: Effect;
    modifyAppResources: Effect;
    listAppPods: Effect;
    scale: Effect;
    listBranch: Effect;
  };
  reducers: {
    setAppList: Reducer<AppState>;
    setAppDetail: Reducer<AppState>;
    setDepartments: Reducer<AppState>;
    setAppMembers: Reducer<AppState>;
    setAppEnv: Reducer<AppState>;
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
    departments: [],
    appMembers: {
      total: 0,
      list: [],
    },
    appEnv: null,
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
    *createApp({ payload, callback }: CreateAppAction, { call, put }) {
      const response = yield call(appService.createApp, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *listBranch({ payload, callback }: ListAppBranchAction, { call, put }) {
      const response = yield call(appService.listAppBranch, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *updateMember({ payload, callback }: UpdateMemberAction, { call, put }) {
      const response = yield call(appService.updateMember, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *addMember({ payload, callback }: AddMemberAction, { call, put }) {
      const response = yield call(appService.addMember, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *deleteMember({ payload, callback }: DeleteMemberAction, { call, put }) {
      const response = yield call(appService.deleteMember, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *createAppEnv({ payload, callback }: CreateAppEnvAction, { call, put }) {
      const response = yield call(appService.createAppEnv, payload);
      // yield put({ type: "getAppDetail" });
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *modifyAppEnvVars(
      { payload, callback }: ModifyAppEnvVarsAction,
      { call, put }
    ) {
      const response = yield call(appService.modifyAppEnvVars, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *modifyAppEnvConfigMap(
      { payload, callback }: ModifyAppEnvConfigMapAction,
      { call, put }
    ) {
      const response = yield call(appService.modifyAppEnvConfigMap, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *modifyAppResources(
      { payload, callback }: ModifyAppResourcesAction,
      { call, put }
    ) {
      const response = yield call(appService.modifyAppEnvResource, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *scale({ payload, callback }: ScaleAction, { call, put }) {
      const response = yield call(appService.scale, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },

    *getAppDetail({ payload }: GetAppDetailAction, { call, put }) {
      const response = yield call(appService.getAppDetail, payload.id);
      yield put({
        type: "setAppDetail",
        payload: response.content,
      });
    },

    *getAppEnv({ payload }: GetAppEnvAction, { call, put }) {
      const response = yield call(appService.getAppEnv, payload);
      yield put({
        type: "setAppEnv",
        payload: response.content,
      });
    },

    *listAppPods({ payload, callback }: ListAppPodsAction, { call, put }) {
      const response = yield call(appService.listAppPods, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *getDepartments({}: QueryDepartmentAction, { call, put }) {
      const response = yield call(appService.getDepartments);
      if (response?.content) {
        yield put({
          type: "setDepartments",
          payload: response.content,
        });
      }
    },

    *pageAppMembers({ payload }: PageAppMembersAction, { call, put }) {
      const response = yield call(appService.pageAppMembers, payload);
      console.log(response);

      if (response?.content) {
        yield put({
          type: "setAppMembers",
          payload: {
            list: response.content.items,
            total: response.content.total,
          },
        });
      }
    },
  },
  reducers: {
    setAppList(state, action) {
      return { ...state, appList: { ...state.appList, ...action.payload } };
    },
    setAppDetail(state: any, action: { payload: any }) {
      return { ...state, appDetail: action.payload };
    },
    setDepartments(state: any, action: { payload: any }) {
      return { ...state, departments: action.payload };
    },
    setAppMembers(state: any, action: { payload: any }) {
      return {
        ...state,
        appMembers: { ...state.appMembers, ...action.payload },
      };
    },
    setAppEnv(state: any, action: { payload: any }) {
      return { ...state, appEnv: action.payload };
    },
  },
};

export default AppModel;
