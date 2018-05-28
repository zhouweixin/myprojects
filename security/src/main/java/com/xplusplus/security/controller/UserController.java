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

import com.xplusplus.security.domain.Department;
import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.service.UserService;
import com.xplusplus.security.utils.ResultUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:37 2018/5/7
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 新增
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<User> add(@Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(userService.save(user));
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<User> update(@Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
		}

		return ResultUtil.success(userService.update(user));
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	public Result<Object> deleteById(String id) {
		userService.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "/deleteByIdBatch")
	public Result<Object> deleteByIdBatch(@RequestBody Collection<User> users) {
		userService.deleteInBatch(users);
		return ResultUtil.success();
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById")
	public Result<User> getById(String id) {
		return ResultUtil.success(userService.findOne(id));
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public Result<List<User>> getAll() {
		return ResultUtil.success(userService.findAll());
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
	public Result<Page<User>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(userService.findAllByPage(page, size, sortFieldName, asc));
	}
	
	/**
	 * 通过名称模糊查询
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/getByNameLike")
	public Result<List<User>> getByNameLike(@RequestParam(value = "name", defaultValue = "") String name){
		return ResultUtil.success(userService.findByNameLike(name));
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
	public Result<Page<User>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(userService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
	}
	
	/**
	 * 通过部门查询
	 * 
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/getByDepartment")
	public Result<List<User>> getByDepartment(Department department){
		return ResultUtil.success(userService.findByDepartment(department));
	}

	/**
	 * 通过部门和名称模糊查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByDepartmentAndNameLikeByPage")
	public Result<Page<User>> getByDepartmentAndNameLikeByPage(Department department,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(userService.findByDepartmentAndNameLikeByPage(department, name, page, size, sortFieldName, asc));
	}
	
	/**
	 * 通过工作性质查询
	 * 
	 * @param jobNature
	 * @return
	 */
	@RequestMapping(value = "/getByJobNature")
	public Result<List<User>> getByJobNature(JobNature jobNature){
		return ResultUtil.success(userService.findByJobNature(jobNature));
	}

	/**
	 * 通过工作性质查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	@RequestMapping(value = "/getByJobNatureByPage")
	public Result<Page<User>> getByJobNatureByPage(JobNature jobNature,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
			@RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		return ResultUtil.success(userService.findByJobNatureByPage(jobNature, page, size, sortFieldName, asc));
	}
	
	/**
	 * 通过id更新工作性质
	 * 
	 * @param jobNature
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateJobNatureById")
	public Result<Object> updateJobNatureById(Integer jobNatureId, String id){
		userService.updateJobNatureById(jobNatureId, id);
		return ResultUtil.success();
	}
	
}
