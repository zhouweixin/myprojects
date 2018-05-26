package com.hnu.mes.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.Operation;
import com.hnu.mes.domain.RoleModelOperationView;
import com.hnu.mes.repository.RoleModelOperationViewRepository;

/**
 * 
 * @author zhouweixin
 *
 */
@Service
public class RoleModelOperationViewService {

	@Autowired
	private RoleModelOperationViewRepository roleModelOperationRepository;

	/**
	 * 通过编码查询
	 * 
	 * @param roleCode
	 * @return
	 */
	public List<RoleModelOperationView> findByRoleCode(Integer roleCode) {
		return roleModelOperationRepository.findByRoleCode(roleCode);
	}

	/**
	 * 通过角色编码和模块编码查询操作
	 * 
	 * @param roleCode
	 * @param modelCode
	 * @return
	 */
	public Set<Operation> findOperationByRoleCodeAndModelCode(Integer roleCode, Integer modelCode) {
		List<RoleModelOperationView> roleModelOperationViews = roleModelOperationRepository
				.findByRoleCodeAndModelCode(roleCode, modelCode);
		Set<Operation> operations = new HashSet<Operation>();

		for (RoleModelOperationView roleModelOperationView : roleModelOperationViews) {
			Operation operation = new Operation();
			operation.setCode(roleModelOperationView.getOperationCode());
			operation.setName(roleModelOperationView.getOperationName());
			operations.add(operation);
		}

		return operations;
	}
}
