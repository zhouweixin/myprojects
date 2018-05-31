package com.hnu.mes.repository;

import com.hnu.mes.domain.SupplierToInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lanyage on 2018/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierToInfoDaoTest {
    @Autowired
    private SupplierToInfoDao supplierToInfoDao;
    @Test
    public void findBySupplierCode() throws Exception {
        List<SupplierToInfo> supplierToInfos = supplierToInfoDao.findBySupplierCode("001");
    }

}