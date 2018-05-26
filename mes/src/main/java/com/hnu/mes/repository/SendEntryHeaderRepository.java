package com.hnu.mes.repository;

import com.hnu.mes.domain.Customer;
import com.hnu.mes.domain.SendEntryHeader;
import com.hnu.mes.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:15 2018/5/1
 * @Modified By:
 */
@Repository
public interface SendEntryHeaderRepository extends JpaRepository<SendEntryHeader, Long> {
    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update SendEntryHeader set status=?1 where code=?2")
    public void updateStatusByCode(Integer status, Long code);

    /**
     * 通过公司-分页查询
     *
     * @param supplier
     * @param pageable
     * @return
     */
    public Page<SendEntryHeader> findSendEntryHeadersBySupplier(Supplier supplier, Pageable pageable);

    /**
     * 通过状态-分页查询
     *
     * @param status
     * @param pageable
     * @return
     */
    public Page<SendEntryHeader> findSendEntryHeadersByStatusAndSender(Integer status, Customer sender, Pageable pageable);

    /**
     * 通过公司和状态-分布查询
     * @param supplier
     * @param status
     * @param pageable
     * @return
     */
    public Page<SendEntryHeader> findSendEntryHeaderBySupplierAndStatus(Supplier supplier, Integer status, Pageable pageable);
}
