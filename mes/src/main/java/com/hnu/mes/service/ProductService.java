package com.hnu.mes.service;

import com.hnu.mes.domain.Judge;
import com.hnu.mes.domain.Product;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.JudgeRepository;
import com.hnu.mes.repository.ProductRepository;
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

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/18
 * @Description: 产品发布
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Product findOne(Long code) {
        /**
         * findOne
         * @Desciption
         * @param [code]
         */
        return productRepository.findOne(code);
    }

    public List<Product> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return productRepository.findAll();
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
    public Page<Product> getAllByStatusCodeByPage(Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        try {
            Product.class.getDeclaredField(sortFieldName);
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

        return productRepository.findByStatusCode(statusCode, pageable);
    }

    public Page<Product> findByBatchNumberLikeAndStatusCode(String batchNumber, Integer statusCode, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNmaeByPage
         * @Desciption
         * @param [name, status, page, size, sortFieldName, asc]
         */
        try {
            Product.class.getDeclaredField(sortFieldName);
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
        return productRepository.findByBatchNumberLikeAndStatusCode("%" + batchNumber + "%", statusCode, pageable);
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
        return productRepository.updateAuditByCode(auditor, date, status, code);
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
        return productRepository.updatePublishByCode(publisher, date, status, code);
    }

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public List<Product> saveList(List<Product> list) {

        if (list == null) {
            return null;
        }

        List<Product> successList = new ArrayList<Product>();
        for (Product product : list) {
            try {
                Product p = productRepository.save(product);
                successList.add(p);
            } catch (Exception e) {
            }
        }

        return successList;
    }

    public List<Product> findAllByExcel(String path, String sheetName, Integer startRow) {
        List<Judge> judges = judgeRepository.findAll();

        List<Product> list = new ArrayList<Product>();

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

            // 从第9行开始读数据,边读边导入数据库
            if (startRow == null) {
                startRow = 9;
            }

            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                // 读入前4列
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                XSSFCell cell2 = row.getCell(2);
                XSSFCell cell3 = row.getCell(3);

                // 创建产品对象
                Product product = new Product();

                // 设置审核状态
                product.setStatus(statusRepository.findOne(1));

                // 测试日期
                product.setTestDate(MyExcelUtil.cell2Date(cell0));

                // 批号
                product.setBatchNumber(MyExcelUtil.cell2String(cell1));

                // 判定
                product.setJudge(MyExcelUtil.cell2Judge(cell2, judges));

                // 数量
                product.setNumber(MyExcelUtil.cell2Integer(cell3));

                // 后面的数据
                int from = 4;
                for (int j = from; j < Product.DATALEN + from && j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);

                    Field p = null;
                    try {
                        // 通过反射找到属性
                        p = Product.class.getDeclaredField("p" + (j - from + 1));
                        p.setAccessible(true);

                        // 设置值
                        p.set(product, MyExcelUtil.cell2Double(cell));
                    } catch (Exception e) {

                    }
                }

                if (product.getBatchNumber() != null && !product.getBatchNumber().equals("")) {
                    list.add(product);
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
    public Product findFirstByBatchNumber(String batchNumber){
        return productRepository.findFirstByBatchNumber(batchNumber);
    }
}
