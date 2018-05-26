package com.hnu.mes.domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;;

/**
 * 部门信息表
 *
 * @author zhouweixin
 *
 */
@Entity
@Table(name = "app_check_head")
public class AppCheckHead {
    /**
     * 编码
     */
    @Id
    @NotBlank(message = "编码不能为空")
    private String code;
    /**
     * 名称
     */
    private String name;




    /**
     * 信息
     */
    private String checkInfo;

    /**
     * 要求
     */
    private String checkRequire;




    /**
     * 发起人
     */
    private String checkInitiator;

    /**
     * num
     */
    private String num;


    /**
     * 点检内容1
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check1", referencedColumnName = "code")
    private Guide check1;

    /**
     * 点检内容2
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check2", referencedColumnName = "code")
    private Guide check2;

    /**
     * 点检内容3
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check3", referencedColumnName = "code")
    private Guide check3;


    /**
     * 点检内容4
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check4", referencedColumnName = "code")
    private Guide check4;

    /**
     * 点检内容5
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check5", referencedColumnName = "code")
    private Guide check5;

    /**
     * 点检内容6
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check6", referencedColumnName = "code")
    private Guide check6;

    /**
     * 点检内容7
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check7", referencedColumnName = "code")
    private Guide check7;

    /**
     * 点检内容8
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check8", referencedColumnName = "code")
    private Guide check8;

    /**
     * 点检内容9
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check9", referencedColumnName = "code")
    private Guide check9;

    /**
     * 点检内容10
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check10", referencedColumnName = "code")
    private Guide check10;

    /**
     * 点检内容11
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check11", referencedColumnName = "code")
    private Guide check11;

    /**
     * 点检内容12
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check12", referencedColumnName = "code")
    private Guide check12;

    /**
     * 点检内容13
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check13", referencedColumnName = "code")
    private Guide check13;

    /**
     * 点检内容12
     */
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "check14", referencedColumnName = "code")
    private Guide check14;


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

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire;
    }


    public String getCheckInitiator() {
        return checkInitiator;
    }

    public void setCheckInitiator(String checkInitiator) {
        this.checkInitiator = checkInitiator;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Guide getCheck1() {
        return check1;
    }

    public void setCheck1(Guide check1) {
        this.check1 = check1;
    }

    public Guide getCheck2() {
        return check2;
    }

    public void setCheck2(Guide check2) {
        this.check2 = check2;
    }

    public Guide getCheck3() {
        return check3;
    }

    public void setCheck3(Guide check3) {
        this.check3 = check3;
    }

    public Guide getCheck4() {
        return check4;
    }

    public void setCheck4(Guide check4) {
        this.check4 = check4;
    }

    public Guide getCheck5() {
        return check5;
    }

    public void setCheck5(Guide check5) {
        this.check5 = check5;
    }

    public Guide getCheck6() {
        return check6;
    }

    public void setCheck6(Guide check6) {
        this.check6 = check6;
    }

    public Guide getCheck7() {
        return check7;
    }

    public void setCheck7(Guide check7) {
        this.check7 = check7;
    }

    public Guide getCheck8() {
        return check8;
    }

    public void setCheck8(Guide check8) {
        this.check8 = check8;
    }

    public Guide getCheck9() {
        return check9;
    }

    public void setCheck9(Guide check9) {
        this.check9 = check9;
    }

    public Guide getCheck10() {
        return check10;
    }

    public void setCheck10(Guide check10) {
        this.check10 = check10;
    }

    public Guide getCheck11() {
        return check11;
    }

    public void setCheck11(Guide check11) {
        this.check11 = check11;
    }

    public Guide getCheck12() {
        return check12;
    }

    public void setCheck12(Guide check12) {
        this.check12 = check12;
    }


    public Guide getCheck13() {
        return check13;
    }

    public void setCheck13(Guide check13) {
        this.check13 = check13;
    }

    public Guide getCheck14() {
        return check14;
    }

    public void setCheck14(Guide check14) {
        this.check14 = check14;
    }

    @Override
    public String toString() {
        return "AppCheckHead [" +
                "code=" + code +
                ", name=" + name +
                ", checkInfo=" + checkInfo +
                ", checkRequire=" + checkRequire +
                ", checkInitiator=" + checkInitiator +
                ", num=" + num +'\'' +
                ", check1=" + check1 +'\'' +
                ", check2=" + check2 +'\'' +
                ", check3=" + check3 +'\'' +
                ", check4=" + check4 +'\'' +
                ", check5=" + check5 +'\'' +
                ", check6=" + check6 +'\'' +
                ", check7=" + check7 +'\'' +
                ", check8=" + check8 +'\'' +
                ", check9=" + check9 +'\'' +
                ", check10=" + check10 +'\'' +
                ", check11=" + check11+'\'' +
                ", check12=" + check12 +'\'' +
                ", check13=" + check13 +'\'' +
                ", check14=" + check14 +'\'' +
                "]";
    }
}
