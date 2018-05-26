package com.hnu.mes.utils;

import com.hnu.mes.domain.RealData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by lanyage on 2018/3/28.
 */
public class FileMonitorUtil {
    private final static String ROOT_DIR = "\\\\192.168.100.182\\Users\\Administrator\\Desktop\\Debug";
//    private final static String ROOT_DIR = "/Users/lanyage/svn/svnRepo/mes_3/src/main/resources";
    public static  List<RealData> loadData() throws IOException {
        List<RealData> list = new ArrayList<>();
        File file = new File(ROOT_DIR+"/RealDatabak.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s ;
        int count = 0;
        try {
            while ((s = reader.readLine()) != null) {
                list.add(transform(s));
                count ++;
                if(count == 3765)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return list;
    }
    private static  RealData transform(String s) {
        String[] strings = s.split(";");
        RealData realData = new RealData();
        realData.setCode(strings[0]);
        realData.setWeihao(strings[1]);
        realData.setValue(strings[2]);
        realData.setCondition(strings[3]);
        realData.setDate(strings[4]);
        return realData;
    }
}
