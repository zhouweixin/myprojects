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

import com.xplusplus.security.domain.Nation;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.NationRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午6:35:17 2018年5月22日
 */
@Service
public class NationService {
	@Autowired
	private NationRepository nationRepository;

	/**
	 * 新增
	 * 
	 * @param nation
	 * @return
	 */
	public Nation save(Nation nation) {

		// 验证是否存在
		if (nation == null || (nation.getId() != null && nationRepository.findOne(nation.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		return nationRepository.save(nation);
	}

	/**
	 * 更新
	 * 
	 * @param nation
	 * @return
	 */
	public Nation update(Nation nation) {

		// 验证是否存在
		if (nation == null || nation.getId() == null || nationRepository.findOne(nation.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}

		return nationRepository.save(nation);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		// 验证是否存在
		if (nationRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		nationRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param nations
	 */
	public void deleteInBatch(Collection<Nation> nations) {
		nationRepository.deleteInBatch(nations);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public Nation findOne(Integer id) {
		return nationRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Nation> findAll() {
		return nationRepository.findAll();
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
	public Page<Nation> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Nation.class.getDeclaredField(sortFieldName);
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
		return nationRepository.findAll(pageable);
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
	public Page<Nation> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			Nation.class.getDeclaredField(sortFieldName);
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
		return nationRepository.findByNameLike("%" + name + "%", pageable);
	}
}
