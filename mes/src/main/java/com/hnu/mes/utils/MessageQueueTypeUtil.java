package com.hnu.mes.utils;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:23 2018/5/17
 * @Modified By:
 */
public enum MessageQueueTypeUtil {
    SEND_NOT_RECEIVE(1, "发货待收货"),
    RAW_GODOWN_TEST_INFORM(2, "原料入库检验通知"),
    RAW_PICKING(3, "原料出库"),
    RAW_PICKING_AUDIT(4, "原料出库审批"),
    GODOWN_PREPARE_GOODS(5, "备货通知"),
    PRODUCT_SEND_AUDIT(6, "产品出库审批"),

    ;

    private int code;
    private String message;

    private MessageQueueTypeUtil(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return code + "-" + message;
    }

}
