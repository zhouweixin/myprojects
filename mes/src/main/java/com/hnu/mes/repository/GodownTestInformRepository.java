package com.hnu.mes.repository;

import com.hnu.mes.domain.GodownTestInform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:03 2018/5/2
 * @Modified By:
 */
@Repository
public interface GodownTestInformRepository extends JpaRepository<GodownTestInform, Long> {
}
