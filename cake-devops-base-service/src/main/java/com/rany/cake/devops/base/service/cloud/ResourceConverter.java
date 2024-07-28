package com.rany.cake.devops.base.service.cloud;

import io.kubernetes.client.custom.Quantity;

public class ResourceConverter {

    /**
     * 将CPU核数字符串转换为Quantity对象，单位为毫核。
     *
     * @param cpuStr CPU核数字符串，如"0.5C"。
     * @return 对应的Quantity对象，单位为毫核。
     */
    public static Quantity convertCpuToMilliCores(String cpuStr) {
        // 移除"C"单位
        String cpuValueStr = cpuStr.replace("C", "");

        // 将字符串转换为浮点数
        double cpuValue = Double.parseDouble(cpuValueStr);

        // 转换为毫核
        long milliCores = (long) (cpuValue * 1000);

        // 返回Quantity对象，单位为毫核
        return new Quantity(milliCores + "m");
    }

    /**
     * 将内存大小字符串转换为Quantity对象。
     *
     * @param memoryStr 内存大小字符串，如"500M"或"1G"。
     * @return 对应的Quantity对象。
     */
    public static Quantity convertMemory(String memoryStr) {
        // 检查并转换单位
        String[] parts = memoryStr.split("(\\d+)(\\D+)");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid memory format: " + memoryStr);
        }
        String numberPart = parts[1];
        String unitPart = parts[2];

        // 根据不同的单位进行转换
        switch (unitPart.toUpperCase()) {
            case "B":
                return new Quantity(numberPart);
            case "KB":
                return new Quantity(Long.parseLong(numberPart) * 1024 + "");
            case "MB":
                return new Quantity(Long.parseLong(numberPart) * 1024 * 1024 + "");
            case "GB":
                return new Quantity(Long.parseLong(numberPart) * 1024 * 1024 * 1024 + "");
            case "TB":
                return new Quantity(Long.parseLong(numberPart) * 1024 * 1024 * 1024 * 1024 + "");
            case "PB":
                return new Quantity(Long.parseLong(numberPart) * 1024L * 1024 * 1024 * 1024 * 1024 + "");
            case "EB":
                return new Quantity(Long.parseLong(numberPart) * 1024L * 1024 * 1024 * 1024 * 1024 * 1024 + "");
            default:
                throw new IllegalArgumentException("Unsupported memory unit: " + unitPart);
        }
    }
}
