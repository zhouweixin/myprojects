package com.xplusplus.security.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xplusplus.security.domain.Contract;
import com.xplusplus.security.domain.ContractStatus;
import com.xplusplus.security.domain.ContractType;
import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.service.ContractService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:28:20 2018年5月23日
 */
@RestController
@RequestMapping(value = "/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;

	/**
	 * 新增
	 * 
	 * @param contract
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<Contract> add(@Valid Contract contract, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(contractService.save(contract));
	}

	/**
	 * 更新
	 * 
	 * @param contract
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<Contract> update(@Valid Contract contract, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(contractService.update(contract));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Long id) {
		contractService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param contracts
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<Contract> contracts) {
		contractService.deleteInBatch(contracts);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<Contract> getById(Long id) {
		return ResultUtil.success(contractService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<Contract>> getAll() {
		return ResultUtil.success(contractService.findAll());

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
	@RequestMapping(value = "/getAllByPage")
	public Result<Page<Contract>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findAllByPage(page, size, sortFieldName, asc));
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
	@RequestMapping(value = "/getByContractTypeByPage")
	public Result<Page<Contract>> getByContractTypeByPage(ContractType contractType,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil
				.success(contractService.findByContractTypeByPage(contractType, page, size, sortFieldName, asc));
	}

	/**
	 * 通过合同状态查询-分页
	 * 
	 * @param user
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByContractStatusByPage")
	public Result<Page<Contract>> getByContractStatusByPage(ContractStatus contractStatus,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil
				.success(contractService.findByContractStatusByPage(contractStatus, page, size, sortFieldName, asc));
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
	@RequestMapping(value = "/getByUserByPage")
	public Result<Page<Contract>> getByUserByPage(User user,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findByUserByPage(user, page, size, sortFieldName, asc));
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
	@RequestMapping(value = "/getByDepartmentByPage")
	public Result<Page<Contract>> getByDepartmentByPage(Department department,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findByDepartmentByPage(department, page, size, sortFieldName, asc));
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
	@RequestMapping(value = "/getByUserNameLikeByPage")
	public Result<Page<Contract>> getByUserNameLikeByPage(String userName,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findByUserNameLikeByPage(userName, page, size, sortFieldName, asc));
	}

	/**
	 * 通过工作性质查询-分页
	 * 
	 * @param userName
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByJobNatureByPage")
	public Result<Page<Contract>> getByJobNatureByPage(JobNature jobNature,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findByJobNatureByPage(jobNature, page, size, sortFieldName, asc));
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
	@RequestMapping(value = "/getByEndDayByPage")
	public Result<Page<Contract>> getByEndDayByPage(@RequestParam(value = "endDay", defaultValue = "30") Integer endDay,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(contractService.findByEndDayByPage(endDay, page, size, sortFieldName, asc));
	}

	/**
	 * 通过开始日期的区间查询-分页
	 * 
	 * @param date1
	 * @param date2
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByStartDateBetweenByPage")
	public Result<Page<Contract>> getByStartDateBetweenByPage(@RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
			@RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(contractService.findByStartDateBetween(date1, date2, page, size, sortFieldName, asc));
	}
	
	/***
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
	@RequestMapping(value = "/getByParametersByPage")
	public Result<Page<Contract>> getByParametersByPage(
			@RequestParam(defaultValue = "-1") Integer departmentId,
			@RequestParam(defaultValue = "-1") Integer jobNatureId,
			@RequestParam(defaultValue = "-1") Integer contractTypeId,
			@RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
			@RequestParam(defaultValue = "1970-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(contractService.getByParametersByPage(departmentId, jobNatureId, contractTypeId, date1, date2, userName, page, size, sortFieldName, asc));
	}
}
