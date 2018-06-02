package com.xplusplus.security.service;

import com.xplusplus.security.domain.*;
import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.AttendanceGroupLeaderRepository;
import com.xplusplus.security.repository.AttendanceGroupRepository;
import com.xplusplus.security.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: 考勤组Service
 * @date:15:33 2018/5/29
 */
@Service
public class AttendanceGroupService {
    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AttendanceGroupLeaderRepository attendanceGroupLeaderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AttendanceGroupLeaderService attendanceGroupLeaderService;
    /**
     * 新增考勤组
     */
    @Transactional
    public AttendanceGroup save(AttendanceGroup attendanceGroup, Set<String> ids, Set<String> leaderIds){
        if (attendanceGroup == null || (attendanceGroup.getId() != null &&
                attendanceGroupRepository.findOne(attendanceGroup.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //验证是否存在班次
        if (attendanceGroup.getSchedule() == null ||
                scheduleRepository.findOne(attendanceGroup.getSchedule().getId())==null
                ) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SCHEDULE_NOT_EXIST);
        }
        //先行保存
        AttendanceGroup save = attendanceGroupRepository.save(attendanceGroup);
        //验证是否设定了外勤打卡
        /**
        if(attendanceGroup.getEnableOut()==null)
        { throw new SecurityExceptions(EnumExceptions.ADD_FAILED_ATTENDANCEGROUP_NOT_EXIST);}
        **/


        //调用AttendanceGroupLeaderService新增负责人
       attendanceGroupLeaderService.addLeadersToAttendanceGroup(attendanceGroup.getId(),leaderIds);
//        调用UserService新增考勤员工
        userService.assignUsersToAttendanceGroup(attendanceGroup.getId(),ids);


        return save;
    }
    /**
     * 更新考勤组
     */
    @Transactional
    public AttendanceGroup update(AttendanceGroup attendanceGroup,Set<String> ids, Set<String> leaderIds) {

        // 验证是否存在
        if (attendanceGroup == null || attendanceGroup.getId() == null ||
                attendanceGroupRepository.findOne(attendanceGroup.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        //验证是否存在班次
        if (attendanceGroup.getSchedule() == null ||
                scheduleRepository.findOne(attendanceGroup.getSchedule().getId())==null) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SCHEDULE_NOT_EXIST);
        }
        /**验证是否设定了外勤打卡
        if(attendanceGroup.getEnableOut()==null)
        { throw new SecurityExceptions(EnumExceptions.ADD_FAILED_ATTENDANCEGROUP_NOT_EXIST);}
         **/
        //先行保存
        AttendanceGroup save = attendanceGroupRepository.save(attendanceGroup);
        //调用AttendanceGroupleaderService新增负责人
        attendanceGroupLeaderService.addLeadersToAttendanceGroup(attendanceGroup.getId(),leaderIds);
        //调用UserService新增考勤员工
        userService.assignUsersToAttendanceGroup(attendanceGroup.getId(),ids);


        return save;
    }
    /**
     * 删除考勤组
     */
    public void delete(Integer id) {
        //验证是否存在
        if(attendanceGroupRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        //先删除 考勤负责人
        attendanceGroupLeaderRepository.deleteByAttendanceGroup(attendanceGroupRepository.getOne(id));
        //再删除考勤组
        attendanceGroupRepository.delete(id);
    }
    /**
     * 通过id查询
     */
    public AttendanceGroup findOne(Integer id) {
        return attendanceGroupRepository.findOne(id);
    }
    /**
     * 通过考勤组ID查询该考勤组员工
     */
    public List<User> findUser(Integer id){
        return userService.findByAttendanceGroup(attendanceGroupRepository.findOne(id));
    }
    /**
     * 通过考勤组ID 查询该考勤组负责人
     */
    public List<AttendanceGroupLeader> findLeaders(Integer id){
        return attendanceGroupLeaderService.findByAttendanceGroup(attendanceGroupRepository.findOne(id));
    }

    /**
     * 查询所有
     */
    public List<AttendanceGroup> findAll(){
        return attendanceGroupRepository.findAll();
    }

    /**
     * 分页查询所有
     */
    public Page<AttendanceGroup> findAllByPage(Integer page,Integer size,String sortFieldName,Integer asc){
        //判断排序字段名是否存在
        try{AttendanceGroup.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            //如果不存在就设置为id
            sortFieldName="id";
        }

        Sort sort = null;
        if (asc==0){
            sort = new Sort(Sort.Direction.DESC,sortFieldName);
        }else {
            sort = new Sort(Sort.Direction.ASC,sortFieldName);
        }
        Pageable pageable = new PageRequest(page,size,sort);
        return attendanceGroupRepository.findAll(pageable);
    }

    /***
     * 通过考勤组名模糊查询-分页
     */
    public Page<AttendanceGroup> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
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
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return attendanceGroupRepository.findByNameLike("%" + name + "%", pageable);
    }

}
