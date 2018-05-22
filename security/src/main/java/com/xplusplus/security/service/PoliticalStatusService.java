package com.xplusplus.security.service;

import com.xplusplus.security.domain.PoliticalStatus;
import com.xplusplus.security.repository.PoliticalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:38 2018/5/22
 * @Modified By:
 */
@Service
public class PoliticalStatusService {
    @Autowired
    private PoliticalStatusRepository politicalStatusRepository;


}
