package com.hnu.mes.repository;

import com.hnu.mes.domain.BasicInfoProduct;
import com.hnu.mes.domain.EqRepairApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lanyage on 2018/3/20.
 */
public interface BasicInfoProductDao extends JpaRepository<BasicInfoProduct, String>, JpaSpecificationExecutor {
}
