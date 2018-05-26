package com.hnu.mes.domain;

import java.util.Set;

import javax.persistence.*;

/**
 * 模块
 *
 * @author zhouweixin
 */
@Entity
@Table(name = "permission_model")
public class Model {
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
     * 操作
     */
    @ManyToMany(targetEntity = Operation.class, cascade = CascadeType.ALL)
    @JoinTable(name = "permission_model_operation")
    @Transient
    private Set<Operation> operations;

    /**
     * 排序
     */
    private Integer rank;

    /**
     * 信息
     */
    private String path;

    /**
     * 接口名
     */
    private String inter;


    /**
     * 刷新函数
     */
    private String page;

    /**
     * 菜单2
     */
    @ManyToOne(targetEntity = Menu2.class)
    @JoinColumn(name = "menu2_code", referencedColumnName = "code")
    private Menu2 menu2;

    /**
     * 菜单1
     */
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

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Menu2 getMenu2() {
        return menu2;
    }

    public void setMenu2(Menu2 menu2) {
        this.menu2 = menu2;
    }

    public Menu1 getMenu1() {
        return menu1;
    }

    public void setMenu1(Menu1 menu1) {
        this.menu1 = menu1;
    }

    @Override
    public String toString() {
        return "Model{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", operations=" + operations +
                ", rank=" + rank +
                ", path='" + path + '\'' +
                ", inter='" + inter + '\'' +
                ", page='" + page + '\'' +
                ", menu2=" + menu2 +
                ", menu1=" + menu1 +
                '}';
    }
}
