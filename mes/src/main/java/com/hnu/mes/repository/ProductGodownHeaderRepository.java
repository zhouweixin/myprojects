package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductGodownHeader;
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
 * @Description:
 * @Date: Created in 16:26 2018/5/10
 * @Modified By:
 */
@Repository
public interface ProductGodownHeaderRepository extends JpaRepository<ProductGodownHeader, Long> {

    /**
     * 通过入库状态查询-分页
     *
     * @param status
     * @return
     */
    public List<ProductGodownHeader> findByStatus(Integer status);

    /**
     * 通过入库状态查询-分页
     *
     * @param status
     * @param pageable
     * @return
     */
    public Page<ProductGodownHeader> findByStatus(Integer status, Pageable pageable);

    /**
     * 通过编号查询
     *
     * @param batchNumber
     * @return
     */
    public ProductGodownHeader findFirstByBatchNumber(String batchNumber);

    /**
     * 通过batchNumber模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List<ProductGodownHeader> findByBatchNumberLike(String batchNumber);

    /**
     * 通过入库状态和编码模糊查询
     *
     * @param status
     * @param batchNum
     * @return
     */
    public List<ProductGodownHeader> findByStatusAndBatchNumberLike(Integer status, String batchNum);

    /**
     * 更新状态， 入库人，入库时间
     * @param status
     * @param godowner
     * @param godownTime
     */
    @Modifying
    @Query(value = "update ProductGodownHeader p set p.status=?1, p.godowner=?2, p.godownTime=?3 where p.code=?4")
    public void updateStatusAndGodownerAndGodownTimeByCode(Integer status, User godowner, Date godownTime, Long code);
}
