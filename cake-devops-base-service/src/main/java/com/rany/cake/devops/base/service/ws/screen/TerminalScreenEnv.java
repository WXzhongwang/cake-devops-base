package com.rany.cake.devops.base.service.ws.screen;

import com.alibaba.fastjson.annotation.JSONField;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.toolkit.net.remote.TerminalType;
import lombok.Data;

/**
 * terminal 录屏环境
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/26 18:27
 */
@Data
public class TerminalScreenEnv {

    /**
     * 终端类型
     */
    @JSONField(name = "TERM")
    private String term;

    /**
     * shell 类型
     */
    @JSONField(name = "SHELL")
    private String shell;

    public TerminalScreenEnv() {
        this(TerminalType.XTERM.getType(), Const.DEFAULT_SHELL);
    }

    public TerminalScreenEnv(String term) {
        this(term, Const.DEFAULT_SHELL);
    }

    public TerminalScreenEnv(String term, String shell) {
        this.term = term;
        this.shell = shell;
    }

}
