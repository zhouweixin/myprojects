package com.hnu.mes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description: 样品入库及检验通知表
 * @Date: Created in 22:41 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "warehouse_godown_test_inform")
public class GodownTestInform {
    // 编码
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    // 样品入库及检验通知表头
    @ManyToOne(targetEntity = GodownTestInformHeader.class)
    @JoinColumn(name = "inform_header_code", referencedColumnName = "code")
    private GodownTestInformHeader godownTestInformHeader;

    // 批号
    private String batchNumber;

    public GodownTestInform(){}

    public GodownTestInform(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @JsonIgnore
    public GodownTestInformHeader getGodownTestInformHeader() {
        return godownTestInformHeader;
    }

    public void setGodownTestInformHeader(GodownTestInformHeader godownTestInformHeader) {
        this.godownTestInformHeader = godownTestInformHeader;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
