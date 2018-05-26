package com.hnu.mes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;;

/**
 * 部门信息表
 *
 * @author chenpingxiao
 *
 *
 */
@Entity
@Table(name = "basicinfo_company")
public class Company {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "公司编码不能为空")
    private String code;
    /**
     * 名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String name;


    /**
     * 信用代码
     */
    private String cocode;

    /**
     * 地址
     */
    private String coaddress;
    /**
     * 联系人
     */
    private String cokeeper;

    /**
     * 电话
     */
    private String cophone;


    /**
     * 公司类型
     */
    private String cotype;


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

    public String getCocode() {
        return cocode;
    }

    public void setCocode(String cocode) {
        this.cocode = cocode;
    }

    public String getCoaddress() {
        return coaddress;
    }

    public void setCoaddress(String coaddress) {
        this.coaddress = coaddress;
    }

    public String getCokeeper() {
        return cokeeper;
    }

    public void setCokeeper(String cokeeper) {
        this.cokeeper = cokeeper;
    }

    public String getCophone() {
        return cophone;
    }

    public void setCophone(String cophone) {
        this.cophone = cophone;
    }

    public String getCotype() {
        return cotype;
    }

    public void setCotype(String cotype) {
        this.cotype = cotype;
    }

    @Override
    public String toString() {
        return "Company [code=" + code + ", name=" + name + ", cocode=" + cocode +", coaddress=" + coaddress +", cokeeper=" + cokeeper +", cophone=" + cophone +", cotype=" + cotype + "]";
    }
}
