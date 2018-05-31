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

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ProjectRepository;
import com.xplusplus.security.repository.UserRepository;
import com.xplusplus.security.utils.GlobalUtil;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:31:38 2018年5月24日
 */
@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 新增
	 * 
	 * @param project
	 * @return
	 */
	public Project save(Project project) {

		// 验证是否存在
		if (project == null || (project.getId() != null && projectRepository.findOne(project.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}
		
		System.out.println(project);
		System.out.println(project.getLeader());

		// 检验项目负责人
		if (project.getLeader() == null || userRepository.getOne(project.getLeader().getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_USER_NOT_EXIST);
		}

		// 计算周期
		project.setPeriod(GlobalUtil.computePeriod(project.getStartDate(), project.getEndDate()));

		return projectRepository.save(project);
	}

	/**
	 * 更新
	 * 
	 * @param project
	 * @return
	 */
	public Project update(Project project) {

		// 验证是否存在
		if (project == null || project.getId() == null || projectRepository.findOne(project.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}

		// 检验项目负责人
		if (project.getLeader() == null || userRepository.getOne(project.getLeader().getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_USER_NOT_EXIST);
		}

		// 计算周期
		project.setPeriod(GlobalUtil.computePeriod(project.getStartDate(), project.getEndDate()));

		return projectRepository.save(project);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {

		// 验证是否存在
		if (projectRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		projectRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param projects
	 */
	public void deleteInBatch(Collection<Project> projects) {
		projectRepository.deleteInBatch(projects);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public Project findOne(Long id) {
		return projectRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Project> findAll() {
		return projectRepository.findAll();
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
	public Page<Project> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Project.class.getDeclaredField(sortFieldName);
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
		return projectRepository.findAll(pageable);
	}

	/**
	 * 通过名称模糊查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Project> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			Project.class.getDeclaredField(sortFieldName);
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
		return projectRepository.findByNameLike("%" + name + "%", pageable);
	}
	
	/**
	 * 通过客户单位模糊查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Project> findByCustomerUnitLikeByPage(String customerUnit, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			Project.class.getDeclaredField(sortFieldName);
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
		return projectRepository.findByCustomerUnitLike("%" + customerUnit + "%", pageable);
	}

	/**
	 * 通过项目状态查询-分页
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<Project> findByProjectStatusByPage(ProjectStatus projectStatus, Integer page, Integer size,
			String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Project.class.getDeclaredField(sortFieldName);
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
		return projectRepository.findByProjectStatus(projectStatus, pageable);
	}
}
