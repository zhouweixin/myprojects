package com.xplusplus.security.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @Author: Huxudong
 * @Description: 政治面貌Entity
 * @Date: Created in 19:29 2018/5/7
 * @Modified By:
 */
@Entity
public class PoliticalStatus {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @NotNull(message = "政治面貌不能为空")
    @NotBlank(message = "政治面貌不能为空")
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
        return "PoliticalStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
