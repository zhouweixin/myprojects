package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: zhouweixin
 * @Description: 出料状态
 * @Date: Created in 13:49 2018/4/29
 * @Modified By:
 */
@Entity
@Table(name = "basicinfo_picking")
public class Picking {
    /** 编码*/
    @Id
    private Integer code;

    // 名称
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
        return "Picking{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
