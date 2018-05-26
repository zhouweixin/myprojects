package com.hnu.mes.service;

import com.hnu.mes.domain.AvailableRawPresoma;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AvailableRawPresomaRepository;
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
 * @Date: Created in 12:35 2018/5/9
 * @Modified By:
 */
@Service
public class AvailableRawPresomaService {
    @Autowired
    private AvailableRawPresomaRepository availableRawPresomaRepository;

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
    public Page<AvailableRawPresoma> findByBatchNumberLikeByPage(String batchNumber, Integer page, Integer size, String sortFieldName,
                                                             Integer asc) {
        try {
            AvailableRawPresoma.class.getDeclaredField(sortFieldName);
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

        return availableRawPresomaRepository.findAvailableRawPresomasByBatchNumberLike("%" + batchNumber + "%", pageable);
    }

    /**
     * 通过批号模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List findByBatchNumberLike(String batchNumber) {
        return availableRawPresomaRepository.findAvailableRawPresomasByBatchNumberLike("%" + batchNumber + "%");
    }
}
