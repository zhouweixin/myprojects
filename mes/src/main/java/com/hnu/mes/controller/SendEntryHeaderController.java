package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.*;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 17:42 2018/5/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/sendEntryHeader")
public class SendEntryHeaderController {
    @Autowired
    private SendEntryHeaderService sendEntryHeaderService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private UserService userService;

    @Autowired
    private RawTypeService rawTypeService;

    @Autowired
    private CustomerService customerService;

    /**
     * 新增
     *
     * @param sendEntryHeader
     * @return
     */
    @PostMapping(value = "/add")
    public Result<SendEntryHeader> add(@RequestBody SendEntryHeader sendEntryHeader, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().toString());
        }

        if (sendEntryHeader.getSupplier().getCode() == null || supplierService.findOne(sendEntryHeader.getSupplier().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_SUPPLIER_NOT_EXISTS));
        }

        if (sendEntryHeader.getSender().getCode() == null || customerService.getByCode(sendEntryHeader.getSender().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_SENDER_NOT_EXISTS));
        }

        return ResultUtil.success(sendEntryHeaderService.save(sendEntryHeader));
    }

    /**
     * 更新
     *
     * @param sendEntryHeader
     * @return
     */
    @PostMapping(value = "/update")
    public Result<SendEntryHeader> update(@RequestBody SendEntryHeader sendEntryHeader, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().toString());
        }

        if (sendEntryHeaderService.findOne(sendEntryHeader.getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if (sendEntryHeader.getSupplier().getCode() == null || supplierService.findOne(sendEntryHeader.getSupplier().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_SUPPLIER_NOT_EXISTS));
        }

        if (sendEntryHeader.getSender().getCode() == null || customerService.getByCode(sendEntryHeader.getSender().getCode()) == null) {
            return ResultUtil.error(new MesException(EnumException.ADD_FAILED_SENDER_NOT_EXISTS));
        }

        return ResultUtil.success(sendEntryHeaderService.update(sendEntryHeader));
    }

    /**
     * 通过编码删除
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/deleteByCode")
    public Result<Object> deleteByCode(@RequestParam(value = "code") Long code) {
        if (sendEntryHeaderService.findOne(code) == null) {
            return ResultUtil.error(new MesException(EnumException.DELETE_FAILED));
        }

        sendEntryHeaderService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     *
     * @param sendEntryHeaders
     * @return
     */
    @PostMapping(value = "/deleteByCodeBatch")
    public Result<Object> deleteByCodeBatch(@RequestBody Set<SendEntryHeader> sendEntryHeaders) {
        sendEntryHeaderService.deleteInBatch(sendEntryHeaders);
        return ResultUtil.success();
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<Object> findByCode(@RequestParam(value = "code") Long code) {
        return ResultUtil.success(sendEntryHeaderService.findOne(code));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<Object> findAll() {
        return ResultUtil.success(sendEntryHeaderService.findAll());
    }

    /**
     * 通过编码更新状态
     *
     * @param code
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatusByCode")
    public Result<Object> updateStatusByCode(@RequestParam(value = "code") Long code,
                                             @RequestParam(value = "status") Integer status,
                                             @RequestParam(value = "userCode") String userCode) {
        SendEntryHeader sendEntryHeader = sendEntryHeaderService.findOne(code);
        if (sendEntryHeader == null) {
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if(status <= sendEntryHeader.getStatus()){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_STATUS_NOT_LEGAL));
        }

        User user = userService.findOne(userCode);
        if(user == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_USER_NOT_EXISTS));
        }

        sendEntryHeaderService.updateStatusByCode(code, status, user);
        return ResultUtil.success();
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
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<SendEntryHeader>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(sendEntryHeaderService.getSendEntryHeaderByPage(page, size, sort, asc));
    }

    /**
     * 通过公司编码查询-分页
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
    @RequestMapping(value = "/getBySupplierCodeByPage")
    public Result<Page<SendEntryHeader>> findBySupplierCodeByPage(
                                            @RequestParam(value = "supplierCode", defaultValue = "0") String supplierCode,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);

        return ResultUtil.success(sendEntryHeaderService.getSendEntryHeadersBySupplierByPage(supplier, page, size, sort, asc));
    }

    /**
     * 通过状态和发货人编码查询-分页
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
    @RequestMapping(value = "/getSendEntryHeadersByStatusAndSenderByPage")
    public Result<Page<SendEntryHeader>> findSendEntryHeadersByStatusAndSenderByPage(@RequestParam(value = "status", defaultValue = "0") Integer status,
                                                                  @RequestParam(value = "senderCode") String senderCode,
                                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                  @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        Customer sender = new Customer();
        sender.setCode(senderCode);
        return ResultUtil.success(sendEntryHeaderService.findSendEntryHeadersByStatusAndSenderByPage(status, sender, page, size, sort, asc));
    }

    /**
     * 通过公司编码和状态查询-分页
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
    @RequestMapping(value = "/getBySupplierCodeAndStatusByPage")
    public Result<Page<SendEntryHeader>> findBySupplierCodeAndStatusByPage(@RequestParam(value = "supplierCode", defaultValue = "0") String supplierCode,
                                                                         @RequestParam(value = "status", defaultValue = "0") Integer status,
                                                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                         @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        Supplier supplier = new Supplier();
        supplier.setCode(supplierCode);

        return ResultUtil.success(sendEntryHeaderService.findSendEntryHeaderBySupplierAndStatusByPage(supplier, status, page, size, sort, asc));
    }

}
