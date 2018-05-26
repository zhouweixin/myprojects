package com.hnu.mes.controller;

import com.hnu.mes.domain.Process;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProcessService;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.ResultUtil;
import com.sun.corba.se.pept.protocol.MessageMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/16
 * @Description: 工序控制层
 */
@RestController
@RequestMapping(value = "/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @PostMapping(value = "/add")
    public Result<Process> save(@Valid Process process, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [process, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否重复
        Process findOne = processService.findOne(process.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(processService.save(process));
    }

    /**
     * 更新
     * @param process
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Process> update(@Valid Process process, BindingResult bindingResult) {
        /**
         * update
         * @Desciption 更新
         * @param [process, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        Process findOne = processService.findOne(process.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if(findOne.getFlag() == GlobalUtil.SYSTEM_FLAG){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_SYSTEM_NOT_ALLOW));
        }

        return ResultUtil.success(processService.save(process));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {

        //判断是否存在
        Process findOne = processService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        // 系统值, 不允许删除
        if(findOne.getFlag() == GlobalUtil.SYSTEM_FLAG){
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_SYSTEM_NOT_ALLOW));
        }

        processService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param processes
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Process> processes){
        // 系统变量,不允许删除
        for(Process process : processes){
            if(process.getCode() == GlobalUtil.SYSTEM_FLAG){
                processes.remove(process);
            }
        }

        processService.deleteInBatch(processes);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<Process> findOne(@RequestParam(value = "code") Integer code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(processService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Process>> findAll() {
        return ResultUtil.success(processService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<Process>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(processService.getProcessByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Process>> findAllByLikeNameByPage(
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
        return ResultUtil.success(processService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
