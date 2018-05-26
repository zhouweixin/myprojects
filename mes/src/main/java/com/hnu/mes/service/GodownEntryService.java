package com.hnu.mes.service;

import com.hnu.mes.domain.GodownEntry;
import com.hnu.mes.domain.GodownEntryHeader;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.GodownEntryHeaderRepository;
import com.hnu.mes.repository.GodownEntryRepository;
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
 * @Date: Created in 21:43 2018/5/1
 * @Modified By:
 */
@Service
public class GodownEntryService {
    @Autowired
    private GodownEntryRepository godownEntryRepository;

    @Autowired
    GodownEntryHeaderRepository godownEntryHeaderRepository;

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    public void updateStatusByCode(Integer status, Long code){
        godownEntryRepository.updateStatusByCode(status, code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<GodownEntry> findAll(){
        return godownEntryRepository.findAll();
    }

    /**
     * 查询所有分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownEntry> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownEntry.class.getDeclaredField(sortFieldName);
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
        return godownEntryRepository.findAll(pageable);
    }

    /**
     * 通过入库单头和批号模糊查询
     *
     * @param rawType
     * @param batchNumber
     * @return
     */
    public List<GodownEntry> findByRawTypeAndBatchNumberLike(RawType rawType, String batchNumber){

        List<GodownEntryHeader> godownEntryHeaders = godownEntryHeaderRepository.findByRawType(rawType);

        return godownEntryRepository.findByGodownEntryHeaderInAndBatchNumberLike(godownEntryHeaders, "%" + batchNumber + "%");
    }

    /**
     * 通过入库单头和批号模糊查询-分页
     *
     * @param rawType
     * @param batchNumber
     * @return
     */
    public Page<GodownEntry> findByRawTypeAndBatchNumberLikeByPage(RawType rawType, String batchNumber, Integer page, Integer size, String sortFieldName, Integer asc) {

        List<GodownEntryHeader> godownEntryHeaders = godownEntryHeaderRepository.findByRawType(rawType);

        // 判断字段名是否存在
        try {
            GodownEntry.class.getDeclaredField(sortFieldName);
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

        return godownEntryRepository.findByGodownEntryHeaderInAndBatchNumberLike(godownEntryHeaders, "%" + batchNumber + "%", pageable);
    }
}
