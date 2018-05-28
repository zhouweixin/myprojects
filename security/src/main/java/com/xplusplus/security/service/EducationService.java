package com.xplusplus.security.service;

import com.xplusplus.security.domain.Education;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: liweifeng
 * @Description:
 * @Date: Created in 12:34 2018/5/22
 * @Modified By:
 */
@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    /**
     * 新增学历
     * @param education
     * @return
     */
    public Education save(Education education){

        //验证是否存在
        if(education == null
                ||(education.getId() != null && educationRepository.findOne(education.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return educationRepository.save(education);
    }

    /**
     * 更新学历
     * @param education
     * @return
     */
    public Education update(Education education){

        //验证是否存在
        if(education == null || education.getId() == null
                ||educationRepository.findOne(education.getId()) == null)  {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return educationRepository.save(education);
    }

    /**
     * 删除学历
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(educationRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        educationRepository.delete(id);
    }

    /**
     * 批量删除
     * @param educations
     */
    public void deleteInBatch(Collection<Education> educations){
        educationRepository.deleteInBatch(educations);
    }

    /**
     * 通过编号查询
     * @param id
     * @return
     */
    public Education findOne(Integer id){
        return educationRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<Education> findAll(){
        return educationRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<Education> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            Education.class.getDeclaredField(sortFieldName);
        }catch (Exception e){
            //如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort =null;
        if(asc ==0 ){
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        }else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return educationRepository.findAll(pageable);
    }

    /**
     * 通过名称模糊分页查询
     * @param name
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<Education> findByNameLikeByPage(String name, Integer page, Integer size,
                                                String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            Education.class.getDeclaredField(sortFieldName);
        }catch (Exception e){
            //如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort =null;
        if(asc ==0 ){
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        }else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return educationRepository.findByNameLike("%" + name + "%",pageable);
    }
}
