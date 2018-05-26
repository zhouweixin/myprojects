package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@Entity
@Table(name = "basicinfo_circle")
public class Circle {
    @Id
    private Integer code;

    @NotBlank(message = "周期名称不能为空")
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
