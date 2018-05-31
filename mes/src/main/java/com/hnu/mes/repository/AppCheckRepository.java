package com.hnu.mes.repository;

import com.hnu.mes.domain.AppMission;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.AppCheck;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 *
 * @author pingxiao
 *
 */
public interface AppCheckRepository extends JpaRepository<AppCheck, Long> {

    /**
     * 更新审核人
     *
     * @param examPerson
     * @return
     */
    @Modifying
    @Query("UPDATE AppCheck p SET p.examPerson=?1,p.examState=?2,p.examDate=?3 where p.code=?4")
    public Integer updateExamPersonByCode(String examPerson, String examState, String examDate, Long code);

    /**
     * 查询审核state
     *
     * @param examState
     * @param pageable
     * @return
     */
    public Page<AppCheck> findByExamState(String examState, Pageable pageable);

}
