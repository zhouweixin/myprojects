package com.xplusplus.security.controller;

import com.xplusplus.security.domain.ContractType;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.ContractTypeService;
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
@RequestMapping(value = "/contractType")
public class ContractTypeController {
    @Autowired
    private ContractTypeService contractTypeService;

    /**
     * 新增学历
     * @param contractType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<ContractType> add(@Valid ContractType contractType, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(contractTypeService.save(contractType));
    }

    /**
     * 更新学历
     * @param contractType
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<ContractType> update(@Valid ContractType contractType, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(contractTypeService.update(contractType));
    }

    /**
     * 删除学历
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        contractTypeService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param contractTypes
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<ContractType> contractTypes) {
        contractTypeService.deleteInBatch(contractTypes);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<ContractType> getById(Integer id) {
        return ResultUtil.success(contractTypeService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<ContractType>> getAll() {
        return ResultUtil.success(contractTypeService.findAll());

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
    public Result<Page<ContractType>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(contractTypeService.findAllByPage(page, size, sortFieldName, asc));
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
    public Result<Page<ContractType>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(contractTypeService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
