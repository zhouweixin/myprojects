package com.hnu.mes.service;

import com.hnu.mes.domain.Manufacturer;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ManufacturerRepository;
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
 * @Date: Created in 11:39 2018/4/5
 * @Modified By:
 */
@Service
public class ManufacturerService {
    // 注入
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    /**
     * 新增
     *
     * @param manufacturer
     * @return
     */
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        manufacturerRepository.delete(code);
    }

    /**
     * 批量删除
     *
     * @param manufacturers
     */
    public void deleteInBatch(Set<Manufacturer> manufacturers){
        manufacturerRepository.deleteInBatch(manufacturers);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Manufacturer findOne(String code) {
        return manufacturerRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Manufacturer> findAll(List<String> codes) {
        return manufacturerRepository.findAll(codes);
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
    public Page<Manufacturer> getManufacturerByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Manufacturer.class.getDeclaredField(sortFieldName);
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
        return manufacturerRepository.findAll(pageable);
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
    public Page<Manufacturer> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Manufacturer.class.getDeclaredField(sortFieldName);
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

        return manufacturerRepository.findByNameLike("%" + name + "%", pageable);
    }
}
