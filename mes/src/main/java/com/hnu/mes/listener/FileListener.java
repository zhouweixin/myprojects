package com.hnu.mes.listener;

import com.hnu.mes.domain.RealData;
import com.hnu.mes.utils.FileMonitorUtil;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Created by lanyage on 2018/3/28.
 */
public class FileListener extends FileAlterationListenerAdaptor {

    private Logger log = Logger.getLogger(FileListener.class);
    private List<RealData> list = null;

    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());
    }


    public void onFileChange(File file) {
//        if (file.getName().indexOf("bak.txt") != -1) {
//            try {
//                list = FileMonitorUtil.getData(1, file);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }


    public void onFileDelete(File file) {
        log.info("[删除]:" + file.getAbsolutePath());
    }

    public void onDirectoryCreate(File directory) {

        log.info("[新建]:" + directory.getAbsolutePath());
    }

    public void onDirectoryChange(File directory) {
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    public void onDirectoryDelete(File directory) {
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    public List<RealData> getList() {
        return list;
    }

    public void setList(List<RealData> list) {
        this.list = list;
    }
}
