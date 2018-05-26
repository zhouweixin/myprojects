package com.hnu.mes.repository;

import com.hnu.mes.domain.ProcessLithium;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/20
 * @Description:
 */
public interface ProcessLithiumRepository extends JpaRepository<ProcessLithium,Long>{
    /**
     * 通过批号和状态模糊查询
     *
     * @param batchNumber
     * @param statusCode
     * @param pageable
     * @return
     */
    public Page<ProcessLithium> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Pageable pageable);

    /**
     * 通过状态查询分页
     *
     * @param statusCode
     * @param pageable
     * @return
     */
    public Page<ProcessLithium> findByStatusCode(Integer statusCode, Pageable pageable);

    /**
     * 更新发布人，发布日期，状态
     *
     * @param publisher
     * @param status
     * @param code
     * @return
     */
    @Modifying
    @Query("UPDATE ProcessLithium p SET p.publisher=?1, p.publishDate=?2, p.status=?3 where p.code=?4")
    public Integer updatePublishByCode(User publisher, Date publishDate, Status status, Long code);

    /**
     * 更新审核人，审核日期，状态
     *
     * @param auditor
     * @param status
     * @param code
     * @return
     */
    @Modifying
    @Query("UPDATE ProcessLithium p SET p.auditor=?1, p.auditDate=?2, p.status=?3 where p.code=?4")
    public Integer updateAuditByCode(User auditor, Date auditDate, Status status, Long code);
}
