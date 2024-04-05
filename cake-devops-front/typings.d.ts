import { S } from "mockjs";
import "umi/typings";

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
    // name?: string;
    // avatar?: string;
    // accountId?: string;
    // tenantId?: string;
    // isvId?: string;
    // email?: string;
    // signature?: string;
    // title?: string;
    // group?: string;
    // tags?: { key?: string; label?: string }[];
    // notifyCount?: number;
    // unreadCount?: number;
    // country?: string;
    // access?: string;
    // geographic?: {
    //   province?: { label?: string; key?: string };
    //   city?: { label?: string; key?: string };
    // };
    // address?: string;
    // phone?: string;

    userId: string;
    userName: string;
    realName: string;
  };

  /** 用户信息响应结果 */
  type UserInfoResponse = ResponseBody<UserInfo>;
  /** 登出响应结果 */
  type LogoutResponse = ResponseBody<Record<string, never>>;
}
