package com.hnu.mes.repository;

import com.hnu.mes.domain.SupplyInfo;
import com.hnu.mes.domain.SupplyInfoHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by lanyage on 2018/3/13.
 */
public interface SupplyInfoDao extends JpaRepository<SupplyInfo, String>, JpaSpecificationExecutor {
    public List<SupplyInfo> findSupplyInfosByHeader(SupplyInfoHeader header);
}
