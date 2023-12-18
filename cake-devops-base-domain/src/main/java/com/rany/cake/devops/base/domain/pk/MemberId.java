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
 * @date 2023/1/15 15:55
 * @email 18668485565163.com
 */
@Data
@NoArgsConstructor
public class MemberId implements Identifier {
    String memberId;

    public MemberId(String memberId) {
        if (StringUtils.isEmpty(memberId)) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
    }
}
