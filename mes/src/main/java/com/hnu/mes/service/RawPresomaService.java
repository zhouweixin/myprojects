package com.hnu.mes.service;

import com.hnu.mes.domain.Judge;
import com.hnu.mes.domain.RawPresoma;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.JudgeRepository;
import com.hnu.mes.repository.RawPresomaRepository;
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
public class RawPresomaService {
    @Autowired
    private RawPresomaRepository rawPresomaRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private StatusRepository statusRepository;

    public RawPresoma findOne(Long code) {
        /**
         * findOne
         * @Desciption
         * @param [code]
         */
        return rawPresomaRepository.findOne(code);
    }

    public List<RawPresoma> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return rawPresomaRepository.findAll();
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
    public Page<RawPresoma> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            RawPresoma.class.getDeclaredField(sortFieldName);
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

        return rawPresomaRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<RawPresoma> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            RawPresoma.class.getDeclaredField(sortFieldName);
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
        return rawPresomaRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
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
        return rawPresomaRepository.updateAuditByCode(auditor, date, status, code);
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
        return rawPresomaRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 新增
     *
     * @param rawPresoma
     * @return
     */
    public RawPresoma save(RawPresoma rawPresoma) {
        return rawPresomaRepository.save(rawPresoma);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<RawPresoma> saveList(List<RawPresoma> list){

        if(list == null){
            return null;
        }

        List<RawPresoma> successList = new ArrayList<RawPresoma>();
        for(RawPresoma rawPresoma : list){
            try {
                RawPresoma one = rawPresomaRepository.save(rawPresoma);
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
    public List<RawPresoma> findAllByExcel(String path, String sheetName, Integer startRow) {

        List<Judge> judges = judgeRepository.findAll();

        List<RawPresoma> list = new ArrayList<RawPresoma>();

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
                XSSFCell cell2 = row.getCell(2);// 内部编号
                XSSFCell cell3 = row.getCell(3);// 生产日期
                XSSFCell cell4 = row.getCell(4);// 数量
                XSSFCell cell5 = row.getCell(5);// 判定

                // 创建产品对象
                RawPresoma rawPresoma = new RawPresoma();

                rawPresoma.setStatus(statusRepository.findOne(1));

                // 测试日期
                rawPresoma.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 厂家批号
                rawPresoma.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // 内部编码
                rawPresoma.setInsideCode(MyExcelUtil.cell2String(cell2));

                // 生产日期:yyyy.MM或yyyy/MM或yyyy-MM
                rawPresoma.setProductDate(MyExcelUtil.cell2Date(cell3));

                // 数量
                rawPresoma.setNumber(MyExcelUtil.cell2Integer(cell4));

                // 判定
                rawPresoma.setJudge(MyExcelUtil.cell2Judge(cell5, judges));

                // 后面的数据
                Double[] data = new Double[RawPresoma.DATALEN];
                int from = 6;
                for (int j = from; j < RawPresoma.DATALEN + from && j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);

                    Field c = null;
                    try {
                        // 通过反射找到属性
                        c = RawPresoma.class.getDeclaredField("c" + (j - from + 1));
                        c.setAccessible(true);

                        // 设置值
                        c.set(rawPresoma, MyExcelUtil.cell2Double(cell));
                    } catch (Exception e) {

                    }
                }

                if (rawPresoma.getBatchNumber() != null && !rawPresoma.getBatchNumber().equals("")) {
                    list.add(rawPresoma);
                }
            }
        } catch (Exception e) {
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
    public RawPresoma findFirstByBatchNumber(String batchNumber){
        return rawPresomaRepository.findFirstByBatchNumber(batchNumber);
    }
}
