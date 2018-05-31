package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.*;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:47 2018/5/30
 * @Modified By:
 */
@Service
public class LossEntryHeaderService {
    @Autowired
    private LossEntryHeaderRepository lossEntryHeaderRepository;

    @Autowired
    private MaterialsTotalRepository materialsTotalRepository;

    @Autowired
    private LossEntryAuditRepository lossEntryAuditRepository;

    @Autowired
    private TakeStockRepository takeStockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterialsEntryRepository materialsEntryRepository;

    /**
     * 新增
     *
     * @param lossEntryHeader
     * @return
     */
    @Transactional
    public LossEntryHeader save(LossEntryHeader lossEntryHeader){

        System.out.println(lossEntryHeader);

        if(lossEntryHeader == null){
            // 不存在
            throw new MesException(EnumException.SUBMIT_FAILED_STOCKING_NOT_EXIST);
        }

        MaterialsTotal materialsTotal = materialsTotalRepository.findOne(lossEntryHeader.getMaterialsTotalCode());
        if(materialsTotal == null){
            // 不存在
            throw new MesException(EnumException.SUBMIT_FAILED_STOCKING_NOT_EXIST);
        }

        if(materialsTotal.getStatus() > GlobalUtil.StockStatus.START_SOTCK){
            // 已盘库
            throw new MesException(EnumException.SUBMIT_FAILED_STOCKING);
        }

        if(materialsTotal.getStatus() < GlobalUtil.StockStatus.START_SOTCK){
            // 未开始盘库
            throw new MesException(EnumException.SUBMIT_FAILED_NOT_START_STOCKING);
        }


        if(lossEntryHeader.getWeight() < 1e-5){
            // 修改库存表的状态为3已审核
            materialsTotalRepository.updateStatusByCoode(GlobalUtil.StockStatus.AUDITED, lossEntryHeader.getMaterialsTotalCode());

            // 修改报损单为不需审核
            lossEntryHeader.setAuditStatus(GlobalUtil.LossEntryAuditStatus.NOT_NEED);
        } else {
            // 修改库存表的状态为2待审核
            materialsTotalRepository.updateStatusByCoode(GlobalUtil.StockStatus.PRE_AUDIT, lossEntryHeader.getMaterialsTotalCode());

            // 修改报损单为待审核
            lossEntryHeader.setAuditStatus(GlobalUtil.LossEntryAuditStatus.PRE_AUDIT);
        }

        LossEntryHeader save = lossEntryHeaderRepository.save(lossEntryHeader);

        return save;
    }

