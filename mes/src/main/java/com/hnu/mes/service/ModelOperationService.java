package com.hnu.mes.service;

import com.hnu.mes.domain.ModelOperation;
import com.hnu.mes.domain.ModelOperationPrimaryKey;
import com.hnu.mes.repository.ModelOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:25 2018/5/1
 * @Modified By:
 */
@Service
public class ModelOperationService {
    @Autowired
    private ModelOperationRepository modelOperationRepository;

    /**
     * 添加一个
     * @param modelOperation
     * @return
     */
    public ModelOperation save(ModelOperation modelOperation){
        return modelOperationRepository.save(modelOperation);
    }

    /**
     * 批量添加
     * @param modelOperations
     * @return
     */
    public Collection<ModelOperation> saveBatch(Collection<ModelOperation> modelOperations){
        return modelOperationRepository.save(modelOperations);
    }

    /**
     * 批量更新
     *
     * @param modelOperations
     * @return
     */
    @Transactional
    public void updateBatch(Collection<ModelOperation> modelOperations) {

        Set<Integer> modelCodes = new HashSet<>();
        for(ModelOperation modelOperation : modelOperations){
            if(modelOperation.getModelCode() != null) {
                modelCodes.add(modelOperation.getModelCode());
            }
        }

        if(modelCodes.size() > 0) {
            for(Integer modelCode : modelCodes) {
                modelOperationRepository.deleteModelOperationsByModelCode(modelCode);
            }
        }

        modelOperationRepository.save(modelOperations);
    }

    /**
     * 删除
     *
     * @param modelOperationPrimaryKey
     */
    public void delete(ModelOperationPrimaryKey modelOperationPrimaryKey){
        modelOperationRepository.delete(modelOperationPrimaryKey);
    }

    public void deleteBatch(Collection<ModelOperationPrimaryKey> modelOperationPrimaryKeys){
        List<ModelOperation> modelOperations = new ArrayList<ModelOperation>();

        for(ModelOperationPrimaryKey modelOperationPrimaryKey : modelOperationPrimaryKeys){
            if(modelOperationPrimaryKey.getModelCode() != null && modelOperationPrimaryKey.getOperationCode() != null){
                modelOperations.add(new ModelOperation(modelOperationPrimaryKey.getModelCode(), modelOperationPrimaryKey.getOperationCode()));
            }
        }

        modelOperationRepository.deleteInBatch(modelOperations);
    }

    /**
     * 查询一个
     * @param modelOperationPrimaryKey
     * @return
     */
    public ModelOperation findOne(ModelOperationPrimaryKey modelOperationPrimaryKey){
        return modelOperationRepository.findOne(modelOperationPrimaryKey);
    }

    /**
     * 查询所有
     * @return
     */
    public List<ModelOperation> findAll(){
        return modelOperationRepository.findAll();
    }

    /**
     * 通过模块编码查询
     *
     * @param modelCode
     * @return
     */
    public List<ModelOperation> findModelOperationsByModeCode(Integer modelCode){
        return modelOperationRepository.findModelOperationsByModelCode(modelCode);
    }

    /**
     * 通过操作编码查询
     *
     * @param operationCode
     * @return
     */
    public List<ModelOperation> findModelOperationsByOperationCode(Integer operationCode){
        return modelOperationRepository.findModelOperationsByOperationCode(operationCode);
    }
}
