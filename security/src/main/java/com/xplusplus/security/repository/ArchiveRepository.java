package com.xplusplus.security.repository;

import com.xplusplus.security.domain.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:23 2018/5/22
 * @Modified By:
 */
@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
}
