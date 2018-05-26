package com.hnu.mes.controller;

import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.Status;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.StatusService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    /**
     * 新增
     *
     * @param status
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Status> save(@Valid Status status, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if (statusService.findOne(status.getCode()) != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(statusService.save(status));
    }

    /**
     * 通过编码删除
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(Integer code) {
        if (statusService.findOne(code) == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        statusService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Status> getByCode(Integer code) {
        return ResultUtil.success(statusService.findOne(code));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Status>> getAll() {
        return ResultUtil.success(statusService.findAll());
    }

    /**
     * 更新
     *
     * @param status
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Status> update(@Valid Status status, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if (statusService.findOne(status.getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(statusService.update(status));
    }
}
