package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectStatus;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:30:21 2018年5月24日
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<Project> findByNameLike(String name, Pageable pageable);

	/**
	 * 通过项目状态查询-分页
	 * 
	 * @param projectStatus
	 * @return
	 */
	public Page<Project> findByProjectStatus(ProjectStatus projectStatus, Pageable pageable);

	/**
	 * 通过客户单位模糊查询-分页
	 * 
	 * @param customerUnit
	 * @param pageable
	 * @return
	 */
	public Page<Project> findByCustomerUnitLike(String customerUnit, Pageable pageable);

	/**
	 * 通过id更新已收款
	 * 
	 * @param receiptPrice
	 * @param id
	 */
	@Modifying
	@Query(value = "update Project p set p.receiptPrice=receiptPrice-?1 where id=?2")
	public void updateReceiptPriceById(Double receiptPrice, Long id);
}
