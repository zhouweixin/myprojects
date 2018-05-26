package com.hnu.mes.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.hnu.mes.domain.Department;
import com.hnu.mes.repository.DefaultPasswordRepository;
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

import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.UserRepository;

/**
 * @author zhouweixin
 */
@Service
public class UserService {
    // 注入
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultPasswordRepository defaultPasswordRepository;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Transactional
    public void updateUser(User user) {
        userRepository.updateUserNameAndContactAndDescription(user.getName(), user.getContact(), user.getDescription(),
                user.getCode());
    }

    /**
     * 更新启用
     *
     * @param code
     * @param enable
     */
    @Transactional
    public void updateUserEnable(String code, Integer enable) {
        userRepository.updateUserEnable(enable, code);
    }

    /**
     * 更新部门编码
     *
     * @param code
     * @param departmentCode
     */
    @Transactional
    public void updateUserDepartmentCode(String code, String departmentCode) {
        userRepository.updateUserDepartmentCode(departmentCode, code);
    }

    /**
     * 更新IC卡
     *
     * @param code
     * @param inteCircCard
     * @param enableIc
     */
    @Transactional
    public void updateUserInteCircCard(String code, String inteCircCard, Integer enableIc) {
        userRepository.updateUserInteCircCard(inteCircCard, enableIc, code);
    }

    /**
     * 重置密码
     *
     * @param user
     */
    @Transactional
    public void updateUserPassword(User user) {
        userRepository.updateUserPassword(user.getPassword(), user.getCode());
    }

    /**
     * 更新密码
     *
     * @param code
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @Transactional
    public Integer updateUserPassword(String code, String newPassword, String oldPassword) {
        return userRepository.updateUserPassword(newPassword, code, oldPassword);
    }

    /**
     * 登录
     *
     * @param code
     * @param password
     * @return
     */
    public User findByCodeAndPassword(String code, String password) {
        return userRepository.findByCodeAndPassword(code, password);
    }

    /**
     * 通过IC卡登录
     *
     * @param inteCircCard
     * @return
     */
    @Transactional
    public User findByInteCircCard(String inteCircCard) {
        return userRepository.findByInteCircCard(inteCircCard);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(String code) {
        userRepository.delete(code);
    }

    /**
     * 查询
     *
     * @param code
     * @return
     */
    public User findOne(String code) {
        return userRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
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
    public Page<User> getUserByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        try {
            User.class.getDeclaredField(sortFieldName);
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
        return userRepository.findAll(pageable);
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
    public Page<User> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName,
                                              Integer asc) {

        try {
            User.class.getDeclaredField(sortFieldName);
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

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findUserByNameLike("%" + name + "%", pageable);
    }

    /**
     * 批量删除
     *
     * @param users
     */
    public void deleteInBatch(Set<User> users) {
        userRepository.deleteInBatch(users);
    }

    public List<User> findUsersByDepartment(Department department) {
        return userRepository.findUsersByDepartment(department);
    }

    /**
     * 通过部门编码查询-分页
     *
     * @param department    名称
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序或减序
     * @return
     */
    public Page<User> findUsersByDepartment(Department department, Integer page, Integer size, String sortFieldName,
                                            Integer asc) {

        try {
            User.class.getDeclaredField(sortFieldName);
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

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findUsersByDepartment(department, pageable);
    }

    /**
     * 重置所有用户的密码
     *
     * @param defaultPassword
     */
    @Transactional
    public void updateAllDefaultPassword(String defaultPassword) {
        userRepository.updateAllDefaultPassword(defaultPassword);
    }
}
