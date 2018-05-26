package com.hnu.mes.controller;

import com.hnu.mes.domain.*;
import com.hnu.mes.exception.EquipmentException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.service.EqRepairApplicationService;
import com.hnu.mes.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanyage on 2018/3/18.
 */
@RestController
@RequestMapping("/equipment")
public class EqRepairApplicationController {

    @Autowired
    private EqRepairApplicationService eqRepairApplicationService;

    /**
     * 查询所有记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @return
     */
    @PostMapping("/listApplicationsInPages")
    public Result getAllInPage(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               @RequestParam(value = "sort", defaultValue = "code") String sortField,
                               @RequestParam(value = "asc", defaultValue = "1") int asc) {
        return ResultUtil.success(eqRepairApplicationService.getAllInPages(pageNumber, pageSize, sortField, asc));
    }

    /**
     * 通过类型查询记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param flag
     * @return
     */
    @PostMapping("/findByFlagInPages")
    public Result findByFlag(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                             @RequestParam(value = "size", defaultValue = "10") int pageSize,
                             @RequestParam(value = "sort", defaultValue = "code") String sortField,
                             @RequestParam(value = "asc", defaultValue = "1") int asc,
                             Flag flag) {
        return ResultUtil.success(eqRepairApplicationService.findByFlagInPages(pageNumber, pageSize, sortField, asc, flag));
    }

    /**
     * 通过申请时间查询记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param date
     * @return
     */
    @PostMapping("/findByApplicationTimeInPages")
    public Result findByApplicationTimeInPages(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                               @RequestParam(value = "asc", defaultValue = "1") int asc,
                                               Date date) {
        return ResultUtil.success(eqRepairApplicationService.findByApplicationTimeInPages(pageNumber, pageSize, sortField, asc, date));
    }

    /**
     * 通过类型和时间查询记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param webBean
     * @return
     */
    @PostMapping("/findByFlagAndApplicationTimeInPages")
    public Result findByFlagAndApplicationTimeInPages(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                      @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                                      @RequestParam(value = "asc", defaultValue = "1") int asc,
                                                      EqRepairApplication webBean) {
        return ResultUtil.success(eqRepairApplicationService.findByFlagAndApplicationTimeInPages(pageNumber, pageSize, sortField, asc, webBean.getFlag(), webBean.getApplicationTime()));
    }

    /**
     * 通过设备名称查询记录
     *
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param equipment
     * @return
     */
    @PostMapping("/findByEquipmentNameInPages")
    public Result findByEquipmentNameInPages(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                             @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                             @RequestParam(value = "asc", defaultValue = "1") int asc,
                                             Equipment equipment) {
        return ResultUtil.success(eqRepairApplicationService.findByEquipmentNameInPages(pageNumber, pageSize, sortField, asc, equipment));
    }

    /**
     * 删除申请
     *
     * @param code
     * @return
     */
    @PostMapping("/deleteApplication")
    public Result deleteApplication(@RequestParam("code") String code) {
        EqRepairApplication one = new EqRepairApplication();
        one.setCode(code);
        eqRepairApplicationService.deleteOne(one);
        return ResultUtil.success();
    }

    /**
     * 批量删除申请
     *
     * @param ones
     * @return
     */
    @PostMapping("/deleteApplicationsInBatch")
    public Result deleteInBatch(@RequestBody List<EqRepairApplication> ones) {
        eqRepairApplicationService.deleteInBatch(ones);
        return ResultUtil.success();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam("code") String code) {
        EqRepairApplication application = eqRepairApplicationService.findByCode(code);
        return ResultUtil.success(application);
    }

    /**
     * 申请
     *
     * @param webBean
     * @return
     */
    @PostMapping("/apply")
    public Result apply(EqRepairApplication webBean) {
        try {
            eqRepairApplicationService.apply(webBean);
        }catch (MesException e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 接单
     *
     * @param webBean
     * @return
     */
    @PostMapping("/accept")
    public Result accept(EqRepairApplication webBean) {
        eqRepairApplicationService.accept(webBean);
        return ResultUtil.success();
    }

    /**
     * 完成修理
     *
     * @param webBean
     * @return
     */
    @PostMapping("/finish")
    public Result finish(EqRepairApplication webBean) {
        eqRepairApplicationService.finish(webBean);
        return ResultUtil.success();
    }

    /**
     * 评价
     *
     * @param webBean
     * @return
     */
    @PostMapping("/evaluate")
    public Result evaluate(EqRepairApplication webBean) {
        eqRepairApplicationService.evaluate(webBean);
        return ResultUtil.success();
    }

    /**
     * 按照申请人查找
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param applicationPerson
     * @return
     */
    @PostMapping("/findByApplicationPerson")
    public Result findByApplicationPerson(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                          @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                          @RequestParam(value = "asc", defaultValue = "1") int asc,
                                          User applicationPerson) {
        return ResultUtil.success(eqRepairApplicationService.findByApplicationPerson(pageNumber, pageSize, sortField, asc, applicationPerson));
    }

    /**
     * 按照维修人查找
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param repairMan
     * @return
     */
    @PostMapping("/findByRepairMan")
    public Result findByRepairMan(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                  @RequestParam(value = "asc", defaultValue = "1") int asc,
                                  User repairMan) {
        return ResultUtil.success(eqRepairApplicationService.findByRepairMan(pageNumber, pageSize, sortField, asc, repairMan));
    }

    /**
     * 按照评价人查找
     * @param pageNumber
     * @param pageSize
     * @param sortField
     * @param asc
     * @param evaluator
     * @return
     */
    @PostMapping("/findByEvaluator")
    public Result findByEvaluator(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "sort", defaultValue = "code") String sortField,
                                  @RequestParam(value = "asc", defaultValue = "1") int asc,
                                  User evaluator) {
        return ResultUtil.success(eqRepairApplicationService.findByEvaluator(pageNumber, pageSize, sortField, asc, evaluator));
    }
}

