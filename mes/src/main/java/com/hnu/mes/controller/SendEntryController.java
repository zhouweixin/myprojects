package com.hnu.mes.controller;

import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.SendEntry;
import com.hnu.mes.service.SendEntryService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:50 2018/5/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/sendEntry")
public class SendEntryController {
    @Autowired
    private SendEntryService sendEntryService;

    @PostMapping(value = "/add")
    public Result<SendEntry> add(@RequestBody SendEntry sendEntry) {
        System.out.println(sendEntry);
        return ResultUtil.success(sendEntryService.save(sendEntry));
    }
}
