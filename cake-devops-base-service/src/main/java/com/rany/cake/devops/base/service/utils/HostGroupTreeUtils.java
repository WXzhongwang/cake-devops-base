package com.rany.cake.devops.base.service.utils;

import com.rany.cake.devops.base.api.dto.HostGroupTreeDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostGroupTreeUtils {

    private HostGroupTreeUtils() {
    }

    public static List<HostGroupTreeDTO> convertListToTree(List<HostGroupTreeDTO> nodeList) {
        Map<String, HostGroupTreeDTO> nodeMap = new HashMap<>();
        // 先将所有节点放入 Map 中，以节点的 id 作为键
        for (HostGroupTreeDTO node : nodeList) {
            nodeMap.put(node.getHostGroupId(), node);
        }
        // 遍历节点，将每个节点放入其父节点的 children 列表中
        List<HostGroupTreeDTO> treeNodes = new ArrayList<>();
        for (HostGroupTreeDTO node : nodeList) {
            String parentId = node.getParentId();
            if (parentId == null || "0".equals(parentId)) {
                // 如果节点没有父节点，说明它是根节点
                treeNodes.add(node);
            } else {
                HostGroupTreeDTO parent = nodeMap.get(parentId);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(node);
                }
            }
        }
        return treeNodes;
    }
}
