package com.hnu.mes.utils;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 9:17 2018/5/2
 * @Modified By:
 */
public class GlobalUtil {
    // 系统标志
    public static final int SYSTEM_FLAG = 0;

    // 紧急审核
    public static final int URGENT_AUDIT = 0;

    // 【消息状态】
    // 未读
    public static final int MESSAGE_NOT_READ = 0;
    // 已读
    public static final int MESSAGE_READ = 1;

    // 【发货单表头】
    // 待发货
    public static final int SEND_ENTRY_HEADER_PRE_SEND = 0;
    // 已发货
    public static final int SEND_ENTRY_HEADER_SENT = 1;
    // 已收货
    public static final int SEND_ENTRY_HEADER_RECEIVED = 2;

    // 【发货单】
    // 未收货
    public static final int SEND_ENTRY_NOT_RECEIVE = 0;
    // 已收货
    public static final int SEND_ENTRY_RECEIVED = 1;

    // 【入库单表头】
    // 样品未入库
    public static final int GODOWN_ENTRY_NOT_GODOWN = 0;
    // 样品已入库
    public static final int GODOWN_ENTRY_GODOWN = 1;

    // 【入库单】
    // 未化验
    public static final int GODOWN_ENTRY_NOT_TEST = 0;
    // 合格
    public static final int GODOWN_ENTRY_QUALIFIED = 1;
    // 不合格
    public static final int GODOWN_ENTRY_NOT_QUALIFIED = 2;

    // 【检验通知单表头】
    // 未领取
    public static final int TEST_INFORM_NOT_PICKING = 0;
    // 已领取
    public static final int TEST_INFORM_PICKING = 1;

    /**
     * 出库申请表头的状态信息
     */
    public class PickingApplyHeaderStatus{
        // 未提交
        public static final int NOT_SUBMIT = 0;
        // 待审批
        public static final int PRE_AUDIT = 1;
        // 通过
        public static final int APPROVE = 2;
        // 未通过
        public static final int NOT_APPROVE = 3;

        // 待出库
        public static final int PRE_PICKING = 0;
        // 已出库
        public static final int PICKING = 1;
    }

    public class GodownStatus{
        // 待入库
        public static final int PRE_GO_DOWN = 0;
        // 已入库
        public static final int GO_DOWN = 1;
    }

    /**
     * 领料审核状态
     */
    public class PickingAuditStatus{
        // 终止
        public static final int STOP = 0;
        // 打回
        public static final int REPULSE = 1;
        // 通过
        public static final int APPROVE = 2;
        // 不通过
        public static final int NOT_APPROVE = 3;
    }

    /**
     * 产品入库状态
     */
    public class ProductGodownStatus{
        // 待入库
        public static final int PRE_GODOWN = 0;
        // 入库
        public static final int GODOWN = 1;
    }

    public class ProductSendStatus{
        // 保存
        public static final int SAVE = 0;
        // 审核中
        public static final int AUDITTING = 1;
        // 通过
        public static final int APPROVE = 2;
        // 未通过
        public static final int NOT_APPROVE = 3;

        // 待出库
        public static final int PRE_OUT = 0;
        // 出库
        public static final int OUT = 1;
    }

    /**
     * 盘库状态
     */
    public class StockStatus{
        // 正常
        public static final int NORMAL = 0;
        // 开始盘库
        public static final int START_SOTCK = 1;
        // 待审核
        public static final int PRE_AUDIT = 2;
        // 已审核
        public static final int AUDITED = 3;
    }

    /**
     * 报损单审核状态
     */
    public class LossEntryAuditStatus{
        // 不需要审核
        public static final int NOT_NEED = 0;
        // 1待审核
        public static final int PRE_AUDIT = 1;
        // 2已通过
        public static final int APPROVAL = 2;
        // 3未通过
        public static final int NOT_APPROVAL = 3;
    }

    public enum AppPageType{

        PICKING_AUDIT(1, "领料申请审核"),

        ;
        private int code;
        private String name;

        private AppPageType(int code, String name){
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
