package com.hnu.mes.repository;

import com.hnu.mes.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:29 2018/4/13
 * @Modified By:
 */
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePrimaryKey> {
    /** 通过用户编码删除*/
    public void deleteUserRolesByUserCode(String userCode);

    /**
     * 通过角色编码查询用户-角色
     * @param roleCode
     * @return
     */
    public UserRole findFirstByRoleCode(Integer roleCode);
}
