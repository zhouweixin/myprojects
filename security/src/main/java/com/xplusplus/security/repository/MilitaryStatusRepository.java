package com.xplusplus.security.repository;

import com.xplusplus.security.domain.MilitaryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:29 2018/5/22
 * @Modified By:
 */
@Repository
public interface MilitaryStatusRepository extends JpaRepository<MilitaryStatus, Integer> {
}
