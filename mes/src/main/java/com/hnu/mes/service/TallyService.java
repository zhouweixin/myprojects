package com.hnu.mes.service;

import java.util.List;

import com.hnu.mes.domain.Archive;
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

import com.hnu.mes.domain.Tally;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.TallyRepository;

/**
 *
 * @author pingxiao
 *
 */
@Service
public class TallyService {
    // 注入
    @Autowired
    private TallyRepository tallyRepository;

    /**
     * 新增
     *
     * @param tally
     * @return
     */
    public Tally save(Tally tally) {
        return tallyRepository.save(tally);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        tallyRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Tally findOne(String code) {
        return tallyRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Tally> findAll() {
        return tallyRepository.findAll();
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
    public Page<Tally> getTallyByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Tally.class.getDeclaredField(sortFieldName);
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
        return tallyRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊查询
     *
     * @param archivecode
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
    public Page<Tally> findAllByLikeNameByPage(String archivecode, Integer page, Integer size, String sortFieldName,
                                                 Integer asc) {

        try {
            Tally.class.getDeclaredField(sortFieldName);
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
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("archivecode", GenericPropertyMatchers.contains())
                .withIgnorePaths("code", "date", "guideCode", "num", "inspectorCode", "inspectorStatus", "verificationCode", "verificationStatus", "abnormalCode", "verificationTime");
        Tally tally = new Tally();
        tally.setArchivecode(archivecode);

        // 创建实例
        Example<Tally> example = Example.of(tally, matcher);

        // 查询
        return tallyRepository.findAll(example, pageable);
    }
}
