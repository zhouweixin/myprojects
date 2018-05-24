package com.xplusplus.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xplusplus.security.domain.Nation;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午6:33:38 2018年5月22日
 */
@Repository
public interface NationRepository extends JpaRepository<Nation, Integer> {

	/**
	 * 通过名称模糊查询-分页
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<Nation> findByNameLike(String name, Pageable pageable);
}
