package com.rany.cake.devops.base.api.dto.code;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private boolean protectBranch;
}
