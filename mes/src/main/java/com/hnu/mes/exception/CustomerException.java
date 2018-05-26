package com.hnu.mes.exception;

/**
 * Created by lanyage on 2018/3/17.
 */
public class CustomerException extends RuntimeException {
    public CustomerException(){}
    public CustomerException(String msg) {
        super(msg);
    }
}
