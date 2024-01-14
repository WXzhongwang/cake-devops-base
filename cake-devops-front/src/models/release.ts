// src/models/app.ts
import * as releaseService from "@/services/release";
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

export interface ReleaseHistory {
  appId: string;
  envId: string;
  releaseDate: Date;
  releaseBranch: string;
  releaseVersion: string;
  docAddress: string;
  comment: string;
  gmtCreate: Date;
  releaseStatus: string;
}

export interface DeployPayload {
  releaseId: string;
}

export interface CreateReleaseAction {
  type: "release/createRelease";
  payload: CreateReleasePayload;
}

export interface DeployAction {
  type: "release/deploy";
  payload: DeployPayload;
}

export interface PageReleaseAction {
  type: "release/pageRelease";
  payload: PageReleasePayload;
}

export interface ReleaseState {
  releases: {
    total: number;
    list: ReleaseHistory[];
  };
}

export interface ReleaseModelType {
  namespace: "release";
  state: ReleaseState;
  effects: {
    pageRelease: Effect;
    createRelease: Effect;
    deploy: Effect;
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
      console.log("response", response);

      if (response?.content) {
        yield put({
          type: "setReleaseList",
          payload: {
            list: response.content.items,
            total: response.content.total,
          },
        });
      }
    },
    *createRelease({ payload }: CreateReleaseAction, { call, put }) {
      // debugger;
      yield call(releaseService.create, payload);
      yield put({ type: "setReleaseList" });
    },
    *deploy({ payload }: DeployAction, { call, put }) {
      yield call(releaseService.deploy, payload);
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
