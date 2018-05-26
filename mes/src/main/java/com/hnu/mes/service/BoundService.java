package com.hnu.mes.service;

import com.hnu.mes.domain.Bound;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.BoundRepository;
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
public class BoundService {
    // 注入
    @Autowired
    private BoundRepository boundRepository;

    /**
     * 新增
     *
     * @param bound
     * @return
     */
    public Bound save(Bound bound) {
        return boundRepository.save(bound);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        boundRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Bound findOne(String code) {
        return boundRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Bound> findAll() {
        return boundRepository.findAll();
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
    public Page<Bound> getBoundByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Bound.class.getDeclaredField(sortFieldName);
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
        return boundRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊查询
     *
     * @param code          编码
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序或减序
     * @return
     */
    public Page<Bound> findByCodeLikeByPage(String code, Integer page, Integer size, String sortFieldName,
                                            Integer asc) {
        try {
            Bound.class.getDeclaredField(sortFieldName);
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

        return boundRepository.findByCodeLike("%" + code + "%", pageable);
    }


    public void deleteInBatch(Set<Bound> bounds) {
        boundRepository.deleteInBatch(bounds);
    }
}
