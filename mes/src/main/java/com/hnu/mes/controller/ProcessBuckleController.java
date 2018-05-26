package com.hnu.mes.controller;

import com.hnu.mes.domain.ProcessBuckle;
import com.hnu.mes.domain.Result;
import com.hnu.mes.domain.Status;
import com.hnu.mes.domain.User;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.ProcessBuckleService;
import com.hnu.mes.service.StatusService;
import com.hnu.mes.service.UserService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhengyibin
 * @Date: 2018/3/20
 * @Description:
 */
@RestController
@RequestMapping(value = "/processBuckle")
public class ProcessBuckleController {
    @Autowired
    private ProcessBuckleService processBuckleService;


    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    /**
     * 发布：更新发布
     *
     * @param publisherCode
     * @param statusCode
     * @param code
     * @return
     */
    @PostMapping(value = "/updatePublishByCode")
    public Result<ProcessBuckle> updatePublishByCode(@RequestParam(name = "publisherCode", defaultValue = "") String publisherCode,
                                                     @RequestParam(name = "statusCode", defaultValue = "3") Integer statusCode,
                                                     @RequestParam(name = "code", defaultValue = "") Long code) {

        Status status = statusService.findOne(statusCode);

        if (status == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }

        User publisher = userService.findOne(publisherCode);
        if (publisher == null) {
            return ResultUtil.error(new MesException(EnumException.PUBLISHER_NOT_EXIST));
        }

        ProcessBuckle processBuckle = processBuckleService.findOne(code);
        if(processBuckle == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if(processBuckle.getStatus().getCode() > 2){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_PUBLISH));
        }else if(processBuckle.getStatus().getCode() < 2){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_AUDIT));
        }

        Integer i = processBuckleService.updatePublishByCode(publisher, status, code);
        if (i > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
    }

    /**
     * 审核：更新审核
     *
     * @param auditorCode
     * @param statusCode
     * @param code
     * @return
     */
    @PostMapping(value = "/updateAuditByCode")
    public Result<Object> updateAuditByCode(
            @RequestParam(value = "auditorCode", defaultValue = "") String auditorCode,
            @RequestParam(value = "statusCode", defaultValue = "2") Integer statusCode,
            @RequestParam(name = "code", defaultValue = "") Long code) {
        Status status = statusService.findOne(statusCode);

        if (status == null) {
            return ResultUtil.error(new MesException(EnumException.STATUS_NOT_EXIST));
        }

        User auditor = userService.findOne(auditorCode);
        if (auditor == null) {
            return ResultUtil.error(new MesException(EnumException.AUDITOR_NOT_EXIST));
        }

        ProcessBuckle processBuckle = processBuckleService.findOne(code);
        if(processBuckle == null){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
        }

        if(processBuckle.getStatus().getCode() > 1){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_AUDIT));
        }

        int i = processBuckleService.updateAuditByCode(auditor, status, code);
        if (i > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_NOT_EXIST));
    }

    /**
     * 通过编码查询
     *
     * @param code
     * @return
     */
    @PostMapping(value = "/getByCode")
    public Result<ProcessBuckle> findOne(@RequestParam(value = "code") Long code) {

        return ResultUtil.success(processBuckleService.findOne(code));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result<List<ProcessBuckle>> findAll() {
        return ResultUtil.success(processBuckleService.findAll());
    }


    /**
     * 通过状态编码查询-分页
     *
     * @param statusCode
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getAllByStatusCodeByPage")
    public Result<Page<ProcessBuckle>> getAllByStatusCodeByPage(@RequestParam(value = "statusCode", defaultValue = "2") Integer statusCode,
                                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(processBuckleService.getAllByStatusCodeByPage(statusCode, page, size, sort, asc));
    }

    /**
     * 通过批号模糊查询-分页
     *
     * @param batchNumber
     * @param statusCode
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @PostMapping(value = "/getByLikeBatchNumberByPage")
    public Result<Page<ProcessBuckle>> findByBatchNumberLikeAndStatusCode(
            @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber,
            @RequestParam(value = "statusCode", defaultValue = "2") Integer statusCode,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "code") String sort,
            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 分页模糊查询所有
         * @param [code, status, page, size, sort, asc]
         */
        return ResultUtil.success(processBuckleService.findByBatchNumberLikeAndStatusCode(batchNumber, statusCode, page, size, sort, asc));
    }
}
