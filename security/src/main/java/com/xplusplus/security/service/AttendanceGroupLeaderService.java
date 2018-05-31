package com.xplusplus.security.service;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.AttendanceGroupLeader;
import com.xplusplus.security.domain.Contract;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.AttendanceGroupLeaderRepository;
import com.xplusplus.security.repository.AttendanceGroupRepository;
import com.xplusplus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:45 2018/5/30
 */
@Service
public class AttendanceGroupLeaderService {
    @Autowired
    private AttendanceGroupLeaderRepository attendanceGroupLeaderRepository;
    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * 新增
     */
    public AttendanceGroupLeader save(AttendanceGroupLeader attendanceGroupLeader){
        if (attendanceGroupLeader == null || (attendanceGroupLeader.getId() != null &&
                attendanceGroupLeaderRepository.findOne(attendanceGroupLeader.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        //检查考勤组是否存在
        if (attendanceGroupLeader.getAttendanceGroup()==null ||
                (attendanceGroupRepository.findOne(attendanceGroupLeader.getAttendanceGroup().getId())==null)){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_ATTENDANCEGROUP_NOT_EXIST);
        }

        //检查员工是否存在
        if (attendanceGroupLeader.getLeader()==null ||
                (userRepository.findOne(attendanceGroupLeader.getLeader().getId())==null)){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_USER_NOT_EXIST);
        }

        return attendanceGroupLeaderRepository.save(attendanceGroupLeader);
    }
    /**
     * 更新
     */
    public AttendanceGroupLeader update(AttendanceGroupLeader attendanceGroupLeader){
        if (attendanceGroupLeader ==null || attendanceGroupLeader.getId() == null ||
                attendanceGroupLeaderRepository.findOne(attendanceGroupLeader.getId())==null){
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return attendanceGroupLeaderRepository.save(attendanceGroupLeader);
    }

    /**
     * 删除,不提供？
     *
     */

    /**
     *通过过ID查询
     */
    public AttendanceGroupLeader findOne(Integer id) {
        return attendanceGroupLeaderRepository.findOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<AttendanceGroupLeader> findAll() {
        return attendanceGroupLeaderRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<AttendanceGroupLeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            Contract.class.getDeclaredField(sortFieldName);
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
        return attendanceGroupLeaderRepository.findAll(pageable);
    }
    /**
     * 通过员工名模糊查询-分页
     */
    /**
     * 通过考勤组名模糊查询-分页
     */
}
