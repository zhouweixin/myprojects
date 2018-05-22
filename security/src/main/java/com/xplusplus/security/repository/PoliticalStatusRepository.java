package com.xplusplus.security.repository;

import com.xplusplus.security.domain.PoliticalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:31 2018/5/22
 * @Modified By:
 */
@Repository
public interface PoliticalStatusRepository extends JpaRepository<PoliticalStatus, Integer> {
}
