package com.rany.cake.devops.base.service.code.codeup;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeUpCodeService extends BaseCodeService {

    private final CodeUpService codeUpService;

    public CodeUpCodeService(String codeUpUrl, String token) {
        this.codeUpService = new CodeUpService(codeUpUrl, "", "");
    }


    @Override
    public Boolean createBranch(String repoUrl, String branchName, String ref) {
        return null;
    }
}
