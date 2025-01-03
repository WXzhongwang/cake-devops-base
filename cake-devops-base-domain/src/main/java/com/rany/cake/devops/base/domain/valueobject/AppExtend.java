package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用拓展
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/12 23:39
 * @email 18668485565163.com
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AppExtend extends BaseValueObject {

    private CodeUpConfig codeUp;

    private GitHubConfig git;
    private GitLabConfig gitlab;
}
