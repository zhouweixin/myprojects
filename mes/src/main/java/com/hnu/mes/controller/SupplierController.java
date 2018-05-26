package com.hnu.mes.controller;

import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.Supplier;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.SupplierService;
import com.hnu.mes.service.SupplierTypeService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description: 供应商
 * @Date: Created in 18:47 2018/5/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierTypeService supplierTypeService;

    @PostMapping(value = "/add")
    public Result<Supplier> save(@Valid Supplier supplier, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [supplier, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if(supplier.getSupplierType() == null || supplierTypeService.findOne(supplier.getSupplierType().getCode()) == null){
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_SUPPLIER_TYPE_NOT_EXISTS));
        }

        //判断是否重复
        Supplier findOne = supplierService.findOne(supplier.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(supplierService.save(supplier));
    }

    @PostMapping(value = "/update")
    public Result<Supplier> update(@Valid Supplier supplier, BindingResult bindingResult) {
        /**
         * update
         * @Desciption 更新
         * @param [supplier, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        Supplier findOne = supplierService.findOne(supplier.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(supplierService.save(supplier));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        //判断是否存在
        Supplier findOne = supplierService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        supplierService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param supplieres
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Supplier> supplieres){
        supplierService.deleteInBatch(supplieres);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<Supplier> findOne(@RequestParam(value = "code") String code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(supplierService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Supplier>> findAll() {
        return ResultUtil.success(supplierService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<Supplier>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(supplierService.getSupplierByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Supplier>> findAllByLikeNameByPage(
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
        return ResultUtil.success(supplierService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
