package com.hnu.mes.repository;

import com.hnu.mes.domain.AvailableRawPresoma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description: 原料前驱体库存查询
 * @Date: Created in 12:35 2018/5/9
 * @Modified By:
 */
@Repository
public interface AvailableRawPresomaRepository extends JpaRepository<AvailableRawPresoma, String>{
    /**
     * 通过批号模糊查询-分页
     *
     * @param batchNumber
     * @return
     */
    public Page<AvailableRawPresoma> findAvailableRawPresomasByBatchNumberLike(String batchNumber, Pageable pageable);

    /**
     *  通过批号模糊查询
     * @param batchNumber
     * @return
     */
    public List<AvailableRawPresoma> findAvailableRawPresomasByBatchNumberLike(String batchNumber);
}
