package com.xplusplus.security.controller;

import com.xplusplus.security.domain.WorkLoggingStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.WorkLoggingStatusService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:39 2018/6/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/workLoggintStatus")
public class WorkLoggingStatusController {
    @Autowired
    private WorkLoggingStatusService workLoggingStatusService;

    /**
     * 新增
     *
     * @param workLoggingStatus
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<WorkLoggingStatus> add(@Valid WorkLoggingStatus workLoggingStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(workLoggingStatusService.save(workLoggingStatus));
    }

    /**
     * 更新
     *
     * @param workLoggingStatus
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<WorkLoggingStatus> update(@Valid WorkLoggingStatus workLoggingStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(workLoggingStatusService.update(workLoggingStatus));
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id) {
        workLoggingStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param workLoggingStatuss
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<WorkLoggingStatus> workLoggingStatuss) {
        workLoggingStatusService.deleteInBatch(workLoggingStatuss);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<WorkLoggingStatus> getById(Integer id) {
        return ResultUtil.success(workLoggingStatusService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<WorkLoggingStatus>> getAll() {
        return ResultUtil.success(workLoggingStatusService.findAll());

    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<WorkLoggingStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(workLoggingStatusService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<WorkLoggingStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                    @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                    @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(workLoggingStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
