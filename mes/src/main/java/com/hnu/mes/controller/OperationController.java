package com.hnu.mes.controller;

import com.hnu.mes.domain.ModelOperation;
import com.hnu.mes.domain.Operation;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ModelOperationService;
import com.hnu.mes.service.OperationService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:01 2018/4/13
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @Autowired
    private ModelOperationService modelOperationService;

    @PostMapping(value = "/add")
    public Result<Operation> save(@Valid Operation operation, BindingResult bindingResult) {
        /**
         * save
         * @Desciption 添加
         * @param [operation, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否重复
        Operation findOne = operationService.findOne(operation.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(operationService.save(operation));
    }

    @PostMapping(value = "/update")
    public Result<Operation> update(@Valid Operation operation, BindingResult bindingResult) {
        /**
         * update
         * @Desciption 更新
         * @param [operation, bindingResult]
         */
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        //判断是否存在
        Operation findOne = operationService.findOne(operation.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(operationService.save(operation));
    }

    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {
        //判断是否存在
        Operation findOne = operationService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        List<ModelOperation> modelOperations = modelOperationService.findModelOperationsByOperationCode(code);
        if(modelOperations != null && modelOperations.size() > 0){
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_REF_KEY_EXISTS));
        }

        operationService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param operationes
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<Operation> operationes){
        operationService.deleteInBatch(operationes);
        return ResultUtil.success();
    }

    @PostMapping(value = "/getByCode")
    public Result<Operation> findOne(@RequestParam(value = "code") Integer code) {
        //不存在时，会成功，data结果为null
        return ResultUtil.success(operationService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Operation>> findAll() {
        return ResultUtil.success(operationService.findAll());
    }

    @PostMapping(value = "/getAllByPage")
    public Result<Page<Operation>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAll
         * @Desciption 分页查询所有
         * @param [page, size, sort, asc]
         */
        return ResultUtil.success(operationService.getOperationByPage(page, size, sort, asc));
    }

    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Operation>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 分页模糊查询所有
         * @param [name, page, size, sort, asc]
         */
        return ResultUtil.success(operationService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }
}
