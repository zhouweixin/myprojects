package com.hnu.mes.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lanyage on 2018/3/22.
 */
@Entity
@Table(name = "basicinfo_supplymanage_header")
public class SupplyInfoHeader {

    /**
     * 合同号
     */
    @Id
    private String code;

    /**
     * 送货人
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_code")
    private Customer customer;

    /**
     *  送货时间
     */
    private Date supplyTime;

    /**
     * 联系电话
     */
    private String contact;

    /**
     * 总量
     */
    private int total;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getSupplyTime() {
        return supplyTime;
    }

    public void setSupplyTime(Date supplyTime) {
        this.supplyTime = supplyTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
