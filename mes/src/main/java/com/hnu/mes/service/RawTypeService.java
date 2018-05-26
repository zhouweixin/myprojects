package com.hnu.mes.service;

import com.hnu.mes.domain.Material;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.RawTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 8:57 2018/5/2
 * @Modified By:
 */
@Service
public class RawTypeService {
    // 注入
    @Autowired
    private RawTypeRepository rawTypeRepository;

    /**
     * 新增
     *
     * @param rawType
     * @return
     */
    public RawType save(RawType rawType) {
        return rawTypeRepository.save(rawType);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Long code) {
        rawTypeRepository.delete(code);
    }

    /**
     * 批量删除
     *
     * @param rawTypes
     */
    public void deleteInBatch(Set<RawType> rawTypes){
        rawTypeRepository.deleteInBatch(rawTypes);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public RawType findOne(Long code) {
        return rawTypeRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<RawType> findAll() {
        return rawTypeRepository.findAll();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<RawType> findAll(List<Long> codes) {
        return rawTypeRepository.findAll(codes);
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
    public Page<RawType> getRawTypeByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            RawType.class.getDeclaredField(sortFieldName);
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
        return rawTypeRepository.findAll(pageable);
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
    public Page<RawType> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            RawType.class.getDeclaredField(sortFieldName);
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

        return rawTypeRepository.findByNameLike("%" + name + "%", pageable);
    }

    /**
     * 通过原料类型查询
     *
     * @param material
     * @return
     */
    public List<RawType> findByMaterial(Material material){
        return rawTypeRepository.findByMaterial(material);
    }
}
