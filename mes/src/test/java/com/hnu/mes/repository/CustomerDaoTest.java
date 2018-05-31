package com.hnu.mes.repository;

import com.hnu.mes.domain.Customer;
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
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne("1");
    }
}