package com.hnu.mes.service;

import com.hnu.mes.domain.Material;
import com.hnu.mes.domain.MaterialsEntry;
import com.hnu.mes.domain.PickingApply;
import com.hnu.mes.repository.MaterialsEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 20:09 2018/5/9
 * @Modified By:
 */
@Service
public class MaterialsEntryService {
    @Autowired
    private MaterialsEntryRepository materialsEntryRepository;

    /**
     * 保存
     *
     * @param materialsEntry
     * @return
     */
    public MaterialsEntry save(MaterialsEntry materialsEntry){
        return materialsEntryRepository.save(materialsEntry);
    }

    /**
     * 批量保存
     * @param materialsEntries
     * @return
     */
    public List<MaterialsEntry> save(Collection<MaterialsEntry> materialsEntries){
        return materialsEntryRepository.save(materialsEntries);
    }

    /**
     * 通过批号更新重量
     *
     * @param pickingApplies
     */
    @Transactional
    public void updateWeightByBatchNumber(Set<PickingApply> pickingApplies) {
        for(PickingApply pickingApply:pickingApplies){
            materialsEntryRepository.updateWeightByBatchNumber(pickingApply.getWeight(), pickingApply.getBatchNumber());
        }
    }
}
