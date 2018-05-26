package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.enums.CustomerExceptionEnum;
import com.hnu.mes.exception.CustomerException;
import com.hnu.mes.repository.CustomerDao;
import com.hnu.mes.repository.DefaultPasswordRepository;
import com.hnu.mes.repository.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lanyage on 2018/3/15.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DefaultPasswordRepository defaultPasswordRepository;
    @Autowired
    private SupplierDao supplierDao;

    Customer findOne(String code) {
        return customerDao.findOne(code);
    }

    public Page<Customer> findAllPage(int pageNumber, int pageSize, String id, int asc) throws RuntimeException {
        try {
            Supplier.class.getDeclaredField(id);
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.SEARCH_ERROR.getMessage());
        }
        Sort sort;
        if (pageSize == 0) {
            sort = new Sort(Sort.Direction.DESC, id);
        } else {
            sort = new Sort(Sort.Direction.ASC, id);
        }
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
        return customerDao.findAll(pageable);
    }

    public void update(String codeBefore, Customer one) {
        if (!one.getCode().equals(codeBefore) && customerDao.exists(one.getCode())) {
            throw new CustomerException(CustomerExceptionEnum.PRIMARY_KEY_ERROR.getMessage());
        }
        Customer exist = customerDao.findOne(codeBefore);

        exist.setCode(one.getCode());//设置登录名
        exist.setName(one.getName());//设置用户名称
        exist.setDescription(one.getDescription());//设置描述说明
        exist.setContact(one.getContact());//设置手机号码
        Supplier supplier = supplierDao.findOne(one.getSupplier().getCode());
        exist.setSupplier(supplier);//设置供应商
        customerDao.save(exist);
    }

    public void add(Customer one) {
        if (customerDao.exists(one.getCode())) {
            throw new CustomerException(CustomerExceptionEnum.PRIMARY_KEY_ERROR.getMessage());
        }
        String password = defaultPasswordRepository.getOne(1).getPassword();
        one.setPassword(password);
        customerDao.saveAndFlush(one);
    }

    public void delete(Customer one) {
        customerDao.delete(one);
    }

    public void deleteInBatch(List<Customer> ones) {
        customerDao.deleteInBatch(ones);
    }

    public void resetPassword(String code) {
        Customer exist = findOne(code);
        DefaultPassword defaultPassword = defaultPasswordRepository.findOne(1);
        exist.setPassword(defaultPassword.getPassword());
        update(code, exist);
    }

    public void changePassword(String code, String oldPassword, String newPassword) {
        if (newPassword == null || oldPassword == null ||
                newPassword.trim().equals("") || oldPassword.trim().equals(""))
            throw new CustomerException(CustomerExceptionEnum.PASSWORD_NULL_ERROR.getMessage());
        Customer exist = findOne(code);
        String existPassword = exist.getPassword();
        if (!existPassword.equals(oldPassword))
            throw new CustomerException(CustomerExceptionEnum.WRONG_PASSWORD.getMessage());
        exist.setPassword(newPassword);
        update(code, exist);
    }

    public Customer findByName(String name) {
        return customerDao.findByName(name);
    }

    public Customer login(Customer one) {
        String username = one.getCode();
        String password = one.getPassword();
        Customer exist = findOne(username);
        if (exist == null) {
            throw new CustomerException(CustomerExceptionEnum.USER_NOT_EXISTS_ERROR.getMessage());
        }
        if (!exist.getPassword().equals(password)) {
            throw new CustomerException(CustomerExceptionEnum.WRONG_PASSWORD.getMessage());
        }
        return exist;
    }

    public Customer getByCode(String code) {
        return customerDao.findOne(code);
    }

    @Transactional
    public void updateInBatch(List ones) {
        DefaultPassword defaultPassword = defaultPasswordRepository.findOne(1);
        customerDao.updateInBatch(defaultPassword.getPassword(), ones);
    }

    @Transactional
    public void updateAllDefaultPassword(String defaultPassword) {
        customerDao.updateAllDefaultPassword(defaultPassword);
    }
}
