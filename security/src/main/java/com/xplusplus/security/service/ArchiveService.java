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

import com.xplusplus.security.domain.Archive;
import com.xplusplus.security.repository.ArchiveRepository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:33 2018/5/22
 * @Modified By:
 */
@Service
public class ArchiveService {
	@Autowired
	private ArchiveRepository archiveRepository;

	/**
	 * 新增
	 * 
	 * @param archive
	 * @return
	 */
	public Archive save(Archive archive) {
		return archiveRepository.save(archive);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		archiveRepository.delete(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param archives
	 */
	public void deleteInBatch(Collection<Archive> archives) {
		archiveRepository.deleteInBatch(archives);
	}

	/**
	 * 通过编码查询
	 * 
	 * @param id
	 * @return
	 */
	public Archive findOne(Long id) {
		return archiveRepository.findOne(id);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Archive> findAll() {
		return archiveRepository.findAll();
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
	public Page<Archive> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

		// 判断排序字段名是否存在
		try {
			Archive.class.getDeclaredField(sortFieldName);
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
		return archiveRepository.findAll(pageable);
	}
}
