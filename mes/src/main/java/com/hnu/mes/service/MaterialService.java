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

import com.hnu.mes.domain.Material;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.MaterialRepository;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class MaterialService {
    // 注入
    @Autowired
    private MaterialRepository materialRepository;

    /**
     * 新增
     *
     * @param material
     * @return
     */
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        materialRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Material findOne(Integer code) {
        return materialRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Material> findAll() {
        return materialRepository.findAll();
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
    public Page<Material> getMaterialByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Material.class.getDeclaredField(sortFieldName);
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
        return materialRepository.findAll(pageable);
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
    public Page<Material> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Material.class.getDeclaredField(sortFieldName);
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
                .withIgnorePaths("code", "info");
        Material material = new Material();
        material.setName(name);

        // 创建实例
        Example<Material> example = Example.of(material, matcher);

        // 查询
        return materialRepository.findAll(example, pageable);
    }

    public void deleteInBatch(Set<Material> materials) {
        materialRepository.deleteInBatch(materials);
    }
}
