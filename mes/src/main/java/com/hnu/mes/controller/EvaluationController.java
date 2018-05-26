package com.hnu.mes.controller;

import com.hnu.mes.domain.Evaluation;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.EvaluationService;
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
@RequestMapping("evaluation")
public class EvaluationController {
    /** 注入 */
    @Autowired
    private EvaluationService evaluationService;

    /**
     * 新增
     *
     * @param evaluation
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Evaluation> save(@Valid Evaluation evaluation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Evaluation findOne = evaluationService.findOne(evaluation.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(evaluationService.save(evaluation));
    }

    /**
     * 更新
     *
     * @param evaluation
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Evaluation> update(@Valid Evaluation evaluation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Evaluation findOne = evaluationService.findOne(evaluation.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(evaluationService.save(evaluation));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {

        evaluationService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param evaluations
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Evaluation> evaluations){
        evaluationService.deleteInBatch(evaluations);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Evaluation> findOne(@RequestParam(value = "code") Integer code) {

        return ResultUtil.success(evaluationService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Evaluation>> findAll() {
        return ResultUtil.success(evaluationService.findAll());
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
    public Result<Page<Evaluation>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(evaluationService.getEvaluationByPage(page, size, sort, asc));
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
    public Result<Page<Evaluation>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(evaluationService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
