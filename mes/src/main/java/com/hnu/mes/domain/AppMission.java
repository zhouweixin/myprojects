package com.hnu.mes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.annotations.Many;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 巡检任务表
 *
 * @author chenpingxiao
 *
 */
@Entity
@Table(name = "app_mission")
public class AppMission {
    /**
     * 编码
     */
    @Id
    @GeneratedValue
    private Long code;


    /**
     * 名称
     */
    private String name;


    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 点检时间
     */
    private String updateTime;



    /**
     * 审核时间
     */
    private String examTime;

    /**
     * 表头编码
     */
    private String checkCode;



    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }



    @Override
    public String toString() {
        return "AppMission{" +
                "code='" + code +
                ", name='" + name +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", updateTime=" + updateTime +
                ", examTime=" + examTime +
                ", checkCode=" + checkCode +
                '}';
    }
}

