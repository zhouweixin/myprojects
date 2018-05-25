package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Nation;
import com.xplusplus.security.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Huxudong
 * @Description: 角色Repository
 * @Date: Created in 12:32 2018/5/22
 * @Modified By:
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * 通过名称模糊查询-分页
     */
    public Page<Role> findByNameLike(String name, Pageable pageable);
}
