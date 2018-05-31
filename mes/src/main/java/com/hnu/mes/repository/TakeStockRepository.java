package com.hnu.mes.repository;

import com.hnu.mes.domain.TakeStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 22:02 2018/5/30
 * @Modified By:
 */
@Repository
public interface TakeStockRepository extends JpaRepository<TakeStock, Long> {
}
