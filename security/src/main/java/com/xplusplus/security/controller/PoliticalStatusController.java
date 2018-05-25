package com.xplusplus.security.controller;

import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.PoliticalStatusService;
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
 * @Description: 政治面貌Controller
 * @Date: Created in 12:45 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/politicalStatus")
public class PoliticalStatusController {
    @Autowired
    private PoliticalStatusService politicalStatusService;

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    public Result<PoliticalStatus> add(@Valid PoliticalStatus politicalStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(politicalStatusService.save(politicalStatus));
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<PoliticalStatus> update(@Valid PoliticalStatus politicalStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage().toString());
        }

        return ResultUtil.success(politicalStatusService.update(politicalStatus));
    }

    /**
     * 通过id删除
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id) {
        politicalStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<PoliticalStatus> politicalStatuss) {
        politicalStatusService.deleteInBatch(politicalStatuss);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<PoliticalStatus> getById(Integer id) {
        return ResultUtil.success(politicalStatusService.findOne(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<PoliticalStatus>> getAll() {
        return ResultUtil.success(politicalStatusService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<PoliticalStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(politicalStatusService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<PoliticalStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                    @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                    @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(politicalStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
