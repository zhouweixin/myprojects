package com.hnu.mes.service;

import com.hnu.mes.domain.SupplierType;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.SupplierTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:47 2018/5/1
 * @Modified By:
 */
@Service
public class SupplierTypeService {
    @Autowired
    private SupplierTypeDao supplierTypeDao;

    public SupplierType save(SupplierType supplierType) {
        /**
         * save
         * @Desciption 新增
         * @param [supplierType]
         */
        return supplierTypeDao.save(supplierType);
    }

    public void delete(Integer code) {
        /**
         * delete
         * @Desciption 删除
         * @param [code]
         */
        supplierTypeDao.delete(code);
    }

    public SupplierType findOne(Integer code) {
        /**
         * findOne
         * @Desciption 查询
         * @param [code]
         */
        return supplierTypeDao.findOne(code);
    }

    public List<SupplierType> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return supplierTypeDao.findAll();
    }

    public Page<SupplierType> getSupplierTypeByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
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
        return supplierTypeDao.findAll(pageable);
    }

    public Page<SupplierType> findAllByLikeNameByPage(String type, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            SupplierType.class.getDeclaredField(sortFieldName);
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
        return supplierTypeDao.findSupplierTypesByTypeLike("%" + type + "%", pageable);
    }

    public void deleteInBatch(Set<SupplierType> supplierTypees) {
        supplierTypeDao.deleteInBatch(supplierTypees);
    }
}
