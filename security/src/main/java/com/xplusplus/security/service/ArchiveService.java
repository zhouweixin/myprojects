package com.xplusplus.security.service;

import com.xplusplus.security.domain.Archive;
import com.xplusplus.security.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 12:33 2018/5/22
 * @Modified By:
 */
@Service
public class ArchiveService {
    @Autowired
    private ArchiveRepository archiveRepository;


}
