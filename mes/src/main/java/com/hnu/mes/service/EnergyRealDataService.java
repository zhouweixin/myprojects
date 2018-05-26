package com.hnu.mes.service;

import com.hnu.mes.domain.EnergyRealData;
import com.hnu.mes.repository.EnergyRealDataRepository;
import com.hnu.mes.utils.EnergyRealDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:30 2018/4/15
 * @Modified By:
 */
@Service
public class EnergyRealDataService {
    @Autowired
    private EnergyRealDataRepository energyRealDataRepository;

    /**
     * 通过ino和当前日期查询之前24小时的内容
     *
     * @param ino
     * @param curDateTime
     * @return
     */
    public List<EnergyRealData> findEnergyRealDatasByDatetime(Integer ino, Long curDateTime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar startDateTime = Calendar.getInstance();
        Calendar endDateTime = Calendar.getInstance();

        startDateTime.setTimeInMillis(curDateTime);
        endDateTime.setTimeInMillis(curDateTime);
        startDateTime.add(Calendar.DAY_OF_MONTH, -1);
        endDateTime.add(Calendar.HOUR_OF_DAY, -1);

        List<EnergyRealData> energyRealDatas = energyRealDataRepository.findEnergyRealDatasByDateTime(ino,
                sdf.format(startDateTime.getTime()), startDateTime.get(Calendar.HOUR_OF_DAY),
                sdf.format(endDateTime.getTime()), endDateTime.get(Calendar.HOUR_OF_DAY));

        return energyRealDatas;
    }

    /**
     * 通过ino和startTime和endTime查询
     *
     * @param ino
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public List<EnergyRealData> findEnergyRealDatasByDatetime(Integer ino, Long startDateTime, Long endDateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.setTimeInMillis(startDateTime);
        endDate.setTimeInMillis(endDateTime);

        startDate.add(Calendar.HOUR_OF_DAY, -1);
        endDate.add(Calendar.HOUR_OF_DAY, -1);

        List<EnergyRealData> energyRealDatas = energyRealDataRepository.findEnergyRealDatasByDateTime(ino,
                sdf.format(startDate.getTime()), startDate.get(Calendar.HOUR_OF_DAY),
                sdf.format(endDate.getTime()), endDate.get(Calendar.HOUR_OF_DAY));

//        energyRealDatas = EnergyRealDataUtil.MakeUp(energyRealDatas, startDate, endDate);
        return energyRealDatas;
    }
}
