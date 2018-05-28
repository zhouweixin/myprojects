package com.xplusplus.security.service;

import com.xplusplus.security.domain.ContractType;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContractTypeService {

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    /**
     * 新增学历
     * @param contractType
     * @return
     */
    public ContractType save(ContractType contractType){

        //验证是否存在
        if(contractType == null
                ||(contractType.getId() != null && contractTypeRepository.findOne(contractType.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return contractTypeRepository.save(contractType);
    }

    /**
     * 更新学历
     * @param contractType
     * @return
     */
    public ContractType update(ContractType contractType){

        //验证是否存在
        if(contractType == null || contractType.getId() == null
                ||contractTypeRepository.findOne(contractType.getId()) == null)  {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return contractTypeRepository.save(contractType);
    }

    /**
     * 删除学历
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(contractTypeRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        contractTypeRepository.delete(id);
    }

    /**
     * 批量删除
     * @param contractTypes
     */
    public void deleteInBatch(Collection<ContractType> contractTypes){
        contractTypeRepository.deleteInBatch(contractTypes);
    }

    /**
     * 通过编号查询
     * @param id
     * @return
     */
    public ContractType findOne(Integer id){
        return contractTypeRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<ContractType> findAll(){
        return contractTypeRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ContractType> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ContractType.class.getDeclaredField(sortFieldName);
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
        return contractTypeRepository.findAll(pageable);
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
    public Page<ContractType> findByNameLikeByPage(String name, Integer page, Integer size,
                                                String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ContractType.class.getDeclaredField(sortFieldName);
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
        return contractTypeRepository.findByNameLike("%" + name + "%",pageable);
    }
}
