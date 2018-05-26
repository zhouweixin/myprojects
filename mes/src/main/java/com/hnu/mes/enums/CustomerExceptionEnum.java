package com.hnu.mes.enums;

/**
 * Created by lanyage on 2018/3/17.
 */
public enum CustomerExceptionEnum {
    SEARCH_ERROR(-1,"查找异常"),
    PRIMARY_KEY_ERROR(-2,"主键冲突"),
    PASSWORD_NULL_ERROR(-3,"新旧密码不能为空"),
    WRONG_PASSWORD(-4,"密码错误"),
    USER_NOT_EXISTS_ERROR(-5,"用户不存在"),
    ;
    private int code;
    private String message;
    CustomerExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
