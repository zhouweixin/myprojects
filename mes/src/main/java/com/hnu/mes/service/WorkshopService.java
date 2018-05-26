package com.hnu.mes.service;

import com.hnu.mes.domain.Workshop;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/17
 * @Description:
 */
@Service
public class WorkshopService {
    @Autowired
    private WorkshopRepository workshopRepository;

    public Workshop save(Workshop workshop) {
        /**
         * save
         * @Desciption 新增
         * @param [workshop]
         */
        return workshopRepository.save(workshop);
    }

    public void delete(String code) {
        /**
         * delete
         * @Desciption 删除
         * @param [code]
         */
        workshopRepository.delete(code);
    }

    public Workshop findOne(String code) {
        /**
         * findOne
         * @Desciption 查询
         * @param [code]
         */
        return workshopRepository.findOne(code);
    }

    public List<Workshop> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return workshopRepository.findAll();
    }

    public Page<Workshop> getWorkshopByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * getWorkshopByPage
         * @Desciption
         * @param [page, size, sortFieldName, asc]
         * @throws Exception
         */

        try {
            Workshop.class.getDeclaredField(sortFieldName);
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
        return workshopRepository.findAll(pageable);
    }

    public Page<Workshop> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            Workshop.class.getDeclaredField(sortFieldName);
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
        return workshopRepository.findAllByNameLike("%" + name + "%", pageable);
    }

    public void deleteInBatch(Set<Workshop> workshops) {
        workshopRepository.deleteInBatch(workshops);
    }
}
