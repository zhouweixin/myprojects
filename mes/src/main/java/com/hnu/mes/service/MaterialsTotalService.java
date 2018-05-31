package com.hnu.mes.service;

import com.hnu.mes.domain.MaterialsTotal;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.MaterialsTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:09 2018/5/9
 * @Modified By:
 */
@Service
public class MaterialsTotalService {
    @Autowired
    private MaterialsTotalRepository materialsTotalRepository;

    /**
     * 保存
     *
     * @param materialsTotal
     * @return
     */
    public MaterialsTotal save(MaterialsTotal materialsTotal) {
        MaterialsTotal materialsTotal1 = materialsTotalRepository.findFirstByRawType(materialsTotal.getRawType());
        if (materialsTotal1 != null) {
            materialsTotal.setWeight(materialsTotal1.getWeight() + materialsTotal.getWeight());
            materialsTotal.setCode(materialsTotal1.getCode());
        }

        return materialsTotalRepository.save(materialsTotal);
    }

    /**
     * 更新全部的状态
     */
    @Transactional
    public void updateAllStatus(Integer status) {

        if (status == 0) {// 结束盘库
            // 尚未结束盘库
            if (materialsTotalRepository.findFirstByStatus(1) != null
                    || materialsTotalRepository.findFirstByStatus(2) != null) {
                throw new MesException(EnumException.NOT_ALL_STOCK);
            }

            // 尚未开始盘库
            if(materialsTotalRepository.findFirstByStatus(0) != null){
                throw new MesException(EnumException.NOT_STOCK);
            }

            materialsTotalRepository.updateAllStatus(status);
        } else if (status == 1) {// 开始盘库
            // 正在盘库中
            if (materialsTotalRepository.findFirstByStatus(1) != null
                    || materialsTotalRepository.findFirstByStatus(2) != null
                    || materialsTotalRepository.findFirstByStatus(3) != null) {
                throw new MesException(EnumException.STOCKING);
            }

            materialsTotalRepository.updateAllStatus(status);
        }
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<MaterialsTotal> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            MaterialsTotal.class.getDeclaredField(sortFieldName);
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
        return materialsTotalRepository.findAll(pageable);
    }

    /**
     * 通过状态查询-分页
     *
     * @param status
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<MaterialsTotal> findByStatusByPage(Integer status, Integer page, Integer size, String sortFieldName,
                                                   Integer asc) {
        try {
            MaterialsTotal.class.getDeclaredField(sortFieldName);
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

        // 查询
        return materialsTotalRepository.findByStatus(status, pageable);
    }

    /**
     * 通过预警状态查询-分页
     *
     * @param warnStatus
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<MaterialsTotal> findByWarnStatusByPage(Integer warnStatus, Integer page, Integer size, String sortFieldName,
                                                   Integer asc) {
        try {
            MaterialsTotal.class.getDeclaredField(sortFieldName);
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

        // 查询
        return materialsTotalRepository.findByWarnStatus(warnStatus, pageable);
    }

}
