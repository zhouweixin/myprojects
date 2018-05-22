package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:32 2018/5/22
 * @Modified By:
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
