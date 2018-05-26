package com.hnu.mes.controller;

import com.hnu.mes.domain.Bound;
import com.hnu.mes.domain.Indicator;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.BoundService;
import com.hnu.mes.service.IndicatorService;
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
@RequestMapping("/bound")
public class BoundController {
    /**
     * 注入
     */
    @Autowired
    private BoundService boundService;

    @Autowired
    private IndicatorService indicatorService;

    /**
     * 新增
     *
     * @param bound
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Bound> save(@Valid Bound bound, BindingResult bindingResult,
                              @RequestParam(name = "indicatorCode") String indicatorCode) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Bound findOne = boundService.findOne(bound.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        Indicator indicator = indicatorService.findOne(indicatorCode);
        if (indicator == null) {
            return ResultUtil.error(new MesException(EnumException.INDICATOR_NOT_EXIST));
        }

        bound.setIndicator(indicator);

        return ResultUtil.success(boundService.save(bound));
    }

    /**
     * 更新
     *
     * @param bound
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Bound> update(@Valid Bound bound, BindingResult bindingResult,
                                @RequestParam(name = "indicatorCode") String indicatorCode) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Bound findOne = boundService.findOne(bound.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        Indicator indicator = indicatorService.findOne(indicatorCode);
        if (indicator == null) {
            return ResultUtil.error(new MesException(EnumException.INDICATOR_NOT_EXIST));
        }

        bound.setIndicator(indicator);

        return ResultUtil.success(boundService.save(bound));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {

        boundService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param bounds
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Bound> bounds){
        boundService.deleteInBatch(bounds);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Bound> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(boundService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Bound>> findAll() {
        return ResultUtil.success(boundService.findAll());
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
    public Result<Page<Bound>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sort", defaultValue = "code") String sort,
                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(boundService.getBoundByPage(page, size, sort, asc));
    }

    /**
     * 通过分页查询所有
     *
     * @param code 名称
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByCodeLikeByPage")
    public Result<Page<Bound>> findByCodeLikeByPage(
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(boundService.findByCodeLikeByPage(code, page, size, sort, asc));
    }
}
