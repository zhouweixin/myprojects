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

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectReceipt;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.ProjectReceiptService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:55:00 2018年5月24日
 */
@RestController
@RequestMapping(value = "/projectReceipt")
public class ProjectReceiptController {
	@Autowired
	private ProjectReceiptService projectReceiptService;

	/**
	 * 新增
	 * 
	 * @param projectReceipt
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<ProjectReceipt> add(@Valid ProjectReceipt projectReceipt, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(projectReceiptService.save(projectReceipt));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Long id) {
		projectReceiptService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param projectReceipts
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<ProjectReceipt> projectReceipts) {
		projectReceiptService.deleteInBatch(projectReceipts);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<ProjectReceipt> getById(Long id) {
		return ResultUtil.success(projectReceiptService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<ProjectReceipt>> getAll() {
		return ResultUtil.success(projectReceiptService.findAll());

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
	public Result<Page<ProjectReceipt>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(projectReceiptService.findAllByPage(page, size, sortFieldName, asc));
	}

	/**
	 * 通过项目查询-分页
	 * 
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByProjectByPage")
	public Result<Page<ProjectReceipt>> getByProjectByPage(Project project,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(projectReceiptService.findByProjectByPage(project, page, size, sortFieldName, asc));
	}
}
