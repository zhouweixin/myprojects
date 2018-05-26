package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Many;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 菜单信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "basicinfo_menu2")
public class Menu2 {
    /**
     * 编码
     */
    @Id
    private Integer code;
    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer rank;

    @ManyToOne(targetEntity = Menu1.class)
    @JoinColumn(name = "menu1_code", referencedColumnName = "code")
    private Menu1 menu1;


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

    public Menu1 getMenu1() {
        return menu1;
    }

    public void setMenu1(Menu1 menu1) {
        this.menu1 = menu1;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Menu2{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", menu1=" + menu1 +
                '}';
    }
}

