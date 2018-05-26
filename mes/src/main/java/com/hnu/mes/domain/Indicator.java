package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 指标
 *
 * Created by zhouweixin on 2018/3/18.
 */
@Entity
@Table(name = "basicinfo_indicator")
public class Indicator {
    /**
     * 编码
     */
    @Id
    private String code;

    /**
     * 名称
     */
    private String name;

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

    @Override
    public String toString() {
        return "Indicator{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
