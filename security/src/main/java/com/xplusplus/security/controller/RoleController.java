package com.xplusplus.security.controller;

import com.xplusplus.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:45 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    private RoleService roleService;
}
