package com.hnu.mes.domain;

import java.io.Serializable;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:22 2018/5/1
 * @Modified By:
 */
public class ModelOperationPrimaryKey implements Serializable {
    private Integer modelCode;
    private Integer operationCode;

    public ModelOperationPrimaryKey(){}

    public ModelOperationPrimaryKey(ModelOperation modelOperation) {
        this.modelCode = modelOperation.getModelCode();
        this.operationCode = modelOperation.getOperationCode();
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
        return "ModelOperationPrimaryKey{" +
                "modelCode=" + modelCode +
                ", operationCode=" + operationCode +
                '}';
    }
}