    /**
     * 审核报损单
     *
     * @param status
     * @param note
     * @param nextAuditorCode
     * @param code
     */
    @Transactional
    public void audit(Integer status, String note, String curAuditorCode, String nextAuditorCode, Long code) {

        LossEntryHeader lossEntryHeader = lossEntryHeaderRepository.findOne(code);
        if(lossEntryHeader == null){
            // 不存在
            throw new MesException(EnumException.AUDITOR_FAILED_NOT_EXIST);
        }

        if(lossEntryHeader.getAuditStatus() > GlobalUtil.LossEntryAuditStatus.PRE_AUDIT){
            throw new MesException(EnumException.UPDATE_FAILED_AUDIT);
        }

        if(lossEntryHeader.getAuditStatus() < GlobalUtil.LossEntryAuditStatus.PRE_AUDIT){
            throw new MesException(EnumException.AUDITOR_FAILED__NOT_NEED);
        }

        // 审核记录
        LossEntryAudit lossEntryAudit = new LossEntryAudit();
        lossEntryAudit.setLossEntryHeader(lossEntryHeader);
        lossEntryAudit.setProcessManage(lossEntryAudit.getProcessManage());
        User curAuditor = new User();
        curAuditor.setCode(curAuditorCode);
        lossEntryAudit.setAuditor(curAuditor);
        lossEntryAudit.setAuditTime(new Date());
        lossEntryAudit.setNote(note);

        // 不通过
        if(status == 0){
            // 创建审核记录不通过
            lossEntryAudit.setAuditResult(GlobalUtil.LossEntryAuditStatus.NOT_APPROVAL);
            lossEntryAuditRepository.save(lossEntryAudit);

            // 盘库状态置为1开始盘库
            materialsTotalRepository.updateStatusByCoode(GlobalUtil.StockStatus.START_SOTCK, lossEntryHeader.getMaterialsTotalCode());
        } else {
            // 创建审核记录为通过
            lossEntryAudit.setAuditResult(GlobalUtil.LossEntryAuditStatus.APPROVAL);
            lossEntryAuditRepository.save(lossEntryAudit);

            if("-1".equals(nextAuditorCode)){
                // 审核结束, 更新库存状态为3已审核
                materialsTotalRepository.updateStatusByCoode(GlobalUtil.StockStatus.AUDITED, lossEntryHeader.getMaterialsTotalCode());

                // 修改库存
                Set<LossEntry> lossEntries = lossEntryHeader.getLossEntries();
                if(lossEntries != null){
                    for(LossEntry lossEntry : lossEntries){
                        lossEntry.getWeight();
                        lossEntry.getBatchNumber();
                        materialsEntryRepository.updateWeightByBatchNumber(lossEntry.getWeight(), lossEntry.getBatchNumber());
                    }
                }

                // 删除为0的库存
                materialsEntryRepository.deleteByRawTypeAndWeightLessThan(lossEntryHeader.getRawType(), 1e-5);

                // 生成盘库表
                TakeStock takeStock = new TakeStock();
                takeStock.setLossEntryHeader(lossEntryHeader);
                takeStock.setStockWeight(lossEntryHeader.getWeight());
                takeStock.setLossWeight(lossEntryHeader.getLossWeight());
                takeStock.setRestWeight(lossEntryHeader.getWeight() - lossEntryHeader.getLossWeight());
                takeStock.setTime(new Date());
                takeStockRepository.save(takeStock);
            }
        }
    }

    /**
     * 通过编码查询剩余审批人
     *
     * @param code
     * @return
     */
    public List<User> getRestAuditorByCode(Long code, String curAuditorCode) {

        // 校验申请单
        LossEntryHeader lossEntryHeader = lossEntryHeaderRepository.findOne(code);
        if(lossEntryHeader == null) {
            throw new MesException(EnumException.FIND_ERROE_PICKING_NOT_EXIST);
        }

        ProcessManage processManage = lossEntryHeader.getProcessManage();
        if(processManage == null) {
            throw new MesException(EnumException.PROCESS_TYPE_NOT_EXIST);
        }

        Set<String> userCodes = new HashSet<String>();

        if (processManage != null) {
            // 紧急
            if (processManage.getLeader2() != null) {
                userCodes.add(processManage.getLeader2().getCode());
            }

            if (processManage.getLeader3() != null) {
                userCodes.add(processManage.getLeader3().getCode());
            }

            if (processManage.getLeader4() != null) {
                userCodes.add(processManage.getLeader4().getCode());
            }

            if (processManage.getLeader5() != null) {
                userCodes.add(processManage.getLeader5().getCode());
            }
        }

        // 去重当前审核人
        if (userCodes.contains(curAuditorCode)) {
            userCodes.remove(curAuditorCode);
        }

        List<LossEntryAudit> lossEntryAudits = lossEntryAuditRepository.findByLossEntryHeader(lossEntryHeader);
        if (lossEntryAudits != null) {
            for (LossEntryAudit lossEntryAudit : lossEntryAudits) {
                if (lossEntryAudit.getAuditor() != null) {
                    if (userCodes.contains(lossEntryAudit.getAuditor().getCode())) {
                        userCodes.remove(lossEntryAudit.getAuditor().getCode());
                    }
                }
            }
        }

        return userRepository.findAll(userCodes);
    }
}
