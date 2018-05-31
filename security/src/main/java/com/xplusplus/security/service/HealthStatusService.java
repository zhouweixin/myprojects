package com.xplusplus.security.service;

import com.xplusplus.security.domain.HealthStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.HealthStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: liweifeng
 * @Description:
 * @Date: Created in 12:35 2018/5/22
 * @Modified By:
 */
@Service
public class HealthStatusService {
	@Autowired
	private HealthStatusRepository healthStatusRepository;

	/**
	 * 新增健康状况
	 * 
	 * @param healthStatus
	 * @return
	 */
	public HealthStatus save(HealthStatus healthStatus) {

		// 验证是否存在
		if (healthStatus == null
				|| (healthStatus.getId() != null && healthStatusRepository.findOne(healthStatus.getId()) != null)) {
			throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
		}
		return healthStatusRepository.save(healthStatus);
	}

	/**
	 * 更新健康状况
	 * 
	 * @param healthStatus
	 * @return
	 */
	public HealthStatus update(HealthStatus healthStatus) {

		// 验证是否存在
		if (healthStatus == null || healthStatus.getId() == null
				|| healthStatusRepository.findOne(healthStatus.getId()) == null) {
			throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
		}
		return healthStatusRepository.save(healthStatus);
	}

	/**
	 * 删除健康状况
	 * 
	 * @param id
	 */
	public void delete(Integer id) {

		// 验证是否存在
		if (healthStatusRepository.findOne(id) == null) {
			throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
		}
		healthStatusRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param healthStatuss
	 */
	public void deleteInBatch(Collection<HealthStatus> healthStatuss) {
		healthStatusRepository.deleteInBatch(healthStatuss);
	}

	/**
	 * 通过编号查询
	 * 
	 * @param id
	 * @return
	 */
	public HealthStatus findOne(Integer id) {
		return healthStatusRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<HealthStatus> findAll() {
		return healthStatusRepository.findAll();
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
	public Page<HealthStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			HealthStatus.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return healthStatusRepository.findAll(pageable);
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
	public Page<HealthStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
			Integer asc) {

		// 判断排序字段名是否存在
		try {
			HealthStatus.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 如果不存在就设置为id
			sortFieldName = "id";
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}

		Pageable pageable = new PageRequest(page, size, sort);
		return healthStatusRepository.findByNameLike("%" + name + "%", pageable);
	}

}
