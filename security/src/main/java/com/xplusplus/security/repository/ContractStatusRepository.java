package com.xplusplus.security.repository;


import com.xplusplus.security.domain.ContractStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractStatusRepository extends JpaRepository<ContractStatus,Integer>{

    /**
     * 通过名称模糊查询
     * @param name
     * @param pageable
     * @return
     */
    public Page<ContractStatus> findByNameLike(String name, Pageable pageable);
}
