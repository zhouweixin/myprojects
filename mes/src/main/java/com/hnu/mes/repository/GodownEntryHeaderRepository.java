package com.hnu.mes.repository;

import com.hnu.mes.domain.GodownEntry;
import com.hnu.mes.domain.GodownEntryHeader;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description: 入库单表头
 * @Date: Created in 21:43 2018/5/1
 * @Modified By:
 */
@Repository
public interface GodownEntryHeaderRepository extends JpaRepository<GodownEntryHeader, Long> {
    /**
     * 更新状态
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update GodownEntryHeader set status=?1 where code=?2")
    public void updateStatusByCode(Integer status, Long code);

    /**
     * 通过入库编号查询
     * @param batchNumber
     * @return
     */
    public GodownEntryHeader findGodownEntryHeaderByBatchNumber(String batchNumber);

    /**
     * 通过状态查询-分页
     *
     * @param status
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersByStatus(Integer status, Pageable pageable);

    /**
     * 通过状态和发货厂家编码查询-分页
     *
     * @param status
     * @param supplier
     * @param pageable
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersByStatusAndSupplier(Integer status, Supplier supplier, Pageable pageable);

    /**
     * 通过发货厂家查询-分页
     *
     * @param supplier
     * @param pageable
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersBySupplier(Supplier supplier, Pageable pageable);

    /**
     * 通过rawType查询
     *
     * @param rawType
     * @return
     */
    public List<GodownEntryHeader> findByRawType(RawType rawType);

    /**
     * 通过批号查询
     *
     * @param batchNumber
     * @return
     */
    public GodownEntryHeader findFirstByBatchNumber(String batchNumber);
}
