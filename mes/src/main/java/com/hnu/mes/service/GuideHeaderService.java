package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import com.hnu.mes.exception.EnumException;
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

import com.hnu.mes.domain.GuideHeader;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.GuideHeaderRepository;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class GuideHeaderService {
    // 注入
    @Autowired
    private GuideHeaderRepository guideHeaderRepository;

    /**
     * 新增
     *
     * @param guideHeader
     * @return
     */
    public GuideHeader save(GuideHeader guideHeader) {
        return guideHeaderRepository.save(guideHeader);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        guideHeaderRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public GuideHeader findOne(String code) {
        return guideHeaderRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<GuideHeader> findAll() {
        return guideHeaderRepository.findAll();
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
    public Page<GuideHeader> getGuideHeaderByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            GuideHeader.class.getDeclaredField(sortFieldName);
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
        return guideHeaderRepository.findAll(pageable);
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
    public Page<GuideHeader> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            GuideHeader.class.getDeclaredField(sortFieldName);
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
                .withIgnorePaths("code", "num", "edition", "effectivedate", "archivecode", "compactorcode", "auditorcode", "approvercode");
        GuideHeader guideHeader = new GuideHeader();
        guideHeader.setName(name);

        // 创建实例
        Example<GuideHeader> example = Example.of(guideHeader, matcher);

        // 查询
        return guideHeaderRepository.findAll(example, pageable);
    }

    public void deleteInBatch(Set<GuideHeader> guideHeaders) {
        guideHeaderRepository.deleteInBatch(guideHeaders);
    }
}
