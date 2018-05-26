package com.hnu.mes.domain;

import javax.persistence.*;
/**
 * Created by lanyage on 2018/3/20.
 */
@Entity
@Table(name = "basicinfo_flag")
public class Flag {
    @Id
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
