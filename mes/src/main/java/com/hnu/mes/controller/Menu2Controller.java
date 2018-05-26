package com.hnu.mes.controller;

import java.util.List;

import javax.validation.Valid;

import com.hnu.mes.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.domain.Menu2;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.Menu2Service;
import com.hnu.mes.utils.ResultUtil;

/**
 * @author zhouweixin
 */
@RestController
@RequestMapping(value = "/menu2")
public class Menu2Controller {

    /**
     * 注入
     */
    @Autowired
    private Menu2Service menu2Service;

    @Autowired
    private ModelService modelService;

    /**
     * 新增
     *
     * @param menu2
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Menu2> save(@Valid Menu2 menu2, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        Integer maxCode = menu2Service.findMaxCode();
        Integer maxRank = menu2Service.findMaxRank();
        if(maxCode == null){
            maxCode = 1;
        }
        if(maxRank == null){
            maxRank = 1;
        }

        menu2.setCode(maxCode+1);
        menu2.setRank(maxRank+1);
        return ResultUtil.success(menu2Service.save(menu2));
    }

    /**
     * 更新
     *
     * @param menu2
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Menu2> update(@Valid Menu2 menu2, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Menu2 findOne = menu2Service.findOne(menu2.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(menu2Service.save(menu2));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {
        // 判断是否存在
        Menu2 findOne = menu2Service.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        Menu2 menu2 = new Menu2();
        menu2.setCode(code);
        if (modelService.findByMenu2(menu2) != null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_SUB_NOT_NULL));
        }
        menu2Service.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Menu2> findOne(@RequestParam(value = "code") Integer code) {

        return ResultUtil.success(menu2Service.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Menu2>> findAll() {
        return ResultUtil.success(menu2Service.findAll());
    }

    /**
     * 通过分页查询所有
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByPage")
    public Result<Page<Menu2>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sort", defaultValue = "code") String sort,
                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(menu2Service.getMenu2ByPage(page, size, sort, asc));
    }

    /**
     * 通过分页查询所有
     *
     * @param name 名称
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getAllByLikeNameByPage")
    public Result<Page<Menu2>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(menu2Service.findAllByLikeNameByPage(name, page, size, sort, asc));
    }

    /**
     * 更新
     *
     * @param menu2
     * @return
     */
    @PostMapping(value = "/updateName")
    public Result<Menu2> updateMenuName(@Valid Menu2 menu2, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Menu2 findOne = menu2Service.findOne(menu2.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }
        menu2Service.updateNameAndPathByCode(menu2.getName(), menu2.getCode());
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
        Menu2 menu21 = menu2Service.findOne(code1);
        Menu2 menu22 = menu2Service.findOne(code2);

        if(menu21 == null || menu22 == null){
            return ResultUtil.error(new MesException(EnumException.SHIFT_FAILED_NOT_EXISTS));
        }

        int temp = menu21.getRank();
        menu21.setRank(menu22.getRank());
        menu22.setRank(temp);
        menu2Service.save(menu21);
        menu2Service.save(menu22);
        return ResultUtil.success();
    }
}
