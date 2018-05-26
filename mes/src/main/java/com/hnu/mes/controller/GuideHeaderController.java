package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hnu.mes.domain.GuideHeader;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.GuideHeaderService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/guideHeader")
public class GuideHeaderController {

    /** 注入 */
    @Autowired
    private GuideHeaderService guideHeaderService;

    /**
     * 新增
     *
     * @param guideHeader
     * @return
     */
    @PostMapping(value = "/add")
    public Result<GuideHeader> save(@Valid GuideHeader guideHeader, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        GuideHeader findOne = guideHeaderService.findOne(guideHeader.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(guideHeaderService.save(guideHeader));
    }

    /**
     * 更新
     *
     * @param guideHeader
     * @return
     */
    @PostMapping(value = "/update")
    public Result<GuideHeader> update(@Valid GuideHeader guideHeader, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        GuideHeader findOne = guideHeaderService.findOne(guideHeader.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(guideHeaderService.save(guideHeader));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {

        guideHeaderService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param guideHeaders
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<GuideHeader> guideHeaders){
        guideHeaderService.deleteInBatch(guideHeaders);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<GuideHeader> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(guideHeaderService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<GuideHeader>> findAll() {
        return ResultUtil.success(guideHeaderService.findAll());
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
    public Result<Page<GuideHeader>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(guideHeaderService.getGuideHeaderByPage(page, size, sort, asc));
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
    public Result<Page<GuideHeader>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(guideHeaderService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
