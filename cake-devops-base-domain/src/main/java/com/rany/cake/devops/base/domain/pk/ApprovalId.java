package com.rany.cake.devops.base.domain.pk;

import com.cake.framework.common.base.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 22:49
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class ApprovalId implements Identifier {
    String approvalId;

    public ApprovalId(String approvalId) {
        if (StringUtils.isEmpty(approvalId)) {
            throw new IllegalArgumentException();
        }
        this.approvalId = approvalId;
    }
}
