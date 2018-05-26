package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: zhouweixin
 * @Description: 审批类型
 * @Date: Created in 13:52 2018/4/29
 * @Modified By:
 */
@Entity
@Table(name = "basicinfo_approval")
public class ApprovalType {
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
        return "ApprovalType{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
