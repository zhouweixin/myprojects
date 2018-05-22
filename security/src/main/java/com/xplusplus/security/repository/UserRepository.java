package com.xplusplus.security.repository;

import com.xplusplus.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:33 2018/5/7
 * @Modified By:
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 查询最大ID
     *
     * @return
     */
    @Query(value = "select max(id) from User")
    public String findMaxId();
}
