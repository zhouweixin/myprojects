package com.hnu.mes.service;

import com.hnu.mes.domain.Process;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/16
 * @Description: 工序服务层
 */
@Service
public class ProcessService {
    @Autowired
    private ProcessRepository processRepository;

    public Process save(Process process) {
        /**
         * save
         * @Desciption 新增
         * @param [process]
         */
        return processRepository.save(process);
    }

    public void delete(Integer code) {
        /**
         * delete
         * @Desciption 删除
         * @param [code]
         */
        processRepository.delete(code);
    }

    public Process findOne(Integer code) {
        /**
         * findOne
         * @Desciption 查询
         * @param [code]
         */
        return processRepository.findOne(code);
    }

    public List<Process> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return processRepository.findAll();
    }

    public Page<Process> getProcessByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * getProcessByPage
         * @Desciption
         * @param [page, size, sortFieldName, asc]
         * @throws Exception
         */

        try {
            Process.class.getDeclaredField(sortFieldName);
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

        Pageable pageable = new PageRequest(page, size, sort);
        return processRepository.findAll(pageable);
    }

    public Page<Process> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            Process.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
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

        // 查询
        return processRepository.findByNameLike("%" + name + "%", pageable);
    }

    public void deleteInBatch(Set<Process> processes) {
        processRepository.deleteInBatch(processes);
    }
}
