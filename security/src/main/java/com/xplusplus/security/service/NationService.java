package com.xplusplus.security.service;

import com.xplusplus.security.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:37 2018/5/22
 * @Modified By:
 */
@Service
public class NationService {
    @Autowired
    private NationRepository nationRepository;


}
