package com.rany.cake.devops.ssh.base.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HostInfo {

    @Builder.Default
    private String hostAddress = "127.0.0.1";

    @Builder.Default
    private String hostname = "cake-devops";

    public static HostInfo build() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            return HostInfo.builder()
                    .hostAddress(addr.getHostAddress())
                    .hostname(hostname)
                    .build();
        } catch (UnknownHostException ex) {
            return HostInfo.builder().build();
        }
    }
}