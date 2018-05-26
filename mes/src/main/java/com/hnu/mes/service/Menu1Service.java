package com.hnu.mes.service;

import java.util.List;
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

import com.hnu.mes.domain.Menu1;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.Menu1Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhouweixin
 *
 */
@Service
public class Menu1Service {
    // 注入
    @Autowired
    private Menu1Repository menu1Repository;

    /**
     * 新增
     *
     * @param menu1
     * @return
     */
    public Menu1 save(Menu1 menu1) {
        return menu1Repository.save(menu1);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        menu1Repository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Menu1 findOne(Integer code) {
        return menu1Repository.findOne(code);
    }

    /**
     * 通过编码更新名称和路径
     * @param name
     * @param path
     * @param code
     */
    @Transactional
    public void updateNameAndPathByCode(String name, String path, Integer code){
        menu1Repository.updateNameAndPathByCode(name, path, code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Menu1> findAll() {
        return menu1Repository.findAll();
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
    public Page<Menu1> getMenu1ByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            Menu1.class.getDeclaredField(sortFieldName);
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
        return menu1Repository.findAll(pageable);
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
    public Page<Menu1> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                                    Integer asc) {

        try {
            Menu1.class.getDeclaredField(sortFieldName);
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
        Menu1 menu1 = new Menu1();
        menu1.setName(name);

        // 创建实例
        Example<Menu1> example = Example.of(menu1, matcher);

        // 查询
        return menu1Repository.findAll(example, pageable);
    }

    /**
     * 查询最大编码
     *
     * @return
     */
    public Integer findMaxCode() {
        return menu1Repository.findMaxCode();
    }

    /**
     * 查询最大排序
     * @return
     */
    public Integer findMaxRank() {
        return menu1Repository.findMaxRank();
    }
}
