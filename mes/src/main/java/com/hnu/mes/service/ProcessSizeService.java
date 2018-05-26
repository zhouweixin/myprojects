package com.hnu.mes.service;

import com.hnu.mes.domain.ProcessSize;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessSizeRepository;
import com.hnu.mes.repository.StatusRepository;
import com.hnu.mes.utils.MyExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouweixin on 2018/3/20.
 */
@Service
public class ProcessSizeService {
    @Autowired
    private ProcessSizeRepository processSizeRepository;

    @Autowired
    private StatusRepository statusRepository;

    public ProcessSize findOne(Long code) {
        /**
         * findOne
         * @Desciption
         * @param code
         */
        return processSizeRepository.findOne(code);
    }

    public List<ProcessSize> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return processSizeRepository.findAll();
    }

    /**
     * 通过状态编码分页查询
     *
     * @param statusCode
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProcessSize> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            ProcessSize.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            //降序，desc
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            //升序，asc
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        //分页
        Pageable pageable = new PageRequest(page, size, sort);

        return processSizeRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<ProcessSize> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            ProcessSize.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        //分页
        Pageable pageable = new PageRequest(page, size, sort);
        // 查询
        return processSizeRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
    }

    /**
     * 更新审核人，审核日期，状态
     *
     * @param auditor
     * @param status
     * @param code
     * @return
     */
    @Transactional
    public Integer updateAuditByCode(User auditor, Status status, Long code) {
        Date date = new Date();
        return processSizeRepository.updateAuditByCode(auditor, date, status, code);
    }

    /**
     * 更新发布人，发布日期，状态
     *
     * @param publisher
     * @param status
     * @param code
     * @return
     */
    @Transactional
    public Integer updatePublishByCode(User publisher, Status status, Long code) {
        Date date = new Date();
        return processSizeRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<ProcessSize> saveList(List<ProcessSize> list) {

        if (list == null) {
            return null;
        }

        List<ProcessSize> successList = new ArrayList<ProcessSize>();
        for (ProcessSize processSize : list) {
            try {
                ProcessSize one = processSizeRepository.save(processSize);
                successList.add(one);
            } catch (Exception e) {
            }
        }

        return successList;
    }

    /**
     * 把excel数据导入数据库
     *
     * @param path
     * @param sheetName
     * @param startRow
     * @param furnaceNum
     * @return
     */
    public List<ProcessSize> findAllByExcel(String path, String sheetName, Integer startRow, Integer furnaceNum) {
        List<ProcessSize> list = new ArrayList<ProcessSize>();

        // 导入行数
        int insertNum = 0;
        XSSFWorkbook workbook = null;
        try {
            // 打开excel文件
            try {
                workbook = new XSSFWorkbook(path);
            } catch (IOException e) {
                return list;
            }
            // 打开第一个sheet
            XSSFSheet sheet = null;
            if (sheetName == null) {
                sheet = workbook.getSheetAt(0);
            } else {
                sheet = workbook.getSheet(sheetName);
            }

            if (sheet == null) {
                return list;
            }

            // 从第4行开始读数据,边读边导入数据库
            if (startRow == null) {
                startRow = 4;
            }

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                // 创建产品对象
                ProcessSize processSize11 = new ProcessSize();
                ProcessSize processSize15 = new ProcessSize();
                ProcessSize processSize42 = new ProcessSize();

                // 设置炉号
                processSize11.setFurnaceNum(11);
                processSize15.setFurnaceNum(15);
                processSize42.setFurnaceNum(42);

                processSize11.setStatus(statusRepository.findOne(1));
                processSize15.setStatus(statusRepository.findOne(1));
                processSize42.setStatus(statusRepository.findOne(1));

                // 读入0-4列
                XSSFCell cell0 = row.getCell(0);// 检测日期
                XSSFCell cell1 = row.getCell(1);// 编号

                // 检测日期
                processSize11.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 编号
                processSize11.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // 后面10个数据
                getAfter10(processSize11, 2, row);

                if (processSize11.getBatchNumber() != null && !processSize11.getBatchNumber().equals("")) {
                    list.add(processSize11);
                }

                // 读入13-14列
                XSSFCell cell13 = row.getCell(13);// 检测日期
                XSSFCell cell14 = row.getCell(14);// 编号

                processSize15.setTestDate(MyExcelUtil.cell2Date(cell13));

                processSize15.setBatchNumber(MyExcelUtil.cell2String(cell14));

                // 后面10个数据
                getAfter10(processSize15, 16, row);

                if (processSize15.getBatchNumber() != null && !processSize15.getBatchNumber().equals("")) {
                    list.add(processSize15);
                }

                // 读入26-27列
                XSSFCell cell26 = row.getCell(26);// 检测日期
                XSSFCell cell27 = row.getCell(27);// 编号

                processSize42.setTestDate(MyExcelUtil.cell2Date(cell26));
                processSize42.setBatchNumber(MyExcelUtil.cell2String(cell27));

                // 后面10个数据
                getAfter10(processSize42, 28, row);

                if (processSize42.getBatchNumber() != null && !processSize42.getBatchNumber().equals("")) {
                    list.add(processSize42);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                }
            }
        }

        return list;
    }

    /**
     * 获得后面10个数据
     * @param processSize
     * @param from
     * @param row
     */
    public void getAfter10(ProcessSize processSize, int from, XSSFRow row){
        // 后面10个数据
        for (int j = from; j < ProcessSize.DATALEN + from && j < row.getLastCellNum(); j++) {
            XSSFCell cell = row.getCell(j);
            Field pc = null;
            try {
                // 通过反射找到属性
                pc = ProcessSize.class.getDeclaredField("pc" + (j - from + 1));
                pc.setAccessible(true);

                // 设置值
                pc.set(processSize, MyExcelUtil.cell2Double(cell));
            } catch (Exception e) {
            }
        }
    }
}
