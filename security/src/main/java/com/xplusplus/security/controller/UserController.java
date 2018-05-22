package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Result;
import com.xplusplus.security.domain.User;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.service.UserService;
import com.xplusplus.security.utils.GlobalUtil;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Pattern;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:37 2018/5/7
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add")
    public Result<User> add(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().toString());
        }

        String maxId = userService.findMaxId();
        if(maxId == null){
            maxId = "0";
        }

        // 全是数字
        boolean b = Pattern.matches("^\\d+$", maxId);
        if(b == false){
            return ResultUtil.error(String.format("工号只能包含数字, 发现异常工号:%s, 请删除后再新增", maxId));
        }

        long id = Long.parseLong(maxId);
        String idStr = String.format("%0" + GlobalUtil.USER_ID_LEN + "d", id);
        user.setId(String.format("%0" + GlobalUtil.USER_ID_LEN + "d", id+1));

        return ResultUtil.success(userService.save(user));
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<User> getById(@RequestParam(name = "id", defaultValue = "0") String id){
        return ResultUtil.success(userService.findOne(id));
    }

    /**
     * 通过工号删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(@RequestParam(name = "id", defaultValue = "0") String id){
        if(userService.findOne(id) == null){
            return ResultUtil.error(new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST));
        }

        userService.delete(id);
        return ResultUtil.success();
    }
}
