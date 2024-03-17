package com.rany.cake.devops.base.web.config;


import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PojoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BusinessException.class})
    public PojoResult<Object> handleBizException(BusinessException ex) {
        log.error("An biz exception occurred: ", ex);
        return new PojoResult<>(false, ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public PojoResult<Object> handleException(Exception ex) {
        log.error("An system exception occurred: ", ex);
        return new PojoResult<>(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }
}
