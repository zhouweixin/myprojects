package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 11:36 2018/4/5
 * @Modified By:
 */
@Entity
@Table(name = "basicinfo_manufacturer")
public class Manufacturer {
    @Id
    private String code;

    private String name;

    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
