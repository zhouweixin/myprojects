package com.hnu.mes.controller;

import java.util.List;

import javax.validation.Valid;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.AppCheckHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.AppCheckHeadService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/appCheckHead")
public class AppCheckHeadController {

    /** 注入 */
    @Autowired
    private AppCheckHeadService appCheckHeadService;


    /**
     * 新增
     *
     * @param appCheckHead
     * @return
     */
    @PostMapping(value = "/add")
    public Result<AppCheckHead> save(@Valid AppCheckHead appCheckHead, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        AppCheckHead findOne = appCheckHeadService.findOne(appCheckHead.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(appCheckHeadService.save(appCheckHead));
    }

    /**
     * 更新
     *
     * @param appCheckHead
     * @return
     */
    @PostMapping(value = "/update")
    public Result<AppCheckHead> update(@Valid AppCheckHead appCheckHead, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        AppCheckHead findOne = appCheckHeadService.findOne(appCheckHead.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(appCheckHeadService.save(appCheckHead));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {

        appCheckHeadService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<AppCheckHead> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(appCheckHeadService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<AppCheckHead>> findAll() {
        return ResultUtil.success(appCheckHeadService.findAll());
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
    public Result<Page<AppCheckHead>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                          @RequestParam(value = "sort", defaultValue = "code") String sort,
                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(appCheckHeadService.getAppCheckHeadByPage(page, size, sort, asc));
    }






    
    
}
