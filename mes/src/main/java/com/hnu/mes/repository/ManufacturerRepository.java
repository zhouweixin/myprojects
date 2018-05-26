package com.hnu.mes.repository;

import com.hnu.mes.domain.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 11:38 2018/4/5
 * @Modified By:
 */
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<Manufacturer> findByNameLike(String name, Pageable pageable);
}
