package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 产品线
 */
@Entity
@Table(name = "basicinfo_productline")
public class ProductLine {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "产品线编码不能为空")
    private String code;
    /**
     * 名称
     */
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
        return "ProductLine{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
