package com.hnu.mes.repository;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.AppMission;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pingxiao
 *
 */
public interface AppMissionRepository extends JpaRepository<AppMission, Long> {


    /**
     * 查询点检时间
     *
     * @param updateTime
     * @param pageable
     * @return
     */
    public Page<AppMission> findByUpdateTime(String updateTime, Pageable pageable);

    /**
     * 更新点检时间
     *
     * @param updateTime
     * @return
     */
    @Modifying
    @Query("UPDATE AppMission p SET p.updateTime=?1 where p.code=?2")
    public Integer updateTimeByCode(String updateTime, Long code);

   //public List<AppMission> findNoCheck(String updateTime);

    /**
     * 查询审核时间
     *
     * @param examTime
     * @param pageable
     * @return
     */
    public Page<AppMission> findByExamTime(String examTime, Pageable pageable);

    /**
     * 更新审核时间
     *
     * @param examTime
     * @return
     */
    @Modifying
    @Query("UPDATE AppMission p SET p.examTime=?1 where p.code=?2")
    public Integer updateExamTimeByCode(String examTime, Long code);



}
