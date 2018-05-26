package com.hnu.mes.repository;

import com.hnu.mes.domain.AvailableRawLithium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 11:00 2018/5/9
 * @Modified By:
 */
@Repository
public interface AvailableRawLithiumRepository extends JpaRepository<AvailableRawLithium, String> {
    /**
     * 通过批号模糊查询-分页
     *
     * @param batchNumber
     * @return
     */
    public Page<AvailableRawLithium> findAvailableRawLithiumsByBatchNumberLike(String batchNumber, Pageable pageable);

    /**
     * 通过批号模糊查询
     * @param batchNumber
     * @return
     */
    public List<AvailableRawLithium> findAvailableRawLithiumsByBatchNumberLike(String batchNumber);
}
