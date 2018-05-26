package com.hnu.mes.repository;

import com.hnu.mes.domain.ModelOperation;
import com.hnu.mes.domain.ModelOperationPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 14:24 2018/5/1
 * @Modified By:
 */
@Repository
public interface ModelOperationRepository extends JpaRepository<ModelOperation, ModelOperationPrimaryKey> {
    /**
     * 通过模块编码查询
     *
     * @param modelCode
     * @return
     */
    public List<ModelOperation> findModelOperationsByModelCode(Integer modelCode);

    /**
     * 通过操作编码查询
     *
     * @param operationCode
     * @return
     */
    public List<ModelOperation> findModelOperationsByOperationCode(Integer operationCode);

    /**
     * 通过模块编码更新
     *
     * @param modelCode
     */
    public void deleteModelOperationsByModelCode(Integer modelCode);
}
