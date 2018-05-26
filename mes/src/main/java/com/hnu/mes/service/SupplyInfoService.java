package com.hnu.mes.service;

import com.hnu.mes.domain.Customer;
import com.hnu.mes.domain.SupplyInfo;
import com.hnu.mes.domain.SupplyInfoHeader;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.repository.CustomerDao;
import com.hnu.mes.repository.SupplyInfoDao;
import com.hnu.mes.repository.SupplyInfoHeaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.UUID;

/**
 * Created by lanyage on 2018/3/14.
 */
@Service
public class SupplyInfoService {

    @Autowired
    private SupplyInfoDao supplyInfoDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private SupplyInfoHeaderDao headerDao;

    /**
     * 添加一个
     *
     * @param one 前端传来的参数
     * @throws RuntimeException
     */
    public void addOne(SupplyInfo one) throws RuntimeException {
        SupplyInfo info = new SupplyInfo();
        //设置主键
        info.setCode(UUID.randomUUID().toString().replaceAll("-",""));
        //设置名字
        info.setName(one.getName());
        //设置规格
        info.setSpecifications(one.getSpecifications());
        //设置数量
        info.setNumber(one.getNumber());
        //设置批号
        info.setBatchNumber(one.getBatchNumber());

        //设置header
        SupplyInfoHeader oneHeader = one.getHeader();
        SupplyInfoHeader header = null;

        SupplyInfoHeader existHeader = headerDao.findOne(oneHeader.getCode());
        if(existHeader != null){
            header = existHeader;
        } else {
            header = new SupplyInfoHeader();
            header.setCode(oneHeader.getCode());//设置合同号
            header.setSupplyTime(oneHeader.getSupplyTime());//设置时间
            header.setContact(oneHeader.getContact());//设置联系电话
            header.setTotal(oneHeader.getTotal());//设置总量
            Customer customer = customerDao.findByName(oneHeader.getCustomer().getName());
            if(customer == null) {
                throw new RuntimeException("当前送货人不存在！");
            }
            header.setCustomer(customer);//设置顾客
        }
        info.setHeader(header);
        //保存
        supplyInfoDao.save(info);
    }
    public void saveInBatch(List<SupplyInfo> supplyInfos) {
        for(SupplyInfo supplyInfo : supplyInfos) {
            supplyInfo.setCode(UUID.randomUUID().toString().replaceAll("-",""));
            Customer customer = customerDao.findByName(supplyInfo.getHeader().getCustomer().getName());
            if(customer == null) {
                throw new RuntimeException("当前送货人不存在！");
            }
            supplyInfo.getHeader().setCustomer(customer);//设置顾客
        }
        supplyInfoDao.save(supplyInfos);
    }
    /**
     * 删除一个
     *
     * @param one
     */
    public void deleteOne(SupplyInfo one) {
        supplyInfoDao.delete(one);
    }

    /**
     * 批量删除
     *
     * @param ones
     */
    public void deleteInBatch(List<SupplyInfo> ones) {

        supplyInfoDao.deleteInBatch(ones);
    }

    /**
     * 详情页
     *
     * @param one
     * @return
     */
    public SupplyInfo detail(SupplyInfo one) {
        String code = one.getCode();
        return supplyInfoDao.findOne(code);
    }

    /**
     * 更新一条
     *
     * @param codeBefore 原来主键
     * @param one
     */
    public void update(String codeBefore, SupplyInfo one) {
        SupplyInfo exist = supplyInfoDao.findOne(codeBefore);
        //设置名字
        exist.setName(one.getName());
        //设置规格
        exist.setSpecifications(one.getSpecifications());
        //设置数量
        exist.setNumber(one.getNumber());
        //设置批号
        exist.setBatchNumber(one.getBatchNumber());

        SupplyInfoHeader oneHeader = one.getHeader();
        SupplyInfoHeader header = new SupplyInfoHeader();
        header.setCode(oneHeader.getCode());//设置合同号
        header.setSupplyTime(oneHeader.getSupplyTime());//设置时间
        header.setContact(oneHeader.getContact());//设置联系电话
        header.setTotal(oneHeader.getTotal());//设置总量
        Customer customer = customerDao.findByName(oneHeader.getCustomer().getName());
        header.setCustomer(customer);//设置顾客
        exist.setHeader(header);

        supplyInfoDao.save(exist);
    }

    /**
     * 分页查询
     *
     * @param i  页号
     * @param i1 每页数量
     * @param id 排序字段
     * @param i2 升序降序
     * @return
     * @throws RuntimeException
     */
    public Page<SupplyInfo> findAllPage(int i, int i1, String id, int i2) throws RuntimeException {
        try {
            SupplyInfo.class.getDeclaredField(id);
        } catch (Exception e) {
            throw new RuntimeException(EnumException.SORT_FIELD.getMessage());
        }
        Sort sort = null;
        if (i2 == 0) {
            sort = new Sort(Sort.Direction.DESC, id);
        } else {
            sort = new Sort(Sort.Direction.ASC, id);
        }
        Pageable pageable = new PageRequest(i, i1, sort);
        return supplyInfoDao.findAll(pageable);
    }

    /**
     * 按供应商公司名查找
     *
     * @param
     * @return
     */
    public Page<SupplyInfo> findBySupplier(final String supplierName, int i, int i1, String id, int i2) {
        try {
            SupplyInfo.class.getDeclaredField(id);
        } catch (Exception e) {
            throw new RuntimeException(EnumException.SORT_FIELD.getMessage());
        }
        Sort sort = null;
        if (i2 == 0) {
            sort = new Sort(Sort.Direction.DESC, id);
        } else {
            sort = new Sort(Sort.Direction.ASC, id);
        }
        Pageable pageable = new PageRequest(i, i1, sort);

        Page<SupplyInfo> page = supplyInfoDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> supplierNamePath = root.get("header").get("customer").get("supplier").get("name");
            Predicate predicate = criteriaBuilder.like(supplierNamePath, "%" + supplierName + "%");
            return criteriaBuilder.and(predicate);
        }, pageable);
        return page;
    }

    public List<SupplyInfo> findSupplyInfosByHeader(String headCode) {
        SupplyInfoHeader header = new SupplyInfoHeader();
        header.setCode(headCode);
        return supplyInfoDao.findSupplyInfosByHeader(header);
    }


}
