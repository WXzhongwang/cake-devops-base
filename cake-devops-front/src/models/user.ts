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
    userData: {},
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
          isLogin: false,
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
