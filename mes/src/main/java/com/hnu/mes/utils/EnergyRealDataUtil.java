package com.hnu.mes.utils;

import com.hnu.mes.domain.EnergyRealData;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:15 2018/4/16
 * @Modified By:
 */
public class EnergyRealDataUtil {

    public static ArrayList<EnergyRealData> MakeUp(List<EnergyRealData> list, Calendar start,
                                                   Calendar end) {
        Map<String, EnergyRealData> hour2energyRealDate = new HashMap<String, EnergyRealData>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        for (EnergyRealData energyRealData : list) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(energyRealData.getIdate());
            calendar.set(Calendar.HOUR_OF_DAY, energyRealData.getIhour());
            hour2energyRealDate.put(simpleDateFormat.format(calendar.getTime()), energyRealData);
        }

        ArrayList<EnergyRealData> energyRealDatas = new ArrayList<>();

        while (true) {
            start.add(Calendar.HOUR_OF_DAY, 1);
            if (start.after(end)) {
                break;
            }

            String key = simpleDateFormat.format(start.getTime());
            if (hour2energyRealDate.containsKey(key)) {
                energyRealDatas.add(hour2energyRealDate.get(key));
            } else {
                // 取出上一个
                Calendar last = Calendar.getInstance();
                last.setTime(start.getTime());
                last.add(Calendar.HOUR_OF_DAY, -1);

                key = simpleDateFormat.format(last.getTime());
                EnergyRealData energyRealDataLast = hour2energyRealDate.getOrDefault(key, new EnergyRealData());

                // 时间设置为当前的
                EnergyRealData energyRealDataCur = new EnergyRealData();
                energyRealDataCur.setIhour(start.get(Calendar.HOUR_OF_DAY));
                energyRealDataCur.setIvalue(energyRealDataLast.getIvalue());
                energyRealDatas.add(energyRealDataCur);
                key = simpleDateFormat.format(start.getTime());
                hour2energyRealDate.put(key, energyRealDataCur);

                // 处理下一个
                Calendar next = Calendar.getInstance();
                next.setTime(start.getTime());
                next.add(Calendar.HOUR_OF_DAY, 1);
                key = simpleDateFormat.format(next.getTime());
                if(hour2energyRealDate.containsKey(key)){
                    EnergyRealData energyRealDataNext = new EnergyRealData();
                    energyRealDataNext.setIhour(next.get(Calendar.HOUR_OF_DAY));
                    energyRealDataNext.setIvalue(energyRealDataCur.getIvalue());
                    energyRealDatas.add(energyRealDataNext);
                }
            }
        }

        return energyRealDatas;
    }

    /**
     * 补充时间
     *
     * @param list
     * @param startHour
     * @param endHour
     */
    public static ArrayList<EnergyRealData> MakeUp(List<EnergyRealData> list, int startHour, int endHour) {

        if (endHour < startHour) {
            endHour += 24;
        }

        Map<Integer, EnergyRealData> hour2energyRealDate = new HashMap<Integer, EnergyRealData>();
        for (EnergyRealData energyRealData : list) {
            hour2energyRealDate.put(energyRealData.getIhour(), energyRealData);
        }

        ArrayList<EnergyRealData> energyRealDatas = new ArrayList<>();
        for (int j = 0; j < 24; j++) {

            int key = (j + startHour) % 24;
            if (hour2energyRealDate.containsKey(key)) {
                energyRealDatas.add(hour2energyRealDate.get(key));
            } else if (j > 0) {
                EnergyRealData energyRealData = new EnergyRealData();
                energyRealData.setIhour(key);
                energyRealData.setIvalue(hour2energyRealDate.get((key + 23) % 24).getIvalue());
                hour2energyRealDate.put(energyRealData.getIhour(), energyRealData);
                energyRealDatas.add(energyRealData);

                if (j + 1 < 24 && hour2energyRealDate.containsKey((key + 1) % 24)) {
                    energyRealData = new EnergyRealData();
                    energyRealData.setIhour((key + 1) % 24);
                    energyRealData.setIvalue(hour2energyRealDate.get(key).getIvalue());
                    energyRealDatas.add(energyRealData);
                }
            } else if (j == 0) {
                EnergyRealData energyRealData = new EnergyRealData();
                energyRealData.setIhour(key);
                energyRealData.setIvalue(0 + "");
                energyRealDatas.add(energyRealData);
                hour2energyRealDate.put(key, energyRealData);
            }
        }

        for (EnergyRealData energyRealData : energyRealDatas) {
            energyRealData.setIhour(energyRealData.getIhour() - startHour + 1);
        }
        return energyRealDatas;
    }
}
