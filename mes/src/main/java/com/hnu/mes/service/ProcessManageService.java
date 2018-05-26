package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import com.hnu.mes.domain.ProcessManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.hnu.mes.domain.Process;

import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessManageRepository;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class ProcessManageService {
    // 注入
    @Autowired
    private ProcessManageRepository processManageRepository;

    /**
     * 新增
     *
     * @param processManage
     * @return
     */
    public ProcessManage save(ProcessManage processManage) {
        return processManageRepository.save(processManage);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        if(code == 0){
            return;
        }

        processManageRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public ProcessManage findOne(Integer code) {
        return processManageRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<ProcessManage> findAll() {
        return processManageRepository.findAll();
    }

    /**
     * 通过分页查询所有
     *
     * @param page
     *            当前页
     * @param size
     *            每页的记录数
     * @param sortFieldName
     *            排序的字段名
     * @param asc
     *            增序还是减序
     * @return
     * @throws Exception
     */
    public Page<ProcessManage> getCheckByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            ProcessManage.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return processManageRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊查询
     *
     * @param name
     *            名称
     * @param page
     *            当前页
     * @param size
     *            每页的记录数
     * @param sortFieldName
     *            排序的字段名
     * @param asc
     *            增序或减序
     * @return
     */
    public Page<ProcessManage> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                       Integer asc) {

        try {
            ProcessManage.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 排序的字段名不存在
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Direction.ASC, sortFieldName);
        }

        // 分页
        Pageable pageable = new PageRequest(page, size, sort);

        // 只匹配name,其它属性全都忽略
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", GenericPropertyMatchers.contains())
                .withIgnorePaths("code", "info");
        ProcessManage processManage = new ProcessManage();
        processManage.setName(name);

        // 创建实例
        Example<ProcessManage> example = Example.of(processManage, matcher);

        // 查询
        return processManageRepository.findAll(example, pageable);
    }

    /**
     * 批量删除
     *
     * @param processManages
     */
    public void deleteInBatch(Set<ProcessManage> processManages){
        processManageRepository.deleteInBatch(processManages);
    }

    /**
     * 通过流程类型查询
     *
     * @param process
     * @return
     */
    public List<ProcessManage> findByProcess(Process process){
        return processManageRepository.findByProcess(process);
    }
}
