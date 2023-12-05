/*
 * Copyright (c) 2020 François Onimus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rany.cake.devops.base.service.ssh.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.sshd.server.channel.ChannelSession;

/**
 * Ssh shell event
 */
@Data
@AllArgsConstructor
public class SshShellEvent {

    private SshShellEventType type;

    private ChannelSession session;

    public long getSessionId() {
        return session.getServerSession().getIoSession().getId();
    }

}
