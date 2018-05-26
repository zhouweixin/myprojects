package com.hnu.mes.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "eqmanage_abnormal")
public class Abnormal {
    @Id
    @NotBlank(message = "编码不能为空")
    private String code;

    private String name;


    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Abnormal{" +
                "code=" + code +
                ", name='" + name +
                ", description='" + description +
                '}';
    }
}
