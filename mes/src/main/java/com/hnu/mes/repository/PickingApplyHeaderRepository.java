package com.hnu.mes.repository;

import com.hnu.mes.domain.Department;
import com.hnu.mes.domain.PickingApplyHeader;
import com.hnu.mes.domain.ProcessManage;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:17 2018/5/5
 * @Modified By:
 */
public interface PickingApplyHeaderRepository extends JpaRepository<PickingApplyHeader, Long> {

    /**
     * 通过部门和申请时间查询
     *
     * @param department
     * @param applyTime1
     * @param applyTime2
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByDepartmentAndApplyDateBetween(Department department, Date applyTime1, Date applyTime2, Pageable pageable);

    /**
     * 通过部门和领料时间查询
     *
     * @param department
     * @param applyTime1
     * @param applyTime2
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByDepartmentAndPickingTimeBetween(Department department, Date applyTime1, Date applyTime2, Pageable pageable);


    /**
     * 通过审核状态查询
     *
     * @param auditStatus
     * @return
     */
    public List<PickingApplyHeader> findByAuditStatus(Integer auditStatus);

    /**
     * 通过审核状态查询
     *
     * @param auditStatus1
     * @param auditStatus2
     * @return
     */
    public List<PickingApplyHeader> findByAuditStatusOrAuditStatus(Integer auditStatus1, Integer auditStatus2);

    /**
     * 通过审核状态查询-分页
     *
     * @param auditStatus
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByAuditStatus(Integer auditStatus, Pageable pageable);

    /**
     * 通过领料状态查询-分页
     *
     * @param pickingStatus
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByPickingStatus(Integer pickingStatus, Pageable pageable);

    /**
     * 通过流程类型查询-分页
     *
     * @param processManage
     * @return
     */
    public Page<PickingApplyHeader> findByProcessManage(ProcessManage processManage, Pageable pageable);

    /**
     * 通过审核状态和流程类型查询-分页
     *
     * @param auditStatus
     * @param processManage
     * @return
     */
    public Page<PickingApplyHeader> findByAuditStatusAndProcessManage(Integer auditStatus, ProcessManage processManage, Pageable pageable);

    /**
     * 通过领料状态和流程类型查询-分页
     *
     * @param pickingStatus
     * @param processManage
     * @return
     */
    public Page<PickingApplyHeader> findByPickingStatusAndProcessManage(Integer pickingStatus, ProcessManage processManage, Pageable pageable);

    /**
     * 通过领料部门, 流程, 领料状态分页查询
     *
     * @param department
     * @param processManage
     * @param pickingStatus
     * @return
     */
    public Page<PickingApplyHeader> findByDepartmentAndProcessManageAndPickingStatus(Department department, ProcessManage processManage, Integer pickingStatus, Pageable pageable);

    /**
     * 通过领料部门分页查询
     *
     * @param department
     * @return
     */
    public Page<PickingApplyHeader> findByDepartment(Department department, Pageable pageable);

    /**
     * 通过部门和领料状态查询
     *
     * @param department
     * @param pickingStatus
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByDepartmentAndPickingStatusAndAuditStatusGreaterThan(Department department, Integer pickingStatus, Integer auditStatus, Pageable pageable);

    /**
     * 通过流程和领料状态分布查询
     *
     * @param processManage
     * @param pickingStatus
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByProcessManageAndPickingStatusAndAuditStatusGreaterThan(ProcessManage processManage, Integer pickingStatus, Integer auditStatus, Pageable pageable);

    /**
     * 通过部门和流程分页查询
     *
     * @param department
     * @param processManage
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByDepartmentAndProcessManage(Department department, ProcessManage processManage, Pageable pageable);

    /**
     * 通过领料时间分页查询
     *
     * @param pickingTime1
     * @param pageable
     * @return
     */
    public Page<PickingApplyHeader> findByPickingTimeBetween(Date pickingTime1, Date pickingTime2, Pageable pageable);

    /**
     * 通过编码更新审核状态
     *
     * @param auditStatus
     * @param code
     */
    @Modifying
    @Query(value = "update PickingApplyHeader p set p.auditStatus=?1 where p.code=?2")
    public void updateAuditStatusByCode(Integer auditStatus, Long code);

    /**
     * 通过编号查询
     *
     * @param number
     * @return
     */
    public PickingApplyHeader findFirstByNumber(String number);

    /**
     * 通过主键查询
     *
     * @param code
     * @return
     */
    public PickingApplyHeader findByCode(Long code);

    /**
     * 更新当前审核人
     *
     * @param auditorCode
     * @param code
     */
    @Modifying
    @Query(value = "update PickingApplyHeader p set p.curAuditorCode=?1 where p.code=?2")
    public void updateCurAuditorCodeByCode(String auditorCode, Long code);

    /**
     * 通过审核状态和出库状态查询
     *
     * @param auditStatus
     * @param pickingStatus
     * @return
     */
    public List<PickingApplyHeader> findByAuditStatusGreaterThanAndPickingStatus(Integer auditStatus, Integer pickingStatus);

    /**
     * 通过审核状态和出库状态查询-分页
     *
     * @param auditStatus
     * @param pickingStatus
     * @return
     */
    public Page<PickingApplyHeader> findByAuditStatusGreaterThanAndPickingStatus(Integer auditStatus, Integer pickingStatus, Pageable pageable);
}
