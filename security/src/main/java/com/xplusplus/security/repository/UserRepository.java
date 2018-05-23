package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.domain.User;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:33 2018/5/7
 * @Modified By:
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<User> findByNameLike(String name, Pageable pageable);
	
	/**
	 * 通过部门简称模糊查询最大id
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "select max(u.id) from User u where u.id like ?1%")
	public String findIdMaxByIdLike(String id);
	
	/**
	 * 通过工作性质查询-分页
	 * 
	 * @param jobNature
	 * @param pageable
	 * @return
	 */
	public Page<User> findByJobNature(JobNature jobNature, Pageable pageable);
	
	/**
	 * 通过部门和名称模糊查询-分页
	 * 
	 * @param department
	 * @param name
	 * @return
	 */
	public Page<User> findByDepartmentAndNameLike(Department department, String name, Pageable pageable);
	
	/**
	 * 通过编码更新工作性质
	 * 
	 * @param jobNature
	 * @param id
	 */
	@Modifying
	@Query(value = "update User u set u.jobNature=?1 where u.id=?2")
	public void updateJobNatureById(JobNature jobNature, String id);
}
