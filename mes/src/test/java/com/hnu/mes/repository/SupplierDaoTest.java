package com.hnu.mes.repository;

import com.hnu.mes.domain.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by lanyage on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierDaoTest {
    @Autowired
    private SupplierDao supplierDao;

    @Test
    public void testFindOne() {

       Supplier supplier = supplierDao.findOne("001");
    }
    @Test
    public void findByName() throws Exception {
        Supplier supplier = supplierDao.findByName("湖南小米公司");
    }
}