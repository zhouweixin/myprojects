package com.hnu.mes.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@Entity
@Table(name = "basicinfo_process_status")
public class ProcessStatus {
    @Id
    private String code;

    /**
     * 状态:0-5表示当前状态,5表示已全部审核,若审核人不足5个,则0-n表示当前已被n个人审核,完成状态依旧是5
     */
    private Integer status;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "curLeaderCode", referencedColumnName = "code")
    private User curLeader;

    private Date lastReviewTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getCurLeader() {
        return curLeader;
    }

    public void setCurLeader(User curLeader) {
        this.curLeader = curLeader;
    }

    public Date getLastReviewTime() {
        return lastReviewTime;
    }

    public void setLastReviewTime(Date lastReviewTime) {
        this.lastReviewTime = lastReviewTime;
    }

    @Override
    public String toString() {
        return "ProcessStatus{" +
                "code='" + code + '\'' +
                ", status=" + status +
                ", curLeader=" + curLeader +
                ", lastReviewTime=" + lastReviewTime +
                '}';
    }
}
