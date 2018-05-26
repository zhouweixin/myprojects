package com.hnu.mes.service;

import com.hnu.mes.domain.Indicator;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.IndicatorRepository;
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
public class IndicatorService {
    // 注入
    @Autowired
    private IndicatorRepository indicatorRepository;

    /**
     * 新增
     *
     * @param indicator
     * @return
     */
    public Indicator save(Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        indicatorRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Indicator findOne(String code) {
        return indicatorRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Indicator> findAll() {
        return indicatorRepository.findAll();
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
    public Page<Indicator> getIndicatorByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Indicator.class.getDeclaredField(sortFieldName);
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
        return indicatorRepository.findAll(pageable);
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
    public Page<Indicator> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Indicator.class.getDeclaredField(sortFieldName);
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

        return indicatorRepository.findByNameLike("%" + name + "%", pageable);
    }

    /**
     * 批量删除
     *
     * @param indicators
     */
    public void deleteInBatch(Set<Indicator> indicators) {
        indicatorRepository.deleteInBatch(indicators);
    }
}
