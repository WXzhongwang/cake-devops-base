package com.rany.cake.devops.base.service.terminal.server.message;

import com.rany.cake.devops.base.service.terminal.server.IState;
import com.rany.cake.devops.base.service.terminal.server.ITerminalSize;
import com.rany.cake.devops.base.service.terminal.server.TerminalSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseServerMessage implements IState, ITerminalSize {

    private String id;
    private String state;
    // 0 普通账户 1 管理员账户
    private Integer loginType;
    private boolean isAdmin;
    private TerminalSize size;

    @Override
    public int getWidth() {
        return size.getWidth();
    }

    @Override
    public int getHeight() {
        return size.getHeight();
    }
}
