package com.hnu.mes.controller;

import com.hnu.mes.domain.EnergyRealData;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.EnergyRealDataService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:44 2018/4/15
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/energyReadData")
public class EnergyRealDataController {
    @Autowired
    private EnergyRealDataService energyRealDataService;

    /**
     * 通过ino和当前日期查询之前24小时的内容
     *
     * @param ino
     * @param curDateTime
     * @return
     */
    @RequestMapping(value = "/getByInoAndCurDateTime")
    public Result<List<EnergyRealData>> findEnergyRealDatasByDatetime(@RequestParam(name = "ino") Integer ino,
                                                                      @RequestParam(name = "curDateTime") Long curDateTime) {
        return ResultUtil.success(energyRealDataService.findEnergyRealDatasByDatetime(ino, curDateTime));
    }

    /**
     * 通过ino和startTime和endTime查询
     *
     * @param ino
     * @param startDateTime
     * @param endTimeDate
     * @return
     */
    @RequestMapping(value = "/getByInoAndStartDateTimeAndEndDateTime")
    public Result<List<EnergyRealData>> findEnergyRealDatasByDatetime(@RequestParam(name = "ino") Integer ino,
                                                                      @RequestParam(name = "startDateTime") Long startDateTime,
                                                                      @RequestParam(name = "endDateTime") Long endTimeDate) {
        return ResultUtil.success(energyRealDataService.findEnergyRealDatasByDatetime(ino, startDateTime, endTimeDate));
    }
}
