package com.hnu.mes.service;

import java.util.Date;
import java.util.List;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.AppCheckHead;
import com.hnu.mes.domain.AppCheckHead;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.repository.StatusRepository;
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

import com.hnu.mes.domain.AppCheckHead;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.AppCheckHeadRepository;

import javax.transaction.Transactional;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class AppCheckHeadService {

    // 注入
    @Autowired
    private AppCheckHeadRepository appCheckHeadRepository;


    /**
     * 新增
     *
     * @param appCheckHead
     * @return
     */
    public AppCheckHead save(AppCheckHead appCheckHead) {
        return appCheckHeadRepository.save(appCheckHead);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        appCheckHeadRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public AppCheckHead findOne(String code) {
        return appCheckHeadRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<AppCheckHead> findAll() {
        return appCheckHeadRepository.findAll();
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
    public Page<AppCheckHead> getAppCheckHeadByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            AppCheckHead.class.getDeclaredField(sortFieldName);
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
        return appCheckHeadRepository.findAll(pageable);
    }

    
    
}
