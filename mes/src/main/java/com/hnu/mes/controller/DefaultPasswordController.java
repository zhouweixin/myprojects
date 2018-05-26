package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hnu.mes.domain.DefaultPassword;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.DefaultPasswordService;
import com.hnu.mes.utils.ResultUtil;

/**
 * 
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/defaultPassword")
public class DefaultPasswordController {

	/** 注入 */
	@Autowired
	private DefaultPasswordService defaultPasswordService;

	/**
	 * 新增
	 * 
	 * @param defaultPassword
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DefaultPassword> save(@Valid DefaultPassword defaultPassword, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		if(defaultPasswordService.findOne(defaultPassword.getCode()) != null){
		    return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

		return ResultUtil.success(defaultPasswordService.save(defaultPassword));
	}

	/**
	 * 更新
	 *
	 * @param defaultPassword
	 * @return
	 */
	@PostMapping(value = "/update")
	public Result<DefaultPassword> update(@Valid DefaultPassword defaultPassword, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

        if(defaultPasswordService.findOne(defaultPassword.getCode()) == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

		return ResultUtil.success(defaultPasswordService.save(defaultPassword));
	}

	/**
	 * 删除
	 * 
	 * @param code
	 */
	@PostMapping(value = "/deleteByCode")
	public Result<Object> delete(@RequestParam(value = "code") Integer code) {
		defaultPasswordService.delete(code);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 *
	 * @param defaultPasswords
	 * @return
	 */
	@PostMapping(value = "/deleteByBatchCode")
	public Result<Object> deleteByBatchCode(@RequestBody Set<DefaultPassword> defaultPasswords){
		defaultPasswordService.deleteInBatch(defaultPasswords);
		return ResultUtil.success();
	}

	/**
	 * 查找
	 * 
	 * @param code
	 * @return
	 */
	@PostMapping(value = "/getByCode")
	public Result<DefaultPassword> findOne(@RequestParam(value = "code") Integer code) {
		return ResultUtil.success(defaultPasswordService.findOne(code));
	}

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	@GetMapping(value = "/getAll")
	public Result<List<DefaultPassword>> findAll() {
		return ResultUtil.success(defaultPasswordService.findAll());
	}

	/**
	 * 查询所有-分页
	 *
	 * @param page
	 *            当前页,从0开始,默认是0
	 * @param size
	 *            每页的记录数,默认是10
	 * @param sort
	 *            排序的字段名,默认是code
	 * @param asc
	 *            排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@PostMapping(value = "/getAllByPage")
	public Result<Page<DefaultPassword>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
											@RequestParam(value = "size", defaultValue = "10") Integer size,
											@RequestParam(value = "sort", defaultValue = "code") String sort,
											@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(defaultPasswordService.getAllByPage(page, size, sort, asc));
	}

	/**
	 * 通过名称模糊查询所有-分页
	 *
	 * @param password
	 *            密码
	 * @param page
	 *            当前页,从0开始,默认是0
	 * @param size
	 *            每页的记录数,默认是10
	 * @param sort
	 *            排序的字段名,默认是code
	 * @param asc
	 *            排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@PostMapping(value = "/getAllByLikePasswordByPage")
	public Result<Page<DefaultPassword>> findAllByLikeNameByPage(
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sort", defaultValue = "code") String sort,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(defaultPasswordService.findAllByLikePasswordByPage(password, page, size, sort, asc));
	}
}
