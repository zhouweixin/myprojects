package com.hnu.mes.service;

import com.hnu.mes.domain.Evaluation;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.EvaluationRepository;
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
public class EvaluationService {
    // 注入
    @Autowired
    private EvaluationRepository evaluationRepository;

    /**
     * 新增
     *
     * @param evaluation
     * @return
     */
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        evaluationRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Evaluation findOne(Integer code) {
        return evaluationRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
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
    public Page<Evaluation> getEvaluationByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Evaluation.class.getDeclaredField(sortFieldName);
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
        return evaluationRepository.findAll(pageable);
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
    public Page<Evaluation> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Evaluation.class.getDeclaredField(sortFieldName);
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

        return evaluationRepository.findByNameLike("%" + name + "%", pageable);
    }

    /**
     * 批量删除
     * @param evaluations
     */
    public void deleteInBatch(Set<Evaluation> evaluations) {
        evaluationRepository.deleteInBatch(evaluations);
    }
}
