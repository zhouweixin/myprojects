package com.hnu.mes.service;

import com.hnu.mes.domain.Judge;
import com.hnu.mes.domain.RawLithium;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.JudgeRepository;
import com.hnu.mes.repository.RawLithiumRepository;
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
public class RawLithiumService {
    @Autowired
    private RawLithiumRepository rawLithiumRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private StatusRepository statusRepository;

    /**
     * 查找一个
     *
     * @param code
     * @return
     */
    public RawLithium findOne(Long code) {
        return rawLithiumRepository.findOne(code);
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<RawLithium> findAll() {
        return rawLithiumRepository.findAll();
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
    public Page<RawLithium> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            RawLithium.class.getDeclaredField(sortFieldName);
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

        return rawLithiumRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<RawLithium> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            RawLithium.class.getDeclaredField(sortFieldName);
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
        return rawLithiumRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
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
        return rawLithiumRepository.updateAuditByCode(auditor, date, status, code);
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
        return rawLithiumRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<RawLithium> saveList(List<RawLithium> list){

        if(list == null){
            return null;
        }

        List<RawLithium> successList = new ArrayList<RawLithium>();
        for(RawLithium rawLithium : list){
            try {
                RawLithium one = rawLithiumRepository.save(rawLithium);
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
    public List<RawLithium> findAllByExcel(String path, String sheetName, Integer startRow) {

        List<Judge> judges = judgeRepository.findAll();
        List<RawLithium> list = new ArrayList<RawLithium>();

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

            // 从第11行开始读数据,边读边导入数据库
            if (startRow == null) {
                startRow = 11;
            }

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                // 读入前4列
                XSSFCell cell0 = row.getCell(0);// 检测日期
                XSSFCell cell1 = row.getCell(1);// 厂家批号
                XSSFCell cell2 = row.getCell(2);// 生产日期
                XSSFCell cell3 = row.getCell(3);// 判定
                XSSFCell cell4 = row.getCell(4);// 数量

                // 创建产品对象
                RawLithium rawLithium = new RawLithium();

                rawLithium.setStatus(statusRepository.findOne(1));

                // 测试日期
                rawLithium.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 厂家批号
                rawLithium.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // 生产日期
                rawLithium.setProductDate(MyExcelUtil.cell2Date(cell2));

                // 判定
                rawLithium.setJudge(MyExcelUtil.cell2Judge(cell3, judges));

                // 数量
                rawLithium.setNumber(MyExcelUtil.cell2Integer(cell4));

                // 后面的数据
                double[] data = new double[RawLithium.DATALEN];
                int from = 6;
                for (int j = from; j < RawLithium.DATALEN + from && j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);

                    Field c = null;
                    try {
                        // 通过反射找到属性
                        c = RawLithium.class.getDeclaredField("c" + (j - from + 1));
                        c.setAccessible(true);

                        // 设置值
                        c.set(rawLithium, MyExcelUtil.cell2Double(cell));
                    } catch (Exception e) {

                    }
                }

                if (rawLithium.getBatchNumber() != null && !rawLithium.getBatchNumber().equals("")) {
                    list.add(rawLithium);
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
     * 通过批号查询
     *
     * @param batchNumber
     * @return
     */
    public RawLithium findFirstByBatchNumber(String batchNumber){
        return rawLithiumRepository.findFirstByBatchNumber(batchNumber);
    }
}
