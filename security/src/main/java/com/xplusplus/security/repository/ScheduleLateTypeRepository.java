package com.xplusplus.security.repository;

import com.xplusplus.security.domain.ScheduleLateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleLateTypeRepository extends JpaRepository<ScheduleLateType,Integer> {
}
