package com.hnu.mes.service;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.*;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.MessageQueueTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 19:16 2018/5/17
 * @Modified By:
 */
@Service
public class ProductSendHeaderService {
    @Autowired
    private ProductSendHeaderRepository productSendHeaderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private ProcessManageRepository processManageRepository;

    @Autowired
    private ProductSendAuditRepository productSendAuditRepository;

    /**
     * 新增
     *
     * @param productSendHeader
     * @return
     */
    public ProductSendHeader save(ProductSendHeader productSendHeader) {

        // 生成7位的编号
        int times = 0;
        String number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        while (productSendHeaderRepository.findFirstByNumber(number) != null) {
            times++;
            if (times > 100) {
                throw new MesException(EnumException.NOT_UNIQUE_NUMBER);
            }
            number = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        }
        productSendHeader.setNumber(number);

        // 开单日期
        productSendHeader.setCreateDate(new Date());

        // 申请时间
        productSendHeader.setApplyTime(new Date());

        ProductSendHeader save = productSendHeaderRepository.save(productSendHeader);

        if (save.getAuditStatus() == GlobalUtil.ProductSendStatus.AUDITTING) {
            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setAddressee(save.getProcessManage().getLeader1());
            messageQueue.setContent(save.getNumber() + "-待出库");
            messageQueue.setTitle(MessageQueueTypeUtil.PRODUCT_SEND_AUDIT.toString());
            messageQueue.setUrl(save.getCode() + "");
            messageQueue.setCreateTime(new Date());
            messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);
            messageQueueService.save(messageQueue);
        }

