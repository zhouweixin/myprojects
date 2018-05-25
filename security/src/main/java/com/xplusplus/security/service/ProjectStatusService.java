package com.xplusplus.security.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.xplusplus.security.domain.ProjectStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ProjectStatusRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:53:58 2018年5月24日
 */
@Service
public class ProjectStatusService {
	@Autowired
	private ProjectStatusRepository projectStatusRepository;

	/**
	 * 新增
	 * 
	 * @param projectStatus
	 * @return
	 */
	public ProjectStatus save(ProjectStatus projectStatus) {

		// 验证是否存在
		if (projectStatus == null || (projectStatus.getId() != null && projectStatusRepository.findOne(projectStatus.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		return projectStatusRepository.save(projectStatus);
	}

	/**
	 * 更新
	 * 
	 * @param projectStatus
	 * @return
	 */
	public ProjectStatus update(ProjectStatus projectStatus) {

		// 验证是否存在
		if (projectStatus == null || projectStatus.getId() == null || projectStatusRepository.findOne(projectStatus.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}

		return projectStatusRepository.save(projectStatus);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		// 验证是否存在
		if (projectStatusRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		projectStatusRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param projectStatuss
	 */
	public void deleteInBatch(Collection<ProjectStatus> projectStatuss) {
		projectStatusRepository.deleteInBatch(projectStatuss);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public ProjectStatus findOne(Integer id) {
		return projectStatusRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<ProjectStatus> findAll() {
		return projectStatusRepository.findAll();
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
	public Page<ProjectStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			ProjectStatus.class.getDeclaredField(sortFieldName);
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
		return projectStatusRepository.findAll(pageable);
	}

	/**
	 * 通过名称模糊分页查询
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<ProjectStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			ProjectStatus.class.getDeclaredField(sortFieldName);
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
		return projectStatusRepository.findByNameLike("%" + name + "%", pageable);
	}
}
