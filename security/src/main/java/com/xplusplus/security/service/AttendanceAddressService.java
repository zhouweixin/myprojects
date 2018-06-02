package com.xplusplus.security.service;

import com.xplusplus.security.domain.AttendanceAddress;
import com.xplusplus.security.domain.AttendanceGroupLeader;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.AttendanceAddressRepository;
import com.xplusplus.security.repository.AttendanceGroupLeaderRepository;
import com.xplusplus.security.repository.AttendanceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：XudongHu
 * @description: 考勤地址
 * @date:20:32 2018/6/1
 */
@Service
public class AttendanceAddressService {
    @Autowired
    private AttendanceAddressRepository attendanceAddressRepository;
    @Autowired
    private AttendanceGroupRepository attendanceGroupRepository;

    /**
     * 新增
     */
    public AttendanceAddress save(AttendanceAddress attendanceAddress) {
        if (attendanceAddress == null || (attendanceAddress.getId() != null) &&
                attendanceAddressRepository.findOne(attendanceAddress.getId()) != null) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return attendanceAddressRepository.save(attendanceAddress);
    }

    /**
     * 更新
     */
    public AttendanceAddress update(AttendanceAddress attendanceAddress) {
        if (attendanceAddress == null || (attendanceAddress.getId() == null) ||
                attendanceAddressRepository.findOne(attendanceAddress.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return attendanceAddressRepository.save(attendanceAddress);}

    /**
     * 通过id删除
      */
    public void delete(Integer id ){
        if(attendanceAddressRepository.findOne(id)==null){
        throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
    }
    }

}
