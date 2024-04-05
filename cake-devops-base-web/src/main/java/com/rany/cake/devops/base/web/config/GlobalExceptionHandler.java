package com.rany.cake.devops.base.web.config;


import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.PojoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = {BusinessException.class})
//    public void handleBizException(BusinessException ex, HttpServletResponse response) throws IOException {
//        log.error("An biz exception occurred: ", ex);
//        PojoResult<Object> result = new PojoResult<>(false, ex.getCode(), ex.getMessage());
//        response.getWriter().write(JSON.toJSONString(result));
//    }
//
//    @ExceptionHandler(value = {Exception.class})
//    public void handleException(Exception ex, HttpServletResponse response) throws IOException {
//        log.error("An system exception occurred: ", ex);
//        PojoResult<Object> result = new PojoResult<>(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
//        response.getWriter().write(JSON.toJSONString(result));
//    }

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseBody
    public PojoResult<Object> handleBizException(BusinessException ex, HttpServletResponse response) {
        log.error("An biz exception occurred: ", ex);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String code = ex.getCode();
        return new PojoResult<>(false, code, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public PojoResult<Object> handleException(Exception ex, HttpServletResponse response) {
        log.error("An system exception occurred: ", ex);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new PojoResult<>(false, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }
}
