package com.hnu.mes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.domain.Responsibility;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ResponsibilityService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/responsibility")
public class ResponsibilityController {

    /** 注入 */
    @Autowired
    private ResponsibilityService responsibilityService;

    /**
     * 新增
     *
     * @param responsibility
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Responsibility> save(@Valid Responsibility responsibility, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Responsibility findOne = responsibilityService.findOne(responsibility.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(responsibilityService.save(responsibility));
    }

    /**
     * 更新
     *
     * @param responsibility
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Responsibility> update(@Valid Responsibility responsibility, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Responsibility findOne = responsibilityService.findOne(responsibility.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(responsibilityService.save(responsibility));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {

        responsibilityService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Responsibility> findOne(@RequestParam(value = "code") Integer code) {

        return ResultUtil.success(responsibilityService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Responsibility>> findAll() {
        return ResultUtil.success(responsibilityService.findAll());
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
    public Result<Page<Responsibility>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sort", defaultValue = "code") String sort,
                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(responsibilityService.getResponsibilityByPage(page, size, sort, asc));
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
    public Result<Page<Responsibility>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(responsibilityService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
