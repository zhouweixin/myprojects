package com.hnu.mes.service;

import com.hnu.mes.domain.AvailableProduct;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AvailableProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:54 2018/5/9
 * @Modified By:
 */
@Service
public class AvailableProductService {
    @Autowired
    private AvailableProductRepository availableProductRepository;

    /**
     * 通过批号模糊查询-分页
     *
     * @param batchNumber  批号
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序或减序
     * @return
     */
    public Page<AvailableProduct> findByBatchNumberLikeByPage(String batchNumber, Integer page, Integer size, String sortFieldName,
                                                                               Integer asc) {
        try {
            AvailableProduct.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 排序的字段名不存在
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

        return availableProductRepository.findAvailableAvailableProductsByBatchNumberLike("%" + batchNumber + "%", pageable);
    }

    /**
     * 通过批号模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List findByBatchNumberLike(String batchNumber) {
        return availableProductRepository.findAvailableAvailableProductsByBatchNumberLike("%" + batchNumber + "%");
    }
}
