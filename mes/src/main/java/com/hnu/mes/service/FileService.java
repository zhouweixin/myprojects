package com.hnu.mes.service;

import com.hnu.mes.domain.RealData;
import com.hnu.mes.listener.FileListener;
import com.hnu.mes.utils.FileMonitorUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by lanyage on 2018/3/28.
 */
@Service
public class FileService {
    /**
     * 192.168.100.182\Users\Administrator\Desktop\Debug
     */
    public List<RealData> getData() {
        List<RealData> list = null;

        try {
            list =  FileMonitorUtil.loadData();
        } catch (IOException e) {
            throw new RuntimeException("数据读取出错了");
        }
        if(list.size() < 3765)
            throw new RuntimeException("数据数量不足");
        return list;
    }
}
