package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class IngressRuleValueObject extends BaseValueObject {
    /**
     * host
     */
    private String host;

    private List<IngressPathValueObject> paths;

}
