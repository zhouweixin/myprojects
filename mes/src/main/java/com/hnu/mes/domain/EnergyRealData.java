package com.hnu.mes.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:03 2018/4/15
 * @Modified By:
 */
@Entity
@Table(name = "realdata_h_dec")
public class EnergyRealData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ino;

    private String ivalue = "0";

    @Temporal(TemporalType.DATE)
    private Date idate;

    private Integer ihour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIno() {
        return ino;
    }

    public void setIno(Integer ino) {
        this.ino = ino;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    public Integer getIhour() {
        return ihour;
    }

    public void setIhour(Integer ihour) {
        this.ihour = ihour;
    }

    @Override
    public String toString() {
        return "EnergyRealData{" +
                "id=" + id +
                ", ino=" + ino +
                ", ivalue='" + ivalue + '\'' +
                ", idate=" + idate +
                ", ihour=" + ihour +
                '}';
    }
}
