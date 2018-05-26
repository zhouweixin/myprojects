package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 判定类型
 * Created by zhouweixin on 2018/3/20.
 */
@Entity
@Table(name = "basicinfo_judge")
public class Judge {
    @Id
    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Judge{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
