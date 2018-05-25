package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Role;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.RoleService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Huxudong
 * @Description: 角色Controller
 * @Date: Created in 12:45 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public Result<Role> add(@Valid Role role, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(roleService.save(role));
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<Role> update(@Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(roleService.update(role));
    }

    /**
     * 删除健康
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        roleService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Role> roles) {
        roleService.deleteInBatch(roles);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<Role> getById(Integer id) {
        return ResultUtil.success(roleService.findOne(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Role>> getAll() {
        return ResultUtil.success(roleService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<Role>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                   @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                   @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(roleService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<Role>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(roleService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
