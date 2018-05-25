package com.xplusplus.security.controller;

import com.xplusplus.security.domain.MaritalStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.MaritalStatusService;
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
 * @Author: Huxudong
 * @Description: 婚姻状况Controller
 * @Date: Created in 12:42 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/maritalStatus")
public class MaritalStatusController {
    @Autowired
    private MaritalStatusService maritalStatusService;

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public Result<MaritalStatus> add(@Valid MaritalStatus maritalStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(maritalStatusService.save(maritalStatus));
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<MaritalStatus> update(@Valid MaritalStatus maritalStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(maritalStatusService.update(maritalStatus));
    }

    /**
     * 通过id删除
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id) {
        maritalStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<MaritalStatus> maritalStatuss) {
        maritalStatusService.deleteInBatch(maritalStatuss);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<MaritalStatus> getById(Integer id) {
        return ResultUtil.success(maritalStatusService.findOne(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<MaritalStatus>> getAll() {
        return ResultUtil.success(maritalStatusService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<MaritalStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(maritalStatusService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<MaritalStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                    @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                    @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(maritalStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
