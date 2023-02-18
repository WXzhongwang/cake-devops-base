package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.repository.ClusterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class ClusterDomainService {
    private final ClusterRepository clusterRepository;
}
