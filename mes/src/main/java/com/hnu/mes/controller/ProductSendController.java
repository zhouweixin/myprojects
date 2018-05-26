package com.hnu.mes.controller;

import com.hnu.mes.service.ProductSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:18 2018/5/17
 * @Modified By:
 */
@RestController
@RequestMapping(name = "/productSend")
public class ProductSendController {
    @Autowired
    private ProductSendService productSendService;
}
