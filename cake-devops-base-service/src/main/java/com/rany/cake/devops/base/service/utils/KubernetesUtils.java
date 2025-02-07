package com.rany.cake.devops.base.service.utils;

import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.integration.cloud.dto.PodInfoDTO;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodStatus;
import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/20 20:09
 * @email 18668485565163.com
 */
public final class KubernetesUtils {

    private KubernetesUtils() {

    }

    /**
     * businessUnit abbreviation english name
     *
     * @param businessUnit
     * @return
     */
    public String getNamespaceName(String businessUnit) {
        return Constants.DEFAULT_NAMESPACE_PREFIX + businessUnit;
    }

    public static String getReplicaAppName(String appName, String appEnvTag) {
        return appName + "-" + appEnvTag;
    }


    public static List<PodInfoDTO> convertPodsToDTOs(List<V1Pod> pods) {
        List<PodInfoDTO> podInfoDTOList = new ArrayList<>();
        for (V1Pod pod : pods) {
            PodInfoDTO dto = convertToDTO(pod);
            podInfoDTOList.add(dto);
        }
        return podInfoDTOList;
    }

    private static PodInfoDTO convertToDTO(V1Pod pod) {
        if (pod == null || pod.getStatus() == null || pod.getSpec() == null) {
            return null;
        }
        V1PodStatus status = pod.getStatus();
        boolean isReady = status.getConditions() != null &&
                status.getConditions().stream()
                        .anyMatch(c -> "Ready".equals(c.getType()) && "True".equals(c.getStatus()));

        OffsetDateTime startTime = status.getStartTime();
        String formattedStartTime = startTime != null ? startTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) : "";

        return new PodInfoDTO(
                Objects.requireNonNull(pod.getMetadata()).getName(),
                pod.getMetadata().getNamespace(),
                status.getPodIP(),
                status.getPhase(),
                pod.getSpec().getNodeName(),
                formattedStartTime,
                isReady
        );
    }

    public static Map<String, byte[]> convertSecretData(Map<String, String> secretData) {
        Map<String, byte[]> result = new HashMap<>();
        for (Map.Entry<String, String> entry : secretData.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getBytes());
        }
        return result;
    }

    public static boolean equalMaps(Map<String, byte[]> map1, Map<String, byte[]> map2) {
        if (map1 == null || map2 == null) {
            return false;
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<String, byte[]> entry : map1.entrySet()) {
            String key = entry.getKey();
            byte[] value1 = entry.getValue();
            byte[] value2 = map2.get(key);
            if (value2 == null || !Arrays.equals(value1, value2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalMaps2(Map<String, String> map1, Map<String, String> map2) {
        // null 判定
        if (map1 == null || map2 == null) {
            return false;
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value1 = entry.getValue();
            String value2 = map2.get(key);
            if (value2 == null || !StringUtils.equals(value1, value2)) {
                return false;
            }
        }
        return true;
    }
}

