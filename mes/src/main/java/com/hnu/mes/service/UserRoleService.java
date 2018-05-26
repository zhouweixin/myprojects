package com.hnu.mes.service;

import com.hnu.mes.domain.UserRole;
import com.hnu.mes.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:34 2018/4/13
 * @Modified By:
 */
@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 更新用户角色
     *
     * @param userCode
     * @param userRoles
     */
    @Transactional
    public void updateUserRole(String userCode, Iterable<UserRole> userRoles){
        userRoleRepository.deleteUserRolesByUserCode(userCode);

        userRoleRepository.save(userRoles);
    }
}
