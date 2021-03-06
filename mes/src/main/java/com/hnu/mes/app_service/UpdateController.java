package com.hnu.mes.app_service;



import com.hnu.mes.domain.AppMission;
import com.hnu.mes.repository.AppMissionRepository;
import com.hnu.mes.service.AppMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.List;


@Component
public class UpdateController {

    @Autowired
    private AppMissionRepository appMissionRepository;

    /** 注入 */
    @Autowired
    private AppMissionService appMissionService;

    private String Time;

    @Scheduled(cron = "0 30 0 * * *")
    public void cron() throws Exception{

    Calendar now = Calendar.getInstance();
        Time=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH);
        List<AppMission> list = appMissionRepository.findAll();
        for(int i=0;i <list.size(); i++) {
            Integer j = appMissionService.updateTimeByCode(Time, list.get(i).getCode());
            Integer q = appMissionService.updateExamTimeByCode(Time, list.get(i).getCode());
            if (j > 0) {
                System.out.println("checkUpdate ");
            }
            if (q > 0) {
                System.out.println("examUpdate ");
            }
        }
    }


  }

