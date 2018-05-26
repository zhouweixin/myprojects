package com.hnu.mes.controller;

import com.hnu.mes.domain.Material;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.RawTypeService;
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
 * @Date: Created in 9:00 2018/5/2
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/rawType")
public class RawTypeController {
    /** 注入 */
    @Autowired
    private RawTypeService rawTypeService;

    /**
     * 新增
     *
     * @param rawType
     * @return
     */
    @PostMapping(value = "/add")
    public Result<RawType> save(@Valid RawType rawType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        RawType findOne = rawTypeService.findOne(rawType.getCode());
        if (findOne != null) {
            return ResultUtil.error(new MesException(EnumException.CODE_DUPLICATE));
        }

        return ResultUtil.success(rawTypeService.save(rawType));
    }

    /**
     * 更新
     *
     * @param rawType
     * @return
     */
    @PostMapping(value = "/update")
    public Result<RawType> update(@Valid RawType rawType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        RawType findOne = rawTypeService.findOne(rawType.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(rawTypeService.save(rawType));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Long code) {

        rawTypeService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param rawTypes
     * @return
     */
    @PostMapping(value = "/deleteByBatchCode")
    public Result<Object> deleteByBatchCode(@RequestBody Set<RawType> rawTypes){
        rawTypeService.deleteInBatch(rawTypes);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<RawType> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(rawTypeService.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<RawType>> findAll() {
        return ResultUtil.success(rawTypeService.findAll());
    }

    /**
     * 查询所有-分页
     *
     * @param page
     *            当前页,从0开始,默认是0
     * @param size
     *            每页的记录数,默认是10
     * @param sort
     *            排序的字段名,默认是code
     * @param asc
     *            排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByPage")
    public Result<Page<RawType>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(rawTypeService.getRawTypeByPage(page, size, sort, asc));
    }

    /**
     * 通过名称模糊查询所有-分页
     *
     * @param name
     *            名称
     * @param page
     *            当前页,从0开始,默认是0
     * @param size
     *            每页的记录数,默认是10
     * @param sort
     *            排序的字段名,默认是code
     * @param asc
     *            排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<RawType>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(rawTypeService.findAllByLikeNameByPage(name, page, size, sort, asc));
    }

    /**
     * 通过原料类型编码查询
     *
     * @param materialCode
     * @return
     */
    @RequestMapping(value = "/getByMaterialCode")
    public Result<List<RawType>> getByMaterialCode(@RequestParam(value = "materialCode") Integer materialCode){
        Material material = new Material();
        material.setCode(materialCode);
        return ResultUtil.success(rawTypeService.findByMaterial(material));
    }
}
