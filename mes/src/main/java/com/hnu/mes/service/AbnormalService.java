package com.hnu.mes.service;

import java.util.List;

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

import com.hnu.mes.domain.Abnormal;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AbnormalRepository;

/**
 * @author zhouweixin
 */
@Service
public class AbnormalService {
    // 注入
    @Autowired
    private AbnormalRepository abnormalRepository;

    /**
     * 新增
     *
     * @param abnormal
     * @return
     */
    public Abnormal save(Abnormal abnormal) {
        return abnormalRepository.save(abnormal);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        abnormalRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Abnormal findOne(String code) {
        return abnormalRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Abnormal> findAll() {
        return abnormalRepository.findAll();
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
    public Page<Abnormal> getAbnormalByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Abnormal.class.getDeclaredField(sortFieldName);
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
        return abnormalRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊查询
     *
     * @param name          名称
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序或减序
     * @return
     */
    public Page<Abnormal> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Abnormal.class.getDeclaredField(sortFieldName);
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

        return abnormalRepository.findByNameLike("%" + name + "%", pageable);
    }
}
