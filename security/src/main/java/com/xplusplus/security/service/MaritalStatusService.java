package com.xplusplus.security.service;

import com.xplusplus.security.domain.MaritalStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Huxudong
 * @Description: 婚姻状况Service
 * @Date: Created in 12:36 2018/5/22
 * @Modified By:
 */
@Service
public class MaritalStatusService {
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    /**
     * 新增
     */
    public MaritalStatus save(MaritalStatus maritalStatus) {

        // 验证是否存在
        if (maritalStatus == null || (maritalStatus.getId() != null && maritalStatusRepository.findOne(maritalStatus.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return maritalStatusRepository.save(maritalStatus);
    }

    /**
     * 更新
     */
    public MaritalStatus update(MaritalStatus maritalStatus) {

        // 验证是否存在
        if (maritalStatus == null || maritalStatus.getId() == null || maritalStatusRepository.findOne(maritalStatus.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return maritalStatusRepository.save(maritalStatus);
    }

    /**
     * 删除
     */
    public void delete(Integer id) {

        // 验证是否存在
        if (maritalStatusRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        maritalStatusRepository.delete(id);
    }

    /**
     * 批量删除
     */
    public void deleteInBatch(Collection<MaritalStatus> maritalStatuss) {
        maritalStatusRepository.deleteInBatch(maritalStatuss);
    }

    /**
     * 通过编码查询
     */
    public MaritalStatus findOne(Integer id) {
        return maritalStatusRepository.findOne(id);
    }

    /**
     * 查询所有
     */
    public List<MaritalStatus> findAll() {
        return maritalStatusRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<MaritalStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            MaritalStatus.class.getDeclaredField(sortFieldName);
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
        return maritalStatusRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     */
    public Page<MaritalStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
                                             Integer asc) {

        // 判断排序字段名是否存在
        try {
            MaritalStatus.class.getDeclaredField(sortFieldName);
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
        return maritalStatusRepository.findByNameLike("%" + name + "%", pageable);
    }

}
