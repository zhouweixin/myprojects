package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "basicinfo_cycle")
public class Cycle {

    /**
     * 编码
     */
    @Id
    @NotBlank(message = "编码不能为空")
    private String code;


    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
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
        return "Cycle{" +
                "code='" + code +
                ", name=" + name +
                '}';
    }
}
