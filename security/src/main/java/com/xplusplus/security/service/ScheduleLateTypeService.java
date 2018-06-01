package com.xplusplus.security.service;

import com.xplusplus.security.domain.LateType;
import com.xplusplus.security.domain.Schedule;
import com.xplusplus.security.domain.ScheduleLateType;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.LateTypeRepository;
import com.xplusplus.security.repository.ScheduleLateTypeRepository;
import com.xplusplus.security.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ScheduleLateTypeService {

    @Autowired
    private ScheduleLateTypeRepository scheduleLateTypeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private LateTypeRepository lateTypeRepository;

    /**
     * 新增班次迟到类型
     * @param scheduleLateType
     * @return
     */
    public ScheduleLateType save(ScheduleLateType scheduleLateType){

        //验证班次迟到类型是否存在
        if(scheduleLateType == null
                ||(scheduleLateType.getId() != null && scheduleLateTypeRepository.findOne(scheduleLateType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //验证班次
        Schedule schedule = null;
        if(scheduleLateType.getSchedule() == null
                || (schedule = scheduleRepository.findOne(scheduleLateType.getSchedule().getId())) == null )
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SCHEDULE_NOT_EXIST);

        //验证迟到类型
        LateType lateType = null;
        if(scheduleLateType.getLateType() == null
                || (lateType = lateTypeRepository.findOne(scheduleLateType.getLateType().getId())) == null )
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_LATETYPE_NOT_EXIST);


        return scheduleLateTypeRepository.save(scheduleLateType);
    }


    /**
     * 更新班次迟到类型
     * @param scheduleLateType
     * @return
     */
    public ScheduleLateType update(ScheduleLateType scheduleLateType){

        //验证班次迟到类型是否存在
        if(scheduleLateType == null
                ||(scheduleLateType.getId() != null && scheduleLateTypeRepository.findOne(scheduleLateType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //验证班次
        Schedule schedule = null;
        if(scheduleLateType.getSchedule() == null
                || (schedule = scheduleRepository.findOne(scheduleLateType.getSchedule().getId())) == null )
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_SCHEDULE_NOT_EXIST);

        //验证迟到类型
        LateType lateType = null;
        if(scheduleLateType.getLateType() == null
                || (lateType = lateTypeRepository.findOne(scheduleLateType.getLateType().getId())) == null )
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_LATETYPE_NOT_EXIST);

        return scheduleLateTypeRepository.save(scheduleLateType);
    }


    /**
     * 删除班次迟到类型
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(scheduleLateTypeRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        scheduleLateTypeRepository.delete(id);
    }


    /**
     * 批量删除
     * @param scheduleLateTypes
     */
    public void deleteInBatch(Collection<ScheduleLateType> scheduleLateTypes){
        scheduleLateTypeRepository.deleteInBatch(scheduleLateTypes);
    }


    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public ScheduleLateType findOne(Integer id){
        return scheduleLateTypeRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<ScheduleLateType> findAll(){
        return scheduleLateTypeRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ScheduleLateType> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ScheduleLateType.class.getDeclaredField(sortFieldName);
        }catch (Exception e){
            //如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort =null;
        if(asc ==0 ){
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        }else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return scheduleLateTypeRepository.findAll(pageable);
    }
}
