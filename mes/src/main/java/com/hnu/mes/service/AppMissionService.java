package com.hnu.mes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.AppMission;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AppMissionRepository;

import javax.transaction.Transactional;

/**
 * @author p[ngxiao
 */
@Service
public class AppMissionService {
    // 注入
    @Autowired
    private AppMissionRepository appMissionRepository;

    /**
     * 新增
     *
     * @param appMission
     * @return
     */
    public AppMission save(AppMission appMission) {
        return appMissionRepository.save(appMission);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Long code) {
        appMissionRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public AppMission findOne(Long code) {
        return appMissionRepository.findOne(code);
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<AppMission> findAll() {
        return appMissionRepository.findAll();
    }


    /**
     * 更新时间
     *
     * @param updateTime
     * @return
     */
    @Transactional
    public Integer updateTimeByCode(String updateTime, Long code) {
        return appMissionRepository.updateTimeByCode(updateTime, code);
    }

    /**
     * 通过状态编码分页查询
     *
     * @param updateTime
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<AppMission> getAllByUpdateCodeByPage(String updateTime, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            AppMission.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            //降序，desc
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            //升序，asc
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        //分页
        Pageable pageable = new PageRequest(page, size, sort);

        return appMissionRepository.findByUpdateTime(updateTime, pageable);
    }


    /**
     * 更新审核时间
     *
     * @param examTime
     * @return
     */
    @Transactional
    public Integer updateExamTimeByCode(String examTime, Long code) {
        return appMissionRepository.updateExamTimeByCode(examTime, code);
    }

    /**
     * 通过审核时间分页查询
     *
     * @param examTime
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<AppMission> getAllByExamCodeByPage(String examTime, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            AppMission.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            //降序，desc
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            //升序，asc
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        //分页
        Pageable pageable = new PageRequest(page, size, sort);

        return appMissionRepository.findByExamTime(examTime, pageable);
    }



}
