package com.hnu.mes.repository;

import com.hnu.mes.domain.SendEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:15 2018/5/1
 * @Modified By:
 */
@Repository
public interface SendEntryRepository extends JpaRepository<SendEntry, Long> {
    @Modifying
    @Query(value = "update SendEntry set status=?1 where code=?2")
    public void updateStatusByCode(Integer status, Long code);
}
