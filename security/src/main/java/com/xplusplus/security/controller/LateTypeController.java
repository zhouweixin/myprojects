package com.xplusplus.security.controller;

import com.xplusplus.security.domain.LateType;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.LateTypeService;
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
@RequestMapping(value = "/lateType")
public class LateTypeController {

    @Autowired
    private LateTypeService lateTypeService;

    /**
     * 新增迟到类型
     * @param lateType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<LateType> add(@Valid LateType lateType, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(lateTypeService.save(lateType));
    }

    /**
     * 更新迟到类型
     * @param lateType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<LateType> update(@Valid LateType lateType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(lateTypeService.update(lateType));
    }

    /**
     * 删除迟到类型
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        lateTypeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param lateTypes
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<LateType> lateTypes) {
        lateTypeService.deleteInBatch(lateTypes);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<LateType> getById(Integer id) {
        return ResultUtil.success(lateTypeService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<LateType>> getAll() {
        return ResultUtil.success(lateTypeService.findAll());

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
    public Result<Page<LateType>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(lateTypeService.findAllByPage(page, size, sortFieldName, asc));
    }
}
