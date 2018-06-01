package com.xplusplus.security.service;

import com.xplusplus.security.domain.User;
import com.xplusplus.security.domain.WorkLogging;
import com.xplusplus.security.domain.WorkLoggingStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.UserRepository;
import com.xplusplus.security.repository.WorkLoggingRepository;
import com.xplusplus.security.repository.WorkLoggingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:50 2018/6/1
 * @Modified By:
 */
@Service
public class WorkLoggingService {
    @Autowired
    private WorkLoggingRepository workLoggingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkLoggingStatusRepository workLoggingStatusRepository;

    /**
     * 查询所有
     *
     * @return
     */
    public List<WorkLogging> findAll(){
        return workLoggingRepository.findAll();
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
    public Page<WorkLogging> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            WorkLogging.class.getDeclaredField(sortFieldName);
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
        return workLoggingRepository.findAll(pageable);
    }

    /**
     * 通过用户查询-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<WorkLogging> findByUserByPage(User user, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            WorkLogging.class.getDeclaredField(sortFieldName);
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
        return workLoggingRepository.findByUser(user, pageable);
    }

    /**
     * 通过状态查询-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<WorkLogging> findByStatusByPage(WorkLoggingStatus workLoggingStatus, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            WorkLogging.class.getDeclaredField(sortFieldName);
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
        return workLoggingRepository.findByStatus(workLoggingStatus, pageable);
    }

    /**
     * 修改状态
     *
     * @param workLoggingStatusId
     * @param note
     * @param modifyUserId
     * @param id
     */
    public void updateStatusAndNoteAndModifyUserAndModifyDateById(Integer workLoggingStatusId, String note, String modifyUserId, Long id){
        // 判断工作记录是否存在
        if(workLoggingRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        // 判断修改用户是否存在
        User modifyUser = userRepository.findOne(modifyUserId);
        if(modifyUser == null){
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_USER_NOT_EXIST);
        }

        // 验证状态是否存在
        WorkLoggingStatus workLoggingStatus = workLoggingStatusRepository.findOne(workLoggingStatusId);
        if(workLoggingStatus == null){
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_USER_NOT_EXIST);
        }

        workLoggingRepository.updateStatusAndNoteAndModifyUserAndModifyDateById(workLoggingStatus, note, modifyUser, new Date(), id);
    }
}
