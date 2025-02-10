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
    /**
     * maven version
     */
    private String mavenVersion;
    /**
     * maven version
     */
    private String jdkVersion;
    /**
     * nodejs version
     */
    private String nodejsVersion;
    /**
     * go version
     */
    private String goVersion;
    /**
     * python version
     */
    private String pythonVersion;

    /**
     * publicKey
     */
    private String publicKey;

    public String getMavenVersion() {
        return mavenVersion == null ? "3.6.3" : mavenVersion;
    }

    public String getNodejsVersion() {
        return nodejsVersion == null ? "v20.13.1" : nodejsVersion;
    }

    public String getJdkVersion() {
        return jdkVersion == null ? "8" : jdkVersion;
    }

    private CodeUpConfig codeUp;

    private GitHubConfig git;
    
    private GitLabConfig gitlab;
}
