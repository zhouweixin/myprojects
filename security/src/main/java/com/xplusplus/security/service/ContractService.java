package com.xplusplus.security.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.xplusplus.security.domain.Contract;
import com.xplusplus.security.domain.ContractStatus;
import com.xplusplus.security.domain.ContractType;
import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ContractRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:26:26 2018年5月23日
 */
@Service
public class ContractService {
	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private UserService userService;

	/**
	 * 新增
	 * 
	 * @param contract
	 * @return
	 */
	public Contract save(Contract contract) {

		// 验证是否存在
		if (contract == null || (contract.getId() != null && contractRepository.findOne(contract.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		return contractRepository.save(contract);
	}

	/**
	 * 更新
	 * 
	 * @param contract
	 * @return
	 */
	public Contract update(Contract contract) {

		// 验证是否存在
		if (contract == null || contract.getId() == null || contractRepository.findOne(contract.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}

		return contractRepository.save(contract);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {

		// 验证是否存在
		if (contractRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		contractRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param contracts
	 */
	public void deleteInBatch(Collection<Contract> contracts) {
		contractRepository.deleteInBatch(contracts);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public Contract findOne(Long id) {
		return contractRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Contract> findAll() {
		return contractRepository.findAll();
	}

	/**
	 * 查询所有-分页
	 * 
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findAll(pageable);
	}

	/**
	 * 通过合同类型查询-分页
	 * 
	 * @param contractType
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByContractTypeByPage(ContractType contractType, Integer page, Integer size,
			String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByContractType(contractType, pageable);
	}

	/**
	 * 通过合同状态查询-分页
	 * 
	 * @param contractStatus
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByContractStatusByPage(ContractStatus contractStatus, Integer page, Integer size,
			String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByContractStatus(contractStatus, pageable);
	}

	/**
	 * 通过用户查询-分页
	 * 
	 * @param user
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByUserByPage(User user, Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByUser(user, pageable);
	}

	/**
	 * 通过用户所在部门查询-分页
	 * 
	 * @param department
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByDepartmentByPage(Department department, Integer page, Integer size,
			String sortFieldName, Integer asc) {

		List<User> users = userService.findByDepartment(department);

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByUserIn(users, pageable);
	}

	/**
	 * 通过用户名称模糊查询-分页
	 * 
	 * @param userName
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByUserNameLikeByPage(String userName, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		List<User> users = userService.findByNameLike(userName);

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByUserIn(users, pageable);
	}

	/**
	 * 通过工作性质查询-分页
	 * 
	 * @param jobNature
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByJobNatureByPage(JobNature jobNature, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		List<User> users = userService.findByJobNature(jobNature);

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByUserIn(users, pageable);
	}

	/**
	 * 通过结束的天数以内查询-分页
	 * 
	 * @param endDay
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByEndDayByPage(Integer endDay, Integer page, Integer size, String sortFieldName,
			Integer asc) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, endDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByEndDateAfter(calendar.getTime(), pageable);
	}

	/**
	 * 通过开始日期的区间查询-分页
	 * 
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> findByStartDateBetween(Date startDate, Date endDate, Integer page, Integer size,
			String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return contractRepository.findByStartDateBetween(startDate, endDate, pageable);
	}

	/**
	 * 通过各种参数检索-分页
	 * 
	 * @param departmentId
	 * @param jobNatureId
	 * @param contractTypeId
	 * @param date1
	 * @param date2
	 * @param userName
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Contract> getByParametersByPage(Integer departmentId, Integer jobNatureId, Integer contractTypeId,
			Date date1, Date date2, String userName, Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断排序字段名是否存在
		try {
			Contract.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);

		List<User> users = userService.findByDepartmentAndJobNatureAndNameLike(departmentId, jobNatureId, userName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ContractType contractType = new ContractType();
		contractType.setId(contractTypeId);
		if (contractTypeId == -1
				&& (sdf.format(date1).equals("1970-01-01") || sdf.format(date2).equals("1970-01-01"))) {
			return contractRepository.findByUserIn(users, pageable);
		}

		if (contractTypeId != -1
				&& (sdf.format(date1).equals("1970-01-01") || sdf.format(date2).equals("1970-01-01"))) {
			return contractRepository.findByContractTypeAndUserIn(contractType, users, pageable);
		}

		return contractRepository.findByContractTypeAndUserInAndStartDateBetween(contractType, users, date1, date2,
				pageable);
	}
}
