package com.rany.cake.devops.base.api.dto.system;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统版本响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/6/9 13:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "系统信息响应")
public class SystemAboutDTO {

    /**
     * kit 版本
     */
    private String kitVersion;

    /**
     * version 版本
     */
    private String version;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者
     */
    private String authorCn;

}
