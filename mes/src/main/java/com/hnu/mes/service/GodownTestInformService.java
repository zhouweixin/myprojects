package com.hnu.mes.service;

import com.hnu.mes.repository.GodownTestInformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:03 2018/5/2
 * @Modified By:
 */
@Service
public class GodownTestInformService {
    @Autowired
    private GodownTestInformRepository godownTestInformRepository;
}
