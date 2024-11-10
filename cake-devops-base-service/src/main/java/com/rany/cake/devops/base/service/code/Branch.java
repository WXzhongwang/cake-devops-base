package com.rany.cake.devops.base.service.code;

import lombok.Data;

@Data
public class Branch {
    private boolean defaultBranch;
    private String name;
    private boolean protectedBranch;
}
