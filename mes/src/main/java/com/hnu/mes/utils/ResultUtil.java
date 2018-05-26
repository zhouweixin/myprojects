package com.hnu.mes.utils;

import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;

/**
 * 统一返回工具类
 *
 * @author zhouweixin
 */
public class ResultUtil {
    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(EnumException.SUCCESS.getCode());
        result.setMessage(EnumException.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 异常
     *
     * @param mesException
     * @return
     */
    public static <T> Result<T> error(MesException mesException) {
        Result<T> result = new Result<T>();
        result.setCode(mesException.getCode());
        result.setMessage(mesException.getMessage());
        return result;
    }

    /**
     * 异常
     *
     * @param message
     * @return
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<T>();
        result.setCode(-2);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result<Object> failure(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
