package com.xplusplus.security.service;

import com.xplusplus.security.repository.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:36 2018/5/22
 * @Modified By:
 */
@Service
public class MilitaryStatusService {
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
}
