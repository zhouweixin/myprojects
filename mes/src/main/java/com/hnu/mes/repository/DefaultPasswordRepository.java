package com.hnu.mes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.DefaultPassword;

public interface DefaultPasswordRepository extends JpaRepository<DefaultPassword, Integer> {

    /**
     * 通过密码模糊查询-分页
     * 
     * @param s
     * @param pageable
     * @return
     */
    public Page<DefaultPassword> findAllByPasswordLike(String s, Pageable pageable);
}
