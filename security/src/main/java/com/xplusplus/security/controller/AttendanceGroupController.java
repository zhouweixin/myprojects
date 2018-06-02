package com.xplusplus.security.controller;

import com.xplusplus.security.domain.AttendanceGroupLeader;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.AttendanceGroup;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.service.AttendanceGroupLeaderService;
import com.xplusplus.security.service.AttendanceGroupService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * @author：XudongHu
 * @description: 考勤组Controller
 * @date:15:38 2018/5/29
 */
@RestController
@RequestMapping(value = "/attendanceGroup")
public class AttendanceGroupController {
    @Autowired
    private AttendanceGroupService attendanceGroupService;
    /**
     * 新增考勤组
     */
    @RequestMapping(value = "/add")
    public Result<AttendanceGroup> add(@Valid AttendanceGroup attendanceGroup, BindingResult bindingResult,
                                       String[] ids, String[] leaderIds){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(attendanceGroupService.save(attendanceGroup, new HashSet<String>(Arrays.asList(ids)), new HashSet<String>(Arrays.asList(leaderIds))));
    }
    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public Result<AttendanceGroup> update(@Valid AttendanceGroup attendanceGroup, BindingResult bindingResult,
                                        String[] ids, String[] leaderIds ){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(attendanceGroupService.update(attendanceGroup,new HashSet<String>(Arrays.asList(ids)), new HashSet<String>(Arrays.asList(leaderIds))));
    }

    /**
     * 删除考勤组
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        attendanceGroupService.delete(id);
        return ResultUtil.success();
    }


    /**
     * 通过id查询
     */
    @RequestMapping(value = "/getById")
    public Result<AttendanceGroup> getById(Integer id) {
        return ResultUtil.success(attendanceGroupService.findOne(id));
    }

    /**
     * 查询考勤组员工
     */
    @RequestMapping(value="/getUsers")
    public Result<List<User>> findUser(Integer id){
        return ResultUtil.success(attendanceGroupService.findUser(id));
    }

    /**
     * 查询考勤组负责人
     */
    @RequestMapping(value="/getLeaders")
    public Result<List<AttendanceGroupLeader>> findLeader(Integer id){
        return ResultUtil.success(attendanceGroupService.findLeaders(id));
    }

    /**
     * 查询所有
     */
    @RequestMapping(value = "/getAll")
    public Result<List<AttendanceGroup>> getAll() {
        return ResultUtil.success(attendanceGroupService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @RequestMapping(value = "/getAllByPage")
    public Result<Page<AttendanceGroup>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(attendanceGroupService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<AttendanceGroup>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                      @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                      @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(attendanceGroupService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }

}
