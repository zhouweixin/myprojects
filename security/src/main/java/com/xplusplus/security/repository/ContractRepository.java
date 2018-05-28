package com.xplusplus.security.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.Contract;
import com.xplusplus.security.domain.ContractStatus;
import com.xplusplus.security.domain.ContractType;
import com.xplusplus.security.domain.User;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:24:41 2018年5月23日
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
	/**
	 * 通过合同类型查询-分页
	 * 
	 * @param contractType
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByContractType(ContractType contractType, Pageable pageable);
	
	/**
	 * 通过合同状态查询-分页
	 * 
	 * @param contractStatus
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByContractStatus(ContractStatus contractStatus, Pageable pageable);
	
	/**
	 * 通过员工查询-分页
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByUser(User user, Pageable pageable);
	
	/**
	 * 通过批量员工查询-分页
	 * 
	 * @param users
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByUserIn(List<User> users, Pageable pageable);
	
	/**
	 * 通过合同类型, 批量员工查询-分页
	 * 
	 * @param contractType
	 * @param users
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByContractTypeAndUserIn(ContractType contractType, List<User> users, Pageable pageable);
	
	/**
	 * 通过合同类型, 批量员工, 开始日期区间查询-分页
	 * 
	 * @param contractType
	 * @param users
	 * @param date1
	 * @param date2
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByContractTypeAndUserInAndStartDateBetween(ContractType contractType, List<User> users, Date date1, Date date2, Pageable pageable);

	/**
	 * 查询未结束的合同-分页
	 * 
	 * @param date
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByEndDateAfter(Date endDate, Pageable pageable);
	
	/**
	 * 通过开始日期的区间查询-分页
	 * 
	 * @param date1
	 * @param date2
	 * @param pageable
	 * @return
	 */
	public Page<Contract> findByStartDateBetween(Date date1, Date date2, Pageable pageable);
}
