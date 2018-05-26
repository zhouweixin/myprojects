package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hnu.mes.domain.Department;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.DepartmentService;
import com.hnu.mes.utils.ResultUtil;

/**
 * 
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

	/** 注入 */
    @Autowired
    private DepartmentService departmentService;

    /**
     * 新增
     *
     * @param department
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Department> save(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Department findOne = departmentService.findOne(department.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(departmentService.save(department));
    }

    /**
     * 更新
     *
     * @param department
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Department> update(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Department findOne = departmentService.findOne(department.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(departmentService.save(department));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        if(departmentService.findOne(code) == null){
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        departmentService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param departments
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Department> departments){
        departmentService.deleteInBatch(departments);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Department> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(departmentService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Department>> findAll() {
        return ResultUtil.success(departmentService.findAll());
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
    public Result<Page<Department>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(departmentService.getDepartmentByPage(page, size, sort, asc));
    }

    /**
     * 通过名称模糊查询所有-分页
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
    public Result<Page<Department>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(departmentService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
