package com.hnu.mes.controller;

import com.hnu.mes.domain.ProcessStatus;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProcessStatusService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@RestController
@RequestMapping("processStatus")
public class ProcessStatusController {
    /**
     * 注入
     */
    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private UserService userService;

    /**
     * 新增
     *
     * @param processStatus
     * @return
     */
    @PostMapping(value = "/add")
    public Result<ProcessStatus> save(@Valid ProcessStatus processStatus, BindingResult bindingResult,
                                      @RequestParam(name = "curLeaderCode", defaultValue = "") String curLeaderCode) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        ProcessStatus findOne = processStatusService.findOne(processStatus.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        User curLeader = userService.findOne(curLeaderCode);
        if (curLeader == null) {
            return ResultUtil.error(new MesException(EnumException.CUR_LEADER_NOT_EXIST));
        }

        processStatus.setCurLeader(curLeader);

        return ResultUtil.success(processStatusService.save(processStatus));
    }

    /**
     * 更新
     *
     * @param processStatus
     * @return
     */
    @PostMapping(value = "/update")
    public Result<ProcessStatus> update(@Valid ProcessStatus processStatus, BindingResult bindingResult, String curLeaderCode) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        ProcessStatus findOne = processStatusService.findOne(processStatus.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        User curLeader = userService.findOne(curLeaderCode);
        if (curLeader == null) {
            return ResultUtil.error(new MesException(EnumException.CUR_LEADER_NOT_EXIST));
        }

        processStatus.setCurLeader(curLeader);

        return ResultUtil.success(processStatusService.save(processStatus));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {
        processStatusService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param processStatuses
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<ProcessStatus> processStatuses){
        processStatusService.deleteInBatch(processStatuses);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<ProcessStatus> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(processStatusService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<ProcessStatus>> findAll() {
        return ResultUtil.success(processStatusService.findAll());
    }

    /**
     * 通过分页查询所有
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByPage")
    public Result<Page<ProcessStatus>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sort", defaultValue = "code") String sort,
                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(processStatusService.getProcessStatusByPage(page, size, sort, asc));
    }

    /**
     * 通过分页查询所有
     *
     * @param status 名称
     * @param page   当前页,从0开始,默认是0
     * @param size   每页的记录数,默认是10
     * @param sort   排序的字段名,默认是code
     * @param asc    排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByStatusByPage")
    public Result<Page<ProcessStatus>> getAllByStatusByPage(
            @RequestParam(value = "status", defaultValue = "") Integer status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(processStatusService.findByStatusByPage(status, page, size, sort, asc));
    }
}
