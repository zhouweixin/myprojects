package com.hnu.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.RoleModelOperation;
import com.hnu.mes.domain.RoleModelOperationPrimaryKey;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface RoleModelOperationRepository extends JpaRepository<RoleModelOperation, RoleModelOperationPrimaryKey> {

    /**
     * 通过角色编码删除
     *
     * @param roleCode
     */
	public void deleteByRoleCode(Integer roleCode);

    /**
     * 通过角色编码和模块编码查询
     *
     * @param roleCode
     * @param modelCode
     * @return
     */
    @Query(value = "select distinct r.operationCode from RoleModelOperation r where r.roleCode=?1 and r.modelCode=?2")
	public Set<Integer> findOperationsByRoleCodeAndModelCode(Integer roleCode, Integer modelCode);
}
