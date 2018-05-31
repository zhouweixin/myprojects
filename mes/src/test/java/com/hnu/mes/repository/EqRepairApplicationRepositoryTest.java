package com.hnu.mes.repository;

import com.hnu.mes.domain.EqRepairApplication;
import com.hnu.mes.domain.Equipment;
import com.hnu.mes.domain.Flag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lanyage on 2018/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqRepairApplicationRepositoryTest {

    @Test
    public void findByFlagCode() throws Exception {

    }

    @Autowired
    private EqRepairApplicationRepository repository;

    @Test
    public void findOne() {
        EqRepairApplication application = repository.findOne("1");
    }

    @Test
    public void findByFlag() {
//        Flag flag = new Flag();
//        flag.setCode(1000);
//        Sort sort = new Sort(Sort.Direction.DESC, "code");
//        Pageable pageable = new PageRequest(0, 3, sort);
//        Page<EqRepairApplication> eqRepairApplications = repository.findByFlag(flag, pageable);
//        Assert.assertEquals(3, eqRepairApplications.getSize());
    }

    @Test
    public void findByApplicationTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse("1993-07-24");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Sort sort = new Sort(Sort.Direction.DESC, "code");
//        Pageable pageable = new PageRequest(0, 3, sort);
//        Page<EqRepairApplication> page = repository.findByApplicationTime(date, pageable);
    }

    @Test
    public void findByFlagAndApplication() {
//        Flag flag = new Flag();
//        flag.setCode(1);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse("1993-07-25");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Sort sort = new Sort(Sort.Direction.ASC, "code");
//        Pageable pageable = new PageRequest(0, 3, sort);
//        Page<EqRepairApplication> page = repository.findByFlagAndApplicationTime(flag, date, pageable);
    }

    @Test
    public void findByEquipment() {
        Equipment equipment = new Equipment();
        equipment.setCode("003");
        Sort sort = new Sort(Sort.Direction.DESC, "code");
        Pageable pageable = new PageRequest(0, 3, sort);
        Page<EqRepairApplication> page = repository.findByEquipment(equipment, pageable);
    }
}