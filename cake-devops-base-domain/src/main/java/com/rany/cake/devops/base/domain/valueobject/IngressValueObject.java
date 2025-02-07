package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class IngressValueObject extends BaseValueObject {
    /**
     * ingressName
     */
    private String ingressName;

    private List<IngressRuleValueObject> rules;
}
