package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:22 2018/5/22
 * @Modified By:
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<Department> findByNameLike(String name, Pageable pageable);
}
