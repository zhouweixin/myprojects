package com.hnu.mes.domain;

/**
 * Created by lanyage on 2018/3/28.
 */
public class RealData {
    private String code;
    private String weihao;
    private String value;
    private String condition;
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RealData{" +
                "code='" + code + '\'' +
                ", weihao='" + weihao + '\'' +
                ", value='" + value + '\'' +
                ", condition='" + condition + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
