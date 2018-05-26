package com.hnu.mes.controller;

import com.hnu.mes.domain.MessageQueue;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.User;
import com.hnu.mes.service.MessageQueueService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 10:53 2018/5/2
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/messageQueue")
public class MessageQueueController {

    @Autowired
    private MessageQueueService messageQueueService;

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getByCode")
    public Result<MessageQueue> getByCode(@RequestParam(value = "code") Long code){
        return ResultUtil.success(messageQueueService.findOne(code));
    }

    /**
     * 通过状态和用户编码查询
     *
     * @param status
     * @param addresseeCode
     * @return
     */
    @RequestMapping(value = "/getByStatusAndAddresseeCode")
    public Result<List<MessageQueue>> getByStatusAndAddresseeCode(@RequestParam(value = "status") Integer status,
                                                                  @RequestParam(value = "addresseeCode") String addresseeCode){
        User addressee = new User();
        addressee.setCode(addresseeCode);

        return ResultUtil.success(messageQueueService.findByStatusAndAddressee(status, addressee));
    }

    /**
     * 通过状态和用户编码查询-分页
     *
     * @param addresseeCode
     *            用户编码
     * @param page
     *            当前页,从0开始,默认是0
     * @param size
     *            每页的记录数,默认是10
     * @param sort
     *            排序的字段名,默认是code
     * @param asc
     *            排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByStatusAndAddresseeCodeByPage")
    public Result<Page<MessageQueue>> findAllByLikeNameByPage(
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "addresseeCode") String addresseeCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        User addressee = new User();
        addressee.setCode(addresseeCode);
        return ResultUtil.success(messageQueueService.findByStatusAndAddresseeByPage(status, addressee, page, size, sort, asc));
    }

    /**
     * 通过用户编码查询
     *
     * @param addresseeCode
     * @return
     */
    @RequestMapping(value = "/getByAddresseeCode")
    public Result<List<MessageQueue>> getByStatusAndAddresseeCode(@RequestParam(value = "addresseeCode") String addresseeCode){
        User addressee = new User();
        addressee.setCode(addresseeCode);

        return ResultUtil.success(messageQueueService.findByAddressee(addressee));
    }

    /**
     * 通过用户编码查询-分页
     *
     * @param addresseeCode
     *            用户编码
     * @param page
     *            当前页,从0开始,默认是0
     * @param size
     *            每页的记录数,默认是10
     * @param sort
     *            排序的字段名,默认是code
     * @param asc
     *            排序的方式,0是减序,1是增序,默认是增序
     * @return
     */
    @PostMapping(value = "/getByAddresseeCodeByPage")
    public Result<Page<MessageQueue>> findAllByLikeNameByPage(
            @RequestParam(value = "addresseeCode") String addresseeCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        User addressee = new User();
        addressee.setCode(addresseeCode);
        return ResultUtil.success(messageQueueService.findByAddresseeByPage(addressee, page, size, sort, asc));
    }

    /**
     * 通过编码更新状态
     *
     * @param status
     * @param code
     * @return
     */
    @RequestMapping(value = "/updateStatusByCode")
    public Result<Object> updateStatusByCode(@RequestParam(value = "status") Integer status,
                                             @RequestParam(value = "code") Long code){
        messageQueueService.updateStatusByCode(status, code);
        return ResultUtil.success();
    }
}
