package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 设备
 */
@Entity
@Table(name = "basicinfo_equipment")
public class Equipment {
    /**
     * 编码
     * 主键
     */
    @Id
    @NotNull(message = "设备编码不能为空")
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 部门编码
     * 外键--部门.编码
     */
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "DepartmentCode", referencedColumnName = "code")
    private Department department;
    /**
     * 产品线编码
     * 外键--产品线.编码
     */
    @ManyToOne(targetEntity = ProductLine.class)
    @JoinColumn(name = "ProductLineCode", referencedColumnName = "code")
    private ProductLine productLine;
    /**
     * 巡检工人号
     * 外键--用户信息.编码
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "InspectorCode", referencedColumnName = "code")
    private User user;

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

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", productLine=" + productLine +
                ", user=" + user +
                '}';
    }
}
