package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.DepartmentService;
import com.hnu.mes.service.GodownEntryHeaderService;
import com.hnu.mes.service.GodownTestInformHeaderService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 13:56 2018/5/2
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/godownTestInformHeader")
public class GodownTestInformHeaderController {
    @Autowired
    private GodownTestInformHeaderService godownTestInformHeaderService;

    @Autowired
    private GodownEntryHeaderService godownEntryHeaderService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add")
    public Result<GodownTestInformHeader> save(Long godownEntryHeaderCode, String departmentCode, String createUserCode){
        GodownEntryHeader godownEntryHeader = godownEntryHeaderService.findOne(godownEntryHeaderCode);
        if(godownEntryHeader == null){
            return ResultUtil.error(new MesException(EnumException.FAILED_GODOWN_ENTRY_NOT_EXIST));
        }

        Department department = departmentService.findOne(departmentCode);
        if(department == null){
            return ResultUtil.error(new MesException(EnumException.FAILED_DEPARTMENT_NOT_EXIST));
        }

        User user = userService.findOne(createUserCode);
        if(userService.findOne(createUserCode) == null){
            return ResultUtil.error(new MesException(EnumException.FAILED_USER_NOT_EXIST));
        }

        return ResultUtil.success(godownTestInformHeaderService.save(godownEntryHeader, department, user));
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<GodownTestInformHeader> findOne(@RequestParam(value = "code") Long code){
        return ResultUtil.success(godownTestInformHeaderService.findOne(code));
    }

    /**
     * 通过状态和领取人工号查询-分页
     *
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getByStatusAndAndReceiptorByPage")
    public Result<Page<GodownTestInformHeader>> findGodownTestInformHeadersByStatusAndAndReceiptorByPage(@RequestParam(value = "status") Integer status,
                                                                                                         @RequestParam(value = "receiptorCode") String receiptorCode,
                                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                                       @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        User user = new User();
        user.setCode(receiptorCode);
        return ResultUtil.success(godownTestInformHeaderService.findGodownTestInformHeadersByStatusAndAndReceiptor(status, user, page, size, sort, asc));
    }

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     * @return
     */
    @RequestMapping(value = "/updateStatusByCode")
    public Result<Object> updateStatusByCode(@RequestParam(value = "status") Integer status,
                                             @RequestParam(value = "code") Long code){
        godownTestInformHeaderService.updateStatusByCode(status, code);
        return ResultUtil.success();
    }

    /**
     * 通过状态查询-分页
     * @return
     */
    @PostMapping(value = "/getGodownTestInformHeadersByStatusByPage")
    public Result<Page<GodownTestInformHeader>> findGodownTestInformHeadersByStatusByPage(@RequestParam(value = "status") Integer status,
                                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                  @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(godownTestInformHeaderService.findGodownTestInformHeadersByStatus(status, page, size, sort, asc));
    }

    /**
     * 通过生产厂家编码查询-分页
     * @return
     */
    @PostMapping(value = "/getGodownTestInformHeadersBySupplierByPage")
    public Result<Page<GodownTestInformHeader>> findGodownTestInformHeadersBySupplierByPage(@RequestParam(value = "supplierCode") String supplierCode,
                                                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                        @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                        @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);
        return ResultUtil.success(godownTestInformHeaderService.findGodownTestInformHeadersBySupplier(supplier, page, size, sort, asc));

    }

    /**
     * 通过生产厂家编码和状态查询-分页
     * @return
     */
    @PostMapping(value = "/getGodownTestInformHeadersByStatusAndSupplierByPage")
    public Result<Page<GodownTestInformHeader>> findGodownTestInformHeadersByStatusAndSupplierByPage(@RequestParam(value = "status") Integer status,
                                                                                 @RequestParam(value = "supplierCode") String supplierCode,
                                                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                                 @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                                 @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);
        return ResultUtil.success(godownTestInformHeaderService.findGodownTestInformHeadersByStatusAndSupplier(status, supplier, page, size, sort, asc));
    }
}
