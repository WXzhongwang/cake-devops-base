import * as userService from "@/services/user";
import { API } from "typings";
import { Effect, Reducer, ConnectProps } from "umi";
import { history } from "umi";

type UserModelState = {
  isLogin: boolean;
  userData: API.UserInfo;
};

interface QueryCurrentUserAction {
  type: "user/getUserInfo";
}

export interface QueryAppAccountPayload {
  name: string;
  pageNo: number;
  pageSize: number;
}

export interface AppAccountDTO {
  id: string;
  accountName: string;
  phone: string;
  email: string;
  tenantId: string;
  isAdmin: boolean;
  accountType: string;
  status: string;
  isDeleted: string;
  lastLoginIp: string;
  lastLoginTime: Date;
  feature: string;
  gmtCreate: Date;
  gmtModified: Date;
  headImage: string;
  dingding: string;
  qq: string;
  wechat: string;
  birthday: Date;
  tags: string;
}

type UserModelType = {
  namespace: "user";
  state: UserModelState;
  effects: {
    getUserInfo: Effect;
    logout: Effect;
  };
  reducers: {
    setUserInfo: Reducer<UserModelState>;
  };
};

const UserModel: UserModelType = {
  namespace: "user",
  state: {
    isLogin: false,
    userData: {
      userId: "",
      userName: "",
      realName: "",
    },
  },
  effects: {
    *logout(_, { call, put }) {
      const res: API.LogoutResponse = yield call(userService.logout);
      console.log("res", res);
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: false,
          userData: {},
        },
      });
    },
    *getUserInfo(_: QueryCurrentUserAction, { call, put }) {
      const res: API.UserInfoResponse = yield call(userService.getUserInfo);
      console.log("res", res);
      console.log("content", res.content);
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: true,
          userData: res.content,
        },
      });
    },
  },
  reducers: {
    setUserInfo(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },
  },
};

export default UserModel;
