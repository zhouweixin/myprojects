package com.hnu.mes.controller;

import com.hnu.mes.domain.RealData;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.FileService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lanyage on 2018/3/28.
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private FileService fileService;
    @GetMapping("/loadData")
    public Result loadData() {
        System.out.println("--------------------------");
        System.out.println("start!!!!!");
        System.out.println("--------------------------");
        List<RealData> list;
        try {
            list = fileService.getData();
        }catch (RuntimeException e) {
            return ResultUtil.error(e.getMessage());
        }
       return ResultUtil.success(list);
    }
}
