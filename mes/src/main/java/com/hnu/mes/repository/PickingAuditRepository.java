package com.hnu.mes.repository;

import com.hnu.mes.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:17 2018/5/5
 * @Modified By:
 */
@Repository
public interface PickingAuditRepository extends JpaRepository<PickingAudit, Long> {
    /**
     * 审核领料申请表
     *
     * @param note
     * @param auditResult
     * @param auditTime
     * @param code
     */
    @Modifying
    @Query(value = "update PickingAudit p set p.note=?1, p.auditResult=?2, p.auditTime=?3 where p.code=?4")
    public void updateAuditResult(String note, Integer auditResult, Date auditTime, Long code);

    /**
     * 通过领料申请头查询
     *
     * @param pickingApplyHeader
     * @return
     */
    public List<PickingAudit> findByPickingApplyHeader(PickingApplyHeader pickingApplyHeader);

    /**
     * 通过申请单和审核人查询
     *
     * @param pickingApplyHeader
     * @param auditor
     * @return
     */
    public PickingAudit findFirstByPickingApplyHeaderAndAuditor(PickingApplyHeader pickingApplyHeader, User auditor);
}
