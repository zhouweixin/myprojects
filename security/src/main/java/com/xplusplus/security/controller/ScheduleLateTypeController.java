package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.ScheduleLateType;
import com.xplusplus.security.service.ScheduleLateTypeService;
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

@RestController
@RequestMapping(value = "/scheduleLateType")
public class ScheduleLateTypeController {
    @Autowired
    private ScheduleLateTypeService scheduleLateTypeService;

    /**
     * 新增学历
     * @param scheduleLateType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<ScheduleLateType> add(@Valid ScheduleLateType scheduleLateType, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleLateTypeService.save(scheduleLateType));
    }

    /**
     * 更新学历
     * @param scheduleLateType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<ScheduleLateType> update(@Valid ScheduleLateType scheduleLateType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleLateTypeService.update(scheduleLateType));
    }

    /**
     * 删除学历
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        scheduleLateTypeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param scheduleLateTypes
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<ScheduleLateType> scheduleLateTypes) {
        scheduleLateTypeService.deleteInBatch(scheduleLateTypes);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<ScheduleLateType> getById(Integer id) {
        return ResultUtil.success(scheduleLateTypeService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<ScheduleLateType>> getAll() {
        return ResultUtil.success(scheduleLateTypeService.findAll());

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
    public Result<Page<ScheduleLateType>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(scheduleLateTypeService.findAllByPage(page, size, sortFieldName, asc));
    }

}
