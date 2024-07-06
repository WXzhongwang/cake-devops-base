// src/models/webhook.ts

import { Effect, Reducer } from "umi";
import * as api from "@/services/webside-message";
import { BaseAction } from "typings";
import { message } from "antd";

export interface WebSideMessageDTO {
  id: number;
  messageClassify: number;
  messageType: number;
  readStatus: number;
  toUserId: number;
  toUserName: string;
  relId: string;
  sendMessage: string;
}

export interface WebSideMessageState {}

export interface WebSideMessageModelType {
  namespace: "webSideMessage";
  state: WebSideMessageState;
  effects: {
    fetchMessages: Effect;
    setAllRead: Effect;
    read: Effect;
    deleteAllRead: Effect;
    deleteMessage: Effect;
    getUnreadCount: Effect;
  };
  reducers: {};
}

const WebSideMessageModel: WebSideMessageModelType = {
  namespace: "webSideMessage",
  state: {},

  effects: {
    *fetchMessages({ payload, callback }: any, { call, put }) {
      const response = yield call(api.fetchMessages, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *setAllRead({ payload, callback }: any, { call, put }) {
      const response = yield call(api.setAllRead, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *read({ payload, callback }: any, { call, put }) {
      const response = yield call(api.read, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteAllRead({ payload, callback }: any, { call, put }) {
      const response = yield call(api.deleteAllRead, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *deleteMessage({ payload, callback }: any, { call, put }) {
      const response = yield call(api.deleteMessage, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
    *getUnreadCount({ payload, callback }: any, { call, put }) {
      const response = yield call(api.getUnreadCount, payload);
      const { success, msg } = response;
      // 如果传入了回调函数，则执行回调函数
      // 调用回调函数
      if (success && callback && typeof callback === "function") {
        callback(response.content);
      } else {
        message.error(msg);
      }
    },
  },

  reducers: {},
};

export default WebSideMessageModel;
