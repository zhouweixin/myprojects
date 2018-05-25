package com.xplusplus.security.controller;

import com.xplusplus.security.domain.MilitaryStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.MilitaryStatusService;
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
 * @Description: 兵役状况Controller
 * @Date: Created in 12:43 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/militaryStatus")
public class MilitaryStatusControlller {
    @Autowired
    private MilitaryStatusService militaryStatusService;
    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public Result<MilitaryStatus> add(@Valid MilitaryStatus militaryStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(militaryStatusService.save(militaryStatus));
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<MilitaryStatus> update(@Valid MilitaryStatus militaryStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(militaryStatusService.update(militaryStatus));
    }

    /**
     * 通过id删除
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id) {
        militaryStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<MilitaryStatus> militaryStatuss) {
        militaryStatusService.deleteInBatch(militaryStatuss);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<MilitaryStatus> getById(Integer id) {
        return ResultUtil.success(militaryStatusService.findOne(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<MilitaryStatus>> getAll() {
        return ResultUtil.success(militaryStatusService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<MilitaryStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(militaryStatusService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<MilitaryStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                    @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                    @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(militaryStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
