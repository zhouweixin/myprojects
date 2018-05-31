package com.hnu.mes.repository;

import com.hnu.mes.domain.LossEntryHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:38 2018/5/30
 * @Modified By:
 */
@Repository
public interface LossEntryHeaderRepository extends JpaRepository<LossEntryHeader, Long>{
}
