package com.xplusplus.security.controller;

import com.xplusplus.security.domain.MaritalStatus;
import com.xplusplus.security.service.MaritalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:42 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "maritalStatus")
public class MaritalStatusController {
    @Autowired
    private MaritalStatusService maritalStatusService;
}
