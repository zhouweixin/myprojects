package com.xplusplus.security.controller;

import com.xplusplus.security.domain.PeriodUnit;
import com.xplusplus.security.service.PeriodUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:44 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "periodUnit")
public class PeriodUnitController {
    @Autowired
    private PeriodUnitService periodUnitService;
}
