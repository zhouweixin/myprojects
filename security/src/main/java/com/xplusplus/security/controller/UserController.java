package com.xplusplus.security.controller;

import com.xplusplus.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:37 2018/5/7
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    
}
