package com.hnu.mes.controller;

import com.hnu.mes.domain.ModelOperation;
import com.hnu.mes.domain.ModelOperationPrimaryKey;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ModelOperationService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:36 2018/5/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/modelOperation")
public class ModelOperationController {
    @Autowired
    private ModelOperationService modelOperationService;

    /**
     * 新增
     *
     * @param modelOperation
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<ModelOperation> save(ModelOperation modelOperation){

        if(modelOperationService.findOne(new ModelOperationPrimaryKey(modelOperation)) != null){
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(modelOperationService.save(modelOperation));
    }

    /**
     * 批量新增
     *
     * @param modelOperations
     * @return
     */
    @RequestMapping(value = "/addBatch")
    public Result<Collection<ModelOperation>> saveBatch(@RequestBody Set<ModelOperation> modelOperations){
        return ResultUtil.success(modelOperationService.saveBatch(modelOperations));
    }

    /**
     * 批量更新
     *
     * @param modelOperations
     * @return
     */
    @RequestMapping(value = "/updateBatch")
    public Result<Object> updateBatch(@RequestBody Set<ModelOperation> modelOperations){
        modelOperationService.updateBatch(modelOperations);
        return ResultUtil.success();
    }

    /**
     * 删除一个
     * @param modelOperationPrimaryKey
     * @return
     */
    @RequestMapping(value = "/deleteByModelCodeAndOperationCode")
    public Result<Object> deleteByModelCodeAndOperationCode(ModelOperationPrimaryKey modelOperationPrimaryKey){
        if(modelOperationService.findOne(modelOperationPrimaryKey) == null){
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        modelOperationService.delete(modelOperationPrimaryKey);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param modelOperationPrimaryKeys
     * @return
     */
    @RequestMapping(value = "/deleteByBatch")
    public Result<Object> deleteBatch(@RequestBody Set<ModelOperationPrimaryKey> modelOperationPrimaryKeys){
        modelOperationService.deleteBatch(modelOperationPrimaryKeys);
        return ResultUtil.success();
    }

    /**
     * 查询一个
     * @param modelOperationPrimaryKey
     * @return
     */
    @RequestMapping(value = "/getByModelCodeAndOperationCode")
    public Result<ModelOperation> findOne(ModelOperationPrimaryKey modelOperationPrimaryKey){
        return ResultUtil.success(modelOperationService.findOne(modelOperationPrimaryKey));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<ModelOperation>> findAll(){
        return ResultUtil.success(modelOperationService.findAll());
    }

    /**
     * 通过modelCode查询
     * @param modelCode
     * @return
     */
    @RequestMapping(value = "/getByModelCode")
    public Result<List<ModelOperation>> findModelOperationsByModeCode(@RequestParam(name = "modelCode") Integer modelCode){
        return ResultUtil.success(modelOperationService.findModelOperationsByModeCode(modelCode));
    }
}
