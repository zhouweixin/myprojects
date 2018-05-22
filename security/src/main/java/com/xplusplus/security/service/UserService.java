package com.xplusplus.security.service;

import com.xplusplus.security.domain.User;
import com.xplusplus.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:35 2018/5/7
 * @Modified By:
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * 通过工号查询
     *
     * @param id
     * @return
     */
    public User findOne(String id){
        return userRepository.findOne(id);
    }

    /**
     * 查询最大的id
     *
     * @return
     */
    public String findMaxId(){
        return userRepository.findMaxId();
    }

    /**
     * 通过id删除
     * @param id
     */
    public void delete(String id){
        userRepository.delete(id);
    }
}
