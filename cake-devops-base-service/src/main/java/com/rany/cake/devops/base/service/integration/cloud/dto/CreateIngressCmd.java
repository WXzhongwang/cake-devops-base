package com.rany.cake.devops.base.service.integration.cloud.dto;

import com.rany.cake.devops.base.domain.valueobject.IngressRuleValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngressCmd {
    private String namespace;

    /**
     * rules
     */
    private List<IngressRuleValueObject> rules;

    /**
     * ingress name
     */
    private String ingressName;
}
