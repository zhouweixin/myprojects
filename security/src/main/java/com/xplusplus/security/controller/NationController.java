package com.xplusplus.security.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xplusplus.security.domain.Nation;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.NationService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:02:30 2018年5月22日
 */
@RestController
@RequestMapping(value = "/nation")
public class NationController {

	@Autowired
	private NationService nationService;

	/**
	 * 新增
	 * 
	 * @param nation
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<Nation> add(@Valid Nation nation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(nationService.save(nation));
	}

	/**
	 * 更新
	 * 
	 * @param nation
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<Nation> update(@Valid Nation nation, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(nationService.update(nation));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Integer id) {
		nationService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param nations
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<Nation> nations) {
		nationService.deleteInBatch(nations);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<Nation> getById(Integer id) {
		return ResultUtil.success(nationService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<Nation>> getAll() {
		return ResultUtil.success(nationService.findAll());

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
	public Result<Page<Nation>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(nationService.findAllByPage(page, size, sortFieldName, asc));
	}

	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByNameLikeByPage")
	public Result<Page<Nation>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
													@RequestParam(value = "page", defaultValue = "0") Integer page,
													@RequestParam(value = "size", defaultValue = "10") Integer size,
													@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
													@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(nationService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
	}
}
