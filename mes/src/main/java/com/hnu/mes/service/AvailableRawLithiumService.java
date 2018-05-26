package com.hnu.mes.service;

import com.hnu.mes.domain.AvailableRawLithium;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AvailableRawLithiumRepository;
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
 * @Date: Created in 11:00 2018/5/9
 * @Modified By:
 */
@Service
public class AvailableRawLithiumService {
    @Autowired
    private AvailableRawLithiumRepository availableRawLithiumRepository;

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
    public Page<AvailableRawLithium> findByBatchNumberLikeByPage(String batchNumber, Integer page, Integer size, String sortFieldName,
                                                                               Integer asc) {
        try {
            AvailableRawLithium.class.getDeclaredField(sortFieldName);
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

        return availableRawLithiumRepository.findAvailableRawLithiumsByBatchNumberLike("%" + batchNumber + "%", pageable);
    }

    /**
     * 通过批号模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List findByBatchNumberLike(String batchNumber) {
        return availableRawLithiumRepository.findAvailableRawLithiumsByBatchNumberLike("%" + batchNumber + "%");
    }
}
