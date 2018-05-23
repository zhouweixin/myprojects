package com.xplusplus.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.UserContract;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 上午11:24:41 2018年5月23日
 */
@Repository
public interface UserContractRepository extends JpaRepository<UserContract, Long>{

}
