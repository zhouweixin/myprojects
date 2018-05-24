package com.xplusplus.security.service;

import com.xplusplus.security.domain.MilitaryStatus;
import com.xplusplus.security.domain.MilitaryStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.MaritalStatusRepository;
import com.xplusplus.security.repository.MilitaryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Huxudong
 * @Description: 兵役状况Service
 * @Date: Created in 12:36 2018/5/22
 * @Modified By:
 */
@Service
public class MilitaryStatusService {
    @Autowired
    private MilitaryStatusRepository militaryStatusRepository;
    /**
     * 新增
     */
    public MilitaryStatus save(MilitaryStatus militaryStatus) {

        if (militaryStatus == null || (militaryStatus.getId() != null && militaryStatusRepository.findOne(militaryStatus.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return militaryStatusRepository.save(militaryStatus);
    }

    /**
     * 更新
     */
    public MilitaryStatus update(MilitaryStatus militaryStatus) {

        // 验证是否存在
        if (militaryStatus == null || militaryStatus.getId() == null || militaryStatusRepository.findOne(militaryStatus.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return militaryStatusRepository.save(militaryStatus);
    }

    /**
     * 删除
     */
    public void delete(Integer id) {

        // 验证是否存在
        if (militaryStatusRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        militaryStatusRepository.delete(id);
    }

    /**
     * 批量删除
     */
    public void deleteInBatch(Collection<MilitaryStatus> militaryStatuss) {
        militaryStatusRepository.deleteInBatch(militaryStatuss);
    }

    /**
     * 通过编码查询
     */
    public MilitaryStatus findOne(Integer id) {
        return militaryStatusRepository.findOne(id);
    }

    /**
     * 查询所有
     */
    public List<MilitaryStatus> findAll() {
        return militaryStatusRepository.findAll();
    }

    /**
     * 查询所有-分页
     *
     */
    public Page<MilitaryStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            MilitaryStatus.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return militaryStatusRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     */
    public Page<MilitaryStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
                                             Integer asc) {

        // 判断排序字段名是否存在
        try {
            MilitaryStatus.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return militaryStatusRepository.findByNameLike("%" + name + "%", pageable);
    }
}