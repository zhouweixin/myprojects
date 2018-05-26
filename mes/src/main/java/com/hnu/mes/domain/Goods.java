package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@Entity
@Table(name = "basicinfo_goods")
public class Goods {
    @Id
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
        return "Goods{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
