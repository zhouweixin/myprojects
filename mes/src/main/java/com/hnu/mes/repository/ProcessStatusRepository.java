package com.hnu.mes.repository;

import com.hnu.mes.domain.ProcessStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhouweixin on 2018/3/22.
 */
public interface ProcessStatusRepository extends JpaRepository<ProcessStatus, String>{
    /**
     * 通过名称模糊查询-分页
     * @param status
     * @param pageable
     * @return
     */
    public Page<ProcessStatus> findByStatus(Integer status, Pageable pageable);
}
