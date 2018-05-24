package com.xplusplus.security.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @Author: Huxudong
 * @Description: 兵役状况Entity
 * @Date: Created in 19:36 2018/5/7
 * @Modified By:
 */
@Entity
public class MilitaryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @NotNull(message = "兵役状况名称不能为空")
    @NotBlank(message = "兵役状况名称不能为空")
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

}
