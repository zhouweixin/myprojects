package com.hnu.mes.repository;

import com.hnu.mes.domain.Material;
import com.hnu.mes.domain.RawType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 8:57 2018/5/2
 * @Modified By:
 */
@Repository
public interface RawTypeRepository extends JpaRepository<RawType, Long> {
    /**
     * 通过名称模糊查询-分页
     * @param name
     * @param pageable
     * @return
     */
    public Page<RawType> findByNameLike(String name, Pageable pageable);

    /**
     * 通过原料类型
     *
     * @param material
     * @return
     */
    public List<RawType> findByMaterial(Material material);
}
