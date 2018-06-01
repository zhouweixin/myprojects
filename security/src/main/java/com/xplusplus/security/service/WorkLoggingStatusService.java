package com.xplusplus.security.service;

import com.xplusplus.security.domain.WorkLoggingStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.WorkLoggingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:35 2018/6/1
 * @Modified By:
 */
@Service
public class WorkLoggingStatusService {
    @Autowired
    private WorkLoggingStatusRepository workLoggingStatusRepository;

    /**
     * 新增
     *
     * @param workLoggingStatus
     * @return
     */
    public WorkLoggingStatus save(WorkLoggingStatus workLoggingStatus) {

        // 验证是否存在
        if (workLoggingStatus == null || (workLoggingStatus.getId() != null && workLoggingStatusRepository.findOne(workLoggingStatus.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return workLoggingStatusRepository.save(workLoggingStatus);
    }

    /**
     * 更新
     *
     * @param workLoggingStatus
     * @return
     */
    public WorkLoggingStatus update(WorkLoggingStatus workLoggingStatus) {

        // 验证是否存在
        if (workLoggingStatus == null || workLoggingStatus.getId() == null || workLoggingStatusRepository.findOne(workLoggingStatus.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return workLoggingStatusRepository.save(workLoggingStatus);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Integer id) {

        // 验证是否存在
        if (workLoggingStatusRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        workLoggingStatusRepository.delete(id);
    }

    /**
     * 批量删除
     *
     * @param workLoggingStatuss
     */
    public void deleteInBatch(Collection<WorkLoggingStatus> workLoggingStatuss) {
        workLoggingStatusRepository.deleteInBatch(workLoggingStatuss);
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    public WorkLoggingStatus findOne(Integer id) {
        return workLoggingStatusRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<WorkLoggingStatus> findAll() {
        return workLoggingStatusRepository.findAll();
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
    public Page<WorkLoggingStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            WorkLoggingStatus.class.getDeclaredField(sortFieldName);
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
        return workLoggingStatusRepository.findAll(pageable);
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
    public Page<WorkLoggingStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
                                             Integer asc) {

        // 判断排序字段名是否存在
        try {
            WorkLoggingStatus.class.getDeclaredField(sortFieldName);
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
        return workLoggingStatusRepository.findByNameLike("%" + name + "%", pageable);
    }
}
