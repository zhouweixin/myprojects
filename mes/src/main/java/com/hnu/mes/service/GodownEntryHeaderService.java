package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.repository.MaterialsTotalRepository;
import org.springframework.data.domain.Page;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.GodownEntryHeaderRepository;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:42 2018/5/1
 * @Modified By:
 */
@Service
public class GodownEntryHeaderService {
    @Autowired
    private GodownEntryHeaderRepository godownEntryHeaderRepository;

    @Autowired
    private GodownTestInformHeaderService godownTestInformHeaderService;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private MaterialsTotalService materialsTotalService;

    @Autowired
    private MaterialsEntryService materialsEntryService;

    /**
     * 新增
     *
     * @param godownEntryHeader
     * @return
     */
    public GodownEntryHeader save(GodownEntryHeader godownEntryHeader) {
        int times = 0;
        String number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        while(godownEntryHeaderRepository.findFirstByBatchNumber(number) != null){
            times ++;
            if(times > 100){
                throw new MesException(EnumException.NOT_UNIQUE_NUMBER);
            }
            number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        }

        //  入库单编号(7位)
        godownEntryHeader.setBatchNumber(number);

        // 到货日期
        godownEntryHeader.setDate(new Date());

        // 到货制单时间
        godownEntryHeader.setCreateTime(new Date());

        // 状态:0样品未入库
        godownEntryHeader.setStatus(GlobalUtil.GODOWN_ENTRY_NOT_GODOWN);

        GodownEntryHeader save = godownEntryHeaderRepository.save(godownEntryHeader);

        // 存入总库存
        if (save != null) {
            MaterialsTotal materialsTotal = new MaterialsTotal();
            materialsTotal.setWeight(save.getWeight());
            materialsTotal.setRawType(save.getRawType());
            materialsTotalService.save(materialsTotal);
        }

        // 存入库存明细
        List<MaterialsEntry> materialsEntries = new ArrayList<>();
        List<GodownEntry> godownEntries = save.getGodownEntries();
        if (godownEntries != null) {
            for(GodownEntry godownEntry : godownEntries){
                MaterialsEntry materialsEntry = new MaterialsEntry();
                materialsEntry.setRawType(save.getRawType());
                materialsEntry.setBatchNumber(godownEntry.getBatchNumber());
                materialsEntry.setWeight(godownEntry.getWeight());
                materialsEntry.setStatus(godownEntry.getTestResult());
                materialsEntries.add(materialsEntry);
            }
        }
        materialsEntryService.save(materialsEntries);

        return save;
    }

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Transactional
    public void updateStatusByCode(Integer status, Long code) {
        godownEntryHeaderRepository.updateStatusByCode(status, code);
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public GodownEntryHeader findOne(Long code) {
        return godownEntryHeaderRepository.findOne(code);
    }

    /**
     * 通过入库编号查询
     *
     * @param batchNumber
     * @return
     */
    public GodownEntryHeader findByBatchNumber(String batchNumber) {
        return godownEntryHeaderRepository.findGodownEntryHeaderByBatchNumber(batchNumber);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<GodownEntryHeader> findAll() {
        return godownEntryHeaderRepository.findAll();
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
    public Page<GodownEntryHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownEntryHeader.class.getDeclaredField(sortFieldName);
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
        return godownEntryHeaderRepository.findAll(pageable);
    }

    /**
     * 通过状态查询-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersByStatus(Integer status, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownEntryHeader.class.getDeclaredField(sortFieldName);
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
        return godownEntryHeaderRepository.findGodownEntryHeadersByStatus(status, pageable);
    }

    /**
     * 通过状态和发货厂家查询-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersByStatusAndSupplier(Integer status, Supplier supplier, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownEntryHeader.class.getDeclaredField(sortFieldName);
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
        return godownEntryHeaderRepository.findGodownEntryHeadersByStatusAndSupplier(status, supplier, pageable);
    }

    /**
     * 通过发货厂家查询-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownEntryHeader> findGodownEntryHeadersBySupplier(Supplier supplier, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownEntryHeader.class.getDeclaredField(sortFieldName);
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
        return godownEntryHeaderRepository.findGodownEntryHeadersBySupplier(supplier, pageable);
    }
}
