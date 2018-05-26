package com.hnu.mes.domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;;

/**
 * 物料类型表
 *
 * @author chenpingxiao
 */
@Entity
@Table(name = "basicinfo_material")
public class Material {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "物资编码不能为空")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    /**
     * 名称
     */
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
        return "Material{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
