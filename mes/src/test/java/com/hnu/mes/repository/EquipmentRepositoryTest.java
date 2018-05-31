package com.hnu.mes.repository;

import com.hnu.mes.domain.Equipment;
import com.hnu.mes.domain.Flag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lanyage on 2018/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentRepositoryTest {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    public void testFindByName() {
//        String name = "流体管";
//        Equipment equipment = equipmentRepository.findByName(name);
    }
}