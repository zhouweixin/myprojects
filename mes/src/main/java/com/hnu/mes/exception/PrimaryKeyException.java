package com.hnu.mes.exception;

/**
 * Created by lanyage on 2018/3/18.
 */
public class PrimaryKeyException extends RuntimeException{
    public PrimaryKeyException(){}
    public PrimaryKeyException(String msg) {
        super(msg);
    }
}
