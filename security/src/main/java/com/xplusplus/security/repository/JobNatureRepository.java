package com.xplusplus.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.JobNature;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:40:45 2018年5月23日
 */
@Repository
public interface JobNatureRepository extends JpaRepository<JobNature, Integer>{

}
