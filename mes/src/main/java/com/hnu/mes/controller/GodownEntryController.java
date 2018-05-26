package com.hnu.mes.controller;

import com.hnu.mes.domain.GodownEntry;
import com.hnu.mes.domain.RawType;
import com.hnu.mes.domain.Result;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.GodownEntryService;
import com.hnu.mes.utils.GlobalUtil;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:10 2018/5/2
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/godownEntry")
public class GodownEntryController {
    @Autowired
    private GodownEntryService godownEntryService;

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
        if(status == GlobalUtil.GODOWN_ENTRY_NOT_TEST){
            return ResultUtil.error(new MesException(EnumException.UPDATE_FAILED_STATUS_NOT_LEGAL));
        }

        godownEntryService.updateStatusByCode(status, code);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     *
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
    @PostMapping(value = "/getAllByPage")
    public Result<Page<GodownEntry>> findAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                   @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                   @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(godownEntryService.findAllByPage(page, size, sort, asc));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<GodownEntry>> findAll(){
        return ResultUtil.success(godownEntryService.findAll());
    }

    /**
     * 通过原料类型和批号模糊查询
     *
     * @param rawTypeCode
     * @param batchNumber
     * @return
     */
    @RequestMapping(value = "/getByRawTypeAndBatchNumberLike")
    public Result<List<GodownEntry>> findByRawTypeAndBatchNumberLike(@RequestParam(value = "rawTypeCode", defaultValue = "0") Long rawTypeCode,
                                                                     @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber){
        RawType rawType = new RawType();
        rawType.setCode(rawTypeCode);
        return ResultUtil.success(godownEntryService.findByRawTypeAndBatchNumberLike(rawType, batchNumber));
    }

    /**
     * 通过原料类型和批号模糊查询-分页
     *
     * @param rawTypeCode
     * @param batchNumber
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByRawTypeAndBatchNumberLikeByPage")
    public Result<Page<GodownEntry>> findByRawTypeAndBatchNumberLikeByPage(@RequestParam(value = "rawTypeCode", defaultValue = "0") Long rawTypeCode,
                                                                           @RequestParam(value = "batchNumber", defaultValue = "") String batchNumber,
                                                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                           @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        RawType rawType = new RawType();
        rawType.setCode(rawTypeCode);
        return ResultUtil.success(godownEntryService.findByRawTypeAndBatchNumberLikeByPage(rawType, batchNumber, page, size, sort, asc));
    }
}
