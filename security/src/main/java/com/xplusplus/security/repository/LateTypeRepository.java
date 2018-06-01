package com.xplusplus.security.repository;

import com.xplusplus.security.domain.LateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LateTypeRepository extends JpaRepository<LateType,Integer> {
}
