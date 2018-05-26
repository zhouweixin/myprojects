package com.hnu.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hnu.mes.domain.ProcessManage;
import com.hnu.mes.domain.Process;

import java.util.List;

/**
 *
 * @author zhouweixin
 *
 */
public interface ProcessManageRepository extends JpaRepository<ProcessManage, Integer>{
        /**
         * 通过流程类型查询
         *
         * @param process
         * @return
         */
    public List<ProcessManage> findByProcess(Process process);
}
