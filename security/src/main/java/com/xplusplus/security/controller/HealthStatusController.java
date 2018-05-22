package com.xplusplus.security.controller;

import com.xplusplus.security.domain.HealthStatus;
import com.xplusplus.security.service.HealthStatusService;
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
@RequestMapping(value = "healthStatus")
public class HealthStatusController {
    @Autowired
    private HealthStatusService healthStatusService;
}
