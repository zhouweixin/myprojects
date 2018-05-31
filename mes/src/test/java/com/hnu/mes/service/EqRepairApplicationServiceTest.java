package com.hnu.mes.service;

import com.hnu.mes.repository.EqRepairApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lanyage on 2018/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqRepairApplicationServiceTest {
    @Autowired
    private EqRepairApplicationService service;
    @Autowired
    private EqRepairApplicationRepository eqRepairApplicationRepository;
    @Test
    public void addOne() throws Exception {
//        EqRepairApplication eqRepairApplication  = service.findByCode("4");
//        eqRepairApplication.setCode("5");
//        EqRepairApplication application = new EqRepairApplication();
//        application.setCode("5");
//        service.addOne(application);
    }

    @Test
    public void deleteOne() throws Exception {
//        EqRepairApplication one = new EqRepairApplication();
//        one.setCode("5");
//        service.deleteOne(one);
    }

    @Test
    public void deleteInBatch() throws Exception {
//        List<EqRepairApplication> list = new ArrayList<>();
//        EqRepairApplication one = new EqRepairApplication();
//        one.setCode("3");
//        EqRepairApplication two = new EqRepairApplication();
//        two.setCode("4");
//        list.add(one);
//        list.add(two);
//        service.deleteInBatch(list);
    }

    @Test
    public void findByCode() throws Exception {
//       EqRepairApplication one =  service.findByCode("2");
//       System.out.println(one);
    }

    @Test
    public void update() throws Exception {
//        EqRepairApplication application  = service.findByCode("4");
//        application.setDescription("修改了2");
//        service.update("4", application);
    }

}