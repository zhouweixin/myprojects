package com.hnu.mes.service;

import com.hnu.mes.domain.Duty;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.DutyRepository;
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
public class DutyService {
    // 注入
    @Autowired
    private DutyRepository dutyRepository;

    /**
     * 新增
     *
     * @param duty
     * @return
     */
    public Duty save(Duty duty) {
        return dutyRepository.save(duty);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        dutyRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Duty findOne(Integer code) {
        return dutyRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Duty> findAll() {
        return dutyRepository.findAll();
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
    public Page<Duty> getDutyByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Duty.class.getDeclaredField(sortFieldName);
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
        return dutyRepository.findAll(pageable);
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
    public Page<Duty> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Duty.class.getDeclaredField(sortFieldName);
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

        return dutyRepository.findByNameLike("%" + name + "%", pageable);
    }

    /**
     * 批量删除
     * @param duties
     */
    public void deleteInBatch(Set<Duty> duties) {
        dutyRepository.deleteInBatch(duties);
    }
}
