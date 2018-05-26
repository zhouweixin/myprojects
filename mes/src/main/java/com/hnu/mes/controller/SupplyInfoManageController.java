package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.service.CustomerService;
import com.hnu.mes.service.SupplierService;
import com.hnu.mes.service.SupplyInfoService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lanyage on 2018/3/14.
 */
@RestController
@RequestMapping("/supplyInfo")
public class SupplyInfoManageController {

    @Autowired
    private SupplyInfoService supplyInfoService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CustomerService customerService;

    /**
     * 查询所有供应信息记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @return
     */
    @PostMapping("/listInfo")
    public Result<Page<SupplyInfo>> listInfo(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                             @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                             @RequestParam(value = "asc", defaultValue = "1") int asc) {
        return ResultUtil.success(supplyInfoService.findAllPage(pageNumber, pageSize, sortField, asc));
    }

    /**
     * 查询所有供应商
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @return
     */
    @PostMapping("/listSupplier")
    public Result<Page<Supplier>> listSupplier(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                               @RequestParam(value = "asc", defaultValue = "1") int asc) {
        return ResultUtil.success(supplierService.findAllPage(pageNumber, pageSize, sortField, asc));
    }

    /**
     * 查询所有顾客
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @return
     */
    @PostMapping("/listCustomer")
    public Result<Page<Customer>> listCustomer(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                               @RequestParam(value = "asc", defaultValue = "1") int asc) {
        return ResultUtil.success(customerService.findAllPage(pageNumber, pageSize, sortField, asc));
    }

    /**
     * 删除一条供应记录
     *
     * @param code
     * @return
     */
    @PostMapping("/deleteSupplyInfo")
    public Result delete(@RequestParam("code") String code) {
        SupplyInfo one = new SupplyInfo();
        one.setCode(code);
        supplyInfoService.deleteOne(one);
        return ResultUtil.success();
    }

    /**
     * 保存一条供应信息
     *
     * @param one
     * @return
     */
    @PostMapping("/addSupplyInfo")
    public Result save(SupplyInfo one) {
        try {
            supplyInfoService.addOne(one);
        }catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success();
    }

    @PostMapping(value = "/saveInBatch")
    public Result saveInBatch(@RequestBody  List<SupplyInfo> supplyInfos) {
        supplyInfoService.saveInBatch(supplyInfos);
        return ResultUtil.success();
    }
    /**
     * 批量删除供应信息记录
     *
     * @param supplyInfoList
     * @return
     */
    @PostMapping(value = "/deleteSupplyInfoInBatch")
    public Result deleteByBatchCode(@RequestBody List<SupplyInfo> supplyInfoList) {
        supplyInfoService.deleteInBatch(supplyInfoList);
        return ResultUtil.success();
    }

    /**
     * 查询供应信息详情
     *
     * @param code
     * @return
     */
    @PostMapping("/infoDetail")
    public Result detail(@RequestParam("code") String code) {
        SupplyInfo one = new SupplyInfo();
        one.setCode(code);
        SupplyInfo detail = supplyInfoService.detail(one);
        return ResultUtil.success(detail);
    }

    @PostMapping("/supplierDetail")
    public Result supplierDetail(@RequestParam("code") String code) {
        Supplier one = new Supplier();
        one.setCode(code);
        Supplier detail = supplierService.detail(one);
        return ResultUtil.success(detail);
    }

    /**
     * 更新供应信息记录
     *
     * @param codeBefore
     * @param one
     * @return
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestParam("codeBefore") String codeBefore, SupplyInfo one) {
        supplyInfoService.update(codeBefore, one);
        return ResultUtil.success();
    }

    /**
     * 更新供应商信息
     *
     * @param codeBefore
     * @param one
     * @return
     */
    @PostMapping("/updateSupplier")
    public Result updateSupplier(@RequestParam("codeBefore") String codeBefore, Supplier one) {
        try {
            supplierService.update(codeBefore, one);
        } catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 更新顾客信息
     *
     * @param codeBefore
     * @param one
     * @return
     */
    @PostMapping("/updateCustomer")
    public Result updateCustomer(@RequestParam("codeBefore") String codeBefore, Customer one) {
        customerService.update(codeBefore, one);
        return ResultUtil.success();
    }

    /**
     * 按照供应商查询
     *
     * @param supplierName
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @return
     */
    @PostMapping("/findByCompany")
    public Result findByCompanyName(@RequestParam("supplierName") String supplierName,
                                    @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                    @RequestParam(value = "asc", defaultValue = "1") int asc) {
        Page<SupplyInfo> page = supplyInfoService.findBySupplier(supplierName, pageNumber, pageSize, sortField, asc);
        return ResultUtil.success(page);
    }

    @RequestMapping(value = "/getSupplyInfosByHeadCode")
    public Result<List<SupplyInfo>> findSupplyInfosByHeader(@RequestParam("headCode") String headCode) {
        return ResultUtil.success(supplyInfoService.findSupplyInfosByHeader(headCode));
    }
}

