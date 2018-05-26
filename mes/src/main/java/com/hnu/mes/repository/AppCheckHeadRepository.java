package com.hnu.mes.repository;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.AppCheckHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.AppCheckHead;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 *
 * @author pingxiao
 *
 */
public interface AppCheckHeadRepository extends JpaRepository<AppCheckHead, String> {



    
}
