package com.xplusplus.security.controller;

import com.xplusplus.security.domain.Education;
import com.xplusplus.security.domain.Result;
import com.xplusplus.security.service.EducationService;
import com.xplusplus.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * @Author: liweifeng
 * @Description:
 * @Date: Created in 12:41 2018/5/22
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    /**
     * 新增学历
     * @param education
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    public Result<Education> add(@Valid Education education, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(educationService.save(education));
    }

    /**
     * 更新学历
     * @param education
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/update")
    public Result<Education> update(@Valid Education education, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(educationService.update(education));
    }

    /**
     * 删除学历
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById")
    public Result<Object> deleteById(Integer id){
        educationService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     * @param educations
     * @return
     */
    @RequestMapping(value = "/deleteByIdBatch")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<Education> educations) {
        educationService.deleteInBatch(educations);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById")
    public Result<Education> getById(Integer id) {
        return ResultUtil.success(educationService.findOne(id));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getAll")
    public Result<List<Education>> getAll() {
        return ResultUtil.success(educationService.findAll());

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
    public Result<Page<Education>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {
        return ResultUtil.success(educationService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过名称模糊查询-分页
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    @RequestMapping(value = "/getByNameLikeByPage")
    public Result<Page<Education>> getByNameLikeByPage(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                       @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                       @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(educationService.findByNameLikeByPage(name, page, size, sortFieldName, asc));
    }
}
