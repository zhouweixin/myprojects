package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductSendHeader;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description: 产品出库
 * @Date: Created in 19:14 2018/5/17
 * @Modified By:
 */
@Repository
public interface ProductSendHeaderRepository extends JpaRepository<ProductSendHeader, Long> {
    /**
     * 通过number查询
     *
     * @param number
     * @return
     */
    public ProductSendHeader findFirstByNumber(String number);

    /**
     * 通过审批状态查询-分页
     *
     * @param auditStatus
     * @param pageable
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatus(Integer auditStatus, Pageable pageable);

    /**
     * 通过开单日期查询-分页
     *
     * @param createDate
     * @param pageable
     * @return
     */
    public Page<ProductSendHeader> findByCreateDate(Date createDate, Pageable pageable);

    /**
     * 通过产品型号查询-分页
     *
     * @param rawType
     * @param pageable
     * @return
     */
    public Page<ProductSendHeader> findByRawType(RawType rawType, Pageable pageable);

    /**
     * 通过审核状态和创建日期查询-分页
     *
     * @param auditStatus
     * @param createDate
     * @param pageable
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndCreateDate(Integer auditStatus, Date createDate, Pageable pageable);

    /**
     * 通过审核状态和产品类型查询-分页
     *
     * @param auditStatus
     * @param rawType
     * @param pageable
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndRawType(Integer auditStatus, RawType rawType, Pageable pageable);

    /**
     * 通过创建日期和原料类型查询-分页
     *
     * @param createDate
     * @param rawType
     * @return
     */
    public Page<ProductSendHeader> findByCreateDateAndRawType(Date createDate, RawType rawType, Pageable pageable);

    /**
     * 通过审核状态， 原料类型和创建日期分布查询
     *
     * @param auditStatus
     * @param rawType
     * @param createDate
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndRawTypeAndCreateDate(Integer auditStatus, RawType rawType, Date createDate, Pageable pageable);

    /**
     * 通过审核状态和编号查询
     *
     * @param auditStatus
     * @param number
     * @return
     */
    public List<ProductSendHeader> findByAuditStatusAndNumberLike(Integer auditStatus, String number);

    /**
     * 通过审核状态和编号查询
     *
     * @param auditStatus
     * @param number
     * @return
     */
    public List<ProductSendHeader> findByAuditStatusGreaterThanAndNumberLike(Integer auditStatus, String number);

    /**
     * 通过编码更新审核状态
     *
     * @param auditStatus
     * @param code
     */
    @Modifying
    @Query(value = "update ProductSendHeader p set p.auditStatus=?1 where p.code=?2")
    public void updateAuditStatusByCode(Integer auditStatus, Long code);

    /**
     * 通过出库状态和编号模糊查询
     *
     * @param outStatus
     * @return
     */
    public List<ProductSendHeader> findByOutStatusAndNumberLike(Integer outStatus, String number);

    @Modifying
    @Query(value = "update ProductSendHeader p set p.outStatus=?1, p.sender=?2, p.sendTime=3 where p.code=?4")
    public void updateOutStatusAndApplicantAndApplyTimeByCode(Integer outStatus, User sender, Date sendTime, Long code);
}
