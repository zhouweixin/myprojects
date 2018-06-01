package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.domain.WorkLogging;
import com.xplusplus.security.domain.WorkLoggingStatus;
import com.xplusplus.security.service.WorkLoggingService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 22:19 2018/6/1
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/workLogging")
public class WorkLoggingController {
    @Autowired
    private WorkLoggingService workLoggingService;

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<WorkLogging>> getAll() {
        return ResultUtil.success(workLoggingService.findAll());
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<WorkLogging>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                             @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(workLoggingService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 
     * @param user
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByUserByPage")
    public Result<Page<WorkLogging>> getByUserByPage(User user, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                  @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                  @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(workLoggingService.findByUserByPage(user, page, size, sortFieldName, asc));
    }
}
