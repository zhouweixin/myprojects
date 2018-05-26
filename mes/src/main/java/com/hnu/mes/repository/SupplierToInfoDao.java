package com.hnu.mes.repository;

import com.hnu.mes.domain.SupplierToInfo;
import com.hnu.mes.domain.SupplierToInfoPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by lanyage on 2018/3/22.
 */
public interface SupplierToInfoDao extends JpaRepository<SupplierToInfo, SupplierToInfoPrimaryKey>, JpaSpecificationExecutor {
    List<SupplierToInfo> findBySupplierCode(String code);
}
