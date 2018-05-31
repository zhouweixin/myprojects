package com.hnu.mes.service;

import com.hnu.mes.domain.PickingApplyHeader;
import com.hnu.mes.repository.PickingApplyHeaderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:46 2018/5/23
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PickingApplyHeaderServiceTest {

    @Autowired
    private PickingApplyHeaderRepository pickingApplyHeaderRepository;

    @Test
    public void findOne() {
        PickingApplyHeader one = pickingApplyHeaderRepository.findOne(1L);
        System.out.println(one);
    }
}