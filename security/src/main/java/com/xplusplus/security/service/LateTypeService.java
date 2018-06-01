package com.xplusplus.security.service;

import com.xplusplus.security.domain.LateType;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.LateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LateTypeService {

    @Autowired
    private LateTypeRepository lateTypeRepository;

    /**
     * 新增迟到类型
     * @param lateType
     * @return
     */
    public LateType save(LateType lateType){

        //验证是否存在
        if(lateType == null
                ||(lateType.getId() != null && lateTypeRepository.findOne(lateType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return lateTypeRepository.save(lateType);
    }


    /**
     * 更新迟到类型
     * @param lateType
     * @return
     */
    public LateType update(LateType lateType){

        //验证是否存在
        if(lateType == null || lateType.getId() == null
                ||lateTypeRepository.findOne(lateType.getId()) == null)  {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return lateTypeRepository.save(lateType);
    }


    /**
     * 删除迟到类型
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(lateTypeRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        lateTypeRepository.delete(id);
    }


    /**
     * 批量删除
     * @param lateTypes
     */
    public void deleteInBatch(Collection<LateType> lateTypes){
        lateTypeRepository.deleteInBatch(lateTypes);
    }


    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public LateType findOne(Integer id){
        return lateTypeRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<LateType> findAll(){
        return lateTypeRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<LateType> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            LateType.class.getDeclaredField(sortFieldName);
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
        return lateTypeRepository.findAll(pageable);
    }
}
