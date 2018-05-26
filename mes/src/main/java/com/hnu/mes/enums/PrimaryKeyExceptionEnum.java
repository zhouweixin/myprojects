package com.hnu.mes.enums;

/**
 * Created by lanyage on 2018/3/18.
 */
public enum PrimaryKeyExceptionEnum {
    PRIMARY_KEY_NULL_OR_CHANGED, PRIMARY_KEY_REPEATED;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
