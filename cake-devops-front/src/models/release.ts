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

export interface ReleaseState {
  releases: {
    total: number;
    list: ReleaseRecord[];
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
  };
  reducers: {
    setReleaseList: Reducer<ReleaseState>;
  };
}

const ReleaseModel: ReleaseModelType = {
  namespace: "release",
  state: {
    releases: {
      total: 0,
      list: [],
    },
  },
  effects: {
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
  },
};

export default ReleaseModel;
