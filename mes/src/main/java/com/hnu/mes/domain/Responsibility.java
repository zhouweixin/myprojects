package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;;

/**
 * 职责信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "permission_responsibility")
public class Responsibility {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "职责编码不能为空")
    private Integer code;
    /**
     * 名称
     */
    @NotBlank(message = "职责名称不能为空")
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
        return "Responsibility [code=" + code + ", name=" + name +  "]";
    }
}
