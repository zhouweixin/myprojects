package com.hnu.mes.service;

import com.hnu.mes.domain.Product;
import com.hnu.mes.domain.ProductGodownHeader;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.ProductGodownHeaderRepository;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:31 2018/5/10
 * @Modified By:
 */
@Service
public class ProductGodownHeaderService {
    @Autowired
    private ProductGodownHeaderRepository productGodownHeaderRepository;

    /**
     * 新增
     *
     * @param productGodownHeader
     * @return
     */
    public ProductGodownHeader save(ProductGodownHeader productGodownHeader) {

        int times = 0;
        String number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        while (productGodownHeaderRepository.findFirstByBatchNumber(number) != null) {
            times++;
            if (times > 100) {
                throw new MesException(EnumException.NOT_UNIQUE_NUMBER);
            }
            number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        }

        productGodownHeader.setBatchNumber(number);

        // 缴库时间
        productGodownHeader.setPayTime(new Date());

        return productGodownHeaderRepository.save(productGodownHeader);
    }

    /**
     * 查询所有-分页
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<ProductGodownHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            ProductGodownHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return productGodownHeaderRepository.findAll(pageable);
    }

    /**
     * 通过状态查询
     *
     * @param status
     * @return
     */
    public List<ProductGodownHeader> findByStatus(Integer status){
        return productGodownHeaderRepository.findByStatus(status);
    }

    /**
     * 通过入库状态查询-分页
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<ProductGodownHeader> findByStatus(Integer status, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            ProductGodownHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return productGodownHeaderRepository.findByStatus(status, pageable);
    }

    /**
     * 通过code查询
     *
     * @param code
     * @return
     */
    public ProductGodownHeader findOne(Long code) {
        return productGodownHeaderRepository.findOne(code);
    }

    /**
     * 通过编号模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List<ProductGodownHeader> findByBatchNumberLike(String batchNumber) {
        return productGodownHeaderRepository.findByBatchNumberLike("%" + batchNumber + "%");
    }

    /**
     * 通过入库状态和编码模糊查询
     *
     * @param batchNumber
     * @return
     */
    public List<ProductGodownHeader> findByStatusAndBatchNumberLike(Integer status, String batchNumber) {
        return productGodownHeaderRepository.findByStatusAndBatchNumberLike(status, "%" + batchNumber + "%");
    }

    /**
     * 通过编码更新状态， 入库人， 入库时间
     * @param status
     * @param godowner
     * @param code
     */
    @Transactional
    public void updateStatusAndGodownerAndGodownTimeByCode(Integer status, User godowner, Long code){
        productGodownHeaderRepository.updateStatusAndGodownerAndGodownTimeByCode(status, godowner, new Date(), code);
    }
}
