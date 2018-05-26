package com.hnu.mes.repository;

import com.hnu.mes.domain.ProductSend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:15 2018/5/17
 * @Modified By:
 */
@Repository
public interface ProductSendRepository extends JpaRepository<ProductSend, Long> {
}
