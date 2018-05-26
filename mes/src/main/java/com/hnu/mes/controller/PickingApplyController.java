package com.hnu.mes.controller;

import com.hnu.mes.domain.PickingApply;
import com.hnu.mes.domain.PickingApplyHeader;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.PickingApplyService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 15:23 2018/5/5
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/pickingApply")
public class PickingApplyController {
    @Autowired
    private PickingApplyService pickingApplyService;

    /**
     * 通过编码删除
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/deleteByCode")
    private Result<Object> deleteByCode(Long code){
        pickingApplyService.delete(code);
        return ResultUtil.success();
    }

    /**
     * 批删除
     *
     * @param pickingApplies
     * @return
     */
    @RequestMapping(value = "/deleteByBatchCode")
    private Result<Object> deleteByBatchCode(Set<PickingApply> pickingApplies){
        pickingApplyService.deleteInBatch(pickingApplies);
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
    @PostMapping(value = "/getByPickingApplyHeaderByPage")
    public Result<Page<PickingApply>> getByPickingApplyHeaderByPage(@RequestParam(value = "pickingApplyHeaderCode", defaultValue = "0") Long pickingApplyHeaderCode,
                                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        PickingApplyHeader pickingApplyHeader = new PickingApplyHeader();
        pickingApplyHeader.setCode(pickingApplyHeaderCode);
        return ResultUtil.success(pickingApplyService.findByPickingApplyHeader(pickingApplyHeader, page, size, sort, asc));
    }
}
