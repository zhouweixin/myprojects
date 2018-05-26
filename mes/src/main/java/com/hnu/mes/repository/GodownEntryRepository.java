package com.hnu.mes.repository;

import com.hnu.mes.domain.GodownEntry;
import com.hnu.mes.domain.GodownEntryHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:43 2018/5/1
 * @Modified By:
 */
@Repository
public interface GodownEntryRepository extends JpaRepository<GodownEntry, Long> {
    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update GodownEntry set status=?1 where code=?2")
    public void updateStatusByCode(Integer status, Long code);

    /**
     * 通过入库表头和批号模糊查询
     *
     * @param godownEntryHeaders
     * @return
     */
    public List<GodownEntry> findByGodownEntryHeaderInAndBatchNumberLike(Collection<GodownEntryHeader> godownEntryHeaders, String batchNumber);

    /**
     * 通过入库表头和批号模糊查询-分页
     *
     * @param godownEntryHeaders
     * @param pageable
     * @return
     */
    public Page<GodownEntry> findByGodownEntryHeaderInAndBatchNumberLike(Collection<GodownEntryHeader> godownEntryHeaders, String batchNumber, Pageable pageable);
}
