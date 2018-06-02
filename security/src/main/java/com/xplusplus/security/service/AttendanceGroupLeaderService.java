package com.xplusplus.security.service;

import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.AttendanceGroupLeader;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.AttendanceGroupLeaderRepository;
import com.xplusplus.security.repository.AttendanceGroupRepository;
import com.xplusplus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * 通过id查询
     */
    public AttendanceGroupLeader findOne(Integer id){
        return attendanceGroupLeaderRepository.findOne(id);
    }
    /**
     * 添加负责人至考勤组
     */
    @Transactional
    public void addLeadersToAttendanceGroup(Integer attendanceGroupId, Set<String> leaderIds){
        // 判断考勤组是否存在
      AttendanceGroup attendanceGroup = attendanceGroupRepository.findOne(attendanceGroupId);
       if (attendanceGroup == null) {
            throw new SecurityExceptions(EnumExceptions.ASSIGN_FAILED_ATTENDANCE_GROUP_NOT_EXIST);
        }

       //删除已有的负责人关系
        attendanceGroupLeaderRepository.deleteByAttendanceGroup(attendanceGroup);

        List<AttendanceGroupLeader> attendanceGroupLeaders = new ArrayList<AttendanceGroupLeader>();
        for (String leaderId : leaderIds) {
            User leader = userRepository.findOne(leaderId);
            if (leader == null) {
                EnumExceptions.ASSIGN_FAILED_USER_NOT_EXIST.setMessage("分配失败, 员工" + leaderId + "不存在");
                throw new SecurityExceptions(EnumExceptions.ASSIGN_FAILED_USER_NOT_EXIST);
            }
            AttendanceGroupLeader attendanceGroupLeader = new AttendanceGroupLeader();
            attendanceGroupLeader.setLeader(leader);
            attendanceGroupLeader.setAttendanceGroup(attendanceGroup);
            attendanceGroupLeaders.add(attendanceGroupLeader);
        }
        attendanceGroupLeaderRepository.save(attendanceGroupLeaders);
    }
    /**
     * 通过考勤组查询
     *
     * @param attendanceGroup
     * @return
     */
    public List<AttendanceGroupLeader> findByAttendanceGroup(AttendanceGroup attendanceGroup){
        return  attendanceGroupLeaderRepository.findByAttendanceGroup(attendanceGroup);
    }

}
