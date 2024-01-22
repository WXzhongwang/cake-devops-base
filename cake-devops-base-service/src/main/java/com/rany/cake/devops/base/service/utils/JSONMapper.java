package com.rany.cake.devops.base.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JSONMapper extends ObjectMapper {

    public JSONMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

}