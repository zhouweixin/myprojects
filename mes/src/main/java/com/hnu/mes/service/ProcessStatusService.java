package com.hnu.mes.service;

import com.hnu.mes.domain.ProcessStatus;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@Service
public class ProcessStatusService {
    // 注入
    @Autowired
    private ProcessStatusRepository processStatusRepository;

    /**
     * 新增
     *
     * @param processStatus
     * @return
     */
    public ProcessStatus save(ProcessStatus processStatus) {
        return processStatusRepository.save(processStatus);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        processStatusRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public ProcessStatus findOne(String code) {
        return processStatusRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<ProcessStatus> findAll() {
        return processStatusRepository.findAll();
    }

    /**
     * 通过分页查询所有
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<ProcessStatus> getProcessStatusByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            ProcessStatus.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return processStatusRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊查询
     *
     * @param status          名称
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序或减序
     * @return
     */
    public Page<ProcessStatus> findByStatusByPage(Integer status, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            ProcessStatus.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 排序的字段名不存在
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        // 分页
        Pageable pageable = new PageRequest(page, size, sort);

        return processStatusRepository.findByStatus(status, pageable);
    }

    public void deleteInBatch(Set<ProcessStatus> processStatuses) {
        processStatusRepository.deleteInBatch(processStatuses);
    }
}
