package com.hnu.mes.repository;

import com.hnu.mes.domain.PickingApply;
import com.hnu.mes.domain.PickingApplyHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:17 2018/5/5
 * @Modified By:
 */
public interface PickingApplyRepository extends JpaRepository<PickingApply, Long> {
    /**
     * 通过领申请头查询-分页
     *
     * @param pickingApplyHeader
     * @return
     */
    public Page<PickingApply> findByPickingApplyHeader(PickingApplyHeader pickingApplyHeader, Pageable pageable);
}
