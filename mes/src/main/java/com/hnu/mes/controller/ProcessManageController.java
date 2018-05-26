package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.hnu.mes.domain.ProcessManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.hnu.mes.domain.Process;

import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProcessManageService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/check")
public class ProcessManageController {

    /** 注入 */
    @Autowired
    private ProcessManageService processManageService;

    /**
     * 新增
     *
     * @param processManage
     * @return
     */
    @PostMapping(value = "/add")
    public Result<ProcessManage> save(@Valid ProcessManage processManage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        ProcessManage findOne = processManageService.findOne(processManage.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(processManageService.save(processManage));
    }

    /**
     * 更新
     *
     * @param processManage
     * @return
     */
    @PostMapping(value = "/update")
    public Result<ProcessManage> update(@Valid ProcessManage processManage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        ProcessManage findOne = processManageService.findOne(processManage.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(processManageService.save(processManage));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {

        processManageService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<ProcessManage> findOne(@RequestParam(value = "code") Integer code) {

        return ResultUtil.success(processManageService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<ProcessManage>> findAll() {
        return ResultUtil.success(processManageService.findAll());
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
    public Result<Page<ProcessManage>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sort", defaultValue = "code") String sort,
                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(processManageService.getCheckByPage(page, size, sort, asc));
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
    public Result<Page<ProcessManage>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(processManageService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }

    /**
     * 批量删除
     *
     * @param processManages
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<ProcessManage> processManages){
        processManageService.deleteInBatch(processManages);
        return ResultUtil.success();
    }

    /**
     * 通过流程类型编码查询
     *
     * @param processCode
     * @return
     */
    @RequestMapping(value = "/getByProcessCode")
    public Result<List<ProcessManage>> getByProcessCode(@RequestParam(value = "processCode") Integer processCode){
        Process process = new Process();
        process.setCode(processCode);
        return ResultUtil.success(processManageService.findByProcess(process));
    }
}
