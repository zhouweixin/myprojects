package com.hnu.mes.service;

import com.hnu.mes.repository.ProductSendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:16 2018/5/17
 * @Modified By:
 */
@Service
public class ProductSendService {
    @Autowired
    private ProductSendRepository productSendRepository;
}
