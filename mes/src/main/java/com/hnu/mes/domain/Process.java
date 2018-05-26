package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * 工序信息表
 */
@Entity
@Table(name = "basicinfo_process")
public class Process {
    /**
     * 工序编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    /**
     * 工序名称
     */
    private String name;

    /**
     * 标志:0表示系统创建,不允许删除
     */
    private Integer flag = 1;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Process{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }
}
