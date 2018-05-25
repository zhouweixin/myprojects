package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.ProjectStatus;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:53:29 2018年5月24日
 */
@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer>{
	/**
	 * 通过名称模糊查询-分页
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<ProjectStatus> findByNameLike(String name, Pageable pageable);
}
