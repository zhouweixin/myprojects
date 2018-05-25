package com.xplusplus.security.controller;

import com.xplusplus.security.domain.HealthStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.HealthStatusService;
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
 * @Author: liweifeng
 * @Description:
 * @Date: Created in 12:41 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/healthStatus")
public class HealthStatusController {
    @Autowired
    private HealthStatusService healthStatusService;

    /**
     * 新增健康状况
     * @param healthStatus
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<HealthStatus> add(@Valid HealthStatus healthStatus, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(healthStatusService.save(healthStatus));
    }

    /**
     * 更新健康状况
     * @param healthStatus
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<HealthStatus> update(@Valid HealthStatus healthStatus, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(healthStatusService.update(healthStatus));
    }

    /**
     * 删除健康状况
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        healthStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param healthStatuss
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<HealthStatus> healthStatuss) {
        healthStatusService.deleteInBatch(healthStatuss);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<HealthStatus> getById(Integer id) {
        return ResultUtil.success(healthStatusService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<HealthStatus>> getAll() {
        return ResultUtil.success(healthStatusService.findAll());

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
    public Result<Page<HealthStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(healthStatusService.findAllByPage(page, size, sortFieldName, asc));
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
    public Result<Page<HealthStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(healthStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
