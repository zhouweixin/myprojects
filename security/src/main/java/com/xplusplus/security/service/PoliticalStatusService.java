package com.xplusplus.security.service;

import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.PoliticalStatusRepository;
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
 * @Description: 政治面貌Service
 * @Date: Created in 12:38 2018/5/22
 * @Modified By:
 */
@Service
public class PoliticalStatusService {
    @Autowired
    private PoliticalStatusRepository politicalStatusRepository;
    /**
     * 新增
     */
    public PoliticalStatus save(PoliticalStatus politicalStatus){
        if (politicalStatus == null || (politicalStatus.getId() != null && politicalStatusRepository.findOne(politicalStatus.getId()) != null)) {
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return politicalStatusRepository.save(politicalStatus);
    }
    /**
     * 更新
     */
    public PoliticalStatus update(PoliticalStatus politicalStatus) {

        // 验证是否存在
        if (politicalStatus == null || politicalStatus.getId() == null || politicalStatusRepository.findOne(politicalStatus.getId()) == null) {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return politicalStatusRepository.save(politicalStatus);
    }

    /**
     * 删除
     */
    public void delete(Integer id) {

        // 验证是否存在
        if (politicalStatusRepository.findOne(id) == null) {
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        politicalStatusRepository.delete(id);
    }

    /**
     * 批量删除
     */
    public void deleteInBatch(Collection<PoliticalStatus> politicalStatuss) {
        politicalStatusRepository.deleteInBatch(politicalStatuss);
    }

    /**
     * 通过编码查询
     */
    public PoliticalStatus findOne(Integer id) {
        return politicalStatusRepository.findOne(id);
    }

    /**
     * 查询所有
     */
    public List<PoliticalStatus> findAll() {
        return politicalStatusRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<PoliticalStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            PoliticalStatus.class.getDeclaredField(sortFieldName);
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
        return politicalStatusRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     */
    public Page<PoliticalStatus> findByNameLikeByPage(String name, Integer page, Integer size, String sortFieldName,
                                             Integer asc) {

        // 判断排序字段名是否存在
        try {
            PoliticalStatus.class.getDeclaredField(sortFieldName);
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
        return politicalStatusRepository.findByNameLike("%" + name + "%", pageable);
    }
}
