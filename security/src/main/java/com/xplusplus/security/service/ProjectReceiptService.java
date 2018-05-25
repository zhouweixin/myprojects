package com.xplusplus.security.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectReceipt;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ProjectReceiptRepository;
import com.xplusplus.security.repository.ProjectRepository;
import com.xplusplus.security.repository.UserRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:54:07 2018年5月24日
 */
@Service
public class ProjectReceiptService {
	@Autowired
	private ProjectReceiptRepository projectReceiptRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 新增
	 * 
	 * @param projectReceipt
	 * @return
	 */
	@Transactional
	public ProjectReceipt save(ProjectReceipt projectReceipt) {

		// 验证是否存在
		if (projectReceipt == null || (projectReceipt.getId() != null
				&& projectReceiptRepository.findOne(projectReceipt.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		// 验证项目
		if (projectReceipt.getProject() == null
				|| projectRepository.findOne(projectReceipt.getProject().getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_PROJECT_NOT_EXIST);
		}

		// 验证经办人
		if (projectReceipt.getOperator() == null
				|| userRepository.findOne(projectReceipt.getOperator().getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_OPERATOR_NOT_EXIST);
		}

		// 设置收款时间
		projectReceipt.setTime(new Date());

		// 添加
		ProjectReceipt save = projectReceiptRepository.save(projectReceipt);

		// 更新已收款
		projectRepository.updateReceiptPriceById(save.getPrice(), save.getProject().getId());

		return save;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {

		// 验证是否存在
		if (projectReceiptRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		projectReceiptRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param projectReceipts
	 */
	public void deleteInBatch(Collection<ProjectReceipt> projectReceipts) {
		projectReceiptRepository.deleteInBatch(projectReceipts);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public ProjectReceipt findOne(Long id) {
		return projectReceiptRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<ProjectReceipt> findAll() {
		return projectReceiptRepository.findAll();
	}

	/**
	 * 查询所有-分页
	 * 
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<ProjectReceipt> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			ProjectReceipt.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return projectReceiptRepository.findAll(pageable);
	}
	
	/**
	 * 通过项目查询-分页
	 * 
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<ProjectReceipt> findByProjectByPage(Project project, Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			ProjectReceipt.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return projectReceiptRepository.findByProject(project, pageable);
	}
}
