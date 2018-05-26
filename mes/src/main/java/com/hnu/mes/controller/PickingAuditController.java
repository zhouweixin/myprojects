package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.*;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:24 2018/5/5
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/pickingAudit")
public class PickingAuditController {
    @Autowired
    private PickingAuditService pickingAuditService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProcessManageService processManageService;

    @Autowired
    private PickingApplyHeaderService pickingApplyHeaderService;

    /**
     * 更新审核状态
     *
     * @param auditResult
     * @param note
     * @param pickingApplyHeaderCode
     * @param processManageCode
     * @param auditorCode
     * @param applierCode
     * @return
     */
    public Result<Object> updateAuditResult(@RequestParam(value = "auditResult") Integer auditResult,
                                            @RequestParam(value = "note") String note,
                                            @RequestParam(value = "pickingApplyHeaderCode") Long pickingApplyHeaderCode,
                                            @RequestParam(value = "processManageCode") Integer processManageCode,
                                            @RequestParam(value = "auditorCode") String auditorCode,
                                            @RequestParam(value = "applierCode") String applierCode){

        PickingApplyHeader pickingApplyHeader = pickingApplyHeaderService.findOne(pickingApplyHeaderCode);
        if(pickingApplyHeader == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_PICKING_APPLY_NOT_EXIST));
        }

        ProcessManage processManage = processManageService.findOne(processManageCode);
        if(processManage == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_PROCESS_MANAGE_NOT_EXIST));
        }

        User auditor = userService.findOne(auditorCode);
        if(auditor == null){
            return ResultUtil.error(new MesException(EnumException.AUDITOR_NOT_EXIST));
        }

        User applier = userService.findOne(applierCode);
        if(auditor == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_APPLIER_NOT_EXIST));
        }

        pickingAuditService.updateAuditResult(pickingApplyHeader, processManage, auditor, auditResult, note, applier);
        return ResultUtil.success();
    }

    /**
     * 更新审核状态-紧急
     *
     * @param auditResult
     * @param note
     * @param pickingApplyHeaderCode
     * @param processManageCode
     * @param auditorCode
     * @param applierCode
     * @return
     */
    public Result<Object> updateAuditResultByUrgent(@RequestParam(value = "auditResult") Integer auditResult,
                                            @RequestParam(value = "note") String note,
                                            @RequestParam(value = "pickingApplyHeaderCode") Long pickingApplyHeaderCode,
                                            @RequestParam(value = "processManageCode") Integer processManageCode,
                                            @RequestParam(value = "auditorCode") String auditorCode,
                                            @RequestParam(value = "applierCode") String applierCode,
                                            @RequestParam(value = "nextAuditorCode", defaultValue = "") String nextAuditorCode){

        PickingApplyHeader pickingApplyHeader = pickingApplyHeaderService.findOne(pickingApplyHeaderCode);
        if(pickingApplyHeader == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_PICKING_APPLY_NOT_EXIST));
        }

        ProcessManage processManage = processManageService.findOne(processManageCode);
        if(processManage == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_PROCESS_MANAGE_NOT_EXIST));
        }

        // 查询未审核的人
        Set<String> auditorCodes = pickingAuditService.findAuditorCodesByPickingApplyHeader(pickingApplyHeader);
        if(auditorCodes == null || auditorCodes.size() == 0 || !auditorCodes.contains(nextAuditorCode)){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_AUDITOR_NOT_EXIST_OR_AUDIT));
        }

        /**
         * 下一个审核人
         */
        User nextAuditor = userService.findOne(nextAuditorCode);
        if(processManage.getProcess() != null && processManage.getProcess().getCode() == GlobalUtil.URGENT_AUDIT && nextAuditor == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_PROCESS_MANAGE_NOT_EXIST));
        }

        User auditor = userService.findOne(auditorCode);
        if(auditor == null){
            return ResultUtil.error(new MesException(EnumException.AUDITOR_NOT_EXIST));
        }

        User applier = userService.findOne(applierCode);
        if(auditor == null){
            return ResultUtil.error(new MesException(EnumException.AUDIT_FAILED_APPLIER_NOT_EXIST));
        }

        pickingAuditService.updateAuditResult(pickingApplyHeader, processManage, auditor, auditResult, note, applier);
        return ResultUtil.success();
    }

    /**
     * 通过领料申请单查询审核记录
     *
     * @param pickingApplyHeaderCode
     * @return
     */
    @RequestMapping(value = "/getByPickingApplyHeader")
    public Result<List<PickingAudit>> getByPickingApplyHeader(Long pickingApplyHeaderCode){
        PickingApplyHeader pickingApplyHeader = new PickingApplyHeader();
        pickingApplyHeader.setCode(pickingApplyHeaderCode);
        return ResultUtil.success(pickingAuditService.findByPickingApplyHeader(pickingApplyHeader));
    }
}
