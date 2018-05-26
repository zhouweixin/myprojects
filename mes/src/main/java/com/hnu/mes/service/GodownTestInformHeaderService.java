package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.GodownTestInformHeaderRepository;
import com.hnu.mes.repository.UserRoleRepository;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:01 2018/5/2
 * @Modified By:
 */
@Service
public class GodownTestInformHeaderService {
    @Autowired
    private GodownTestInformHeaderRepository godownTestInformHeaderRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private MessageQueueService messageQueueService;

    /**
     * 保存
     * @param godownTestInformHeader
     * @return
     */
    public GodownTestInformHeader save(GodownTestInformHeader godownTestInformHeader){

        // 设置状态为:未领取
        godownTestInformHeader.setStatus(GlobalUtil.TEST_INFORM_NOT_PICKING);

        return godownTestInformHeaderRepository.save(godownTestInformHeader);
    }

    /**
     * 新增
     * @param godownEntryHeader
     * @param department
     * @param createUser
     * @return
     */
    @Transactional
    public GodownTestInformHeader save(GodownEntryHeader godownEntryHeader, Department department, User createUser){

        GodownTestInformHeader godownTestInformHeader = new GodownTestInformHeader();

        // 设置状态为:未领取
        godownTestInformHeader.setStatus(GlobalUtil.TEST_INFORM_NOT_PICKING);
        godownTestInformHeader.setRawType(godownEntryHeader.getRawType());
        godownTestInformHeader.setSupplier(godownEntryHeader.getSupplier());
        godownTestInformHeader.setDate(godownEntryHeader.getDate());
        godownTestInformHeader.setCreateTime(new Date());
        godownTestInformHeader.setDepartment(department);
        godownTestInformHeader.setCreateUser(createUser);

        // 设置接收人:IQC
        UserRole userRole = userRoleRepository.findFirstByRoleCode(7);
        User user = null;
        if(userRole != null){
            user = new User();
            user.setCode(userRole.getUserCode());
            godownTestInformHeader.setReceiptor(user);
        }else {
            throw new MesException(EnumException.IQC_NOT_EXIST);
        }

        GodownTestInformHeader save = godownTestInformHeaderRepository.save(godownTestInformHeader);

        // 添加一条信息给IQC
        if(save != null){
            // 向消息队列里添加消息
            MessageQueue messageQueue = new MessageQueue();

            String content = "";
            if (godownEntryHeader.getRawType() != null) {
                content += godownEntryHeader.getRawType().getName();
            }

            List<GodownTestInform> godownTestInforms = new ArrayList<GodownTestInform>();
            List<GodownEntry> godownEntries = godownEntryHeader.getGodownEntries();
            if(godownEntries != null) {
                for (GodownEntry godownEntry : godownEntries) {
                    godownTestInforms.add(new GodownTestInform(godownEntry.getBatchNumber()));

                    content += "-" + godownEntry.getBatchNumber();
                }
            }

            // 设置内容
            messageQueue.setContent(content);

            // 设置URL
            messageQueue.setUrl(godownTestInformHeader.getCode() + "");

            // 设置IQC
            messageQueue.setAddressee(user);

            messageQueueService.testInformSave(messageQueue);
        }

        return save;
    }

    /**
     * 通过状态和领取人查询
     *
     * @param status
     * @param receiptor
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatusAndAndReceiptor(Integer status, User receiptor,
                                                                                           Integer page, Integer size,
                                                                                           String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownTestInformHeader.class.getDeclaredField(sortFieldName);
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
        return godownTestInformHeaderRepository.findGodownTestInformHeadersByStatusAndAndReceiptor(status, receiptor, pageable);
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public GodownTestInformHeader findOne(Long code) {
        return godownTestInformHeaderRepository.findOne(code);
    }

    /**
     * 通过编码更新状态
     */
    @Transactional
    public void updateStatusByCode(Integer status, Long code) {

        GodownTestInformHeader godownTestInformHeader = godownTestInformHeaderRepository.findOne(code);

        // 上一个状态是未领取, 更新为已领取
        if(godownTestInformHeader.getStatus() == GlobalUtil.TEST_INFORM_NOT_PICKING && status == GlobalUtil.TEST_INFORM_PICKING) {
            godownTestInformHeaderRepository.updateStatusByCode(status, new Date(), code);
        }
    }

    /**
     * 通过状态查询
     *
     * @param status
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatus(Integer status,
                                                                                           Integer page, Integer size,
                                                                                           String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownTestInformHeader.class.getDeclaredField(sortFieldName);
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
        return godownTestInformHeaderRepository.findGodownTestInformHeadersByStatus(status, pageable);
    }

    /**
     * 通过生产厂家查询
     *
     * @param supplier
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersBySupplier(Supplier supplier,
                                                                            Integer page, Integer size,
                                                                            String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownTestInformHeader.class.getDeclaredField(sortFieldName);
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
        return godownTestInformHeaderRepository.findGodownTestInformHeadersBySupplier(supplier, pageable);
    }

    /**
     * 通过状态和生产厂家查询
     *
     * @param supplier
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<GodownTestInformHeader> findGodownTestInformHeadersByStatusAndSupplier(Integer status, Supplier supplier,
                                                                              Integer page, Integer size,
                                                                              String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            GodownTestInformHeader.class.getDeclaredField(sortFieldName);
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
        return godownTestInformHeaderRepository.findGodownTestInformHeadersByStatusAndSupplier(status, supplier, pageable);
    }
}
