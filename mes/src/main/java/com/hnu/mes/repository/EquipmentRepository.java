package com.hnu.mes.repository;

import com.hnu.mes.domain.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description: 设备dao
 */
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    Equipment findByName(String name);

    /**
     * 通过名称模糊查询-分页
     * @param name
     * @return
     */
    public Page<Equipment> findAllByNameLike(String name, Pageable pageable);
}
