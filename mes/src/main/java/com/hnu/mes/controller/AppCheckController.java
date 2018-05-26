package com.hnu.mes.controller;

import java.util.List;

import javax.validation.Valid;

import com.hnu.mes.domain.*;
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
import com.hnu.mes.service.AppCheckService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author PINGXIAO
 *
 */
@RestController
@RequestMapping(value = "/appCheck")
public class AppCheckController {

    /** 注入 */
    @Autowired
    private AppCheckService appCheckService;

    /**
     * 新增
     *
     * @param appCheck
     * @return
     */
    @PostMapping(value = "/add")
    public Result<AppCheck> save(@Valid AppCheck appCheck, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [appCheck, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }


        return ResultUtil.success(appCheckService.save(appCheck));
    }

    /**
     * 更新
     *
     * @param appCheck
     * @return
     */
    @PostMapping(value = "/update")
    public Result<AppCheck> update(@Valid AppCheck appCheck, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        AppCheck findOne = appCheckService.findOne(appCheck.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(appCheckService.save(appCheck));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Long code) {

        appCheckService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<AppCheck> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(appCheckService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<AppCheck>> findAll() {
        return ResultUtil.success(appCheckService.findAll());
    }


    /**
     * 发布：更新审核人
     *
     * @param examPerson
     * @param code
     * @return
     */
    @PostMapping(value = "/updateExamPersonByCode")
    public Result<AppCheck> updatePublishByCode(@RequestParam(name = "examPerson", defaultValue = "") String examPerson,
                                                @RequestParam(name = "examState", defaultValue = "") String examState,
                                                @RequestParam(name = "examDate", defaultValue = "") String examDate,
                                                  @RequestParam(name = "code", defaultValue = "") Long code) {

        AppCheck s1 = new AppCheck();
        s1.getExamPerson();

        if (s1 == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }

        AppCheck s2 = new AppCheck();
        s2.getExamState();

        if (s2 == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }

        AppCheck s3 = new AppCheck();
        s1.getExamDate();

        if (s3 == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }


        AppCheck appCheck = appCheckService.findOne(code);
        if(appCheck == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }



        Integer i = appCheckService.updateExamPersonByCode(examPerson,examState,examDate, code);
        if (i > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
    }

    /**
     * 通过状态编码查询-分页
     *
     * @param examState
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getAllByExamStateByPage")
    public Result<Page<AppCheck>> getAllByUpdateTimeByPage(@RequestParam(value = "examState", defaultValue = "null") String examState,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                             @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(appCheckService.getAllByExamPersonByPage(examState, page, size, sort, asc));
    }

}
