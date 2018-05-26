package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 车间信息
 */
@Entity
@Table(name = "basicinfo_workshop")
public class Workshop {
    /**
     * 编码
     * 主键
     */
    @Id
    @NotNull(message = "车间编码不能为空")
    private String code;
    /**
     * 名称
     */
    private String name;;
    /**
     * 部门编码
     * 外键--部门.编码
     */
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "DepartmentCode", referencedColumnName = "code")
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
