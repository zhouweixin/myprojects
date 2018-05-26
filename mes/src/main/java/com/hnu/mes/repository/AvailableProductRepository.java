package com.hnu.mes.repository;

import com.hnu.mes.domain.AvailableProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:54 2018/5/9
 * @Modified By:
 */
@Repository
public interface AvailableProductRepository extends JpaRepository<AvailableProduct, String> {
    /**
     * 通过批号模糊查询-分页
     *
     * @param batchNumber
     * @return
     */
    public Page<AvailableProduct> findAvailableAvailableProductsByBatchNumberLike(String batchNumber, Pageable pageable);

    /**
     * 通过批号模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List<AvailableProduct> findAvailableAvailableProductsByBatchNumberLike(String batchNumber);
}
