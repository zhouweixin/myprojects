package com.hnu.mes.repository;

import com.hnu.mes.domain.MaterialsEntry;
import com.hnu.mes.domain.RawType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:08 2018/5/9
 * @Modified By:
 */
@Repository
public interface MaterialsEntryRepository extends JpaRepository<MaterialsEntry, Long> {
    /**
     * 通过批号更新重量
     *
     * @param weight
     * @param batchNumber
     */
    @Modifying
    @Query(value = "update MaterialsEntry m set m.weight=m.weight-?1 where m.batchNumber=?2")
    public void updateWeightByBatchNumber(Double weight, String batchNumber);

    /**
     * 删除重量为0的记录
     *
     * @param weight
     */
    public void deleteByRawTypeAndWeightLessThan(RawType rawType, Double weight);
}
