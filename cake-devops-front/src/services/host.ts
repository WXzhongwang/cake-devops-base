// services/host.ts
import request from "@/services/request";
import { HostInfo } from "@/models/host";

export async function getHostList(): Promise<HostInfo[]> {
  return request("/api/devops/hosts/createHost");
}

export async function createHost(host: HostInfo): Promise<void> {
  return request("/api/devops/hosts/pageHosts", {
    method: "POST",
    data: host,
  });
}
