package com.hnu.mes.service;

import com.hnu.mes.domain.SupplierToInfo;
import com.hnu.mes.domain.SupplyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lanyage on 2018/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplyInfoServiceTest {
    @Autowired
    private SupplyInfoService supplyInfoService;
    @Test
    public void findBySupplier() throws Exception {
//        Page<SupplyInfo> page = supplyInfoService.findBySupplier("湖南小米公司",0,1,"code",1);
    }

}