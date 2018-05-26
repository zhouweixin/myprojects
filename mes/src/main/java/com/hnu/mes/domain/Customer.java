package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lanyage on 2018/3/14.
 */
@Entity
@Table(name = "permission_customer")
public class Customer {
    /**
     * 登录名
     */
    @Id
    @NotBlank(message = "账号不能为空")
    private String code;
    /**
     * 名字
     */
    private String name;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 提供商,也就是公司
     */
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "supplier_code")
    private Supplier supplier;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色
     */
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "permission_customer_role", joinColumns = {@JoinColumn(name = "customerCode", referencedColumnName = "code") },
            inverseJoinColumns = {@JoinColumn(name = "roleCode", referencedColumnName = "code") })
    private Set<CustomerRole> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Set<CustomerRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CustomerRole> roles) {
        this.roles = roles;
    }
}
