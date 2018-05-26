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
@Table(name = "app_check")
public class AppCheck {
    /**
     * 编码
     */
    @Id
    @GeneratedValue
    private Long code;

    /**
     * 表头
     */
    private String checkHeadCode;


    /**
     * time
     */
    private String time;

    /**
     * 是否合格1
     */
    private String state1;

    /**
     * 异常1
     */
    private String abnormal1;

    /**
     * 是否合格2
     */
    private String state2;

    /**
     * 异常2
     */
    private String abnormal2;

    /**
     * 是否合格3
     */
    private String state3;

    /**
     * 异常3
     */
    private String abnormal3;

    /**
     * 是否合格4
     */
    private String state4;

    /**
     * 异常4
     */
    private String abnormal4;

    /**
     * 是否合格5
     */
    private String state5;

    /**
     * 异常5
     */
    private String abnormal5;

    /**
     * 是否合格6
     */
    private String state6;

    /**
     * 异常6
     */
    private String abnormal6;

    /**
     * 是否合格7
     */
    private String state7;

    /**
     * 异常7
     */
    private String abnormal7;

    /**
     * 是否合格8
     */
    private String state8;

    /**
     * 异常
     */
    private String abnormal8;

    /**
     * 是否合格9
     */
    private String state9;

    /**
     * 异常9
     */
    private String abnormal9;


    /**
     * 是否合格10
     */
    private String state10;

    /**
     * 异常10
     */
    private String abnormal10;

    /**
     * 是否合格11
     */
    private String state11;

    /**
     * 异常11
     */
    private String abnormal11;

    /**
     * 是否合格12
     */
    private String state12;

    /**
     * 异常12
     */
    private String abnormal12;

    /**
     * 是否合格13
     */
    private String state13;


    /**
     * 异常13
     */
    private String abnormal13;

    /**
     * 是否合格14
     */
    private String state14;

    /**
     * 异常14
     */
    private String abnormal14;


    /**
     * 点检人
     */
    private String checkPerson;

    /**
     * 审核人
     */
    private String examPerson;

    /**
     * 审核state
     */
    private String examState;


    /**
     * 审核date
     */
    private String examDate;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCheckHeadCode() {
        return checkHeadCode;
    }

    public void setCheckHeadCode(String checkHeadCode) {
        this.checkHeadCode = checkHeadCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getAbnormal1() {
        return abnormal1;
    }

    public void setAbnormal1(String abnormal1) {
        this.abnormal1 = abnormal1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getAbnormal2() {
        return abnormal2;
    }

    public void setAbnormal2(String abnormal2) {
        this.abnormal2 = abnormal2;
    }

    public String getState3() {
        return state3;
    }

    public void setState3(String state3) {
        this.state3 = state3;
    }

    public String getAbnormal3() {
        return abnormal3;
    }

    public void setAbnormal3(String abnormal3) {
        this.abnormal3 = abnormal3;
    }

    public String getState4() {
        return state4;
    }

    public void setState4(String state4) {
        this.state4 = state4;
    }

    public String getAbnormal4() {
        return abnormal4;
    }

    public void setAbnormal4(String abnormal4) {
        this.abnormal4 = abnormal4;
    }

    public String getState5() {
        return state5;
    }

    public void setState5(String state5) {
        this.state5 = state5;
    }

    public String getAbnormal5() {
        return abnormal5;
    }

    public void setAbnormal5(String abnormal5) {
        this.abnormal5 = abnormal5;
    }

    public String getState6() {
        return state6;
    }

    public void setState6(String state6) {
        this.state6 = state6;
    }

    public String getAbnormal6() {
        return abnormal6;
    }

    public void setAbnormal6(String abnormal6) {
        this.abnormal6 = abnormal6;
    }

    public String getState7() {
        return state7;
    }

    public void setState7(String state7) {
        this.state7 = state7;
    }

    public String getAbnormal7() {
        return abnormal7;
    }

    public void setAbnormal7(String abnormal7) {
        this.abnormal7 = abnormal7;
    }

    public String getState8() {
        return state8;
    }

    public void setState8(String state8) {
        this.state8 = state8;
    }

    public String getAbnormal8() {
        return abnormal8;
    }

    public void setAbnormal8(String abnormal8) {
        this.abnormal8 = abnormal8;
    }

    public String getState9() {
        return state9;
    }

    public void setState9(String state9) {
        this.state9 = state9;
    }

    public String getAbnormal9() {
        return abnormal9;
    }

    public void setAbnormal9(String abnormal9) {
        this.abnormal9 = abnormal9;
    }

    public String getState10() {
        return state10;
    }

    public void setState10(String state10) {
        this.state10 = state10;
    }

    public String getAbnormal10() {
        return abnormal10;
    }

    public void setAbnormal10(String abnormal10) {
        this.abnormal10 = abnormal10;
    }

    public String getState11() {
        return state11;
    }

    public void setState11(String state11) {
        this.state11 = state11;
    }

    public String getAbnormal11() {
        return abnormal11;
    }

    public void setAbnormal11(String abnormal11) {
        this.abnormal11 = abnormal11;
    }

    public String getState12() {
        return state12;
    }

    public void setState12(String state12) {
        this.state12 = state12;
    }

    public String getAbnormal12() {
        return abnormal12;
    }

    public void setAbnormal12(String abnormal12) {
        this.abnormal12 = abnormal12;
    }


    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getExamPerson() {
        return examPerson;
    }

    public void setExamPerson(String examPerson) {
        this.examPerson = examPerson;
    }


    public String getState13() {
        return state13;
    }

    public void setState13(String state13) {
        this.state13 = state13;
    }

    public String getAbnormal13() {
        return abnormal13;
    }

    public void setAbnormal13(String abnormal13) {
        this.abnormal13 = abnormal13;
    }

    public String getState14() {
        return state14;
    }

    public void setState14(String state14) {
        this.state14 = state14;
    }

    public String getAbnormal14() {
        return abnormal14;
    }

    public void setAbnormal14(String abnormal14) {
        this.abnormal14 = abnormal14;
    }

    public String getExamState() {
        return examState;
    }

    public void setExamState(String examState) {
        this.examState = examState;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }





    @Override
    public String toString() {
        return "AppCheck [" +
                "code=" + code +
                ", checkHeadCode=" + checkHeadCode +
                ", time=" + time +
                ", state1=" + state1 +
                ", abnormal1=" + abnormal1 +
                ", state2=" + state2 +
                ", abnormal2=" + abnormal2 +
                ", state3=" + state3 +
                ", abnormal3=" + abnormal3 +
                ", state4=" + state4 +
                ", abnormal4=" + abnormal4 +
                ", state5=" + state5 +
                ", abnormal5=" + abnormal5 +
                ", state6=" + state6 +
                ", abnormal6=" + abnormal6 +
                ", state7=" + state7 +
                ", abnormal7=" + abnormal7 +
                ", state8=" + state8 +
                ", abnormal8=" + abnormal8 +
                ", state9=" + state9 +
                ", abnormal9=" + abnormal9 +
                ", state10=" + state10 +
                ", abnormal10=" + abnormal10 +
                ", state11=" + state11 +
                ", abnormal11=" + abnormal11 +
                ", state12=" + state12 +
                ", abnormal12=" + abnormal12 +
                ", state13=" + state13 +
                ", abnormal13=" + abnormal14 +
                ", state14=" + state14 +
                ", abnormal14=" + abnormal14 +
                ", checkPerson=" + checkPerson +
                ", examPerson=" + examPerson +
                ", examState=" + examState +
                ", examDate=" + examDate +
                "]";
    }
}
