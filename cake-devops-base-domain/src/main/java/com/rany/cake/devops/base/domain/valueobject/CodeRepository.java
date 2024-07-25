package com.rany.cake.devops.base.domain.valueobject;

import com.cake.framework.common.base.BaseValueObject;
import com.rany.cake.devops.base.util.enums.CodePlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 代码仓库
 *
 * @author zhongshengwang
 * @description 代码仓库
 * @date 2023/1/12 23:04
 * @email 18668485565163.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CodeRepository extends BaseValueObject {
    /**
     * 仓库地址
     */
    private String repo;
    /**
     * 默认分支
     */
    private String defaultBranch;
    /**
     * 代码平台
     */
    private String codePlatform;
    /**
     * 连接字符串
     */
    private String connectionString;
    /**
     * 凭证token
     */
    private String token;

    public CodePlatformEnum of() {
        if (StringUtils.equals(codePlatform, CodePlatformEnum.GITHUB.getValue())) {
            return CodePlatformEnum.GITHUB;
        }
        if (StringUtils.equals(codePlatform, CodePlatformEnum.GITLAB.getValue())) {
            return CodePlatformEnum.GITLAB;
        }
        throw new UnsupportedOperationException("不支持的代码平台");
    }
}
