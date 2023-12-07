package com.rany.cake.devops.base.service.terminal.server;


public interface ITerminalSize {

    double W = 7.0;
    double H = 14.4166;

    /**
     * 宽
     *
     * @return 宽
     */
    int getWidth();

    /**
     * 高
     *
     * @return 高
     */
    int getHeight();

    /**
     * 列
     *
     * @return 列
     */
    default int getCols() {
        return (int) Math.floor(getWidth() / W);
    }

    /**
     * 行
     *
     * @return 行
     */
    default int getRows() {
        return (int) Math.floor(getHeight() / H);
    }

}
