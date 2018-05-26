package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.service.RoleModelOperationViewService;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.RoleModelOperationService;
import com.hnu.mes.service.RoleService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

	/** 注入 */
	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleModelOperationService roleModelOperationService;

	@Autowired
	private RoleModelOperationViewService roleModelOperationViewService;

	/**
	 * 新增
	 *
	 * @param role
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Role> save(@Valid Role role, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		// 判断是否已经存在
		Role findOne = roleService.findOne(role.getCode());
		if (findOne != null) {
			return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
		}

		return ResultUtil.success(roleService.save(role));
	}

	/**
	 * 更新
	 *
	 * @param role
	 * @return
	 */
	@PostMapping(value = "/update")
	public Result<Role> update(@Valid Role role, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		// 判断是否存在
		Role findOne = roleService.findOne(role.getCode());
		if (findOne == null) {
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
		}

		if(findOne.getFlag() == GlobalUtil.SYSTEM_FLAG){
			return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_SYSTEM_NOT_ALLOW));
		}

		return ResultUtil.success(roleService.save(role));
	}

	/**
	 * 删除
	 *
	 * @param code
	 */
	@PostMapping(value = "/deleteByCode")
	public Result<Object> delete(@RequestParam(value = "code") Integer code) {

		// 判断是否存在
		Role findOne = roleService.findOne(code);
		if (findOne == null) {
			return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
		}

		// 系统值, 不允许删除
		if(findOne.getFlag() == GlobalUtil.SYSTEM_FLAG){
			return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_SYSTEM_NOT_ALLOW));
		}

		roleService.delete(code);
		return ResultUtil.success();
	}

	/**
	 * 批量删除
	 *
	 * @param roles
	 * @return
	 */
	@PostMapping(value = "/deleteByBatchCode")
	public Result<Object> deleteByBatchCode(@RequestBody Set<Role> roles){
		// 系统变量,不允许删除
		for(Role role : roles){
			if(role.getCode() == GlobalUtil.SYSTEM_FLAG){
				roles.remove(role);
			}
		}

		roleService.deleteInBatch(roles);
		return ResultUtil.success();
	}

	/**
	 * 查找
	 *
	 * @param code
	 * @return
	 */
	@PostMapping(value = "/getByCode")
	public Result<Role> findOne(@RequestParam(value = "code") Integer code) {
		return ResultUtil.success(roleService.findOne(code));
	}

	/**
	 * 查找所有
	 *
	 * @return
	 */
	@GetMapping(value = "/getAll")
	public Result<List<Role>> findAll() {
		return ResultUtil.success(roleService.findAll());
	}

	/**
	 * 通过分页查询所有
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
	public Result<Page<Role>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
									  @RequestParam(value = "size", defaultValue = "10") Integer size,
									  @RequestParam(value = "sort", defaultValue = "code") String sort,
									  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(roleService.getRoleByPage(page, size, sort, asc));
	}

	/**
	 * 通过分页查询所有
	 *
	 * @param name
	 *            名称
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
	@PostMapping(value = "/getAllByLikeNameByPage")
	public Result<Page<Role>> findAllByLikeNameByPage(@RequestParam(value = "name", defaultValue = "") String name,
													  @RequestParam(value = "page", defaultValue = "0") Integer page,
													  @RequestParam(value = "size", defaultValue = "10") Integer size,
													  @RequestParam(value = "sort", defaultValue = "code") String sort,
													  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
		return ResultUtil.success(roleService.findAllByLikeNameByPage(name, page, size, sort, asc));
	}

	/**
	 * 通过角色编码查询角色，模块，操作
	 *
	 * @param code
	 * @return
	 */
	@PostMapping(value = "/getRoleModelOperationsByCode")
	public Result<List<RoleModelOperationView>> getAllByRoleCode(@RequestParam(value = "code") Integer code){
		return ResultUtil.success(roleModelOperationViewService.findByRoleCode(code));
	}

	/**
	 * 添加角色，模块，操作
	 *
	 * @param roleModelOperations
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/updateRoleModelOperations")
	public Result<Object> updateRoleModelOperations(@RequestBody Set<RoleModelOperation> roleModelOperations,
													BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		return ResultUtil.success(roleModelOperationService.updateRoleModelOperations(roleModelOperations));
	}

	/**
	 * 通过角色编码和模块编码查询操作
	 *
	 * @param roleCode
	 * @param modelCode
	 * @return
	 */
	@RequestMapping(value = "/getOperationsByRoleCodeAndModelCode")
	public Result<List<Operation>> findByRoleCodeAndModelCode(@RequestParam(value = "roleCode", defaultValue = "0") Integer roleCode,
														@RequestParam(value = "modelCode", defaultValue = "0") Integer modelCode){
		return ResultUtil.success(roleModelOperationService.findOperationsByRoleCodeAndModelCode(roleCode, modelCode));
	}

	/**
	 * 查询所有
	 *
	 * @return
	 */
	@RequestMapping(value = "/getAllRoleModelOperation")
	public Result<List<RoleModelOperation>> getRoleModelOperations(){
		return ResultUtil.success(roleModelOperationService.findAll());
	}
}
