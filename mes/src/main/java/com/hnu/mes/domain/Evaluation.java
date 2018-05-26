package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lanyage on 2018/3/18.
 */
@Entity
@Table(name = "basicinfo_evaluation")
public class Evaluation {
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
        return "Evaluation{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
