package com.hnu.mes.controller;

import com.hnu.mes.domain.ProductGodownHeader;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.DepartmentService;
import com.hnu.mes.service.ProductGodownHeaderService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:33 2018/5/10
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/productGodownHeader")
public class ProductGodownHeaderController {

    @Autowired
    private ProductGodownHeaderService productGodownHeaderService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    /**
     * 新增
     *
     * @param productGodownHeader
     * @return
     */

    @PostMapping(value = "/add")
    public Result<ProductGodownHeader> save(@RequestBody ProductGodownHeader productGodownHeader, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().toString());
        }

        // 验证制造部门
        if(productGodownHeader.getDepartment() == null || departmentService.findOne(productGodownHeader.getDepartment().getCode()) == null){
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_PROD_DEPARTMENT_NOT_EXIST));
        }

        // 验证缴库人
        if(productGodownHeader.getPayer() == null || userService.findOne(productGodownHeader.getPayer().getCode()) == null){
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_PAYER_NOT_EXIST));
        }

        return ResultUtil.success(productGodownHeaderService.save(productGodownHeader));
    }

    /**
     * 查找
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<ProductGodownHeader> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(productGodownHeaderService.findOne(code));
    }

    /**
     * 查询所有-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<ProductGodownHeader>> findAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(productGodownHeaderService.findAllByPage(page, size, sort, asc));
    }

    /**
     * 通过入库状态查询
     *
     * @return
     */
    @RequestMapping(value = "/getByStatus")
    public Result<List<ProductGodownHeader>> findByStatus(
            @RequestParam(value = "status", defaultValue = "0") Integer status) {
        return ResultUtil.success(productGodownHeaderService.findByStatus(status));
    }

    /**
     * 通过入库状态查询-分页
     *
     * @param page 当前页,从0开始,默认是0
     * @param size 每页的记录数,默认是10
     * @param sort 排序的字段名,默认是code
     * @param asc  排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @RequestMapping(value = "/getByStatusByPage")
    public Result<Page<ProductGodownHeader>> findByStatus(
            @RequestParam(value = "status", defaultValue = "0") Integer status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(productGodownHeaderService.findByStatus(status, page, size, sort, asc));
    }

    /**
     * 通过编号模糊查询
     *
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getByBatchNumberLike")
    public Result<List<ProductGodownHeader>> getByBatchNumberLike(String batchNumber){
        return ResultUtil.success(productGodownHeaderService.findByBatchNumberLike(batchNumber));
    }

    /**
     * 通过入库状态和编号模糊查询
     *
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getByStatusBatchNumberLike")
    public Result<List<ProductGodownHeader>> getByStatusBatchNumberLike(Integer status, String batchNumber){
        return ResultUtil.success(productGodownHeaderService.findByStatusAndBatchNumberLike(status, batchNumber));
    }

    /**
     * 通过编码更新入库状态, 入库人
     * @param status
     * @param godownerCode
     * @param code
     * @return
     */
    @RequestMapping(value = "/updateStatusAndGodownerByCode")
    public Result<Object> updateStatusAndGodownerByCode(Integer status, String godownerCode, Long code){
        ProductGodownHeader productGodownHeader = productGodownHeaderService.findOne(code);
        if(productGodownHeader == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_PRODUCT_GODOWN_HEADER_NOT_EXIST));
        }

        User user = userService.findOne(godownerCode);
        if(user == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_GODOWNER_NOT_EXIST));
        }

        productGodownHeaderService.updateStatusAndGodownerAndGodownTimeByCode(status, user, code);

        return ResultUtil.success();
    }
}
