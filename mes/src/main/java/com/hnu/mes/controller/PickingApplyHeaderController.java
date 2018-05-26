package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.DepartmentService;
import com.hnu.mes.service.PickingApplyHeaderService;
import com.hnu.mes.service.ProcessManageService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:23 2018/5/5
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/pickingApplyHeader")
public class PickingApplyHeaderController {
    @Autowired
    private PickingApplyHeaderService pickingApplyHeaderService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProcessManageService processManageService;

    /**
     * 新增
     *
     * @param pickingApplyHeader
     * @return
     */
    @PostMapping(value = "/add")
    public Result<PickingApplyHeader> add(@RequestBody PickingApplyHeader pickingApplyHeader) {
        return ResultUtil.success(pickingApplyHeaderService.save(pickingApplyHeader));
    }

    /**
     * 更新
     *
     * @param pickingApplyHeader
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<PickingApplyHeader> update(PickingApplyHeader pickingApplyHeader) {

        PickingApplyHeader one = pickingApplyHeaderService.findOne(pickingApplyHeader.getCode());
        if (one == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if (one.getAuditStatus() != GlobalUtil.PickingApplyHeaderStatus.NOT_SUBMIT) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_SUBMIT_NOT_ALLOW));
        }

        if (pickingApplyHeader.getDepartment() == null || departmentService.findOne(pickingApplyHeader.getDepartment().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.SUBMIT_FAILED_DEPARTMENT_NULL_OR_NOT_EXIST));
        }

        if (pickingApplyHeader.getUser() == null || userService.findOne(pickingApplyHeader.getUser().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.SUBMIT_FAILED_USER_NULL_OR_NOT_EXIST));
        }

        if (pickingApplyHeader.getProcessManage() == null || processManageService.findOne(pickingApplyHeader.getProcessManage().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.SUBMIT_FAILED_PROCESS_MANAGE_NULL_OR_NOT_EXIST));
        }

        return ResultUtil.success(pickingApplyHeaderService.update(pickingApplyHeader));
    }

    /**
     * 正常审批
     *
     * @param code        申请表编码
     * @param auditStatus 审核结果
     * @return
     */
    @RequestMapping(value = "/updateAuditStatusByCode")
    public Result<Object> updateAuditStatusByCode(@RequestParam(name = "code") Long code,
                                                  @RequestParam(name = "auditStatus") Integer auditStatus,
                                                  @RequestParam(name = "note", defaultValue = "") String note,
                                                  @RequestParam(name = "auditorCode") String auditorCode) {

        pickingApplyHeaderService.updateAuditStatusByCode(auditStatus, note, code, auditorCode);
        return ResultUtil.success();
    }

    /**
     * 紧急审核
     *
     * @param code
     * @param auditStatus
     * @param note
     * @param auditorCode
     * @param nextAuditorCode
     * @return
     */
    @RequestMapping(value = "/updateAuditStatusByCodeUrgent")
    public Result<Object> updateAuditStatusByCodeUrgent(@RequestParam(name = "code") Long code,
                                                        @RequestParam(name = "auditStatus") Integer auditStatus,
                                                        @RequestParam(name = "note", defaultValue = "") String note,
                                                        @RequestParam(name = "auditorCode") String auditorCode,
                                                        @RequestParam(name = "nextAuditorCode") String nextAuditorCode){
        pickingApplyHeaderService.updateAuditStatusByCodeUrgent(auditStatus, note, code, auditorCode, nextAuditorCode);
        return ResultUtil.success();
    }

