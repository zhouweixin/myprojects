package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.EquipmentService;
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
 * @Description: 设备信息控制层
 */
@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(value = "/add")
    public Result<Equipment> save(@Valid Equipment equipment, BindingResult bindingResult,
                                  @RequestParam(name = "departmentCode", defaultValue = "") String departmentCode,
                                  @RequestParam(name = "productLineCode", defaultValue = "") String productLineCode,
                                  @RequestParam(name = "inspectorCode", defaultValue = "") String inspectorCode) {

        /**
         * save
         * @Desciption 添加
         * @param [equipment, bindingResult, departmentCode, productLineCode, inspectorCode]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否重复
        Equipment findOne = equipmentService.findOne(equipment.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        //设置部门
        if (!departmentCode.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            equipment.setDepartment(department);
        }

        //设置产品线
        if (!productLineCode.equals("")) {
            ProductLine productLine = new ProductLine();
            productLine.setCode(productLineCode);
            equipment.setProductLine(productLine);
        }

        //设置人员
        if (!inspectorCode.equals("")) {
            User user = new User();
            user.setCode(inspectorCode);
            equipment.setUser(user);
        }

        return ResultUtil.success(equipmentService.save(equipment));
    }

    @PostMapping(value = "/update")
    public Result<Equipment> update(@Valid Equipment equipment, BindingResult bindingResult,
                                    @RequestParam(name = "departmentCode", defaultValue = "") String departmentCode,
                                    @RequestParam(name = "productLineCode", defaultValue = "") String productLineCode,
                                    @RequestParam(name = "inspectorCode", defaultValue = "") String inspectorCode) {
        /**
         * update
         * @Desciption 更新
         * @param [equipment, bindingResult, departmentCode, productLineCode, inspectorCode]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        Equipment findOne = equipmentService.findOne(equipment.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        //设置部门
        if (!departmentCode.equals("")) {
            Department department = new Department();
            department.setCode(departmentCode);
            equipment.setDepartment(department);
        }

        //设置产品线
        if (!productLineCode.equals("")) {
            ProductLine productLine = new ProductLine();
            productLine.setCode(productLineCode);
            equipment.setProductLine(productLine);
        }

        //设置人员
        if (!inspectorCode.equals("")) {
            User user = new User();
            user.setCode(inspectorCode);
            equipment.setUser(user);
        }

        return ResultUtil.success(equipmentService.save(equipment));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        //判断是否存在
        Equipment findOne = equipmentService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        equipmentService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param equipments
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Equipment> equipments){
        equipmentService.deleteInBatch(equipments);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<Equipment> findOne(@RequestParam(value = "code") String code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(equipmentService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Equipment>> findAll() {
        return ResultUtil.success(equipmentService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<Equipment>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           @RequestParam(value = "sort", defaultValue = "code") String sort,
                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(equipmentService.getEquipmentByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Equipment>> findAllByLikeNameByPage(
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
        return ResultUtil.success(equipmentService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
