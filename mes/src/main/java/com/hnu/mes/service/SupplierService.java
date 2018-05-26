package com.hnu.mes.service;

import com.hnu.mes.domain.Supplier;
import com.hnu.mes.domain.SupplierType;
import com.hnu.mes.enums.PrimaryKeyExceptionEnum;
import com.hnu.mes.exception.CustomerException;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.SupplierDao;
import com.hnu.mes.repository.SupplierTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by lanyage on 2018/3/15.
 */
@Service
public class SupplierService {
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private SupplierTypeDao supplierTypeDao;

    public Supplier findOne(String code){
        return supplierDao.findOne(code);
    }

    /**
     * 保存
     * @param supplier
     * @return
     */
    public Supplier save(Supplier supplier){
        return supplierDao.save(supplier);
    }

    public Page<Supplier> findAllPage(int pageNumber, int pageSize, String id, int asc) throws RuntimeException {
        try {
            Supplier.class.getDeclaredField(id);
        } catch (Exception e) {
            throw new RuntimeException("排序异常");
        }
        Sort sort = null;
        if (pageSize == 0) {
            sort = new Sort(Sort.Direction.DESC, id);
        } else {
            sort = new Sort(Sort.Direction.ASC, id);
        }
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
        return supplierDao.findAll(pageable);
    }

    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }
    public void update(String codeBefore, Supplier one) throws RuntimeException {
        if(!one.getCode().equals(codeBefore) && supplierDao.exists(one.getCode())) {
            throw new CustomerException(PrimaryKeyExceptionEnum.PRIMARY_KEY_REPEATED.getMessage());
        }

        Supplier exist = supplierDao.findOne(codeBefore);

        exist.setCode(one.getCode());//设置公司编号
        exist.setName(one.getName());//设置公司名称
        exist.setContact(one.getContact());//设置联系电话
        exist.setContactPerson(one.getContactPerson());//设置公司联系人
        exist.setAddress(one.getAddress());//设置地址
        exist.setCreditCode(one.getCreditCode());//设置信用代码

        SupplierType supplierType = supplierTypeDao.findByCode(one.getSupplierType().getCode());
        exist.setSupplierType(supplierType);//设置公司类型
        supplierDao.save(exist);
    }

    public Supplier findByName(String name) {
        return supplierDao.findByName(name);
    }

    public Supplier detail(Supplier one) {
        return supplierDao.findOne(one.getCode());
    }


    public void delete(String code) {
        supplierDao.delete(code);
    }

    public void deleteInBatch(Set<Supplier> supplieres) {
        supplierDao.deleteInBatch(supplieres);
    }

    public Page<Supplier> getSupplierByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * getSupplierTypeByPage
         * @Desciption
         * @param [page, size, sortFieldName, asc]
         * @throws Exception
         */

        try {
            SupplierType.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            //降序，desc
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            //升序，asc
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return supplierDao.findAll(pageable);
    }

    public Page<Supplier> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            Process.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        // 分页
        Pageable pageable = new PageRequest(page, size, sort);

        // 查询
        return supplierDao.findByNameLike("%" + name + "%", pageable);
    }
}
