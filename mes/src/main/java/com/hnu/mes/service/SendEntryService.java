package com.hnu.mes.service;

import com.hnu.mes.domain.SendEntry;
import com.hnu.mes.repository.SendEntryHeaderRepository;
import com.hnu.mes.repository.SendEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:14 2018/5/1
 * @Modified By:
 */
@Service
public class SendEntryService {

    @Autowired
    private SendEntryHeaderRepository sendEntryHeaderRepository;

    @Autowired
    private SendEntryRepository sendEntryRepository;

    public SendEntry save(SendEntry sendEntry){
        return sendEntryRepository.save(sendEntry);
    }


}
