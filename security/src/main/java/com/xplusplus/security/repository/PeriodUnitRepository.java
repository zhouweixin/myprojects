package com.xplusplus.security.repository;

import com.xplusplus.security.domain.PeriodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:30 2018/5/22
 * @Modified By:
 */
@Repository
public interface PeriodUnitRepository extends JpaRepository<PeriodUnit, Integer> {
}
