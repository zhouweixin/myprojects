package com.hnu.mes.service;

import com.hnu.mes.domain.MessageQueue;
import com.hnu.mes.domain.User;
import com.hnu.mes.domain.UserRole;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.MessageQueueRepository;
import com.hnu.mes.repository.UserRoleRepository;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.MessageQueueTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:36 2018/4/30
 * @Modified By:
 */
@Service
public class MessageQueueService {
    @Autowired
    private MessageQueueRepository messageQueueRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue save(MessageQueue messageQueue) {
        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 发货信息新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue sendSave(MessageQueue messageQueue) {

        if (messageQueue == null) {
            return null;
        }

        // 设置标题
        messageQueue.setTitle(MessageQueueTypeUtil.SEND_NOT_RECEIVE.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        // 设置接收人:库管
        UserRole userRole = userRoleRepository.findFirstByRoleCode(6);
        if (userRole != null) {
            User user = new User();
            user.setCode(userRole.getUserCode());
            messageQueue.setAddressee(user);
        } else {
            throw new MesException(EnumException.WAREHOUSE_KEEPER_NOT_EXIST);
        }

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 化验通知信息新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue testInformSave(MessageQueue messageQueue) {
        if (messageQueue == null) {
            return null;
        }

        // 标题
        messageQueue.setTitle(MessageQueueTypeUtil.RAW_GODOWN_TEST_INFORM.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 原料出库信息新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue pickingSave(MessageQueue messageQueue) {
        if (messageQueue == null) {
            return null;
        }

        // 设置标题
        messageQueue.setTitle(MessageQueueTypeUtil.RAW_PICKING.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 原料出库审批新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue pickingAuditSave(MessageQueue messageQueue) {
        if (messageQueue == null) {
            return null;
        }

        // 设置标题
        messageQueue.setTitle(MessageQueueTypeUtil.RAW_PICKING_AUDIT.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 仓库备货信息新增
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue godownPrepareGoods(MessageQueue messageQueue) {
        if (messageQueue == null) {
            return null;
        }

        // 设置标题
        messageQueue.setTitle(MessageQueueTypeUtil.GODOWN_PREPARE_GOODS.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        // 设置接收人:库管
        UserRole userRole = userRoleRepository.findFirstByRoleCode(6);
        if (userRole != null) {
            User user = new User();
            user.setCode(userRole.getUserCode());
            messageQueue.setAddressee(user);
        } else {
            throw new MesException(EnumException.WAREHOUSE_KEEPER_NOT_EXIST);
        }

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 审核通知
     *
     * @param messageQueue
     * @return
     */
    public MessageQueue auditSave(MessageQueue messageQueue) {
        if (messageQueue == null) {
            return null;
        }

        // 设置标题
        messageQueue.setTitle(MessageQueueTypeUtil.RAW_PICKING_AUDIT.toString());

        // 设置创建日期
        messageQueue.setCreateTime(new Date());

        // 设置状态
        messageQueue.setStatus(GlobalUtil.MESSAGE_NOT_READ);

        return messageQueueRepository.save(messageQueue);
    }

    /**
     * 通过编码删除
     *
     * @param code
     */
    public void delete(Long code) {
        messageQueueRepository.delete(code);
    }

    /**
     * 通过状态和用户查询信息
     *
     * @param status
     * @param addressee
     * @return
     */
    public List<MessageQueue> findByStatusAndAddressee(Integer status, User addressee) {
        return messageQueueRepository.findByStatusAndAddressee(status, addressee);
    }

    /**
     * 通过状态和用户查询信息
     *
     * @param status
     * @param addressee
     * @return
     */
    public Page<MessageQueue> findByStatusAndAddresseeByPage(Integer status, User addressee, Integer page, Integer size, String sortFieldName, Integer asc) {
        // 判断字段名是否存在
        try {
            MessageQueue.class.getDeclaredField(sortFieldName);
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

        return messageQueueRepository.findByStatusAndAddressee(status, addressee, pageable);
    }

    /**
     * 通过用户查询信息
     *
     * @param addressee
     * @return
     */
    public List<MessageQueue> findByAddressee(User addressee) {
        return messageQueueRepository.findByAddressee(addressee);
    }

    /**
     * 通过用户查询信息
     *
     * @param addressee
     * @return
     */
    public Page<MessageQueue> findByAddresseeByPage(User addressee, Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断字段名是否存在
        try {
            MessageQueue.class.getDeclaredField(sortFieldName);
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

        return messageQueueRepository.findByAddressee(addressee, pageable);
    }

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Transactional
    public void updateStatusByCode(Integer status, Long code) {

        MessageQueue messageQueue = messageQueueRepository.findOne(code);

        if (messageQueue == null) {
            throw new MesException(EnumException.UPDATE_FAILED_NOT_EXIST);
        }

        if (messageQueue.getStatus() == GlobalUtil.MESSAGE_NOT_READ && status == GlobalUtil.MESSAGE_READ) {
            messageQueueRepository.updateStatusAndReadTimeByCode(status, new Date(), code);
        } else {
            throw new MesException(EnumException.UPDATE_FAILED_STATUS_NOT_LEGAL);
        }
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    public MessageQueue findOne(Long code) {
        return messageQueueRepository.findOne(code);
    }
}
