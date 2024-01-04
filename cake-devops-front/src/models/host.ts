// src/models/app.ts
import * as hostService from "@/services/host";
import { Effect, Reducer } from "umi";

export interface HostInfo {
  hostId: string;
  name: string;
  hostName: string;
  serverAddr: string;
  port: number;
  username: string;
  pkey: string;
}
