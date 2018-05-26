package com.hnu.mes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.hnu.mes.service.Menu2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnu.mes.domain.Menu1;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.Menu1Service;
import com.hnu.mes.utils.ResultUtil;

/**
 * @author zhouweixin
 */
@RestController
@RequestMapping(value = "/menu1")
public class Menu1Controller {
    /**
     * 注入
     */
    @Autowired
    private Menu1Service menu1Service;

    @Autowired
    private Menu2Service menu2Service;

    /**
     * 新增
     *
     * @param menu1
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Menu1> save(@Valid Menu1 menu1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否重复
        Integer maxCode = menu1Service.findMaxCode();
        Integer maxRank = menu1Service.findMaxRank();
        if(maxCode == null){
            maxCode = 1;
        }
        if(maxRank == null){
            maxRank = 1;
        }

        menu1.setCode(maxCode+1);
        menu1.setRank(maxRank+1);
        return ResultUtil.success(menu1Service.save(menu1));
    }

    /**
     * 更新
     *
     * @param menu1
     * @return
     */
    @PostMapping(value = "/update")
    public Result<Menu1> update(@Valid Menu1 menu1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Menu1 findOne = menu1Service.findOne(menu1.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        return ResultUtil.success(menu1Service.save(menu1));
    }

    /**
     * 删除
     *
     * @param code
     */
    @PostMapping(value = "/deleteByCode")
    public Result<Object> delete(@RequestParam(value = "code") Integer code) {
        // 判断是否存在
        Menu1 findOne = menu1Service.findOne(code);
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        Menu1 menu1 = new Menu1();
        menu1.setCode(code);
        if(menu2Service.findByMenu1(menu1) != null){
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED_SUB_NOT_NULL));
        }

        menu1Service.delete(code);
        return ResultUtil.success();
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<Menu1> findOne(@RequestParam(value = "code") Integer code) {

        return ResultUtil.success(menu1Service.findOne(code));
    }

    /**
     * 查找所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<Menu1>> findAll() {
        return ResultUtil.success(menu1Service.findAll());
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
    public Result<Page<Menu1>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sort", defaultValue = "code") String sort,
                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(menu1Service.getMenu1ByPage(page, size, sort, asc));
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
    public Result<Page<Menu1>> findAllByLikeNameByPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(menu1Service.findAllByLikeNameByPage(name, page, size, sort, asc));
    }

    /**
     * 查询所有菜单图标
     *
     * @return
     */
    @RequestMapping(value = "getAllMenuIcon")
    public Result<List<String>> getMenuIcon() {
        List<String> imageIcon = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            imageIcon.add("dist/images/nav/" + i + ".png");
        }

        return ResultUtil.success(imageIcon);
    }

    /**
     * 更新
     *
     * @param menu1
     * @return
     */
    @PostMapping(value = "/updateNameAndPath")
    public Result<Menu1> updateMenuName(@Valid Menu1 menu1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        // 判断是否存在
        Menu1 findOne = menu1Service.findOne(menu1.getCode());
        if (findOne == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }
        menu1Service.updateNameAndPathByCode(menu1.getName(), menu1.getPath(), menu1.getCode());
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
        Menu1 menu11 = menu1Service.findOne(code1);
        Menu1 menu12 = menu1Service.findOne(code2);

        if(menu11 == null || menu12 == null){
            return ResultUtil.error(new MesException(EnumException.SHIFT_FAILED_NOT_EXISTS));
        }

        int temp = menu11.getRank();
        menu11.setRank(menu12.getRank());
        menu12.setRank(temp);
        menu1Service.save(menu11);
        menu1Service.save(menu12);
        return ResultUtil.success();
    }
}
