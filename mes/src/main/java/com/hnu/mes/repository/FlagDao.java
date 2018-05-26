package com.hnu.mes.repository;

import com.hnu.mes.domain.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lanyage on 2018/3/20.
 */
public interface FlagDao extends JpaRepository<Flag, String>, JpaSpecificationExecutor {
    Flag findByCode(int code);
}