        return save;
    }

    /**
     * 更新产品出库单
     *
     * @param productSendHeader
     * @return
     */
    @Transactional
    public ProductSendHeader update(ProductSendHeader productSendHeader) {
        productSendHeaderRepository.delete(productSendHeader);

        return save(productSendHeader);
    }

    /**
     * 通过code删除
     *
     * @param code
     */
    public void delete(Long code) {
        productSendHeaderRepository.delete(code);
    }

    /**
     * 批量删除
     *
     * @param productSendHeaders
     */
    public void deleteInBatch(Collection<ProductSendHeader> productSendHeaders) {
        productSendHeaderRepository.deleteInBatch(productSendHeaders);
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public ProductSendHeader findOne(Long code) {
        return productSendHeaderRepository.findOne(code);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<ProductSendHeader> findAll() {
        return productSendHeaderRepository.findAll();
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
    public Page<ProductSendHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findAll(pageable);
    }

    /**
     * 通过审核状态查询-分页
     *
     * @param auditStatus
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusByPage(Integer auditStatus, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByAuditStatus(auditStatus, pageable);
    }

    /**
     * 通过创建日期查询-分页
     *
     * @param createDate
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByCreateDateByPage(Long createDate, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByCreateDate(new Date(createDate), pageable);
    }

    /**
     * 通过原料类型查询-分页
     *
     * @param rawType
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByRawTypeByPage(RawType rawType, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByRawType(rawType, pageable);
    }

    /**
     * 通过审核状态和创建时间查询-分页
     *
     * @param auditStatus
     * @param createDate
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndCreateDateByPage(Integer auditStatus, Long createDate, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByAuditStatusAndCreateDate(auditStatus, new Date(createDate), pageable);
    }

    /**
     * 通过审核状态和原料类型查询-分页
     *
     * @param auditStatus
     * @param rawType
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndRawTypeByPage(Integer auditStatus, RawType rawType, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByAuditStatusAndRawType(auditStatus, rawType, pageable);
    }

    /**
     * 通过创建日期和原料类型查询-分页
     *
     * @param createDate
     * @param rawType
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByCreateDateAndRawTypeByPage(Long createDate, RawType rawType, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByCreateDateAndRawType(new Date(createDate), rawType, pageable);
    }

    /**
     * 通过审核状态，创建日期，原料类型查询-分页
     *
     * @param auditStatus
     * @param createDate
     * @param rawType
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ProductSendHeader> findByAuditStatusAndRawTypeAndCreateDateByPage(Integer auditStatus, Long createDate, RawType rawType, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            ProductSendHeader.class.getDeclaredField(sortFieldName);
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
        return productSendHeaderRepository.findByAuditStatusAndRawTypeAndCreateDate(auditStatus, rawType, new Date(createDate), pageable);
    }

    /**
     * 通过审核状态和批号模糊查询
     *
     * @param auditStatus
     * @param number
     * @return
     */
    public List<ProductSendHeader> findByAuditStatusAndNumberLike(Integer auditStatus, String number) {
        if (auditStatus == GlobalUtil.ProductSendStatus.APPROVE || auditStatus == GlobalUtil.ProductSendStatus.NOT_APPROVE) {
            return productSendHeaderRepository.findByAuditStatusGreaterThanAndNumberLike(GlobalUtil.ProductSendStatus.AUDITTING, "%" + number + "%");
        } else {
            return productSendHeaderRepository.findByAuditStatusAndNumberLike(auditStatus, "%" + number + "%");
        }
    }

    /**
     * 通过编码更新
     *
     * @param auditStatus
     * @param code
     */
    @Transactional
    public void updateAuditStatusByCode(Integer auditStatus, String note, Long code, String auditCode) {
        productSendHeaderRepository.updateAuditStatusByCode(auditStatus, code);

        ProductSendHeader productSendHeader = productSendHeaderRepository.findOne(code);
        if (productSendHeader == null) {
            throw new MesException(EnumException.UPDATE_FAILED_PRODUCT_SEND_HEADER_NOT_EXIST);
        }

        User auditor = userRepository.findOne(auditCode);
        if (auditor == null) {
            throw new MesException(EnumException.AUDITOR_NOT_EXIST);
        }

        ProcessManage processManage = null;
        if (productSendHeader.getProcessManage() == null || (processManage = processManageRepository.findOne(productSendHeader.getProcessManage().getCode())) == null) {
            throw new MesException(EnumException.AUDIT_FAILED_PROCESS_MANAGE_NOT_EXIST);
        }

        if (auditStatus == GlobalUtil.ProductSendStatus.NOT_APPROVE) {
            // 生成审核记录
            ProductSendAudit productSendAudit = new ProductSendAudit();
            productSendAudit.setAuditor(auditor);
            productSendAudit.setAuditTime(new Date());
            productSendAudit.setNote(note);
            productSendAudit.setAuditResult(GlobalUtil.PickingAuditStatus.NOT_APPROVE);
            productSendAudit.setProcessManage(processManage);
            productSendAudit.setProductSendHeader(productSendHeader);

            productSendAuditRepository.save(productSendAudit);
            productSendHeaderRepository.updateAuditStatusByCode(GlobalUtil.PickingAuditStatus.NOT_APPROVE, code);

            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setAddressee(productSendHeader.getApplicant());
            messageQueue.setContent(productSendHeader.getNumber() + "-审核结果未通过");
            messageQueue.setTitle(MessageQueueTypeUtil.PRODUCT_SEND_AUDIT.toString());
            messageQueue.setUrl(productSendHeader.getCode() + "");
            messageQueue.setCreateTime(new Date());
            messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);
            messageQueueService.save(messageQueue);
        } else {
            // 生成审核记录
            ProductSendAudit productSendAudit = new ProductSendAudit();
            productSendAudit.setAuditor(auditor);
            productSendAudit.setAuditTime(new Date());
            productSendAudit.setNote(note);
            productSendAudit.setAuditResult(GlobalUtil.PickingAuditStatus.APPROVE);
            productSendAudit.setProcessManage(processManage);
            productSendAudit.setProductSendHeader(productSendHeader);

            productSendAuditRepository.save(productSendAudit);

            if (auditCode.equals(processManage.getLeader1().getCode())) {
                // 发消息给下一位
                if (processManage.getLeader2() != null) {
                    MessageQueue messageQueue = new MessageQueue();
                    messageQueue.setAddressee(productSendHeader.getProcessManage().getLeader1());
                    messageQueue.setContent(productSendHeader.getNumber() + "-待出库");
                    messageQueue.setTitle(MessageQueueTypeUtil.PRODUCT_SEND_AUDIT.toString());
                    messageQueue.setUrl(productSendHeader.getCode() + "");
                    messageQueue.setCreateTime(new Date());
                    messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);
                    messageQueueService.save(messageQueue);
                }
            } else {
                // 审核通过
                productSendHeaderRepository.updateAuditStatusByCode(GlobalUtil.PickingAuditStatus.APPROVE, code);
            }

            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setAddressee(productSendHeader.getApplicant());
            messageQueue.setContent(productSendHeader.getNumber() + "-审核结果通过");
            messageQueue.setTitle(MessageQueueTypeUtil.PRODUCT_SEND_AUDIT.toString());
            messageQueue.setUrl(productSendHeader.getCode() + "");
            messageQueue.setCreateTime(new Date());
            messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);
            messageQueueService.save(messageQueue);
        }
    }

    /**
     * 通过出库状态和编码模糊查询
     *
     * @param outStatus
     * @param number
     * @return
     */
    public List<ProductSendHeader> findByOutStatusAndNumberLike(Integer outStatus, String number) {
        return productSendHeaderRepository.findByOutStatusAndNumberLike(outStatus, "%" + number + "%");
    }

    /**
     * 执行产品出库
     *
     * @param outStatus
     * @param sender
     * @param code
     */
    public void updateOutStatusAndApplicantAndApplyTimeByCode(Integer outStatus, User sender, Long code){
        productSendHeaderRepository.updateOutStatusAndApplicantAndApplyTimeByCode(outStatus, sender, new Date(), code);
    }
}
