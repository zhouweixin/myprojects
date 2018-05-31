package com.xplusplus.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.xplusplus.security.domain.Project;
import com.xplusplus.security.domain.ProjectUser;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ProjectRepository;
import com.xplusplus.security.repository.ProjectUserRepository;
import com.xplusplus.security.repository.UserRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午3:55:25 2018年5月31日
 */
@Service
public class ProjectUserService {
	@Autowired
	private ProjectUserRepository projectUserRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectRepository projectRepository;

	/**
	 * 新增
	 * 
	 * @param projectUser
	 * @return
	 */
	public ProjectUser save(ProjectUser projectUser) {
		// 全不可为空的校验
		if (projectUser == null || projectUser.getUser() == null || projectUser.getProject() == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_NULL_EXIST);
		}

		// 用户不存在
		User user = userRepository.findOne(projectUser.getUser().getId());
		if (user == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_USER_NOT_EXIST);
		}

		// 项目不存在
		Project project = projectRepository.findOne(projectUser.getProject().getId());
		if (project == null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_PROJECT_NOT_EXIST);
		}

		if (projectUserRepository.findFirstByProjectAndUser(project, user) != null) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_EXIST);
		}

		return projectUserRepository.save(projectUser);
	}
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<ProjectUser> findAll(){
		return projectUserRepository.findAll();
	}
	
	/**
	 * 通过项目查询员工
	 * 
	 * @param project
	 * @return
	 */
	public List<User> findUserByProject(Project project){
		return projectUserRepository.findUserByProject(project);
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
	public Page<ProjectUser> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断排序字段名是否存在
		try {
			ProjectUser.class.getDeclaredField(sortFieldName);
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
		return projectUserRepository.findAll(pageable);
	}

	/**
	 * 通过员工查询
	 * 
	 * @param user
	 * @return
	 */
	public List<ProjectUser> findByUser(User user) {
		return projectUserRepository.findByUser(user);
	}

	/**
	 * 通过项目查询
	 * 
	 * @param project
	 * @return
	 */
	public List<ProjectUser> findByProject(Project project) {
		return projectUserRepository.findByProject(project);
	}

	/**
	 * 通过员工查询-分页
	 * 
	 * @param user
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<ProjectUser> findByUserByPage(User user, Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断排序字段名是否存在
		try {
			ProjectUser.class.getDeclaredField(sortFieldName);
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
		return projectUserRepository.findByUser(user, pageable);
	}
	
	/**
	 * 通过项目查询-分页
	 * 
	 * @param project
	 * @param page
	 * @param size
	 * @param sortFieldName
	 * @param asc
	 * @return
	 */
	public Page<ProjectUser> findByProjectByPage(Project project, Integer page, Integer size, String sortFieldName, Integer asc) {
		// 判断排序字段名是否存在
		try {
			ProjectUser.class.getDeclaredField(sortFieldName);
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
		return projectUserRepository.findByProject(project, pageable);
	}
}
