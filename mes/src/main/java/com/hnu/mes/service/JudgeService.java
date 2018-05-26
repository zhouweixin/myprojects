package com.hnu.mes.service;

import com.hnu.mes.domain.Judge;
import com.hnu.mes.repository.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouweixin on 2018/3/20.
 */
@Service
public class JudgeService {
    @Autowired
    private JudgeRepository judgeRepository;

    /**
     * 新增
     *
     * @param judge
     * @return
     */
    public Judge save(Judge judge) {
        return judgeRepository.save(judge);
    }

    /**
     * 删除
     *
     * @param code
     */
    public void delete(Integer code) {
        judgeRepository.delete(code);
    }


    /**
     * 查询
     *
     * @param code
     * @return
     */
    public Judge findOne(Integer code) {
        return judgeRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Judge> findAll() {
        return judgeRepository.findAll();
    }
}
