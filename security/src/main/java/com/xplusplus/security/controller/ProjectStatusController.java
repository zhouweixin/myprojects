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

import com.xplusplus.security.domain.ProjectStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.ProjectStatusService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:54:05 2018年5月24日
 */
@RestController
@RequestMapping(value = "/projectStatus")
public class ProjectStatusController {
	@Autowired
	private ProjectStatusService projectStatusService;

	/**
	 * 新增
	 * 
	 * @param projectStatus
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<ProjectStatus> add(@Valid ProjectStatus projectStatus, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(projectStatusService.save(projectStatus));
	}

	/**
	 * 更新
	 * 
	 * @param projectStatus
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<ProjectStatus> update(@Valid ProjectStatus projectStatus, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(projectStatusService.update(projectStatus));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Integer id) {
		projectStatusService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param projectStatuss
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<ProjectStatus> projectStatuss) {
		projectStatusService.deleteInBatch(projectStatuss);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<ProjectStatus> getById(Integer id) {
		return ResultUtil.success(projectStatusService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<ProjectStatus>> getAll() {
		return ResultUtil.success(projectStatusService.findAll());

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
	public Result<Page<ProjectStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(projectStatusService.findAllByPage(page, size, sortFieldName, asc));
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
	public Result<Page<ProjectStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		
		return ResultUtil.success(projectStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
	}
}
