package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * Created by zhouweixin on 2018/3/18.
 */
@Entity
@Table(name = "basicinfo_bound")
public class Bound {
    /**
     * 编码
     */
    @Id
    private String code;

    /**
     * 序号
     */
    private Integer num;

    /**
     * 指标
     */
    @ManyToOne(targetEntity = Indicator.class)
    @JoinColumn(name = "indicator_code", referencedColumnName = "code")
    private Indicator indicator;

    /**
     * 上界
     */
    private Double upperBound;

    /**
     * 下界
     */
    private Double downBound;

    /**
     * 均值
     */
    private Double mean;

    /**
     * 3位的标准差上界
     */
    private Double upperStandardDeviation;

    /**
     * 3倍的标准差下界
     */
    private Double downStandardDeviation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public Double getDownBound() {
        return downBound;
    }

    public void setDownBound(Double downBound) {
        this.downBound = downBound;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getUpperStandardDeviation() {
        return upperStandardDeviation;
    }

    public void setUpperStandardDeviation(Double upperStandardDeviation) {
        this.upperStandardDeviation = upperStandardDeviation;
    }

    public Double getDownStandardDeviation() {
        return downStandardDeviation;
    }

    public void setDownStandardDeviation(Double downStandardDeviation) {
        this.downStandardDeviation = downStandardDeviation;
    }

    @Override
    public String toString() {
        return "Bound{" +
                "code='" + code + '\'' +
                ", num=" + num +
                ", indicator=" + indicator +
                ", upperBound=" + upperBound +
                ", downBound=" + downBound +
                ", mean=" + mean +
                ", upperStandardDeviation=" + upperStandardDeviation +
                ", downStandardDeviation=" + downStandardDeviation +
                '}';
    }
}