    /**
     * 通过申请表编码查询剩余审批用户
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getRestAuditorByCode")
    public Result<List<User>> getRestAuditorByCode(@RequestParam(name = "code") Long code, @RequestParam(name = "curAuditorCode") String curAuditorCode) {
        return ResultUtil.success(pickingApplyHeaderService.getRestAuditorByCode(code, curAuditorCode));
    }

    /**
     * 通过code查询
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<PickingApplyHeader> getByCode(Long code) {
        return ResultUtil.success(pickingApplyHeaderService.findOne(code));
    }

    /**
     * 通过code删除
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/deleteByCode")
    public Result<PickingApplyHeader> deleteByCode(Long code) {
        pickingApplyHeaderService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<PickingApplyHeader>> deleteByCode() {
        return ResultUtil.success(pickingApplyHeaderService.findAll());
    }

    /**
     * 查询所有-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByPage")
    public Result<Page<PickingApplyHeader>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                    @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                    @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(pickingApplyHeaderService.findAllByPage(page, size, sort, asc));
    }

    /**
     * 通过部门和申请时间查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByDepartmentAndApplyDateByPage")
    public Result<Page<PickingApplyHeader>> findByDepartmentAndApplyTimeBetweenByPage(
            @RequestParam(value = "departmentCode", defaultValue = "0") String departmentCode,
            @RequestParam(value = "applyDate", defaultValue = "0") Long applyDate,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Department department = new Department();
        department.setCode(departmentCode);

        return ResultUtil.success(pickingApplyHeaderService.findByDepartmentAndApplyTimeBetween(department, applyDate, page, size, sort, asc));
    }

    /**
     * 通过部门和领料时间查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByDepartmentAndPickingTimeByPage")
    public Result<Page<PickingApplyHeader>> findByDepartmentAndPickingTimeBetweenByPage(
            @RequestParam(value = "departmentCode", defaultValue = "0") String departmentCode,
            @RequestParam(value = "pickingTime", defaultValue = "0") Long pickingTime,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Department department = new Department();
        department.setCode(departmentCode);
        return ResultUtil.success(pickingApplyHeaderService.findByDepartmentAndPickingTimeBetween(department, pickingTime, page, size, sort, asc));
    }

    /**
     * 批量删除
     *
     * @param pickingApplyHeaders
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<PickingApplyHeader> pickingApplyHeaders) {
        pickingApplyHeaderService.deleteInBatch(pickingApplyHeaders);
        return ResultUtil.success();
    }

    /**
     * 通过审核状态查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByAuditStatusByPage")
    public Result<Page<PickingApplyHeader>> findByAuditStatusByPage(
            @RequestParam(value = "auditStatus", defaultValue = "0") Integer auditStatus,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(pickingApplyHeaderService.findByAuditStatus(auditStatus, page, size, sort, asc));
    }

    /**
     * 通过审核状态查询
     *
     * @param auditStatus
     * @return
     */
    @RequestMapping(value = "/getByAuditStatus")
    public Result<List<PickingApplyHeader>> findByAuditStatus(@RequestParam(value = "auditStatus", defaultValue = "1") Integer auditStatus) {
        return ResultUtil.success(pickingApplyHeaderService.findByAuditStatus(auditStatus));
    }

    /**
     * 通过领料状态查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByPickingStatusByPage")
    public Result<Page<PickingApplyHeader>> getByPickingStatusByPage(
            @RequestParam(value = "pickingStatus", defaultValue = "0") Integer pickingStatus,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(pickingApplyHeaderService.findByPickingStatus(pickingStatus, page, size, sort, asc));
    }

    /**
     * 通过领料状态查询
     *
     * @param pickingStatus
     * @return
     */
    @RequestMapping(value = "/getByPickingStatus")
    public Result<List<PickingApplyHeader>> getByPickingStatus(Integer pickingStatus){
        return ResultUtil.success(pickingApplyHeaderService.getByPickingStatus(pickingStatus));
    }

