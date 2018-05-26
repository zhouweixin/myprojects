package com.hnu.mes.controller;

import com.hnu.mes.domain.Judge;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.JudgeService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhouweixin on 2018/3/20.
 */
@RestController
@RequestMapping(value = "/judge")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    /**
     * 新增
     *
     * @param judge
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Judge> add(@Valid Judge judge, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if (judgeService.findOne(judge.getCode()) != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(judgeService.save(judge));
    }

    /**
     * 删除
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/delete")
    public Result<Object> delete(Integer code) {

        if (judgeService.findOne(code) == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        judgeService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 更新
     *
     * @param judge
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Judge> update(@Valid Judge judge, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if (judgeService.findOne(judge.getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(judgeService.save(judge));
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/getByCode")
    public Result<Judge> getOne(Integer code) {
        return ResultUtil.success(judgeService.findOne(code));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Judge>> getAll() {
        return ResultUtil.success(judgeService.findAll());
    }
}
