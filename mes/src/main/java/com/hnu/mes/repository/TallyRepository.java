package com.hnu.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.Tally;

/**
 *
 * @author chenpingxiao
 *
 */
public interface TallyRepository extends JpaRepository<Tally, String> {
}
