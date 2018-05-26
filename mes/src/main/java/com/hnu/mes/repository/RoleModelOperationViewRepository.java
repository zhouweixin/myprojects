package com.hnu.mes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hnu.mes.domain.RoleModelOperationView;
import com.hnu.mes.domain.RoleModelOperationPrimaryKey;

/**
 * 
 * @author zhouweixin
 *
 */
public interface RoleModelOperationViewRepository
		extends JpaRepository<RoleModelOperationView, RoleModelOperationPrimaryKey> {
	/**
	 * 通过角色编码查询
	 * 
	 * @param roleCode
	 * @return
	 */
	public List<RoleModelOperationView> findByRoleCode(Integer roleCode);

	/**
	 * 通过角色编码和模块编码查询
	 * 
	 * @param roleCode
	 * @param modelCode
	 * @return
	 */
	public List<RoleModelOperationView> findByRoleCodeAndModelCode(Integer roleCode, Integer modelCode);
}
