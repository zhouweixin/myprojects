package com.hnu.mes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hnu.mes.domain.Department;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.DepartmentRepository;

/**
 * @author zhouweixin
 */
@Service
public class DepartmentService {
    // 注入
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 新增
     *
     * @param department
     * @return
     */
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        departmentRepository.delete(code);
    }

    /**
     * 批量删除
     *
     * @param departments
     */
    public void deleteInBatch(Set<Department> departments){
        departmentRepository.deleteInBatch(departments);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Department findOne(String code) {
        return departmentRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Department> findAll(List<String> codes) {
        return departmentRepository.findAll(codes);
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
    public Page<Department> getDepartmentByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Department.class.getDeclaredField(sortFieldName);
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
        return departmentRepository.findAll(pageable);
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
    public Page<Department> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Department.class.getDeclaredField(sortFieldName);
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

        return departmentRepository.findByNameLike("%" + name + "%", pageable);
    }
}
