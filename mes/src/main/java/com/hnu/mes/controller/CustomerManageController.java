package com.hnu.mes.controller;

import com.hnu.mes.domain.Customer;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.Supplier;
import com.hnu.mes.service.CustomerService;
import com.hnu.mes.service.DefaultPasswordService;
import com.hnu.mes.service.SupplierService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lanyage on 2018/3/15.
 */
@RestController
@RequestMapping("/customer")
public class CustomerManageController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DefaultPasswordService defaultPasswordService;

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

    @GetMapping("/getAllSuppliers")
    public Result<List<Supplier>> getAllSuppliers() {
        return ResultUtil.success(supplierService.findAll());
    }

    /**
     * 查询所有顾客信息
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
     * 添加一个顾客信息  http:localhost:8080/mes/customer/addCustomer 数据: {code:1,name:'',contact:''...}
     *
     * @param one
     * @return
     */
    @PostMapping("/addCustomer")
    public Result addCustomer(Customer one) {
        try {
            Supplier supplier = supplierService.detail(one.getSupplier());
            one.setSupplier(supplier);
            customerService.add(one);
        } catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 删除一个顾客信息 http:localhost:8080/mes/customer/1/deleteCustomer  数据: {}
     *
     * @param code
     * @return
     */
    @PostMapping("/deleteCustomer")
    public Result deleteCustomer(@RequestParam("code") String code) {
        Customer customer = new Customer();
        customer.setCode(code);
        customerService.delete(customer);
        return ResultUtil.success();
    }

    /**
     * 批量删除顾客信息 http:localhost:8080/mes/customer/deleteCustomerInBatch  数据: {code :'1',code:'2',...}
     *
     * @param ones
     * @return
     */
    @PostMapping("/deleteCustomerInBatch")
    public Result deleteCustomerInBatch(@RequestBody List<Customer> ones) {
        customerService.deleteInBatch(ones);
        return ResultUtil.success();
    }


    /**
     * 重制顾客密码 http:localhost:8080/mes/customer/1/resetPassword  数据: {}
     *
     * @param code
     * @return
     */
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestParam("code") String code) {
        customerService.resetPassword(code);
        return ResultUtil.success();
    }

    @PostMapping("/resetPasswordInBatch")
    public Result resetPasswordInBatch(@RequestBody List<String> codes) {
        customerService.updateInBatch(codes);
        return ResultUtil.success(codes);
    }

    /**
     * 修改顾客密码 http:localhost:8080/mes/customer/changePassword  数据: {code:'1',oldPassword:'123',newPassword:'111'}
     *
     * @param code
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/changePassword")
    public Result changePassword(@RequestParam("code") String code,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        try {
            customerService.changePassword(code, oldPassword, newPassword);
        } catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 按照顾客姓名查找顾客 http:localhost:8080/mes/customer/findByName  数据: {name :'张三'}
     *
     * @param name
     * @return
     */
    @PostMapping("/findByName")
    public Result findByName(@RequestParam("name") String name) {
        Customer customer = customerService.findByName(name);
        return ResultUtil.success(customer);
    }

    /**
     * 顾客登录 http:localhost:8080/mes/customer/login 数据: {code:1,name:'',contact:''...}
     *
     * @param customer
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public Result login(@Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        Customer exist;
        try {
            exist = customerService.login(customer);
        } catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success(exist);
    }

    @GetMapping("/customerDetail")
    public Result detail(@RequestParam("code") String code) {
        return ResultUtil.success(customerService.getByCode(code));
    }

    /**
     * 重置所有用户的密码
     *
     * @return
     */
    @RequestMapping(value = "/resetAllDefaultPassword")
    public Result<Object> resetAllDefaultPassword(){
        // 取出默认密码
        String password = defaultPasswordService.getDefaultPassword();

        // 加密
        String defaultPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        customerService.updateAllDefaultPassword(defaultPassword);
        return ResultUtil.success();
    }
}
