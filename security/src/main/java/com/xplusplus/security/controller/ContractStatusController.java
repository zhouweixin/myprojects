package com.xplusplus.security.controller;

import com.xplusplus.security.domain.ContractStatus;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.ContractStatusService;
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
@RequestMapping(value = "/contractStatus")
public class ContractStatusController {
    @Autowired
    private ContractStatusService contractStatusService;

    /**
     * 新增学历
     * @param contractStatus
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<ContractStatus> add(@Valid ContractStatus contractStatus, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(contractStatusService.save(contractStatus));
    }

    /**
     * 更新学历
     * @param contractStatus
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<ContractStatus> update(@Valid ContractStatus contractStatus, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(contractStatusService.update(contractStatus));
    }

    /**
     * 删除学历
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        contractStatusService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param contractStatuss
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<ContractStatus> contractStatuses) {
        contractStatusService.deleteInBatch(contractStatuses);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<ContractStatus> getById(Integer id) {
        return ResultUtil.success(contractStatusService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<ContractStatus>> getAll() {
        return ResultUtil.success(contractStatusService.findAll());

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
    public Result<Page<ContractStatus>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(contractStatusService.findAllByPage(page, size, sortFieldName, asc));
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
    public Result<Page<ContractStatus>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(contractStatusService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
