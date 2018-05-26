package com.hnu.mes.domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

/**
 * 菜单信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "basicinfo_menu1")
public class Menu1 {
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

    /**
     * 信息
     */
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Menu1{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", path='" + path + '\'' +
                '}';
    }
}

