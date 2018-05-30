package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.Schedule;
import com.xplusplus.security.service.ScheduleService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author：XudongHu
 * @description:
 * @date:20:13 2018/5/30
 */
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    /**
     * 新增班次
     */
    @RequestMapping(value = "/add")
    public Result<Schedule> add(@Valid Schedule schedule, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleService.save(schedule));
    }
    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<Schedule> update(@Valid Schedule schedule, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleService.update(schedule));
    }

    /**
     * 删除班次
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        scheduleService.delete(id);
        return ResultUtil.success();
    }


    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<Schedule> getById(Integer id) {
        return ResultUtil.success(scheduleService.findOne(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Schedule>> getAll() {
        return ResultUtil.success(scheduleService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<Schedule>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(scheduleService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<Schedule>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                      @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                      @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(scheduleService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }



}
