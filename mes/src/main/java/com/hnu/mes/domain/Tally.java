package com.hnu.mes.domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 巡检信息表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "eqmanage_tally")
public class Tally {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "巡检编码不能为空")
    private String code;



    /**
     * 设备编码
     */

    private String archivecode;

    /**
     * 异常编码
     */

    private String abnormalcode;

    /**
     * 巡检人编码
     */

    private String inspectorcode;

    /**
     * 键和人编码
     */

    private String verificationcode;

    /**
     * 指导书编码
     */

    private String guidecode;

    /**
     * 日期
     */
    private String date;

    /**
     * 序号
     */
    private String num;


    private String inspectorstatus;



    private String verificationstatus;

    private String verificationtime;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArchivecode() {
        return archivecode;
    }

    public void setArchivecode(String archivecode) {
        this.archivecode = archivecode;
    }

    public String getAbnormalcode() {
        return abnormalcode;
    }

    public void setAbnormalcode(String abnormalcode) {
        this.abnormalcode = abnormalcode;
    }

    public String getInspectorcode() {
        return inspectorcode;
    }

    public void setInspectorcode(String inspectorcode) {
        this.inspectorcode = inspectorcode;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getGuidecode() {
        return guidecode;
    }

    public void setGuidecode(String guidecode) {
        this.guidecode = guidecode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getInspectorstatus() {
        return inspectorstatus;
    }

    public void setInspectorstatus(String inspectorstatus) {
        this.inspectorstatus = inspectorstatus;
    }

    public String getVerificationstatus() {
        return verificationstatus;
    }

    public void setVerificationstatus(String verificationstatus) {
        this.verificationstatus = verificationstatus;
    }

    public String getVerificationtime() {
        return verificationtime;
    }

    public void setVerificationtime(String verificationtime) {
        this.verificationtime = verificationtime;
    }



    @Override
    public String toString() {
        return "Tally{" +
                "code='" + code +'\'' +
                ", archivecode=" + archivecode +
                ", date=" + date +'\'' +
                ", guidecode=" + guidecode +
                ", num=" + num +'\'' +
                ", inspectorcode=" + inspectorcode +
                ", inspectorStatus=" + inspectorstatus +'\'' +
                ", verificationcode=" + verificationcode +
                ", verificationStatus=" + verificationstatus +
                ", verificationTime=" + verificationtime +
                ", abnormalcode=" + abnormalcode +
                '}';
    }
}
