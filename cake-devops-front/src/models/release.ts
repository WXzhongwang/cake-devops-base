// src/models/app.ts
import * as releaseService from "@/services/release";
import { message } from "antd";
import { BaseAction } from "typings";
import { Effect, Reducer } from "umi";

export interface CreateReleasePayload {
  appId: string;
  envId: string;
  releaseDate: Date;
  releaseBranch: string;
  releaseVersion: string | undefined;
  docAddress: string;
  comment: string;
}

export interface PageReleasePayload {
  appId: string;
  envId: string;
  pageNo: number;
  pageSize: number;
}

export interface PageDeployHistoryPayload {
  appId: string;
  envId: string;
  pageNo: number;
  pageSize: number;
}

export interface QueryDeployLogPayload {
  pipeKey: string;
}

export interface ReleaseRecord {
  releaseId: string;
  appId: string;
  envId: string;
  releaseNo: string;
  releaseDate: Date;
  releaseBranch: string;
  commitId: string;
  releaseVersion: string;
  docAddress: string;
  comment: string;
  gmtCreate: Date;
  gmtModified: Date;
  releaseStatus: string;
  approvalDTO: ApprovalDTO | null;
}

export interface DeployHistoryDTO {
  pipeKey: string;
  appId: string;
  envId: string;
  startTime: Date;
  endTime: Date;
  deployStatus: string;
  imagePath: string;
  deploymentName: string;
  content: string;
  releaseId: string;
}

export interface DeployLogDTO {
  pipeKey: string;
  time: string;
  level: string;
  thread: string;
  location: string;
  message: string;
}

export interface ApprovalDTO {
  approvalId: string;
  docAddress: string;
  changeDate: Date;
  approvalStatus: string;
  comment: string;
}

export interface DeployPayload {
  releaseId: string;
}

export interface ClosePayload {
  releaseId: string;
}

export interface CreateReleaseAction extends BaseAction {
  type: "release/createRelease";
  payload: CreateReleasePayload;
}

export interface CloseReleaseAction extends BaseAction {
  type: "release/close";
  payload: ClosePayload;
}

export interface DeployAction extends BaseAction {
  type: "release/deploy";
  payload: DeployPayload;
}

export interface PageReleaseAction extends BaseAction {
  type: "release/pageRelease";
  payload: PageReleasePayload;
}

export interface QueryDeployLogAction extends BaseAction {
  type: "release/queryDeployLog";
  payload: QueryDeployLogPayload;
}

export interface PageDeployHistoryAction extends BaseAction {
  type: "release/pageDeployHistory";
  payload: PageDeployHistoryPayload;
}

export interface ReleaseState {
  releases: {
    total: number;
    list: ReleaseRecord[];
  };
  deployHistory: {
    total: number;
    list: DeployHistoryDTO[];
  };
}

export interface ReleaseModelType {
  namespace: "release";
  state: ReleaseState;
  effects: {
    pageRelease: Effect;
    createRelease: Effect;
    deploy: Effect;
    close: Effect;
    pageDeployHistory: Effect;
    queryDeployLog: Effect;
  };
  reducers: {
    setReleaseList: Reducer<ReleaseState>;
    setDeployHistoryList: Reducer<ReleaseState>;
  };
}

const ReleaseModel: ReleaseModelType = {
  namespace: "release",
  state: {
    releases: {
      total: 0,
      list: [],
    },
    deployHistory: {
      total: 0,
      list: [],
    },
  },
  effects: {
    *pageDeployHistory({ payload }: PageDeployHistoryAction, { call, put }) {
      const response = yield call(releaseService.pageDeployHistory, payload);
      const { success, msg } = response;
      if (success) {
        if (response?.content) {
          yield put({
            type: "setDeployHistoryList",
            payload: {
              list: response.content.items,
              total: response.content.total,
            },
          });
        }
      } else {
        message.error(msg);
      }
    },
    *pageRelease({ payload }: PageReleaseAction, { call, put }) {
      const response = yield call(releaseService.page, payload);
      const { success, msg } = response;
      if (success) {
        if (response?.content) {
          yield put({
            type: "setReleaseList",
            payload: {
              list: response.content.items,
              total: response.content.total,
            },
          });
        }
      } else {
        message.error(msg);
      }
    },

    *queryDeployLog(
      { payload, callback }: QueryDeployLogAction,
      { call, put }
    ) {
      const response = yield call(releaseService.queryPipeLog, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },

    *createRelease({ payload, callback }: CreateReleaseAction, { call, put }) {
      const response = yield call(releaseService.create, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback();
        }
      } else {
        message.error(msg);
      }
    },
    *deploy({ payload }: DeployAction, { call, put }) {
      yield call(releaseService.deploy, payload);
      yield put({ type: "setReleaseList" });
    },
    *close({ payload }: CloseReleaseAction, { call, put }) {
      yield call(releaseService.close, payload);
      yield put({ type: "setReleaseList" });
    },
  },
  reducers: {
    setReleaseList(state, action) {
      return { ...state, releases: { ...state.releases, ...action.payload } };
    },
    setDeployHistoryList(state, action) {
      return {
        ...state,
        deployHistory: { ...state.deployHistory, ...action.payload },
      };
    },
  },
};

export default ReleaseModel;
