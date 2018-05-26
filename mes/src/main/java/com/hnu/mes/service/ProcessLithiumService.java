package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.domain.Process;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessLithiumRepository;
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
public class ProcessLithiumService {
    @Autowired
    private ProcessLithiumRepository processLithiumRepository;

    @Autowired
    private StatusRepository statusRepository;

    /**
     * 查找一个
     * @param code
     * @return
     */
    public ProcessLithium findOne(Long code) {
        return processLithiumRepository.findOne(code);
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<ProcessLithium> findAll() {
        return processLithiumRepository.findAll();
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
    public Page<ProcessLithium> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            ProcessLithium.class.getDeclaredField(sortFieldName);
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

        return processLithiumRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<ProcessLithium> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            ProcessLithium.class.getDeclaredField(sortFieldName);
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
        return processLithiumRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
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
        return processLithiumRepository.updateAuditByCode(auditor, date, status, code);
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
        return processLithiumRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<ProcessLithium> saveList(List<ProcessLithium> list){

        if(list == null){
            return null;
        }

        List<ProcessLithium> successList = new ArrayList<ProcessLithium>();
        for(ProcessLithium processLithium : list){
            try {
                ProcessLithium one = processLithiumRepository.save(processLithium);
                successList.add(one);
            }catch (Exception e){}
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
    public List<ProcessLithium> findAllByExcel(String path, String sheetName, Integer startRow, Integer furnaceNum) {
        List<ProcessLithium> list = new ArrayList<ProcessLithium>();

        // 导入行数
        int insertNum = 0;
        XSSFWorkbook workbook = null;
        try {
            try {
                // 打开excel文件
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
                ProcessLithium processLithium11 = new ProcessLithium();
                ProcessLithium processLithium15 = new ProcessLithium();
                ProcessLithium processLithium42 = new ProcessLithium();

                // 设置炉号
                processLithium11.setFurnaceNum(11);
                processLithium15.setFurnaceNum(15);
                processLithium42.setFurnaceNum(42);

                // 设置审核状态
                processLithium11.setStatus(statusRepository.findOne(1));
                processLithium15.setStatus(statusRepository.findOne(1));
                processLithium42.setStatus(statusRepository.findOne(1));

                // 读入0-4列
                int startCol = 0;
                XSSFCell cell0 = row.getCell(startCol + 0);// 检测日期
                XSSFCell cell1 = row.getCell(startCol + 1);// 编号
                XSSFCell cell2 = row.getCell(startCol + 2);// Li2CO3
                XSSFCell cell3 = row.getCell(startCol + 3);// LiOH
                XSSFCell cell4 = row.getCell(startCol + 4);// 总Li含量

                // 检测日期
                processLithium11.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 编号
                processLithium11.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // LiCO3
                Field pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc1");
                    pc.setAccessible(true);

                    pc.set(processLithium11, MyExcelUtil.cell2Double(cell2));
                } catch (Exception e) {
                }

                // LiOH
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc2");
                    pc.setAccessible(true);

                    pc.set(processLithium11, MyExcelUtil.cell2Double(cell3));
                } catch (Exception e) {
                }

                // 总Li含量
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc3");
                    pc.setAccessible(true);

                    pc.set(processLithium11, MyExcelUtil.cell2Double(cell4));
                } catch (Exception e) {
                }

                if (processLithium11.getBatchNumber() != null && !processLithium11.getBatchNumber().equals("")) {
                    list.add(processLithium11);
                }

                // 读入6-10列
                startCol = 6;
                cell0 = row.getCell(startCol + 0);// 检测日期
                cell1 = row.getCell(startCol + 1);// 编号
                cell2 = row.getCell(startCol + 2);// Li2CO3
                cell3 = row.getCell(startCol + 3);// LiOH
                cell4 = row.getCell(startCol + 4);// 总Li含量

                // 检测日期
                processLithium15.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 编号
                processLithium15.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // LiCO3
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc1");
                    pc.setAccessible(true);

                    pc.set(processLithium15, MyExcelUtil.cell2Double(cell2));
                } catch (Exception e) {
                }

                // LiOH
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc2");
                    pc.setAccessible(true);

                    pc.set(processLithium15, MyExcelUtil.cell2Double(cell3));
                } catch (Exception e) {
                }

                // 总Li含量
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc3");
                    pc.setAccessible(true);

                    pc.set(processLithium15, MyExcelUtil.cell2Double(cell4));
                } catch (Exception e) {
                }

                if (processLithium15.getBatchNumber() != null && !processLithium15.getBatchNumber().equals("")) {
                    list.add(processLithium15);
                }

                // 读入12-16列
                startCol = 12;
                cell0 = row.getCell(startCol + 0);// 检测日期
                cell1 = row.getCell(startCol + 1);// 编号
                cell2 = row.getCell(startCol + 2);// Li2CO3
                cell3 = row.getCell(startCol + 3);// LiOH
                cell4 = row.getCell(startCol + 4);// 总Li含量

                // 检测日期
                processLithium42.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 编号
                processLithium42.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // LiCO3
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc1");
                    pc.setAccessible(true);

                    pc.set(processLithium42, MyExcelUtil.cell2Double(cell2));
                } catch (Exception e) {
                }

                // LiOH
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc2");
                    pc.setAccessible(true);

                    pc.set(processLithium42, MyExcelUtil.cell2Double(cell3));
                } catch (Exception e) {
                }

                // 总Li含量
                pc = null;
                try {
                    pc = ProcessLithium.class.getDeclaredField("pc3");
                    pc.setAccessible(true);

                    pc.set(processLithium42, MyExcelUtil.cell2Double(cell4));
                } catch (Exception e) {
                }

                if (processLithium42.getBatchNumber() != null && !processLithium42.getBatchNumber().equals("")) {
                    list.add(processLithium15);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                }
            }
        }

        return list;
    }
}
