import * as userService from "@/services/user";
import { API } from "typings";
import { Effect, Reducer } from "umi";

type UserModelState = {
  isLogin: boolean;
  userData: API.UserInfo;
  members: AppAccountDTO[];
  appMembers: AppAccountDTO[];
  menu: UserRoleMenuDTO | null;
};

interface QueryCurrentUserAction {
  type: "user/getUserInfo";
}

interface QueryUsersAction {
  type: "user/queryMembers";
  payload: QueryAccountPayload;
}

interface QueryAppMembersAction {
  type: "user/queryAppMembers";
  payload: QueryAppAccountPayload;
}

interface QueryMenuAction {
  type: "user/queryMenu";
}

export interface QueryAccountPayload {
  name: string;
  pageNo: number;
  pageSize: number;
}

export interface QueryAppAccountPayload {
  appId: string;
  name: string;
  pageNo: number;
  pageSize: number;
}

export interface UserRoleMenuDTO {
  roles: RoleDTO[];
  menuTree: MenuTreeDTO[];
}
export interface RoleDTO {
  roleId: string;
  roleName: string;
  roleDesc: string;
  roleKey: string;
  parentId: string;
  isDeleted: string;
  status: string;
}
export interface MenuTreeDTO {
  menuId: string;
  name: string;
  path: string;
  parentId: string;
  level: number;
  icon: string;
  hidden: boolean;
  isDeleted: string;
  sort: number;
  children?: MenuTreeDTO[];
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
    logout: Effect;
    getUserInfo: Effect;
    queryMembers: Effect;
    queryAppMembers: Effect;
    queryMenu: Effect;
  };
  reducers: {
    setUserInfo: Reducer<UserModelState>;
    setAppMembers: Reducer<UserModelState>;
    setMembers: Reducer<UserModelState>;
    setMenu: Reducer<UserModelState>;
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
    members: [],
    appMembers: [],
    menu: null,
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
    *queryMembers({ payload }: QueryUsersAction, { call, put }) {
      const res = yield call(userService.queryMembers, payload);
      yield put({
        type: "setMembers",
        payload: res.content?.items,
      });
    },
    *queryAppMembers({ payload }: QueryAppMembersAction, { call, put }) {
      const res = yield call(userService.queryAppMembers, payload);
      yield put({
        type: "setAppMembers",
        payload: res.content?.items,
      });
    },
    *queryMenu({}: QueryMenuAction, { call, put }) {
      const res = yield call(userService.queryUserMenu);
      yield put({
        type: "setMenu",
        payload: res.content,
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
    setMembers(state, action) {
      return {
        ...state,
        members: action.payload,
      };
    },
    setAppMembers(state, action) {
      return {
        ...state,
        appMembers: action.payload,
      };
    },
    setMenu(state, action) {
      return {
        ...state,
        menu: action.payload,
      };
    },
  },
};

export default UserModel;
