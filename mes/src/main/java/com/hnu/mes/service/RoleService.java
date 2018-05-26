package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.Role;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.RoleRepository;

/**
 * 
 * @author zhouweixin
 *
 */
@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;	

	/**
	 * 新增
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	/**
	 * 删除
	 * 
	 * @param code
	 */
	public void delete(Integer code) {
		roleRepository.delete(code);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @return
	 */
	public Role findOne(Integer code) {
		return roleRepository.findOne(code);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	/**
	 * 通过分页查询所有
	 * 
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<Role> getRoleByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		try {
			Role.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return roleRepository.findAll(pageable);
	}

	/**
	 * 通过名称模糊查询
	 * 
	 * @param name
	 *            名称
	 * @param page
	 *            当前页
	 * @param size
	 *            每页的记录数
	 * @param sortFieldName
	 *            排序的字段名
	 * @param asc
	 *            增序或减序
	 * @return
	 */
	public Page<Role> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		try {
			Role.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 排序的字段名不存在
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);

		// 只匹配name,其它属性全都忽略
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", GenericPropertyMatchers.contains())
				.withIgnorePaths("code", "description");
		Role role = new Role();
		role.setName(name);

		// 创建实例
		Example<Role> example = Example.of(role, matcher);

		// 查询
		return roleRepository.findAll(example, pageable);
	}

	/**
	 * 批量删除
	 * @param roles
	 */
    public void deleteInBatch(Set<Role> roles) {
		roleRepository.deleteInBatch(roles);
    }
}
