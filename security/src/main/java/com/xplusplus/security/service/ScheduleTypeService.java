package com.xplusplus.security.service;

import com.xplusplus.security.domain.ScheduleType;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ScheduleTypeRepository;
import com.xplusplus.security.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ScheduleTypeService {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    /**
     * 新增班次类型
     * @param scheduleType
     * @return
     */
    public ScheduleType save(ScheduleType scheduleType){

        //验证班次类型是否存在
        if(scheduleType == null
                ||(scheduleType.getId() != null && scheduleTypeRepository.findOne(scheduleType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //验证时间正确性，end>start
        if(scheduleType.getEndTime().compareTo(scheduleType.getStartTime()) != 1
                && scheduleType.getEndBreakTime().compareTo(scheduleType.getStartBreakTime()) != 1){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_TIME_NOT_CORRECT);
        }

        //计算工作时长
        scheduleType.setWorkPeriod(GlobalUtil.computeWorkPeriod(scheduleType.getStartTime(),scheduleType.getEndTime(),
                scheduleType.getStartBreakTime(),scheduleType.getEndBreakTime()));

        //计算休息时长
        scheduleType.setBreakPeriod(GlobalUtil.computeBreakPeriod(scheduleType.getStartBreakTime(),scheduleType.getEndBreakTime()));

        return scheduleTypeRepository.save(scheduleType);
    }

    /**
     * 更新班次类型
     * @param scheduleType
     * @return
     */
    public ScheduleType update(ScheduleType scheduleType){

        //验证班次类型是否存在
        if(scheduleType == null
                ||(scheduleType.getId() != null && scheduleTypeRepository.findOne(scheduleType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        //验证时间正确性，end>start
        if(scheduleType.getEndTime().compareTo(scheduleType.getStartTime()) != 1
                && scheduleType.getEndBreakTime().compareTo(scheduleType.getStartBreakTime()) != 1){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_TIME_NOT_CORRECT);
        }

        //计算工作时长
        scheduleType.setBreakPeriod(GlobalUtil.computeWorkPeriod(scheduleType.getStartTime(),scheduleType.getEndTime(),
                scheduleType.getStartBreakTime(),scheduleType.getEndBreakTime()));

        //计算休息时长
        scheduleType.setWorkPeriod(GlobalUtil.computeBreakPeriod(scheduleType.getStartTime(),scheduleType.getEndTime()));

        return scheduleTypeRepository.save(scheduleType);
    }

    /**
     * 删除班次类型
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(scheduleTypeRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        scheduleTypeRepository.delete(id);
    }

    /**
     * 批量删除
     * @param scheduleTypes
     */
    public void deleteInBatch(Collection<ScheduleType> scheduleTypes){
        scheduleTypeRepository.deleteInBatch(scheduleTypes);
    }

    /**
     * 通过编号查询
     * @param id
     * @return
     */
    public ScheduleType findOne(Integer id){
        return scheduleTypeRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<ScheduleType> findAll(){
        return scheduleTypeRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ScheduleType> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ScheduleType.class.getDeclaredField(sortFieldName);
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
        return scheduleTypeRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ScheduleType> findByNameLikeByPage(String name, Integer page, Integer size,
                                                String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ScheduleType.class.getDeclaredField(sortFieldName);
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
        return scheduleTypeRepository.findByNameLike("%" + name + "%",pageable);
    }
}
