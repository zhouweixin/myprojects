package com.hnu.mes.repository;

import com.hnu.mes.domain.GodownTestInformHeader;
import com.hnu.mes.domain.Supplier;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:02 2018/5/2
 * @Modified By:
 */
@Repository
public interface GodownTestInformHeaderRepository extends JpaRepository<GodownTestInformHeader, Long> {
    /**
     * 通过状态和领取人查询
     *
     * @param status
     * @param receiptor
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatusAndAndReceiptor(Integer status, User receiptor, Pageable pageable);

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update GodownTestInformHeader set status=?1, receiveTime=?2 where code=?3")
    public void updateStatusByCode(Integer status, Date receiveTime, Long code);

    /**
     * 通过状态查询
     *
     * @param status
     * @param pageable
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatus(Integer status, Pageable pageable);

    /**
     * 通过生产厂家查询
     *
     * @param supplier
     * @param pageable
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersBySupplier(Supplier supplier, Pageable pageable);

    /**
     * 通过状态和生产厂家查询
     * @param status
     * @param supplier
     * @param pageable
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatusAndSupplier(Integer status, Supplier supplier, Pageable pageable);


}
