package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.*;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 11:00 2018/5/9
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/available")
public class AvailableController {

    @Autowired
    private RawTypeService rawTypeService;

    @Autowired
    private AvailableRawPresomaService availableRawPresomaService;

    @Autowired
    private AvailableRawLithiumService availableRawLithiumService;

    @Autowired
    private AvailableProductService availableProductService;

    @Autowired
    private RawPresomaService rawPresomaService;

    @Autowired
    private RawLithiumService rawLithiumService;

    @Autowired
    private ProductService productService;

    /**
     * 查询可用库存-分页
     *
     * @param rawTypeCode
     * @param batchNumber
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByRawTypeCodeAndBatchNumberLikeByPage")
    public Result<Page> getByRawTypeCodeAndBatchNumberLikeByPage(@RequestParam(value = "rawTypeCode", defaultValue = "1") Long rawTypeCode,
                                                           @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber,
                                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        RawType rawType = rawTypeService.findOne(rawTypeCode);
        if (rawType == null || rawType.getDataTableName() == null) {
            return ResultUtil.error(new MesException(EnumException.FIND_FAILED_RAW_TYPE_NOT_EXIST));
        }

        Page availabel = null;
        switch (rawType.getDataTableName().toLowerCase().trim()) {
            case "available_raw_presoma":
                availabel = availableRawPresomaService.findByBatchNumberLikeByPage(batchNumber, page, size, sort, asc);
                break;
            case "available_raw_lithium":
                availabel = availableRawLithiumService.findByBatchNumberLikeByPage(batchNumber, page, size, sort, asc);
                break;
            case "available_product":
                availabel = availableProductService.findByBatchNumberLikeByPage(batchNumber, page, size, sort, asc);
                break;
            default:
                return ResultUtil.error(new MesException(EnumException.FIND_FAILED_TABLE_NAME_NOT_EXIST));
        }

        return ResultUtil.success(availabel);
    }

    /**
     * 查询可用库存
     *
     * @param rawTypeCode
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getByRawTypeCodeAndBatchNumberLike")
    public Result<List> getByRawTypeCodeAndBatchNumberLike(@RequestParam(value = "rawTypeCode", defaultValue = "1") Long rawTypeCode,
                                                                 @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber) {
        RawType rawType = rawTypeService.findOne(rawTypeCode);
        if (rawType == null || rawType.getDataTableName() == null) {
            return ResultUtil.error(new MesException(EnumException.FIND_FAILED_RAW_TYPE_NOT_EXIST));
        }

        List availabels = null;
        switch (rawType.getDataTableName().toLowerCase().trim()) {
            case "available_raw_presoma":
                availabels = availableRawPresomaService.findByBatchNumberLike(batchNumber);
                break;
            case "available_raw_lithium":
                availabels = availableRawLithiumService.findByBatchNumberLike(batchNumber);
                break;
            case "available_product":
                availabels = availableProductService.findByBatchNumberLike(batchNumber);
                break;
            default:
                return ResultUtil.error(new MesException(EnumException.FIND_FAILED_TABLE_NAME_NOT_EXIST));
        }

        return ResultUtil.success(availabels);
    }

    /**
     * 通过原料类型编码和批号查询详细
     *
     * @param rawTypeCode
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getDetailByRawTypeCodeAndBatchNumber")
    public Result getDetailByRawTypeCodeAndBatchNumber(@RequestParam(value = "rawTypeCode", defaultValue = "1") Long rawTypeCode,
                                                       @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber){
        RawType rawType = rawTypeService.findOne(rawTypeCode);
        if (rawType == null || rawType.getDataTableName() == null) {
            return ResultUtil.error(new MesException(EnumException.FIND_FAILED_RAW_TYPE_NOT_EXIST));
        }

        Object o = null;
        switch (rawType.getDataTableName().toLowerCase().trim()) {
            case "available_raw_presoma":
                o = rawPresomaService.findFirstByBatchNumber(batchNumber);
                break;
            case "available_raw_lithium":
                o = rawLithiumService.findFirstByBatchNumber(batchNumber);
                break;
            case "available_product":
                o = productService.findFirstByBatchNumber(batchNumber);
                break;
            default:
                return ResultUtil.error(new MesException(EnumException.FIND_FAILED_TABLE_NAME_NOT_EXIST));
        }

        return ResultUtil.success(o);
    }
}
