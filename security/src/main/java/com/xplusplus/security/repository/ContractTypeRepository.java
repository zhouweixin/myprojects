package com.xplusplus.security.repository;

import com.xplusplus.security.domain.ContractType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType,Integer> {

    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    public Page<ContractType> findByNameLike(String name, Pageable pageable);
}
