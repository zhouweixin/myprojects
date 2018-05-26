package com.hnu.mes.repository;

import com.hnu.mes.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hnu.mes.domain.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * 更新用户
	 *
	 * @param name
	 * @param contact
	 * @param description
	 * @param code
	 */
	@Modifying
	@Query("update User set name=?1, contact=?2, description=?3 where code=?4")
	public void updateUserNameAndContactAndDescription(String name, String contact, String description, String code);

	/**
	 * 更新启用
	 * 
	 * @param enable
	 * @param code
	 */
	@Modifying
	@Query("update User set enable=?1 where code=?2)")
	public void updateUserEnable(Integer enable, String code);

	/**
	 * 更新部门
	 * @param departmentCode
	 * @param code
	 */
	@Modifying
	@Query("update User set department_code=?1 where code=?2)")
	public void updateUserDepartmentCode(String departmentCode, String code);

	/**
	 * 更新IC卡
	 * @param inteCircCard
	 * @param enableIc
	 * @param code
	 */
	@Modifying
	@Query("update User set inteCircCard=?1, enableIc=?2 where code=?3)")
	public void updateUserInteCircCard(String inteCircCard, Integer enableIc, String code);

	/**
	 * 重置密码
	 * 
	 * @param password
	 * @param code
	 */
	@Modifying
	@Query("update User set password=?1 where code=?2)")
	public void updateUserPassword(String password, String code);

	/**
	 * 更新密码
	 * 
	 * @param newPassword
	 * @param code
	 * @param oldPassword
	 */
	@Modifying
	@Query("update User set password=?1 where code=?2 and password=?3)")
	public Integer updateUserPassword(String newPassword, String code, String oldPassword);

	/**
	 * 登录
	 * 
	 * @param code
	 * @param password
	 * @return
	 */
	public User findByCodeAndPassword(String code, String password);

	/**
	 * 通过IC卡登录
	 * 
	 * @param inteCircCard
	 * @return
	 */
	public User findByInteCircCard(String inteCircCard);

	/**
	 * 通过部门查询用户
	 *
	 * @param department
	 * @return
	 */
    public List<User> findUsersByDepartment(Department department);

	/**
	 * 通过部门查询用户
	 *
	 * @param department
	 * @return
	 */
	public Page<User> findUsersByDepartment(Department department, Pageable pageable);

	/**
	 * 通过名称模糊查询-分页
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<User> findUserByNameLike(String name, Pageable pageable);

	/**
	 * 重置所有用户的密码
	 * @param defaultPassword
	 */
	@Modifying
	@Query(value = "update User u set u.password=?1")
    public void updateAllDefaultPassword(String defaultPassword);
}
