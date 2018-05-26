package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Many;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 指导书信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "eqmanage_guide_header")
public class GuideHeader {
    /**
     * 指导书编码
     */
    @Id
    @NotBlank(message = "指导书编码不能为空")
    private String code;


    /**
     * 指导书编号
     */
    @NotBlank(message = "指导书名称不能为空")
    private String name;

    /**
     * 指导书编号
     */
    private String num;

    /**
     * 版次
     */
    private String edition;

    /**
     * 生效日期
     */
    private String effectivedate;

    /**
     * 设备编码
     */
    @ManyToOne(targetEntity = Archive.class)
    @JoinColumn(name = "archivecode", referencedColumnName = "code")
    private Archive archivecode;

    /**
     * 编制人
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "compactorcode", referencedColumnName = "code")
    private User compactorcode;

    /**
     * 审核人
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "auditorcode", referencedColumnName = "code")
    private User auditorcode;


    /**
     * 批准人
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "approvercode", referencedColumnName = "code")
    private User approvercode;


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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Archive getArchivecode() {
        return archivecode;
    }

    public void setArchivecode(Archive archivecode) {
        this.archivecode = archivecode;
    }

    public User getCompactorcode() {
        return compactorcode;
    }

    public void setCompactorcode(User compactorcode) {
        this.compactorcode = compactorcode;
    }

    public User getAuditorcode() {
        return auditorcode;
    }

    public void setAuditorcode(User auditorcode) {
        this.auditorcode = auditorcode;
    }

    public User getApprovercode() {
        return approvercode;
    }

    public void setApprovercode(User approvercode) {
        this.approvercode = approvercode;
    }


    @Override
    public String toString() {
        return "GuideHeader{" +
                "code=" + code +
                "name=" + name +
                ", num=" + num +
                ", edition='" + edition +
                ", effectivedate='" + effectivedate + '\'' +
                ", archivecode='" + archivecode + '\'' +
                ", compactorcode='" + compactorcode + '\'' +
                ", auditorcode='" + auditorcode +'\'' +
                ", approvercode='" + approvercode +
                '}';
    }
}

