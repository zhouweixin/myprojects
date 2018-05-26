package com.hnu.mes.controller;

import com.hnu.mes.domain.Model;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ModelService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhouweixin on 2018/3/18.
 */
@RestController
@RequestMapping(value = "/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    /**
     * 新增
     *
     * @param model
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Model> save(@Valid Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        Integer maxCode = modelService.findMaxCode();
        Integer maxRank = modelService.findMaxRank();
        if(maxCode == null){
            maxCode = 1;
        }
        if(maxRank == null){
            maxRank = 1;
        }

        model.setCode(maxCode+1);
        model.setRank(maxRank+1);
        return ResultUtil.success(modelService.save(model));
    }

    /**
     * 更新
     *
     * @param model
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Model> update(@Valid Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Model findOne = modelService.findOne(model.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(modelService.save(model));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {
        // 判断是否存在
        Model findOne = modelService.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        modelService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Model> findOne(@RequestParam(value = "code") Integer code) {
        return ResultUtil.success(modelService.findOne(code));
    }

    @GetMapping(value = "/getAll")
    public Result<List<Model>> findAll(){
        return ResultUtil.success(modelService.findAll());
    }

    /**
     * 更新
     *
     * @param model
     * @return
     */
    @PostMapping(value = "/updateName")
    public Result<Model> updateMenuName(@Valid Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Model findOne = modelService.findOne(model.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        modelService.updateNameAndPathByCode(model.getName(), model.getCode());
        return ResultUtil.success();
    }

    /**
     * 交换
     *
     * @param code1
     * @param code2
     * @return
     */
    @RequestMapping(value = "/shift")
    public Result<Object> shiftUp(@RequestParam(name = "code1") Integer code1, @RequestParam(name = "code2") Integer code2){
        Model model1 = modelService.findOne(code1);
        Model model2 = modelService.findOne(code2);

        if(model1 == null || model2 == null){
            return ResultUtil.error(new MesException(EnumException.SHIFT_FAILED_NOT_EXISTS));
        }

        int temp = model1.getRank();
        model1.setRank(model2.getRank());
        model2.setRank(temp);
        modelService.save(model1);
        modelService.save(model2);
        return ResultUtil.success();
    }
}
