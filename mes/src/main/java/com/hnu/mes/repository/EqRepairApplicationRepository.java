package com.hnu.mes.repository;

import com.hnu.mes.domain.EqRepairApplication;
import com.hnu.mes.domain.Equipment;
import com.hnu.mes.domain.Flag;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

/**
 * Created by lanyage on 2018/3/18.
 */
public interface EqRepairApplicationRepository extends JpaRepository<EqRepairApplication, String>, JpaSpecificationExecutor {
    Page<EqRepairApplication> findByFlag(Flag flag, Pageable pageable);

    Page<EqRepairApplication> findByApplicationTime(Date date, Pageable pageable);

    Page<EqRepairApplication> findByFlagAndApplicationTime(Flag flag, Date date, Pageable pageable);

    Page<EqRepairApplication> findByEquipment(Equipment equipment, Pageable pageable);

    Page<EqRepairApplication> findByApplicationPerson(User applicationPerson, Pageable pageable);

    Page<EqRepairApplication> findByRepairMan(User repairMan, Pageable pageable);

    Page<EqRepairApplication> findByEvaluator(User evaluator, Pageable pageable);
}
