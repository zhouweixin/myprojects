package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import com.hnu.mes.domain.Equipment;
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

import com.hnu.mes.domain.Archive;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ArchiveRepository;

/**
 *
 * @author pingxiao
 *
 */
@Service
public class ArchiveService {
    // 注入
    @Autowired
    private ArchiveRepository archiveRepository;

    /**
     * 新增
     *
     * @param archive
     * @return
     */
    public Archive save(Archive archive) {
        return archiveRepository.save(archive);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Long code) {
        archiveRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Archive findOne(Long code) {
        return archiveRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Archive> findAll() {
        return archiveRepository.findAll();
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
    public Page<Archive> getArchiveByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Archive.class.getDeclaredField(sortFieldName);
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
        return archiveRepository.findAll(pageable);
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
    public Page<Archive> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                 Integer asc) {

        try {
            Archive.class.getDeclaredField(sortFieldName);
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
                .withIgnorePaths("code", "installTime", "equipmentName", "defectPeriod", "supplyFactory", "supplyContact", "repairFactory", "repairContact", "document");
        Archive archive = new Archive();
        archive.setName(name);

        // 创建实例
        Example<Archive> example = Example.of(archive, matcher);

        // 查询
        return archiveRepository.findAll(example, pageable);
    }

    /**
     * 批量删除
     *
     * @param archives
     */
    public void deleteInBatch(Set<Archive> archives) {
        archiveRepository.deleteInBatch(archives);
    }
}
