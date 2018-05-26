package com.hnu.mes.service;

import com.hnu.mes.domain.Status;
import com.hnu.mes.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouweixin on 2018/3/22.
 */
@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    /**
     * 新增
     *
     * @param status
     * @return
     */
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        statusRepository.delete(code);
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public Status findOne(Integer code) {
        return statusRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    /**
     * 更新
     *
     * @param status
     * @return
     */
    public Status update(Status status) {
        return statusRepository.save(status);
    }
}
