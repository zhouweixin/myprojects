package com.hnu.mes.service;

import com.hnu.mes.domain.PickingApply;
import com.hnu.mes.domain.PickingApplyHeader;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.PickingApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:19 2018/5/5
 * @Modified By:
 */
@Service
public class PickingApplyService {

    @Autowired
    private PickingApplyRepository pickingApplyRepository;

    /**
     * 通过编码删除
     *
     * @param code
     */
    public void delete(Long code) {
        pickingApplyRepository.delete(code);
    }

    /**
     * 批删除
     *
     * @param pickingApplies
     */
    public void deleteInBatch(Collection<PickingApply> pickingApplies){
        pickingApplyRepository.deleteInBatch(pickingApplies);
    }

    /**
     * 通过分页查询所有
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<PickingApply> getAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            PickingApply.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return pickingApplyRepository.findAll(pageable);
    }

    /**
     * 通过领料申请头查询-分页
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<PickingApply> findByPickingApplyHeader(PickingApplyHeader pickingApplyHeader, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            PickingApply.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return pickingApplyRepository.findByPickingApplyHeader(pickingApplyHeader, pageable);
    }
}
