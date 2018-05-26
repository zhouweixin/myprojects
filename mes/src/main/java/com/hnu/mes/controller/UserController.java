package com.hnu.mes.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.hnu.mes.domain.*;
import com.hnu.mes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.utils.ResultUtil;

/**
 * @author zhouweixin
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
    private UserRoleService userRoleService;

	@Autowired
    private RoleService roleService;

	@Autowired
	private DefaultPasswordService defaultPasswordService;

	@Autowired
	private RoleModelOperationViewService roleModelOperationViewService;

	/**
	 * 新增
	 *
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<User> save(@Valid User user, BindingResult bindingResult,
							 @RequestParam(name = "departmentCode", defaultValue = "") String departmentCode) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		// 判断是否已经存在
		User findOne = userService.findOne(user.getCode());
		if (findOne != null) {
			return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
		}

		// 取出默认密码
		String password = defaultPasswordService.getDefaultPassword();

		// 加密
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

		// 设置部门
		if (!departmentCode.equals("")) {
			Department department = new Department();
			department.setCode(departmentCode);
			user.setDepartment(department);
		}

		return ResultUtil.success(userService.save(user));
	}

	/**
	 * 更新用户
	 *
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/update")
	public Result<User> updateUser(@Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
		}

		// 判断是否存在
		User findOne = userService.findOne(user.getCode());
		if (findOne == null) {
			return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
		}

		userService.updateUser(user);
		return ResultUtil.success();
	}

	/**
	 * 更新启用
	 * @param code
	 * @param enable
	 * @return
	 */
	@PostMapping(value = "/updateEnable")
	public Result<User> updateUserEnable(@RequestParam(value = "code") String code,
										 @RequestParam(value = "enable", defaultValue = "1") Integer enable) {
		userService.updateUserEnable(code, enable);
		return ResultUtil.success();
	}

	/**
	 * 更新编码
	 * @param code
	 * @param departmentCode
	 * @return
	 */
	@PostMapping(value = "/updateDepartmentCode")
	public Result<User> updateUserDepartmentCode(@RequestParam(value = "code") String code,
												 @RequestParam(value = "departmentCode") String departmentCode) {
		userService.updateUserDepartmentCode(code, departmentCode);
		return ResultUtil.success();
	}

	/**
	 * 更新IC卡
	 * @param code
	 * @param inteCircCard
	 * @param enableIc
	 * @return
	 */
	@PostMapping(value = "/updateInteCircCard")
	public Result<User> updateUserInteCircCard(@RequestParam(value = "code") String code,
											   @RequestParam(value = "inteCircCard") String inteCircCard,
											   @RequestParam(value = "enableIc", defaultValue = "0") Integer enableIc) {
		userService.updateUserInteCircCard(code, inteCircCard, enableIc);
		return ResultUtil.success();
	}

	/**
	 * 重置密码
	 *
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/resetPassword")
	public Result<User> updateUserPassword(User user) {
		// 取出默认密码
		String password = defaultPasswordService.getDefaultPassword();

		// 加密
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

		userService.updateUserPassword(user);
		return ResultUtil.success();
	}

	/**
	 * 修改密码
	 * @param code
	 * @param oldPassword
	 * @param newPassword
	 * @param reNewPassword
	 * @return
	 */
	@PostMapping(value = "/updatePassword")
	public Result<User> updateUserPassword(@RequestParam(value = "code") String code,
										   @RequestParam(value = "oldPassword") String oldPassword,
										   @RequestParam(value = "newPassword") String newPassword,
										   @RequestParam(value = "reNewPassword") String reNewPassword) {

		if (newPassword == null || newPassword.equals("")) {
			return ResultUtil.error(new MesException(EnumException.NEW_PASSWORD_NULL));
		} else if (!newPassword.equals(reNewPassword)) {
			return ResultUtil.error(new MesException(EnumException.TWICE_PASSWORD_DIFF));
		}

		// 加密
		oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
		newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());

		Integer i = userService.updateUserPassword(code, newPassword, oldPassword);

		if (i == 0) {
			return ResultUtil.error(new MesException(EnumException.OLD_PASSWORD_FALSE));
		}

		return ResultUtil.success();
	}

	/**
	 * 登录
	 * @param code
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/login")
	public Result<User> findByCodeAndPassword(@RequestParam(value = "code") String code,
											  @RequestParam(value = "password") String password) {
		if (code == null || password == null || code.equals("") || password.equals("")) {
			return ResultUtil.error(new MesException(EnumException.CODE_OR_PASSWORD_BLANK));
		}

		// 加密
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		User user = userService.findByCodeAndPassword(code, password);

		if (user == null) {
			return ResultUtil.error(new MesException(EnumException.CODE_OR_PASSWORD_ERROR));
		}

		// 通过roleCode和modelCode查询操作
		getOperationsByRoleCodeAndModelCode(user);

		return ResultUtil.success(user);
	}

	private void getOperationsByRoleCodeAndModelCode(User user) {
		// 通过roleCode和modelCode查询操作
		for (Role role : user.getRoles()) {
			Integer roleCode = role.getCode();

			for (Model model : role.getModels()) {
				Integer modelCode = model.getCode();

				model.setOperations(
						roleModelOperationViewService.findOperationByRoleCodeAndModelCode(roleCode, modelCode));
			}
		}
	}

	/**
	 * 通过IC卡登录
	 * @param inteCircCard
	 * @return
	 */
	@PostMapping(value = "/loginByInteCircCard")
	public Result<User> findByInteCircCard(
			@RequestParam(value = "inteCircCard", defaultValue = "") String inteCircCard) {

		if (inteCircCard == null || inteCircCard.equals("")) {
			return ResultUtil.error(new MesException(EnumException.INTE_CIRC_CARD_BLANK));
		}

		User user = userService.findByInteCircCard(inteCircCard);

		if (user == null) {
			return ResultUtil.error(new MesException(EnumException.INTE_CIRC_CARD_MAY_NO));
		} else if (user.getEnableIc() == 0) {
			return ResultUtil.error(new MesException(EnumException.ENABLE_IC_FALSE));
		}

		// 通过roleCode和modelCode查询操作
		getOperationsByRoleCodeAndModelCode(user);

		return ResultUtil.success(user);
	}

	/**
	 * 删除
	 *
	 * @param code
	 */
	@PostMapping(value = "/deleteByCode")
	public Result<Object> delete(@RequestParam(value = "code") String code) {

		// 判断是否存在
		User findOne = userService.findOne(code);
		if (findOne == null) {
			return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
		}

		userService.delete(code);
		return ResultUtil.success();
	}

    /**
     * 批量删除
     *
     * @param users
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<User> users){
        userService.deleteInBatch(users);
        return ResultUtil.success();
    }

	/**
	 * 查找
	 *
	 * @param code
	 * @return
	 */
	@PostMapping(value = "/getByCode")
	public Result<User> findOne(@RequestParam(value = "code") String code) {

		User user = userService.findOne(code);
		if (user == null) {
			return ResultUtil.error(new MesException(EnumException.CODE_OR_PASSWORD_ERROR));
		}

		// 通过roleCode和modelCode查询操作
		getOperationsByRoleCodeAndModelCode(user);
		return ResultUtil.success(user);
	}

    /**
     * 通过部门编码查询用户
     *
     * @param departmentCode
     * @return
     */
	@PostMapping(value = "/getByDepartmentCode")
	public Result<List<User>> findUsersByDepartmentCode(@RequestParam(name = "departmentCode") String departmentCode){
	    Department department = new Department();
	    department.setCode(departmentCode);
	    return ResultUtil.success(userService.findUsersByDepartment(department));
    }

	/**
	 * 通过部门编码查询用户-分页
	 *
	 * @param departmentCode 名称
	 * @param page 当前页,从0开始,默认是0
	 * @param size 每页的记录数,默认是10
	 * @param sort 排序的字段名,默认是code
	 * @param asc  排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@PostMapping(value = "/getByDepartmentCodeByPage")
	public Result<Page<User>> getByDepartmentCodeByPage(@RequestParam(value = "departmentCode", defaultValue = "") String departmentCode,
													  @RequestParam(value = "page", defaultValue = "0") Integer page,
													  @RequestParam(value = "size", defaultValue = "10") Integer size,
													  @RequestParam(value = "sort", defaultValue = "code") String sort,
													  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        Department department = new Department();
        department.setCode(departmentCode);

		Page<User> pageUser = userService.findUsersByDepartment(department, page, size, sort, asc);
		List<User> list = pageUser.getContent();
		if (list != null) {
			for (User user : list) {
				// 通过roleCode和modelCode查询操作
				getOperationsByRoleCodeAndModelCode(user);
			}
		}
		return ResultUtil.success(pageUser);
	}

	/**
	 * 查找所有
	 *
	 * @return
	 */
	@GetMapping(value = "/getAll")
	public Result<List<User>> findAll() {
		List<User> list = userService.findAll();
		if (list != null) {
			for (User user : list) {
				// 通过roleCode和modelCode查询操作
				getOperationsByRoleCodeAndModelCode(user);
			}
		}
		return ResultUtil.success(list);
	}

	/**
	 * 通过分页查询所有
	 *
	 * @param page 当前页,从0开始,默认是0
	 * @param size 每页的记录数,默认是10
	 * @param sort 排序的字段名,默认是code
	 * @param asc  排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@PostMapping(value = "/getAllByPage")
	public Result<Page<User>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
									  @RequestParam(value = "size", defaultValue = "10") Integer size,
									  @RequestParam(value = "sort", defaultValue = "code") String sort,
									  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		Page<User> pageUser = userService.getUserByPage(page, size, sort, asc);
		List<User> list = pageUser.getContent();
		if (list != null) {
			for (User user : list) {
				// 通过roleCode和modelCode查询操作
				getOperationsByRoleCodeAndModelCode(user);
			}
		}
		return ResultUtil.success(pageUser);
	}

	/**
	 * 通过分页查询所有
	 *
	 * @param name 名称
	 * @param page 当前页,从0开始,默认是0
	 * @param size 每页的记录数,默认是10
	 * @param sort 排序的字段名,默认是code
	 * @param asc  排序的方式,0是减序,1是增序,默认是增序
	 * @return
	 */
	@PostMapping(value = "/getAllByLikeNameByPage")
	public Result<Page<User>> findAllByLikeNameByPage(@RequestParam(value = "name", defaultValue = "") String name,
													  @RequestParam(value = "page", defaultValue = "0") Integer page,
													  @RequestParam(value = "size", defaultValue = "10") Integer size,
													  @RequestParam(value = "sort", defaultValue = "code") String sort,
													  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

		Page<User> pageUser = userService.findAllByLikeNameByPage(name, page, size, sort, asc);
		List<User> list = pageUser.getContent();
		if (list != null) {
			for (User user : list) {
				// 通过roleCode和modelCode查询操作
				getOperationsByRoleCodeAndModelCode(user);
			}
		}
		return ResultUtil.success(pageUser);
	}

    /**
     * 更新角色
     * @param code
     * @param roleCodes
     * @return
     */
	@RequestMapping(value = "/updateRoles")
	public Result<Object> updateRoles(@RequestParam(value = "code", defaultValue = "") String code, @RequestParam(value = "roleCode", defaultValue = "") Set<Integer> roleCodes){

	    if(userService.findOne(code) == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_ROLES_FAILED_USER_NOT_EXISTS));
        }

	    Set<UserRole> userRoles = new HashSet<UserRole>();

        for(Integer roleCode : roleCodes){
            if(roleService.findOne(roleCode) != null){
                UserRole userRole = new UserRole();
                userRole.setUserCode(code);
                userRole.setRoleCode(roleCode);
                userRoles.add(userRole);
            }
        }

        userRoleService.updateUserRole(code, userRoles);
        return ResultUtil.success();
    }

    /**
     * 重置所有用户的密码
     *
     * @return
     */
    @RequestMapping(value = "/resetAllDefaultPassword")
    public Result<Object> resetAllDefaultPassword(){
        // 取出默认密码
        String password = defaultPasswordService.getDefaultPassword();

        // 加密
        String defaultPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        userService.updateAllDefaultPassword(defaultPassword);
        return ResultUtil.success();
    }
}
