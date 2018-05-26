package com.hnu.mes.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lanyage on 2018/3/18.
 */
@Entity
@Table(name = "eqmanage_repair_application")
public class EqRepairApplication {
    /**
     * 主键
     */
    @Id
    private String code;

    /**
     * 部门
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "department_code")
    private Department department;

    /**
     * 设备编码
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "archive_code")
    private EqArchive eqArchive;

    /**
     * 申请时间
     */
    private Date applicationTime;

    /**
     * 产品线
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "productline_code")
    private ProductLine productLine;

    /**
     * 责任
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "duty_code")
    private Duty duty;

    /**
     * 申请人
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "application_person_code")
    private User applicationPerson;

    /**
     * 申请人联系电话
     */
    private String applicationPersonContact;

    /**
     * 描述
     */
    private String applicationDescription;
    private String repairmanDescription;

    /**
     * 订单时间
     */
    private Date orderTime;

    /**
     * 维修员
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "repairman_code")
    private User repairMan;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 评估信息
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "evaluation_code")
    private Evaluation evaluation;

    /**
     * 评估员
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "evaluator_code")
    private User evaluator;
    /**
     * 选择状态
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER, targetEntity = Flag.class)
    @JoinColumn(name = "flag_code")
    private Flag flag;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER, targetEntity = Equipment.class)
    @JoinColumn(name = "equipment_code")
    private Equipment equipment;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public User getApplicationPerson() {
        return applicationPerson;
    }

    public void setApplicationPerson(User applicationPerson) {
        this.applicationPerson = applicationPerson;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getRepairmanDescription() {
        return repairmanDescription;
    }

    public void setRepairmanDescription(String repairmanDescription) {
        this.repairmanDescription = repairmanDescription;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public User getRepairMan() {
        return repairMan;
    }

    public void setRepairMan(User repairMan) {
        this.repairMan = repairMan;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public User getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(User evaluator) {
        this.evaluator = evaluator;
    }

    public String getApplicationPersonContact() {
        return applicationPersonContact;
    }

    public void setApplicationPersonContact(String applicationPersonContact) {
        this.applicationPersonContact = applicationPersonContact;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EqArchive getEqArchive() {
        return eqArchive;
    }

    public void setEqArchive(EqArchive eqArchive) {
        this.eqArchive = eqArchive;
    }

    public Duty getDuty() {
        return duty;
    }

    public void setDuty(Duty duty) {
        this.duty = duty;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
