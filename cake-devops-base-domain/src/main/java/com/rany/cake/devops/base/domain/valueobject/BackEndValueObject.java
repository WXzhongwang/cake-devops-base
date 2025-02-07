package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.*;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/2/7 15:06
 * @slogon 找到银弹
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class BackEndValueObject extends BaseValueObject {
    private String serviceName;
    private Integer servicePort;
}
