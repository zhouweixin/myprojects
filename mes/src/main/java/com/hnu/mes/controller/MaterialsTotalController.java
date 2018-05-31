package com.hnu.mes.controller;

import com.hnu.mes.domain.MaterialsTotal;
import com.hnu.mes.domain.Result;
import com.hnu.mes.service.MaterialsTotalService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 下午8:03:11 2018年5月30日
 */
@RestController
@RequestMapping(value = "/materialsTotal")
public class MaterialsTotalController {
    @Autowired
    private MaterialsTotalService materialsTotalService;

    /**
     * 通过状态查询-分页
     *
     * @param status
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByStatusByPage")
    public Result<Page<MaterialsTotal>> getByStatusByPage(@RequestParam(value = "status", defaultValue = "0") Integer status,
                                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(materialsTotalService.findByStatusByPage(status, page, size, sort, asc));
    }

    /**
     * 通过预警状态查询-分页
     *
     * @param warnStatus
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByWarnStatusByPage")
    public Result<Page<MaterialsTotal>> getByWarnStatusByPage(@RequestParam(value = "warnStatus", defaultValue = "0") Integer warnStatus,
                                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                          @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                          @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(materialsTotalService.findByWarnStatusByPage(warnStatus, page, size, sort, asc));
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sort
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<MaterialsTotal>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                     @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                     @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(materialsTotalService.findAllByPage(page, size, sort, asc));
    }

    /**
     * 开始盘库
     *
     * @return
     */
    @RequestMapping(value = "/startStock")
    public Result<Object> startStock(){
        materialsTotalService.updateAllStatus(1);
        return ResultUtil.success();
    }

    /**
     * 结束盘库
     *
     * @return
     */
    @RequestMapping(value = "/endStock")
    public Result<Object> endStock(){
        materialsTotalService.updateAllStatus(0);
        return ResultUtil.success();
    }
}
