package com.hnu.mes.domain;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 指导书信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "eqmanage_guide")
public class Guide {
    /**
     * 档案编码
     */
    @Id
    @NotBlank(message = "档案编码不能为空")
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 序号
     */
    private Integer num;


    /**
     * 内容
     */
    private String content;

    /**
     * 标准
     */
    private String standard;

    /**
     * 参考图
     */
    private String picture;

    /**
     * 周期编码
     */
    @ManyToOne(targetEntity = Cycle.class)
    @JoinColumn(name = "cyclecode", referencedColumnName = "code")
    private Cycle cycleCode;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Cycle getCycleCode() {
        return cycleCode;
    }

    public void setCycleCode(Cycle cycleCode) {
        this.cycleCode = cycleCode;
    }


    @Override
    public String toString() {
        return "Guide{" +
                "code='" + code +
                ", num=" + num +
                ", content=" + content +
                ", standard=" + standard +
                ", picture=" + picture +'\'' +
                ", cycleCode=" + cycleCode +
                '}';
    }
}

