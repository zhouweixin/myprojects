package com.xplusplus.security.service;

import com.xplusplus.security.domain.Department;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.DepartmentRepository;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:34 2018/5/22
 * @Modified By:
 */
@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * 新增
	 * 
	 * @param department
	 * @return
	 */
	public Department save(Department department) {

		// 验证是否存在
		if (department == null
				|| (department.getId() != null && departmentRepository.findOne(department.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		if (departmentRepository.findFirstByShortName(department.getShortName()) != null
				|| Pattern.matches("^[A-Za-z]{2}$", department.getShortName()) == false) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SHORT_NAME_NOT_LAWER);
		}

		return departmentRepository.save(department);
	}

	/**
	 * 更新
	 * 
	 * @param department
	 * @return
	 */
	public Department update(Department department) {

		// 验证是否存在
		if (department == null || department.getId() == null
				|| departmentRepository.findOne(department.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}
		
		// 删除
		departmentRepository.delete(department.getId());
		
		if (departmentRepository.findFirstByShortName(department.getShortName()) != null
				|| Pattern.matches("^[A-Za-z]{2}$", department.getShortName()) == false) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SHORT_NAME_NOT_LAWER);
		}

		return departmentRepository.save(department);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		// 验证是否存在
		if (departmentRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		departmentRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param departments
	 */
	public void deleteInBatch(Collection<Department> departments) {
		departmentRepository.deleteInBatch(departments);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public Department findOne(Integer id) {
		return departmentRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Department> findAll() {
		return departmentRepository.findAll();
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
	public Page<Department> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Department.class.getDeclaredField(sortFieldName);
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
		return departmentRepository.findAll(pageable);
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
	public Page<Department> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			Department.class.getDeclaredField(sortFieldName);
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
		return departmentRepository.findByNameLike("%" + name + "%", pageable);
	}
}
