package com.xplusplus.security.controller;

import com.xplusplus.security.domain.ScheduleType;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.ScheduleTypeService;
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
@RequestMapping(value = "/scheduleType")
public class ScheduleTypeController {
    @Autowired
    private ScheduleTypeService scheduleTypeService;

    /**
     * 新增班次类型
     * @param scheduleType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<ScheduleType> add(@Valid ScheduleType scheduleType, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleTypeService.save(scheduleType));
    }

    /**
     * 更新班次类型
     * @param scheduleType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<ScheduleType> update(@Valid ScheduleType scheduleType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(scheduleTypeService.update(scheduleType));
    }

    /**
     * 删除班次类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        scheduleTypeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param scheduleTypes
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<ScheduleType> scheduleTypes) {
        scheduleTypeService.deleteInBatch(scheduleTypes);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<ScheduleType> getById(Integer id) {
        return ResultUtil.success(scheduleTypeService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<ScheduleType>> getAll() {
        return ResultUtil.success(scheduleTypeService.findAll());

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
    public Result<Page<ScheduleType>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(scheduleTypeService.findAllByPage(page, size, sortFieldName, asc));
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
    public Result<Page<ScheduleType>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(scheduleTypeService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
