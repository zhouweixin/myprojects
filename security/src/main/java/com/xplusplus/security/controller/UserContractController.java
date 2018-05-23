package com.xplusplus.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.UserContract;
import com.xplusplus.security.service.UserContractService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:28:20 2018年5月23日
 */
@RestController
@RequestMapping(value = "/userContract")
public class UserContractController {
	@Autowired
	private UserContractService userContractService;
	
	/**
	 * 新增
	 * 
	 * @param userContract
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<UserContract> add(UserContract userContract){
		return ResultUtil.success(userContractService.save(userContract));
	}
	
	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<UserContract> getById(Long id){
		return ResultUtil.success(userContractService.findOne(id));
	}
}
