package com.hnu.mes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.hnu.mes.domain.Operation;
import com.hnu.mes.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.RoleModelOperation;
import com.hnu.mes.repository.RoleModelOperationRepository;

/**
 * 
 * @author zhouweixin
 *
 */
@Service
public class RoleModelOperationService {
	
	@Autowired
	private RoleModelOperationRepository roleModelOperationRepository;

	@Autowired
	private OperationRepository operationRepository;
	
	/**
	 * 保存一个
	 * @param roleModelOperation
	 * @return
	 */
	public RoleModelOperation save(RoleModelOperation roleModelOperation){
		return roleModelOperationRepository.save(roleModelOperation);
	}
	
	/**
	 * 保存多个
	 * @param roleModelOperations
	 * @return
	 */
	@Transactional
	public List<RoleModelOperation> updateRoleModelOperations(Set<RoleModelOperation> roleModelOperations){
		// 删除所有
		for(RoleModelOperation roleModelOperation : roleModelOperations){			
			roleModelOperationRepository.deleteByRoleCode(roleModelOperation.getRoleCode());
		}

		// 添加
		return roleModelOperationRepository.save(roleModelOperations);
	}

	/**
	 * 通过角色编码和模块编码查询
	 *
	 * @param roleCode
	 * @param modelCode
	 * @return
	 */
	public List<Operation> findOperationsByRoleCodeAndModelCode(Integer roleCode, Integer modelCode){
		Set<Integer> operationCodes = roleModelOperationRepository.findOperationsByRoleCodeAndModelCode(roleCode, modelCode);

		List<Operation> operations = operationRepository.findAll(operationCodes);

		return operations;
	}

	/**
	 * 查询所有
	 *
	 * @return
	 */
	public List<RoleModelOperation> findAll(){
		return roleModelOperationRepository.findAll();
	}
}
