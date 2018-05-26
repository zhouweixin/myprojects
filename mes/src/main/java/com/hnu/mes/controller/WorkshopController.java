package com.hnu.mes.controller;

import com.hnu.mes.domain.Department;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.Workshop;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.WorkshopService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 车间信息控制层
 */
@RestController
@RequestMapping(value = "/workshop")
public class WorkshopController {
    @Autowired
    private WorkshopService workshopService;

    @PostMapping(value = "/add")
    public Result<Workshop> save(@Valid Workshop workshop, BindingResult bindingResult,
                                 @RequestParam(name = "departmentCode", defaultValue = "") String departmentCode) {
        /**
         * save
         * @Desciption 新增
         * @param [workshop, bindingResult, departmentCode]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否重复
        Workshop findOne = workshopService.findOne(workshop.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        //设置部门
        if (!departmentCode.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            workshop.setDepartment(department);
        }

        return ResultUtil.success(workshopService.save(workshop));
    }

    @PostMapping(value = "/update")
    public Result<Workshop> update(@Valid Workshop workshop, BindingResult bindingResult,
                                   @RequestParam(name = "departmentCode", defaultValue = "") String departmentCode){
        /**
         * update
         * @Desciption 更新
         * @param [workshop, bindingResult, departmentCode]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        Workshop findOne = workshopService.findOne(workshop.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        //设置部门
        if (!departmentCode.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            workshop.setDepartment(department);
        }

        return ResultUtil.success(workshopService.save(workshop));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        //判断是否存在
        Workshop findOne = workshopService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        workshopService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param workshops
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Workshop> workshops){
        workshopService.deleteInBatch(workshops);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<Workshop> findOne(@RequestParam(value = "code") String code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(workshopService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Workshop>> findAll() {
        return ResultUtil.success(workshopService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<Workshop>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                          @RequestParam(value = "sort", defaultValue = "code") String sort,
                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(workshopService.getWorkshopByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Workshop>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 分页模糊查询所有
         * @param [name, page, size, sort, asc]
         */
        return ResultUtil.success(workshopService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
