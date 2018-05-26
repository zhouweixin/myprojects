package com.hnu.mes.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.hnu.mes.domain.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ArchiveService;
import com.hnu.mes.utils.ResultUtil;

/**
 *
 * @author zhouweixin
 *
 */
@RestController
@RequestMapping(value = "/archive")
public class ArchiveController {

    /** 注入 */
    @Autowired
    private ArchiveService archiveService;

    /**
     * 新增
     *
     * @param archive
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Archive> save(@Valid Archive archive, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [archive, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }


        return ResultUtil.success(archiveService.save(archive));
    }

    /**
     * 更新
     *
     * @param archive
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Archive> update(@Valid Archive archive, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Archive findOne = archiveService.findOne(archive.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(archiveService.save(archive));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Long code) {

        archiveService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param archives
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Archive> archives){
        archiveService.deleteInBatch(archives);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Archive> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(archiveService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Archive>> findAll() {
        return ResultUtil.success(archiveService.findAll());
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
    public Result<Page<Archive>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(archiveService.getArchiveByPage(page, size, sort, asc));
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
    public Result<Page<Archive>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(archiveService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
