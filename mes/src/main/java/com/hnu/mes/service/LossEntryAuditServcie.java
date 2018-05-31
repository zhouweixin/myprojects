package com.hnu.mes.service;

import com.hnu.mes.repository.LossEntryHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:43 2018/5/30
 * @Modified By:
 */
@Service
public class LossEntryAuditServcie {
    @Autowired
    private LossEntryHeaderRepository lossEntryHeaderRepository;
}
