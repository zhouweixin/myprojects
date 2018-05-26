package com.hnu.mes.enums;

/**
 * Created by lanyage on 2018/3/18.
 */
public enum EqExceptionEnum {
    APPLIED_ALREADY(-1, "该设备故障已申请过了");
    private int code;
    private String message;
    EqExceptionEnum(int code, String message) {
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
