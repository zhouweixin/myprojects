package com.hnu.mes.repository;

import com.hnu.mes.domain.MessageQueue;
import com.hnu.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 21:33 2018/4/30
 * @Modified By:
 */
@Repository
public interface MessageQueueRepository extends JpaRepository<MessageQueue, Long> {
    /**
     * 通过状态和用户查询所有信息
     *
     * @param status
     * @param addressee
     * @return
     */
    public List<MessageQueue> findByStatusAndAddressee(Integer status, User addressee);

    /**
     * 通过状态和用户查询所有信息
     *
     * @param status
     * @param addressee
     * @return
     */
    public Page<MessageQueue> findByStatusAndAddressee(Integer status, User addressee, Pageable pageable);

    /**
     * 通过用户查询所有信息
     *
     * @param addressee
     * @return
     */
    public List<MessageQueue> findByAddressee(User addressee);

    /**
     * 通过用户查询所有信息
     *
     * @param addressee
     * @return
     */
    public Page<MessageQueue> findByAddressee(User addressee, Pageable pageable);

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     */
    @Modifying
    @Query(value = "update MessageQueue set status=?1 where code=?2")
    public void updateStatusByCode(Integer status, Long code);

    @Modifying
    @Query(value = "update MessageQueue set status=?1, readTime=?2 where code=?3")
    public void updateStatusAndReadTimeByCode(Integer status, Date readTime, Long code);
}
