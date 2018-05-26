package com.hnu.mes.controller;

import com.hnu.mes.domain.ProductLine;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProductLineService;
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
 * @Description:
 * @Date: Created in 11:17 2018/4/5
 * @Modified By:
 */
@RestController
@RequestMapping("/productLine")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;

    @PostMapping(value = "/add")
    public Result<ProductLine> save(@Valid ProductLine productLine, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [productLine, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否重复
        ProductLine findOne = productLineService.findOne(productLine.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(productLineService.save(productLine));
    }

    @PostMapping(value = "/update")
    public Result<ProductLine> update(@Valid ProductLine productLine, BindingResult bindingResult) {
        /**
         * update
         * @Desciption 更新
         * @param [productLine, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        ProductLine findOne = productLineService.findOne(productLine.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(productLineService.save(productLine));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        //判断是否存在
        ProductLine findOne = productLineService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        productLineService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param productLinees
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<ProductLine> productLinees){
        productLineService.deleteInBatch(productLinees);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<ProductLine> findOne(@RequestParam(value = "code") String code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(productLineService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<ProductLine>> findAll() {
        return ResultUtil.success(productLineService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<ProductLine>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(productLineService.getProductLineByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<ProductLine>> findAllByLikeNameByPage(
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
        return ResultUtil.success(productLineService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
