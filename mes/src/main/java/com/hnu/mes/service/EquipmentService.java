package com.hnu.mes.service;

import com.hnu.mes.domain.Equipment;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 设备信息服务层
 */
@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment save(Equipment equipment) {
        /**
         * save
         * @Desciption 新增
         * @param [equipment]
         */
        return equipmentRepository.save(equipment);
    }

    public void delete(String code) {
        /**
         * delete
         * @Desciption 删除
         * @param [code]
         */
        equipmentRepository.delete(code);
    }

    public Equipment findOne(String code) {
        /**
         * findOne
         * @Desciption 查询
         * @param [code]
         */
        return equipmentRepository.findOne(code);
    }

    public List<Equipment> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return equipmentRepository.findAll();
    }

    public Page<Equipment> getEquipmentByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * getEquipmentByPage
         * @Desciption
         * @param [page, size, sortFieldName, asc]
         * @throws Exception
         */

        try {
            Equipment.class.getDeclaredField(sortFieldName);
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
        return equipmentRepository.findAll(pageable);
    }

    public Page<Equipment> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            Equipment.class.getDeclaredField(sortFieldName);
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
        return equipmentRepository.findAllByNameLike("%" + name + "%", pageable);
    }

    public void deleteInBatch(Set<Equipment> equipments) {
        equipmentRepository.deleteInBatch(equipments);
    }

}