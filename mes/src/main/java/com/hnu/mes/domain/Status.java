package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 审核-发布状态
 *
 * Created by zhouweixin on 2018/3/21.
 */
@Entity
@Table(name = "basicinfo_status")
public class Status {
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
        return "Status{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
