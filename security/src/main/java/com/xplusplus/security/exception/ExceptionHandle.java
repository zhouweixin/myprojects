package com.xplusplus.security.exception;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e) {
        if (e instanceof SecurityException) {
            // 自定义异常
            SecurityException securityException = (SecurityException) e;
            return ResultUtil.error(securityException);
        } else if (e.getMessage().contains("NumberFormatException")) {
            logger.error("【系统异常】 {}", e.getMessage());
            // String转Integer或Double出错
            return ResultUtil.error(new SecurityException(EnumException.STRING_TO_NUM_FALIED));
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            logger.error("【系统异常】 {}", e.getMessage());
            // 参数类型不匹配
            return ResultUtil.error(new SecurityException(EnumException.ARGU_MISMATCH_EXCEPTION));
        } else if (e.getMessage().contains("exceeds the configured maximum")) {
            logger.error("【系统异常】 {}", e.getMessage());
            return ResultUtil.error(new SecurityException(EnumException.FILE_SIZE_EXCEED_MAXIMUM));
        } else {
            logger.error("【系统异常】 {}", e.getMessage());

            if (e.getMessage().contains("Request method")) {
                // 请求方法不匹配
                return ResultUtil.error(new SecurityException(EnumException.REQUEST_METHOD));
            } else {
                return ResultUtil.error(new SecurityException(EnumException.UNKOWN_ERROR));
            }
        }
    }
}
