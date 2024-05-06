package com.rany.cake.devops.base.service.ws;

import com.rany.cake.devops.base.api.dto.TerminalConnectDTO;
import com.rany.cake.devops.base.api.dto.TerminalSizeDTO;
import com.rany.cake.toolkit.lang.utils.Strings;

/**
 * 终端工具类
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/10 18:30
 */
public class TerminalUtils {

    private TerminalUtils() {
    }

    /**
     * 解析连接参数
     * <p>
     * .e.g cols|rows|loginToken
     *
     * @param body body
     * @return connect
     */
    public static TerminalConnectDTO parseConnectBody(String body) {
        String[] arr = body.split("\\|");
        if (arr.length != 3) {
            return null;
        }
        // 解析 size
        if (!Strings.isInteger(arr[0]) || !Strings.isInteger(arr[1])) {
            return null;
        }
        TerminalConnectDTO connect = new TerminalConnectDTO();
        connect.setCols(Integer.parseInt(arr[0]));
        connect.setRows(Integer.parseInt(arr[1]));
        connect.setLoginToken(arr[2]);
        return connect;
    }

    /**
     * 解析修改大小参数
     * <p>
     * .e.g cols|rows
     *
     * @param body body
     * @return size
     */
    public static TerminalSizeDTO parseResizeBody(String body) {
        String[] arr = body.split("\\|");
        if (arr.length != 2) {
            return null;
        }
        // 解析 size
        if (!Strings.isInteger(arr[0]) || !Strings.isInteger(arr[1])) {
            return null;
        }
        TerminalSizeDTO size = new TerminalSizeDTO();
        size.setCols(Integer.parseInt(arr[0]));
        size.setRows(Integer.parseInt(arr[1]));
        return size;
    }

}
