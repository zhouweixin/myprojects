package com.xplusplus.security.controller;

import com.xplusplus.security.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:41 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "education")
public class EducationController {
    @Autowired
    private EducationService educationService;
}
