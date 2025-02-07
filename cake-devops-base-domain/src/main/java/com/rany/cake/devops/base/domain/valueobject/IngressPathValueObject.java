package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.*;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/2/7 15:04
 * @slogon 找到银弹
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class IngressPathValueObject extends BaseValueObject {

    private String path;

    private String pathType;

    private BackEndValueObject backend;
}
