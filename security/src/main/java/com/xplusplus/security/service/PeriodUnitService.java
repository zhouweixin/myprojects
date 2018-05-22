package com.xplusplus.security.service;

import com.xplusplus.security.domain.PeriodUnit;
import com.xplusplus.security.repository.PeriodUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:37 2018/5/22
 * @Modified By:
 */
@Service
public class PeriodUnitService {
    @Autowired
    private PeriodUnitRepository periodUnitRepository;

}
