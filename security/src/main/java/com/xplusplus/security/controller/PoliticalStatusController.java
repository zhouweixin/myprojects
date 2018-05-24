package com.xplusplus.security.controller;

import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.service.PoliticalStatusService;
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
@RequestMapping(value = "/politicalStatus")
public class PoliticalStatusController {
    @Autowired
    private PoliticalStatusService politicalStatusService;
}
