package com.hnu.mes.domain;


import com.hnu.mes.domain.Process;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 审核信息表
 *
 * @author chenpingxiao
 *
 */

@Entity
@Table(name = "basicinfo_process_manage")
public class ProcessManage {
    /**
     * 编码
     */
    @Id
    @NotNull(message = "流程编码不能为空")
    private Integer code;
    /**
     * 名称
     */
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "leader1_code", referencedColumnName = "code")
    private User leader1;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "leader2_code", referencedColumnName = "code")
    private User leader2;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "leader3_code", referencedColumnName = "code")
    private User leader3;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "leader4_code", referencedColumnName = "code")
    private User leader4;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "leader5_code", referencedColumnName = "code")
    private User leader5;

    @ManyToOne(targetEntity = Process.class)
    @JoinColumn(name = "process_code", referencedColumnName = "code")
    private Process process;

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

    public User getLeader1() {
        return leader1;
    }

    public void setLeader1(User leader1) {
        this.leader1 = leader1;
    }

    public User getLeader2() {
        return leader2;
    }

    public void setLeader2(User leader2) {
        this.leader2 = leader2;
    }

    public User getLeader3() {
        return leader3;
    }

    public void setLeader3(User leader3) {
        this.leader3 = leader3;
    }

    public User getLeader4() {
        return leader4;
    }

    public void setLeader4(User leader4) {
        this.leader4 = leader4;
    }

    public User getLeader5() {
        return leader5;
    }

    public void setLeader5(User leader5) {
        this.leader5 = leader5;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "ProcessManage{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", leader1=" + leader1 +
                ", leader2=" + leader2 +
                ", leader3=" + leader3 +
                ", leader4=" + leader4 +
                ", leader5=" + leader5 +
                ", process=" + process +
                '}';
    }
}
