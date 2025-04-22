import * as userService from "@/services/user";
import { message } from "antd";
import { API, BaseAction } from "typings";
import { Effect, Reducer } from "umi";
import { AppMemberDTO } from "./app";

type UserModelState = {
  isLogin: boolean;
  userData: UserInfo;
  members: AppAccountDTO[];
  appMembers: AppAccountDTO[];
  menu: UserRoleMenuDTO | null;
};

interface QueryCurrentUserAction extends BaseAction {
  type: "user/getUserInfo";
}

interface QueryUsersAction extends BaseAction {
  type: "user/queryMembers";
  payload: QueryAccountPayload;
}

interface QueryAppMembersAction extends BaseAction {
  type: "user/queryAppMembers";
  payload: QueryAppAccountPayload;
}

interface QueryMenuAction extends BaseAction {
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
/** 用户信息数据 */
export interface UserInfo {
  userId: string;
  userName: string;
  realName: string;
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

export interface AppAccountPage {
  total: number;
  items: AppAccountDTO[];
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
      const res: API.ResponseBody<Record<string, never>> = yield call(
        userService.logout
      );
      yield put({
        type: "setUserInfo",
        payload: {
          isLogin: false,
          userData: {},
        },
      });
    },
    *getUserInfo({ callback }: QueryCurrentUserAction, { call, put }) {
      const response: API.ResponseBody<UserInfo> = yield call(
        userService.getUserInfo
      );
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *queryMembers({ payload, callback }: QueryUsersAction, { call, put }) {
      const response: API.ResponseBody<API.Page<AppAccountDTO>> = yield call(
        userService.queryMembers,
        payload
      );
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *queryAppMembers(
      { payload, callback }: QueryAppMembersAction,
      { call, put }
    ) {
      const response: API.ResponseBody<API.Page<AppMemberDTO>> = yield call(
        userService.queryAppMembers,
        payload
      );
      const { success, msg } = response;
      if (success) {
        if (callback && typeof callback === "function") {
          callback(response.content);
        }
      } else {
        message.error(msg);
      }
    },
    *queryMenu({ callback }: QueryMenuAction, { call, put }) {
      const response: API.ResponseBody<UserRoleMenuDTO> = yield call(
        userService.queryUserMenu
      );

      console.log(response.content);

      yield put({
        type: "setMenu",
        payload: {
          menu: response.content,
        },
      });

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
      console.log("action", action.payload);
      return {
        ...state,
        ...action.payload,
      };
    },
  },
};

export default UserModel;
