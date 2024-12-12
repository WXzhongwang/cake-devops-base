import { S } from "mockjs";
import "umi/typings";

export interface BaseAction {
  callback?: (res?: any) => void;
}

declare namespace API {
  /**
   * 响应体
   * @description content 数据
   * @description code 返回码: 200 成功, 其他 失败
   * @description message 消息: 返回的消息
   * @description success 调用是否成功
   */
  type ResponseBody<T> = {
    content: T;
    code: string;
    msg: string;
    success: boolean;
  };

  /** 用户信息数据 */
  type UserInfo = {
    userId: string;
    userName: string;
    realName: string;
  };

  type Page<T> = {
    pageNo: number;
    pageSize: number;
    totalPage: number;
    total: number;
    items: T[];
  };

  /** 用户信息响应结果 */
  type UserInfoResponse = ResponseBody<UserInfo>;
  /** 登出响应结果 */
  type LogoutResponse = ResponseBody<Record<string, never>>;
}
