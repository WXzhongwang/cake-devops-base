// src/models/app.ts
import * as clusterService from "@/services/cluster";
import { message } from "antd";
import { BaseAction } from "typings";
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

interface QueryClusterAction extends BaseAction {
  type: "cluster/listAll";
}

interface CreateClusterAction extends BaseAction {
  type: "cluster/createCluster";
  payload: CreateClusterPayload;
}

interface ConnectClusterAction extends BaseAction {
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
  reducers: {};
}

const ClusterModel: ClusterModelType = {
  namespace: "cluster",
  state: {
    clusterList: [],
  },
  effects: {
    *listAll({ callback }: QueryClusterAction, { call, put }) {
      const response = yield call(clusterService.listAll);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *createCluster({ payload, callback }: CreateClusterAction, { call, put }) {
      const response = yield call(clusterService.createCluster, payload);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *connectCluster(
      { payload, callback }: ConnectClusterAction,
      { call, put }
    ) {
      const response = yield call(clusterService.connect);
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
  },
  reducers: {},
};

export default ClusterModel;
