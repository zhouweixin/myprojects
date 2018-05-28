package com.xplusplus.security.service;

import com.xplusplus.security.domain.ContractStatus;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.ContractStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContractStatusService {

    @Autowired
    private ContractStatusRepository contractStatusRepository;

    /**
     * 新增学历
     * @param contractStatus
     * @return
     */
    public ContractStatus save(ContractStatus contractStatus){

        //验证是否存在
        if(contractStatus == null
                ||(contractStatus.getId() != null && contractStatusRepository.findOne(contractStatus.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return contractStatusRepository.save(contractStatus);
    }

    /**
     * 更新学历
     * @param contractStatus
     * @return
     */
    public ContractStatus update(ContractStatus contractStatus){

        //验证是否存在
        if(contractStatus == null || contractStatus.getId() == null
                ||contractStatusRepository.findOne(contractStatus.getId()) == null)  {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return contractStatusRepository.save(contractStatus);
    }

    /**
     * 删除学历
     * @param id
     */
    public void delete(Integer id){

        //验证是否存在
        if(contractStatusRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        contractStatusRepository.delete(id);
    }

    /**
     * 批量删除
     * @param contractStatuss
     */
    public void deleteInBatch(Collection<ContractStatus> contractStatuss){
        contractStatusRepository.deleteInBatch(contractStatuss);
    }

    /**
     * 通过编号查询
     * @param id
     * @return
     */
    public ContractStatus findOne(Integer id){
        return contractStatusRepository.findOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<ContractStatus> findAll(){
        return contractStatusRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<ContractStatus> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ContractStatus.class.getDeclaredField(sortFieldName);
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
        return contractStatusRepository.findAll(pageable);
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
    public Page<ContractStatus> findByNameLikeByPage(String name, Integer page, Integer size,
                                                String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            ContractStatus.class.getDeclaredField(sortFieldName);
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
        return contractStatusRepository.findByNameLike("%" + name + "%",pageable);
    }
}
