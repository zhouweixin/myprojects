package com.hnu.mes.repository;

import com.hnu.mes.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lanyage on 2018/3/15.
 */
public interface CustomerDao extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
    Customer findByName(String name);

    @Modifying
    @Query(value = "update Customer c set c.password = :password where c.code in :ones")
    void updateInBatch(@Param(value = "password") String password, @Param(value = "ones") List ones);

    @Modifying
    @Query(value = "update Customer c set c.password = ?1")
    void updateAllDefaultPassword(String defaultPassword);
}
