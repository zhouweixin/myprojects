package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectReceipt;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:52:56 2018年5月24日
 */
@Repository
public interface ProjectReceiptRepository extends JpaRepository<ProjectReceipt, Long>{
	/**
	 * 通过项目查询-分页
	 * 
	 * @param project
	 * @param pageable
	 * @return
	 */
	public Page<ProjectReceipt> findByProject(Project project, Pageable pageable);
}
