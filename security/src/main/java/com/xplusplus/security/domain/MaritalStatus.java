package com.xplusplus.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: Huxudong
 * @Description: 婚姻状况Entity
 * @Date: Created in 19:34 2018/5/7
 * @Modified By:
 */
@Entity
public class MaritalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MaritalStatusController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
