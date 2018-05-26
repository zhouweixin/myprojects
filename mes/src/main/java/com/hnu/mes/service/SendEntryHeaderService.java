package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.SendEntryHeaderRepository;
import com.hnu.mes.repository.SendEntryRepository;
import com.hnu.mes.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:14 2018/5/1
 * @Modified By:
 */
@Service
public class SendEntryHeaderService {
    @Autowired
    private SendEntryHeaderRepository sendEntryHeaderRepository;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private GodownEntryHeaderService godownEntryHeaderService;

    @Autowired
    private SendEntryRepository sendEntryRepository;

    /**
     * 新增
     * @param sendEntryHeader
     * @return
     */
    @Transactional
    public SendEntryHeader save(SendEntryHeader sendEntryHeader){
        sendEntryHeader.setStatus(GlobalUtil.SEND_ENTRY_HEADER_PRE_SEND);

        sendEntryHeader = sendEntryHeaderRepository.save(sendEntryHeader);
        if(sendEntryHeader == null){
            return null;
        }

        MessageQueue messageQueue = new MessageQueue();

        messageQueue.setContent(sendEntryHeader.getName() + "-" + sendEntryHeader.getWeight());
        messageQueue.setUrl(sendEntryHeader.getCode() + "");

        messageQueueService.sendSave(messageQueue);

        return sendEntryHeader;
    }

    /**
     * 更新
     *
     * @param sendEntryHeader
     * @return
     */
    public SendEntryHeader update(SendEntryHeader sendEntryHeader){
        return sendEntryHeaderRepository.save(sendEntryHeader);
    }

    /**
     * 通过code查询
     * @param code
     * @return
     */
    public SendEntryHeader findOne(Long code) {
        return sendEntryHeaderRepository.findOne(code);
    }

    /**
     * 通过编码删除
     * @param code
     */
    public void delete(Long code){
        sendEntryHeaderRepository.delete(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<SendEntryHeader> findAll(){
        return sendEntryHeaderRepository.findAll();
    }

    /**
     * 批量删除
     *
     * @param sendEntryHeaders
     */
    public void deleteInBatch(Collection<SendEntryHeader> sendEntryHeaders) {
        sendEntryHeaderRepository.deleteInBatch(sendEntryHeaders);
    }

    /**
     * 通过编码更新状态
     *
     * @param code
     * @param status
     */
    @Transactional
    public void updateStatusByCode(Long code, Integer status, User user){
        sendEntryHeaderRepository.updateStatusByCode(status, code);

        // 已收货
        if(status == GlobalUtil.SEND_ENTRY_HEADER_RECEIVED){
            SendEntryHeader sendEntryHeader = sendEntryHeaderRepository.findOne(code);

            // 生成入库单
            GodownEntryHeader godownEntryHeader = new GodownEntryHeader();

            // 发货单编号
            godownEntryHeader.setSendEntryHeader(sendEntryHeader);

            // 发货厂家
            godownEntryHeader.setSupplier(sendEntryHeader.getSupplier());

            // 原料名称
            godownEntryHeader.setRawType(sendEntryHeader.getRawType());

            // 原料总重
            godownEntryHeader.setWeight(sendEntryHeader.getWeight());

            // 入库单制单人工号
            godownEntryHeader.setCreateUser(user);

            // 入库单内容
            List<GodownEntry> godownEntries = new ArrayList<>();
            List<SendEntry> sendEntries = sendEntryHeader.getSendEntries();
            for(SendEntry sendEntry : sendEntries){
                sendEntry.setStatus(GlobalUtil.SEND_ENTRY_RECEIVED);
                sendEntryRepository.updateStatusByCode(sendEntry.getStatus(), sendEntry.getCode());

                GodownEntry godownEntry = new GodownEntry(sendEntry.getBatchNumber(), sendEntry.getUnit(), sendEntry.getWeight());
                godownEntries.add(godownEntry);
            }
            godownEntryHeader.setGodownEntries(godownEntries);

            godownEntryHeaderService.save(godownEntryHeader);
        }
    }

    /**
     * 通过分页查询所有
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<SendEntryHeader> getSendEntryHeaderByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            SendEntryHeader.class.getDeclaredField(sortFieldName);
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
        return sendEntryHeaderRepository.findAll(pageable);
    }


    /**
     * 通过公司-分页查询
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<SendEntryHeader> getSendEntryHeadersBySupplierByPage(Supplier supplier, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            SendEntryHeader.class.getDeclaredField(sortFieldName);
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
        return sendEntryHeaderRepository.findSendEntryHeadersBySupplier(supplier, pageable);
    }

    /**
     * 通过公司和状态-分页查询
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<SendEntryHeader> findSendEntryHeaderBySupplierAndStatusByPage(Supplier supplier, Integer status, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            SendEntryHeader.class.getDeclaredField(sortFieldName);
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
        return sendEntryHeaderRepository.findSendEntryHeaderBySupplierAndStatus(supplier, status, pageable);
    }

    /**
     * 通过状态-分页查询
     *
     * @param page          当前页
     * @param size          每页的记录数
     * @param sortFieldName 排序的字段名
     * @param asc           增序还是减序
     * @return
     * @throws Exception
     */
    public Page<SendEntryHeader> findSendEntryHeadersByStatusAndSenderByPage(Integer status, Customer sender, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            SendEntryHeader.class.getDeclaredField(sortFieldName);
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
        return sendEntryHeaderRepository.findSendEntryHeadersByStatusAndSender(status, sender, pageable);
    }
}
