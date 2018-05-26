package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.DepartmentService;
import com.hnu.mes.service.GodownEntryHeaderService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 9:45 2018/5/2
 * @Modified By:
 */
@RestController
@RequestMapping("/godownEntryHeader")
public class GodownEntryHeaderController {
    @Autowired
    private GodownEntryHeaderService godownEntryHeaderService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 通过编码更新状态
     *
     * @param code
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatusByCode")
    public Result<Object> updateStatusByCode(@RequestParam(value = "code") Long code,
                                             @RequestParam(value = "status") Integer status) {
        GodownEntryHeader godownEntryHeader = godownEntryHeaderService.findOne(code);
        if (godownEntryHeader == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        godownEntryHeaderService.updateStatusByCode(status, code);
        return ResultUtil.success();
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<GodownEntryHeader> getByCode(@RequestParam(value = "code") Long code) {
        return ResultUtil.success(godownEntryHeaderService.findOne(code));
    }

    /**
     * 通过入库编号查询
     *
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getByBatchNumber")
    public Result<GodownEntryHeader> getByBatchNumber(@RequestParam(value = "batchNumber") String batchNumber) {
        return ResultUtil.success(godownEntryHeaderService.findByBatchNumber(batchNumber));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<GodownEntryHeader>> findAll(){
        return ResultUtil.success(godownEntryHeaderService.findAll());
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
    public Result<Page<GodownEntryHeader>> findAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(godownEntryHeaderService.findAllByPage(page, size, sort, asc));
    }

    /**
     * 通过状态分页查询-分页
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getByStatusByPage")
    public Result<Page<GodownEntryHeader>> getByStatusByPage(@RequestParam(value = "status", defaultValue = "0") Integer status,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(godownEntryHeaderService.findGodownEntryHeadersByStatus(status, page, size, sort, asc));
    }

    /**
     * 通过发货厂家编码和状态查询-分页
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getByStatusAndSupplierCodeByPage")
    public Result<Page<GodownEntryHeader>> getByStatusBySupplierCodeByPage(@RequestParam(value = "status", defaultValue = "0") Integer status,
                                                             @RequestParam(value = "supplierCode") String supplierCode,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                             @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);
        return ResultUtil.success(godownEntryHeaderService.findGodownEntryHeadersByStatusAndSupplier(status, supplier, page, size, sort, asc));
    }

    /**
     * 通过发货厂家编码查询-分页
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getBySupplierByPage")
    public Result<Page<GodownEntryHeader>> findGodownEntryHeadersBySupplierByPage(@RequestParam(value = "supplierCode") String supplierCode,
                                                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                           @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);
        return ResultUtil.success(godownEntryHeaderService.findGodownEntryHeadersBySupplier(supplier, page, size, sort, asc));
    }
}
