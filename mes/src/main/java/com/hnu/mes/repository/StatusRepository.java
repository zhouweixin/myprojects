package com.hnu.mes.repository;

import com.hnu.mes.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhouweixin on 2018/3/21.
 */
public interface StatusRepository extends JpaRepository<Status, Integer>{

    /**
     * 通过名称查询状态
     *
     * @param name
     * @return
     */
    public Status findByName(String name);
}
