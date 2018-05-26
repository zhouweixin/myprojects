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
import com.hnu.mes.service.AppMissionService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author PINGXIAO
 *
 */
@RestController
@RequestMapping(value = "/appMission")
public class AppMissionController {

    /** 注入 */
    @Autowired
    private AppMissionService appMissionService;

    /**
     * 新增
     *
     * @param appMission
     * @return
     */
    @PostMapping(value = "/add")
    public Result<AppMission> save(@Valid AppMission appMission, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [appMission, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }


        return ResultUtil.success(appMissionService.save(appMission));
    }

    /**
     * 更新
     *
     * @param appMission
     * @return
     */
    @PostMapping(value = "/update")
    public Result<AppMission> update(@Valid AppMission appMission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        AppMission findOne = appMissionService.findOne(appMission.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(appMissionService.save(appMission));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Long code) {

        appMissionService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<AppMission> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(appMissionService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<AppMission>> findAll() {
        return ResultUtil.success(appMissionService.findAll());
    }




    /**
     * 发布：更新时间
     *
     * @param updateTime
     * @param code
     * @return
     */
    @PostMapping(value = "/updateTimeByCode")
    public Result<AppMission> updatePublishByCode(@RequestParam(name = "updateTime", defaultValue = "") String updateTime,
                                                @RequestParam(name = "code", defaultValue = "") Long code) {

        AppMission s1 = new AppMission();
        s1.getUpdateTime();

        if (s1 == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }


        AppMission appMission = appMissionService.findOne(code);
        if(appMission == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }



        Integer i = appMissionService.updateTimeByCode(updateTime, code);
        if (i > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
    }


    /**
     * 通过状态编码查询-分页
     *
     * @param updateTime
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getAllByUpdateTimeByPage")
    public Result<Page<AppMission>> getAllByUpdateTimeByPage(@RequestParam(value = "updateTime", defaultValue = "") String updateTime,
                                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                               @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(appMissionService.getAllByUpdateCodeByPage(updateTime, page, size, sort, asc));
    }


    /**
     * 发布：更新时间
     *
     * @param examTime
     * @param code
     * @return
     */
    @PostMapping(value = "/updateExamTimeByCode")
    public Result<AppMission> updateExamTimeByCode(@RequestParam(name = "examTime", defaultValue = "") String examTime,
                                                  @RequestParam(name = "code", defaultValue = "") Long code) {

        AppMission s1 = new AppMission();
        s1.getUpdateTime();

        if (s1 == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }


        AppMission appMission = appMissionService.findOne(code);
        if(appMission == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }



        Integer i = appMissionService.updateTimeByCode(examTime, code);
        if (i > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
    }


    /**
     * 通过状态编码查询-分页
     *
     * @param examTime
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getAllByExamTimeByPage")
    public Result<Page<AppMission>> getAllByExamTimeByPage(@RequestParam(value = "examTime", defaultValue = "") String examTime,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                             @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(appMissionService.getAllByExamCodeByPage(examTime, page, size, sort, asc));
    }
}
