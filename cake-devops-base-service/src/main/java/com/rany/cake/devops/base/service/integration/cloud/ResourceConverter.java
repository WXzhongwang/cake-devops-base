package com.rany.cake.devops.base.service.integration.cloud;

import io.kubernetes.client.custom.Quantity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhongshengwang
 */
public final class ResourceConverter {

    private static  final Pattern PATTERN = Pattern.compile("(\\d+)([A-Za-z])");

    /**
     * 将CPU核数字符串转换为Quantity对象，单位为毫核。
     *
     * @param cpuStr CPU核数字符串，如"0.5C"。
     * @return 对应的Quantity对象，单位为毫核。
     */
    public static Quantity convertCpuToMilliCores(String cpuStr) {
        // 移除"C"单位
        String cpuValueStr = cpuStr.replace("C", "").replace("c", "");

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

        Matcher matcher = PATTERN.matcher(memoryStr);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid memory format: " + memoryStr);
        }


        String numberPart = matcher.group(1);
        String unitPart = matcher.group(2).toUpperCase();
        BigDecimal number = new BigDecimal(numberPart);
        BigDecimal result = number;

        switch (unitPart) {
            case "B":
                break;
            case "KB":
                result = result.multiply(BigDecimal.valueOf(1000));
                break;
            case "M":
            case "MB":
                result = result.multiply(BigDecimal.valueOf(1000 * 1000));
                break;
            case "GB":
                result = result.multiply(BigDecimal.valueOf(1000 * 1000 * 1000));
                break;
            case "TB":
                result = result.multiply(BigDecimal.valueOf(1000L * 1000 * 1000 * 1000));
                break;
            case "PB":
                result = result.multiply(BigDecimal.valueOf(1000L * 1000 * 1000 * 1000 * 1000));
                break;
            case "EB":
                result = result.multiply(BigDecimal.valueOf(1000L * 1000 * 1000 * 1000 * 1000 * 1000));
                break;
            // ... 其他二进制前缀的处理保持不变 ...

            default:
                throw new IllegalArgumentException("Unsupported memory unit: " + unitPart);
        }
        // 将转换后的数值转换为BigInteger
        BigInteger bigIntResult = result.toBigIntegerExact();
        // 将BigInteger转换为字符串
        String strResult = bigIntResult.toString();

        // 创建Quantity对象时，传递数值字符串
        return new Quantity(strResult);
    }


}
