package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.repository.MessageQueueRepository;
import com.hnu.mes.repository.PickingAuditRepository;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:19 2018/5/5
 * @Modified By:
 */
@Service
public class PickingAuditService {
    @Autowired
    private PickingAuditRepository pickingAuditRepository;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private MaterialsEntryService materialsEntryService;

    /**
     * 领料申请表更新
     *
     * @param pickingApplyHeader
     * @param processManage
     * @param auditor
     * @param auditResult
     * @param note
     */
    public PickingAudit updateAuditResult(PickingApplyHeader pickingApplyHeader, ProcessManage processManage, User auditor, Integer auditResult, String note, User applier) {

        PickingAudit pickingAuditResult = null;

        // 创建【审核单】对象
        PickingAudit pickingAudit = new PickingAudit();

        // 领料申请表表头
        pickingAudit.setPickingApplyHeader(pickingApplyHeader);

        // 流程
        pickingAudit.setProcessManage(processManage);

        // 审核结果
        pickingAudit.setAuditResult(auditResult);

        // 审核时间
        pickingAudit.setAuditTime(new Date());

        // 审核人
        pickingAudit.setAuditor(auditor);

        // 审核备注
        pickingAudit.setNote(note);

        // 系统流程:紧急, 有多人审核
        if(processManage.getProcess() != null && processManage.getProcess().getCode() == GlobalUtil.URGENT_AUDIT){

        }else{// 其它正常流程, 只有一人审核
            // 创建申请审批表
             pickingAuditResult = pickingAuditRepository.save(pickingAudit);

            // 【添加消息】
            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setAddressee(applier);
            messageQueue.setContent("原料出库审批已完成");
            // TODO APP界面2+申请表ID+审核记录ID
            messageQueue.setUrl(pickingApplyHeader.getCode() + "-" + pickingAuditResult.getCode());
            messageQueueService.auditSave(messageQueue);

            // 如果同意
            if (auditResult == GlobalUtil.PickingAuditStatus.APPROVE) {
                // 减少库存
                materialsEntryService.updateWeightByBatchNumber(pickingApplyHeader.getPickingApplies());
            } else if (auditResult == GlobalUtil.PickingAuditStatus.NOT_APPROVE) {//不同意

            }
        }

        return pickingAuditResult;
    }

    /**
     * 通过领料申请头查询未审核的人
     *
     * @param pickingApplyHeader
     * @return
     */
    public Set<String> findAuditorCodesByPickingApplyHeader(PickingApplyHeader pickingApplyHeader){
        // 未审核人
        Set<String> unAuditorCodes = new HashSet<String>();
        // 已审核人
        Set<String> auditorCodes = new HashSet<String>();
        // 所有审核人
        Set<String> sumAuditorCodes = new HashSet<String>();

        // 已审核的人
        List<PickingAudit> pickingAudits = pickingAuditRepository.findByPickingApplyHeader(pickingApplyHeader);
        for(PickingAudit pickingAudit : pickingAudits){
            auditorCodes.add(pickingAudit.getAuditor().getCode());
        }

        if(pickingAudits == null){
            return unAuditorCodes;
        }

        // 审核流程上的审核人
        for(PickingAudit pickingAudit : pickingAudits){
            ProcessManage processManage = pickingAudit.getProcessManage();

            if(processManage != null) {
                if (processManage.getLeader1() != null) {
                    sumAuditorCodes.add(processManage.getLeader1().getCode());
                }

                if (processManage.getLeader2() != null) {
                    sumAuditorCodes.add(processManage.getLeader2().getCode());
                }

                if (processManage.getLeader3() != null) {
                    sumAuditorCodes.add(processManage.getLeader3().getCode());
                }

                if (processManage.getLeader4() != null) {
                    sumAuditorCodes.add(processManage.getLeader4().getCode());
                }

                if (processManage.getLeader5() != null) {
                    sumAuditorCodes.add(processManage.getLeader5().getCode());
                }
                break;
            }
        }

        for(String code : sumAuditorCodes){
            if(!auditorCodes.contains(code)){
                unAuditorCodes.add(code);
            }
        }
        return auditorCodes;
    }

    /**
     * 通过领料申请单查询审核记录
     *
     * @param pickingApplyHeader
     * @return
     */
    public List<PickingAudit> findByPickingApplyHeader(PickingApplyHeader pickingApplyHeader){
        return pickingAuditRepository.findByPickingApplyHeader(pickingApplyHeader);
    }
}
