package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProcessPremixRepository;
import com.hnu.mes.repository.StatusRepository;
import com.hnu.mes.repository.SupplierDao;
import com.hnu.mes.utils.MyExcelUtil;
import org.apache.poi.ss.usermodel.CellType;
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
public class ProcessPremixService {
    @Autowired
    private ProcessPremixRepository processPremixRepository;

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private StatusRepository statusRepository;

    /**
     * 查找一个
     * @param code
     * @return
     */
    public ProcessPremix findOne(Long code) {
        return processPremixRepository.findOne(code);
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<ProcessPremix> findAll() {
        return processPremixRepository.findAll();
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
    public Page<ProcessPremix> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            ProcessPremix.class.getDeclaredField(sortFieldName);
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

        return processPremixRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<ProcessPremix> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            ProcessPremix.class.getDeclaredField(sortFieldName);
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
        return processPremixRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
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
        return processPremixRepository.updateAuditByCode(auditor, date, status, code);
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
        return processPremixRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<ProcessPremix> saveList(List<ProcessPremix> list){

        if(list == null){
            return null;
        }

        List<ProcessPremix> successList = new ArrayList<ProcessPremix>();
        for(ProcessPremix processPremix : list){
            try {
                ProcessPremix one = processPremixRepository.save(processPremix);
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
     * @return
     */
    public List<ProcessPremix> findAllByExcel(String path, String sheetName, Integer startRow, String type) {
        List<ProcessPremix> list = new ArrayList<ProcessPremix>();

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

                // 读入前4列
                XSSFCell cell0 = row.getCell(0);// 检测日期
                XSSFCell cell1 = row.getCell(1);// 编号
                XSSFCell cell2 = row.getCell(2);// 可溶锂
                XSSFCell cell3 = row.getCell(3);// 主原料厂家

                // 创建产品对象
                ProcessPremix processPremix = new ProcessPremix();

                // 设置审核状态
                processPremix.setStatus(statusRepository.findOne(1));

                // 测试日期
                processPremix.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 编号
                processPremix.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // 可溶锂
                processPremix.setLithiumSoluble(MyExcelUtil.cell2Double(cell2));

                // 主原料厂家
                String code = MyExcelUtil.cell2String(cell3);

                if(code != null) {
                    Supplier supplier = supplierDao.findOne(code);
                    processPremix.setSupplier(supplier);
                }

                // 后面的数据
                double[] data = new double[ProcessPremix.DATALEN];
                int from = 4;
                for (int j = from; j < ProcessPremix.DATALEN + from && j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);

                    Field pc = null;
                    try {
                        // 通过反射找到属性
                        pc = ProcessPremix.class.getDeclaredField("pc" + (j - from + 1));
                        pc.setAccessible(true);

                        // 设置值
                        pc.set(processPremix, MyExcelUtil.cell2Double(cell));
                    } catch (Exception e) {
                    }

                    // TODO standardCode 标准编码

                    // 类型：预混11/15
                    processPremix.setType(type);

                    if (processPremix.getBatchNumber() != null && !processPremix.getBatchNumber().equals("")) {
                        list.add(processPremix);
                    }
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
}
