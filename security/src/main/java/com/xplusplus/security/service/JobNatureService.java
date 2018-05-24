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

import com.xplusplus.security.domain.JobNature;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.JobNatureRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午7:00:58 2018年5月24日
 */
@Service
public class JobNatureService {
	@Autowired
	private JobNatureRepository jobNatureRepository;

	/**
	 * 新增
	 * 
	 * @param jobNature
	 * @return
	 */
	public JobNature save(JobNature jobNature) {

		// 验证是否存在
		if (jobNature == null || (jobNature.getId() != null && jobNatureRepository.findOne(jobNature.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}

		return jobNatureRepository.save(jobNature);
	}

	/**
	 * 更新
	 * 
	 * @param jobNature
	 * @return
	 */
	public JobNature update(JobNature jobNature) {

		// 验证是否存在
		if (jobNature == null || jobNature.getId() == null || jobNatureRepository.findOne(jobNature.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}

		return jobNatureRepository.save(jobNature);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		// 验证是否存在
		if (jobNatureRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		jobNatureRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param jobNatures
	 */
	public void deleteInBatch(Collection<JobNature> jobNatures) {
		jobNatureRepository.deleteInBatch(jobNatures);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public JobNature findOne(Integer id) {
		return jobNatureRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<JobNature> findAll() {
		return jobNatureRepository.findAll();
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
	public Page<JobNature> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			JobNature.class.getDeclaredField(sortFieldName);
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
		return jobNatureRepository.findAll(pageable);
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
	public Page<JobNature> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			JobNature.class.getDeclaredField(sortFieldName);
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
		return jobNatureRepository.findByNameLike("%" + name + "%", pageable);
	}
}
