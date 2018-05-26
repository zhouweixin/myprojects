package com.hnu.mes.repository;

import com.hnu.mes.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:05 2018/4/13
 * @Modified By:
 */
public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
