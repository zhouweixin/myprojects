package com.hnu.mes.repository;

import com.hnu.mes.domain.MaterialsTotal;
import com.hnu.mes.domain.RawType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
     * 通过编码更新重量
     *
     * @param weight
     * @param code
     */
    @Modifying
    @Query(value = "update MaterialsTotal m set m.weight=m.weight-?1 where m.code=?2")
    public void updateWeightByCode(double weight, Long code);
}
