package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.DefaultPassword;
import com.hnu.mes.repository.DefaultPasswordRepository;

/**
 * 
 * @author zhouweixin
 *
 */
@Service
public class DefaultPasswordService {
	// 注入
	@Autowired
	private DefaultPasswordRepository defaultPasswordRepository;

	/**
	 * 新增
	 * 
	 * @param defaultPassword
	 * @return
	 */
	public DefaultPassword save(DefaultPassword defaultPassword) {
		return defaultPasswordRepository.save(defaultPassword);
	}

	/**
	 * 删除
	 * 
	 * @param code
	 */
	public void delete(Integer code) {
		defaultPasswordRepository.delete(code);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @return
	 */
	public DefaultPassword findOne(Integer code) {
		return defaultPasswordRepository.findOne(code);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<DefaultPassword> findAll() {
		return defaultPasswordRepository.findAll();
	}

	/**
	 * 查询默认密码
	 *
	 * @return
	 */
	public String getDefaultPassword() {
		// 取出默认密码
		Integer code = 1;
		String password = "123456";
		DefaultPassword defaultPassword = defaultPasswordRepository.findOne(code);
		if (defaultPassword == null) {
			// 添加默认密码
			defaultPassword = new DefaultPassword();
			defaultPassword.setCode(code);
			defaultPassword.setPassword(password);
			defaultPasswordRepository.save(defaultPassword);
		} else {
			password = defaultPassword.getPassword();
		}

		return password;
	}

	/**
	 * 批量删除
	 *
	 * @param defaultPasswords
	 */
    public void deleteInBatch(Set<DefaultPassword> defaultPasswords) {
		defaultPasswordRepository.deleteInBatch(defaultPasswords);
    }

	/**
	 * 通过分页查询所有
	 *
	 * @param page          当前页
	 * @param size          每页的记录数
	 * @param sortFieldName 排序的字段名
	 * @param asc           增序还是减序
	 * @return
	 * @throws Exception
	 */
	public Page<DefaultPassword> getAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断字段名是否存在
		try {
			DefaultPassword.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}
		Pageable pageable = new PageRequest(page, size, sort);
		return defaultPasswordRepository.findAll(pageable);
	}

	/**
	 * 通过名称模糊查询
	 *
	 * @param name          名称
	 * @param page          当前页
	 * @param size          每页的记录数
	 * @param sortFieldName 排序的字段名
	 * @param asc           增序或减序
	 * @return
	 */
	public Page<DefaultPassword> findAllByLikePasswordByPage(String name, Integer page, Integer size, String sortFieldName,
													Integer asc) {

		try {
			DefaultPassword.class.getDeclaredField(sortFieldName);
		} catch (Exception e) {
			// 排序的字段名不存在
			throw new MesException(EnumException.SORT_FIELD);
		}

		Sort sort = null;
		if (asc == 0) {
			sort = new Sort(Sort.Direction.DESC, sortFieldName);
		} else {
			sort = new Sort(Sort.Direction.ASC, sortFieldName);
		}

		// 分页
		Pageable pageable = new PageRequest(page, size, sort);

		return defaultPasswordRepository.findAllByPasswordLike("%" + name + "%", pageable);
	}
}
