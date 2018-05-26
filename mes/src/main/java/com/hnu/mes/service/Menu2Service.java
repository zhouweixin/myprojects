package com.hnu.mes.service;

import java.util.List;

import com.hnu.mes.domain.Menu1;
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

import com.hnu.mes.domain.Menu2;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.Menu2Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class Menu2Service {
    // 注入
    @Autowired
    private Menu2Repository menu2Repository;

    /**
     * 新增
     *
     * @param menu2
     * @return
     */
    public Menu2 save(Menu2 menu2) {
        return menu2Repository.save(menu2);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        menu2Repository.delete(code);
    }

    /**
     * 通过编码更新名称和路径
     * @param name
     * @param code
     */
    @Transactional
    public void updateNameAndPathByCode(String name, Integer code){
        menu2Repository.updateNameByCode(name, code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Menu2 findOne(Integer code) {
        return menu2Repository.findOne(code);
    }

    /**
     * 根据menu1查询menu2
     * @param menu1
     * @return
     */
    public Menu2 findByMenu1(Menu1 menu1){
        return menu2Repository.findByMenu1(menu1);
    }
    /**
     * 查询所有
     *
     * @return
     */
    public List<Menu2> findAll() {
        return menu2Repository.findAll();
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
    public Page<Menu2> getMenu2ByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Menu2.class.getDeclaredField(sortFieldName);
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
        return menu2Repository.findAll(pageable);
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
    public Page<Menu2> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                               Integer asc) {

        try {
            Menu2.class.getDeclaredField(sortFieldName);
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
        Menu2 menu2 = new Menu2();
        menu2.setName(name);

        // 创建实例
        Example<Menu2> example = Example.of(menu2, matcher);

        // 查询
        return menu2Repository.findAll(example, pageable);
    }

    /**
     * 查询最大编码
     *
     * @return
     */
    public Integer findMaxCode() {
        return menu2Repository.findMaxCode();
    }

    /**
     * 查询最大排序
     *
     * @return
     */
    public Integer findMaxRank() {
        return menu2Repository.findMaxRank();
    }
}
