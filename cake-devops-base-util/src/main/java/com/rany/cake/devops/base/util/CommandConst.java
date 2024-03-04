package com.rany.cake.devops.base.util;


/**
 * 命令常量
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 11:21
 */
public class CommandConst {

    private CommandConst() {
    }

    public static final String TAIL_FILE_DEFAULT = "tail -f -n "
            + EnvConst.getReplaceVariable(EnvConst.OFFSET)
            + " '" + EnvConst.getReplaceVariable(EnvConst.FILE)
            + "'";

    public static final String SCP_TRANSFER_DEFAULT = "scp \""
            + EnvConst.getReplaceVariable(EnvConst.BUNDLE_PATH)
            + "\" " + EnvConst.getReplaceVariable(EnvConst.TARGET_USERNAME)
            + "@" + EnvConst.getReplaceVariable(EnvConst.TARGET_HOST)
            + ":\"" + EnvConst.getReplaceVariable(EnvConst.TRANSFER_PATH)
            + "\"";

}
