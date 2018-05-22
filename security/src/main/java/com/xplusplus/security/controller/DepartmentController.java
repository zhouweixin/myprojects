package com.xplusplus.security.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.DepartmentService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:40 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 新增
	 * 
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<Department> add(Department department) {
		return ResultUtil.success(departmentService.save(department));
	}

	/**
	 * 更新
	 * 
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<Department> update(Department department) {
		return ResultUtil.success(departmentService.update(department));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(Integer id) {
		departmentService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param departments
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(Collection<Department> departments) {
		departmentService.deleteInBatch(departments);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<Department> getById(Integer id) {
		return ResultUtil.success(departmentService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<Department>> getAll() {
		return ResultUtil.success(departmentService.findAll());

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
	public Result<Page<Department>> getAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) { 
    	return ResultUtil.success(departmentService.findAllByPage(page, size, sortFieldName, asc));
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
	public Result<Page<Department>> getByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {
		return ResultUtil.success(departmentService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
	}
}
