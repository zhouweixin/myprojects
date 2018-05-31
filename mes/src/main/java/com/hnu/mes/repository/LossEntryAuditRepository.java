package com.hnu.mes.repository;

import com.hnu.mes.domain.LossEntryAudit;
import com.hnu.mes.domain.LossEntryHeader;
import com.hnu.mes.domain.PickingAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:42 2018/5/30
 * @Modified By:
 */
@Repository
public interface LossEntryAuditRepository extends JpaRepository<LossEntryAudit, Long> {
    /**
     * 通过报损单查询
     *
     * @param lossEntryHeader
     * @return
     */
    public List<LossEntryAudit> findByLossEntryHeader(LossEntryHeader lossEntryHeader);
}
