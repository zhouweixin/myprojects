package com.hnu.mes.service;

import com.hnu.mes.domain.Operation;
import com.hnu.mes.exception.EnumException;
import com.hnu.mes.exception.MesException;
import com.hnu.mes.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 16:04 2018/4/13
 * @Modified By:
 */
@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public Operation save(Operation operation) {
        /**
         * save
         * @Desciption 新增
         * @param [operation]
         */
        return operationRepository.save(operation);
    }

    public void delete(Integer code) {
        /**
         * delete
         * @Desciption 删除
         * @param [code]
         */
        operationRepository.delete(code);
    }

    public Operation findOne(Integer code) {
        /**
         * findOne
         * @Desciption 查询
         * @param [code]
         */
        return operationRepository.findOne(code);
    }

    public List<Operation> findAll() {
        /**
         * findAll
         * @Desciption 查找全部
         * @param []
         */
        return operationRepository.findAll();
    }

    public Page<Operation> getOperationByPage(Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * getOperationByPage
         * @Desciption
         * @param [page, size, sortFieldName, asc]
         * @throws Exception
         */

        try {
            Operation.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            //降序，desc
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            //升序，asc
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return operationRepository.findAll(pageable);
    }

    public Page<Operation> findAllByLikeNameByPage(String name, Integer page, Integer size, String sortFieldName, Integer asc) {
        /**
         * findAllByLikeNameByPage
         * @Desciption 通过名称模糊查询
         * @param [name, page, size, sortFieldName, asc]
         */

        try {
            Operation.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            throw new MesException(EnumException.SORT_FIELD);
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        // 分页
        Pageable pageable = new PageRequest(page, size, sort);

        // 只匹配name,其它属性全都忽略
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("code");
        Operation operation = new Operation();
        operation.setName(name);

        // 创建实例
        Example<Operation> example = Example.of(operation, matcher);

        // 查询
        return operationRepository.findAll(example, pageable);
    }

    public void deleteInBatch(Set<Operation> operationes) {
        operationRepository.deleteInBatch(operationes);
    }
}
