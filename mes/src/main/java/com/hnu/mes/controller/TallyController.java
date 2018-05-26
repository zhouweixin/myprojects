package com.hnu.mes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.domain.Tally;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.TallyService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/tally")
public class TallyController {

    /** 注入 */
    @Autowired
    private TallyService tallyService;

    /**
     * 新增
     *
     * @param tally
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Tally> save(@Valid Tally tally, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Tally findOne = tallyService.findOne(tally.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(tallyService.save(tally));
    }

    /**
     * 更新
     *
     * @param tally
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Tally> update(@Valid Tally tally, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Tally findOne = tallyService.findOne(tally.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(tallyService.save(tally));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") String code) {

        tallyService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Tally> findOne(@RequestParam(value = "code") String code) {

        return ResultUtil.success(tallyService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Tally>> findAll() {
        return ResultUtil.success(tallyService.findAll());
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
    public Result<Page<Tally>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(tallyService.getTallyByPage(page, size, sort, asc));
    }

    /**
     * 通过分页查询所有
     *
     * @param archivecode
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
    public Result<Page<Tally>> findAllByLikeNameByPage(
            @RequestParam(value = "archivecode", defaultValue = "") String archivecode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(tallyService.findAllByLikeNameByPage(archivecode, page, size, sort, asc));
    }
}
