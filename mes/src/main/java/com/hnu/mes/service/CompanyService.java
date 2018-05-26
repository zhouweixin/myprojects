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

import com.hnu.mes.domain.Company;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.CompanyRepository;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class CompanyService {
    // 注入
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * 新增
     *
     * @param company
     * @return
     */
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        companyRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Company findOne(String code) {
        return companyRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Company> findAll() {
        return companyRepository.findAll();
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
    public Page<Company> getCompanyByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Company.class.getDeclaredField(sortFieldName);
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
        return companyRepository.findAll(pageable);
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
    public Page<Company> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Company.class.getDeclaredField(sortFieldName);
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
        Company company = new Company();
        company.setName(name);

        // 创建实例
        Example<Company> example = Example.of(company, matcher);

        // 查询
        return companyRepository.findAll(example, pageable);
    }

    public void deleteInBatch(Set<Company> companies) {
        companyRepository.deleteInBatch(companies);
    }
}
