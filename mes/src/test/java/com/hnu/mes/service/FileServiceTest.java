package com.hnu.mes.service;

import com.hnu.mes.domain.RealData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lanyage on 2018/4/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void getData() throws Exception {
        List<RealData> list = fileService.getData();
        System.out.println(list.get(0));
    }
}