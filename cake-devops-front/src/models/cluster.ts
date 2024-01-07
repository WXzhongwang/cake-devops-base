// src/models/app.ts
import * as clusterService from "@/services/cluster";
import { Effect, Reducer } from "umi";

// 应用信息
export interface ClusterInfo {
  clusterId: string;
  clusterName: string;
  version: string;
  clusterType: string;
  status: string;
  connectionString: string;
  token: string;
  isDeleted: string;
  gmtCreate: number;
  gmtModified: number;
  creator: string;
  modifier: string;
  tags: string;
}

export interface ClusterState {
  clusterList: ClusterInfo[];
}

interface QueryClusterAction {
  type: "cluster/listAll";
}

interface CreateClusterAction {
  type: "cluster/createCluster";
  payload: CreateClusterPayload;
}

interface ConnectClusterAction {
  type: "cluster/connectCluster";
  payload: ConnectClusterPayload;
}

export interface CreateClusterPayload extends ConnectClusterPayload {
  name: string;
  tags: string[];
}

export interface ConnectClusterPayload {
  connectString: string;
  token: string;
  clusterType: string;
}

export interface ClusterModelType {
  namespace: "cluster";
  state: ClusterState;
  effects: {
    listAll: Effect;
    createCluster: Effect;
    connectCluster: Effect;
  };
  reducers: {
    setClusterList: Reducer<ClusterState>;
  };
}

const ClusterModel: ClusterModelType = {
  namespace: "cluster",
  state: {
    clusterList: [],
  },
  effects: {
    *listAll(_: QueryClusterAction, { call, put }) {
      const response = yield call(clusterService.listAll);
      yield put({
        type: "setClusterList",
        payload: response.content,
      });
    },
    *createCluster({ payload }: CreateClusterAction, { call, put }) {
      const response = yield call(clusterService.createCluster, payload);
      yield put({ type: "setClusterList" });
    },
    *connectCluster({ payload }: ConnectClusterAction, { call, put }) {
      const response = yield call(clusterService.connect);
    },
  },
  reducers: {
    setClusterList(state, action) {
      return { ...state, clusterList: action.payload };
    },
  },
};

export default ClusterModel;
