package com.xplusplus.security.service;

import com.xplusplus.security.domain.Role;
import com.xplusplus.security.exception.EnumExceptions;
import com.xplusplus.security.exception.SecurityExceptions;
import com.xplusplus.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Huxudong
 * @Description: 角色Service
 * @Date: Created in 12:39 2018/5/22
 * @Modified By:
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 新增角色
     */
    public Role save(Role role){

        //验证是否存在
        if(role == null
                ||(role.getId() != null && roleRepository.findOne(role.getId()) != null)  ){
            throw new SecurityExceptions(EnumExceptions.ADD_FAILED_DUPLICATE);
        }
        return roleRepository.save(role);
    }

    /**
     * 更新角色
     */
    public Role update(Role role){

        //验证是否存在
        if(role == null || role.getId() == null
                ||roleRepository.findOne(role.getId()) != null)  {
            throw new SecurityExceptions(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }
        return roleRepository.save(role);
    }

    /**
     * 删除角色
     */
    public void delete(Integer id){

        //验证是否存在
        if(roleRepository.findOne(id) == null){
            throw new SecurityExceptions(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        roleRepository.delete(id);
    }

    /**
     * 批量删除角色
     */
    public void deleteInBatch(Collection<Role> roles){
        roleRepository.deleteInBatch(roles);
    }

    /**
     * 通过编号查询
     */
    public Role findOne(Integer id){
        return roleRepository.findOne(id);
    }

    /**
     * 查询所有
     */
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<Role> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            Role.class.getDeclaredField(sortFieldName);
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
        return roleRepository.findAll(pageable);
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
    public Page<Role> findByNameLikeByPage(String name, Integer page, Integer size,
                                                   String sortFieldName, Integer asc){

        //判断排序字段名是否存在
        try{
            Role.class.getDeclaredField(sortFieldName);
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
        return roleRepository.findByNameLike("%" + name + "%",pageable);
    }
}
