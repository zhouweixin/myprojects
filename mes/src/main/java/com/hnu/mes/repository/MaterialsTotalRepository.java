package com.hnu.mes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hnu.mes.domain.MaterialsTotal;
import com.hnu.mes.domain.RawType;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:07 2018/5/9
 * @Modified By:
 */
public interface MaterialsTotalRepository extends JpaRepository<MaterialsTotal, Long> {
    /**
     * 通过原料名称查询
     *
     * @param rawType
     * @return
     */
    public MaterialsTotal findFirstByRawType(RawType rawType);
    
    /**
     * 通过状态查询-分页
     * 
     * @param status
     * @param pageable
     * @return
     */
    public Page<MaterialsTotal> findByStatus(Integer status, Pageable pageable);

    /**
     * 通过预警状态查询-分页
     *
     * @param warnStatus
     * @param pageable
     * @return
     */
    public Page<MaterialsTotal> findByWarnStatus(Integer warnStatus, Pageable pageable);
    
    /**
     * 通过状态查询
     * 
     * @param status
     * @return
     */
    public MaterialsTotal findFirstByStatus(Integer status);
    
    /**
     * 更新全部的状态
     * 
     * @param status
     */
    @Modifying
    @Query(value = "update MaterialsTotal m set m.status=?1")
    public void updateAllStatus(Integer status);

    /**
     * 通过主键更新
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update MaterialsTotal m set m.status=?1 where m.code=?2")
    public void updateStatusByCoode(Integer status, Long code);

    /**
     * 通过主键修改库存量
     *
     * @param weight
     * @param code
     */
    @Modifying
    @Query(value = "update MaterialsTotal m set m.weight=?1 where m.code=?2")
    public void updateWeightByCode(Double weight, Long code);
}
