package com.xplusplus.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xplusplus.security.domain.Archive;
import com.xplusplus.security.domain.User;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:23 2018/5/22
 * @Modified By:
 */
@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
	/**
	 * 通过用户查询档案
	 * 
	 * @param user
	 * @return
	 */
	public Archive findFirstByUser(User user);
}
