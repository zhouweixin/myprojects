package com.xplusplus.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xplusplus.security.domain.UserContract;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.UserContractRepository;
import com.xplusplus.security.utils.GlobalUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:26:26 2018年5月23日
 */
@Service
public class UserContractService {
	@Autowired
	private UserContractRepository userContractRepository;

	/**
	 * 新增
	 * 
	 * @param userContract
	 * @return
	 */
	public UserContract save(UserContract userContract) {
		
		// 判断是否已存在
		if(userContract == null || (userContract.getId() != null && userContractRepository.findOne(userContract.getId()) != null)){
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);			
		}

		// 计算周期
		if (userContract != null) {
			userContract.setPeriod(GlobalUtil.computePeriod(userContract.getStartDate(), userContract.getEndDate()));
		}
		
		return userContractRepository.save(userContract);
	}
	
	/**
	 * 查询
	 * 
	 * @param id
	 * @return
	 */
	public UserContract findOne(Long id) {
		return userContractRepository.findOne(id);
	}
}