    /**
     * 通过流程类型查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByProcessManageByPage")
    public Result<Page<PickingApplyHeader>> getByProcessManageByPage(
            @RequestParam(value = "processManageCode", defaultValue = "0") Integer processManageCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        ProcessManage processManage = new ProcessManage();
        processManage.setCode(processManageCode);
        return ResultUtil.success(pickingApplyHeaderService.findByProcessManage(processManage, page, size, sort, asc));
    }

    /**
     * 通过审核状态和流程类型查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByAuditStatusAndProcessManageByPage")
    public Result<Page<PickingApplyHeader>> getByAuditStatusAndProcessManageByPage(
            @RequestParam(value = "auditStatus", defaultValue = "0") Integer auditStatus,
            @RequestParam(value = "processManageCode", defaultValue = "0") Integer processManageCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        ProcessManage processManage = new ProcessManage();
        processManage.setCode(processManageCode);
        return ResultUtil.success(pickingApplyHeaderService.findByAuditStatusAndProcessManage(auditStatus, processManage, page, size, sort, asc));
    }

    /**
     * 通过审核状态和流程类型查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByPickingStatusAndProcessManageByPage")
    public Result<Page<PickingApplyHeader>> getByPickingStatusAndProcessManageByPage(
            @RequestParam(value = "pickingStatus", defaultValue = "0") Integer pickingStatus,
            @RequestParam(value = "processManageCode", defaultValue = "0") Integer processManageCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        ProcessManage processManage = new ProcessManage();
        processManage.setCode(processManageCode);
        return ResultUtil.success(pickingApplyHeaderService.findByPickingStatusAndProcessManage(pickingStatus, processManage, page, size, sort, asc));
    }

    /**
     * 通过领料时间查询
     *
     * @param pickingTime
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByPickingTime")
    public Result<Page<PickingApplyHeader>> findByPickingTimeBetween(
            @RequestParam(value = "pickingTime", defaultValue = "") Long pickingTime,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(pickingApplyHeaderService.findByPickingTimeBetween(pickingTime, page, size, sort, asc));
    }

    /**
     * 原料出库检索
     *
     * @param departmentCode
     * @param processManageCode
     * @param pickingStatus
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByDepartmentAndProcessManageAndPickingStatusByPage")
    public Result<Page<PickingApplyHeader>> getByDepartmentAndProcessManageAndPickingStatusByPage(
            @RequestParam(value = "departmentCode", defaultValue = "") String departmentCode,
            @RequestParam(value = "processManageCode", defaultValue = "") Integer processManageCode,
            @RequestParam(value = "pickingStatus", defaultValue = "") Integer pickingStatus,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        Page<PickingApplyHeader> pickingApplyHeaderPage = null;

        if (departmentCode.equals("") && processManageCode.equals("") && pickingStatus.equals("")) {
            pickingApplyHeaderPage = pickingApplyHeaderService.findAllByPage(page, size, sort, asc);
        } else if (departmentCode.equals("") && processManageCode.equals("") && !pickingStatus.equals("")) {
            pickingApplyHeaderPage = pickingApplyHeaderService.findByPickingStatus(pickingStatus, page, size, sort, asc);
        } else if (departmentCode.equals("") && !processManageCode.equals("") && pickingStatus.equals("")) {
            ProcessManage processManage = new ProcessManage();
            processManage.setCode(processManageCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByProcessManage(processManage, page, size, sort, asc);
        } else if (!departmentCode.equals("") && processManageCode.equals("") && pickingStatus.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByDepartment(department, page, size, sort, asc);
        } else if (!departmentCode.equals("") && processManageCode.equals("") && !pickingStatus.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByDepartmentAndPickingStatus(department, pickingStatus, page, size, sort, asc);
        } else if (departmentCode.equals("") && !processManageCode.equals("") && !pickingStatus.equals("")) {
            ProcessManage processManage = new ProcessManage();
            processManage.setCode(processManageCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByProcessManageAndPickingStatus(processManage, pickingStatus, page, size, sort, asc);
        } else if (!departmentCode.equals("") && !processManageCode.equals("") && pickingStatus.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            ProcessManage processManage = new ProcessManage();
            processManage.setCode(processManageCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByDepartmentAndProcessManage(department, processManage, page, size, sort, asc);
        } else if (!departmentCode.equals("") && !processManageCode.equals("") && !pickingStatus.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            ProcessManage processManage = new ProcessManage();
            processManage.setCode(processManageCode);
            pickingApplyHeaderPage = pickingApplyHeaderService.findByDepartmentAndProcessManageAndPickingStatus(department, processManage, pickingStatus, page, size, sort, asc);
        }

        return ResultUtil.success(pickingApplyHeaderPage);
    }
}
