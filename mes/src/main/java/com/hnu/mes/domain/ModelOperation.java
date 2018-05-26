package com.hnu.mes.domain;

import javax.persistence.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:20 2018/5/1
 * @Modified By:
 */
@Entity
@Table(name = "permission_model_operation")
@IdClass(value = ModelOperationPrimaryKey.class)
public class ModelOperation {
    @Id
    private Integer modelCode;
    @Id
    private Integer operationCode;

    public ModelOperation(){}

    public ModelOperation(Integer modelCode, Integer operationCode) {
        this.modelCode = modelCode;
        this.operationCode = operationCode;
    }

    public Integer getModelCode() {
        return modelCode;
    }

    public void setModelCode(Integer modelCode) {
        this.modelCode = modelCode;
    }

    public Integer getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(Integer operationCode) {
        this.operationCode = operationCode;
    }

    @Override
    public String toString() {
        return "ModelOperation{" +
                "modelCode=" + modelCode +
                ", operationCode=" + operationCode +
                '}';
    }
}
